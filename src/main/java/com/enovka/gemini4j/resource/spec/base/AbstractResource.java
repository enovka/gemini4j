package com.enovka.gemini4j.resource.spec.base;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import com.enovka.gemini4j.infrastructure.tool.MultiTurnConversation;
import com.enovka.gemini4j.model.request.spec.Request;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;

/**
 * Abstract base class for resource implementations, providing shared functionality for
 * handling API requests and responses.
 *
 * @param <T> The type of resource.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public abstract class AbstractResource<T> extends BaseClass implements Resource {

    protected final GeminiClient geminiClient;
    protected final HttpClient httpClient;
    protected final JsonService jsonService;
    protected final MultiTurnConversation multiTurnConversation = new MultiTurnConversation(); // Restored


    /**
     * Constructs a new {@code AbstractResource} with the provided {@link GeminiClient}.
     *
     * @param geminiClient The Gemini client for API communication.
     * @since 0.2.0
     */
    protected AbstractResource(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
        this.httpClient = geminiClient.getHttpClient();
        this.jsonService = geminiClient.getJsonService();
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public GeminiClient getGeminiClient() {
        return geminiClient;
    }

    /**
     * Executes a POST request to the specified endpoint.
     *
     * @param endpoint      The API endpoint for the POST request.
     * @param requestObject The request object to serialize and send.
     * @param responseType  The expected response type.
     * @param <R>           The type of the response object.
     * @param <S>           The type of the request object.
     * @return The deserialized response object.
     * @throws ResourceException If an error occurs.
     * @since 0.2.0
     */
    protected <S extends Request, R> R executePostRequest(String endpoint, S requestObject, Class<R> responseType) throws ResourceException {
        return executeRequest("POST", endpoint, requestObject, responseType);
    }

    /**
     * Executes a request to the Gemini API.  Handles serialization, deserialization, and error handling.
     *
     * @param method        The HTTP method ("GET", "POST", "PATCH", "DELETE").
     * @param endpoint     The API endpoint.
     * @param requestObject The request object (can be null for GET and DELETE).
     * @param responseType  The expected response type.
     * @param <R>           The type of the response object.
     * @return The deserialized response object, or null if responseType is Void.class.
     * @throws ResourceException If an error occurs during the API request or response processing.
     * @since 0.2.0
     */
    protected <R> R executeRequest(String method, String endpoint, Object requestObject, Class<R> responseType) throws ResourceException {
        try {
            String uri = buildEndpointUrl(endpoint);
            HttpResponse response = null;

            switch (method) {
                case "GET":
                    response = httpClient.get(uri, geminiClient.buildAuthHeaders());
                    break;
                case "POST":
                    response = httpClient.post(uri, jsonService.serialize(requestObject), geminiClient.buildAuthHeaders());
                    break;
                case "PATCH":
                    response = httpClient.patch(uri, jsonService.serialize(requestObject), geminiClient.buildAuthHeaders());
                    break;
                case "DELETE":
                    response = httpClient.delete(uri, geminiClient.buildAuthHeaders());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            }

            if (responseType == Void.class) {
                return null;
            }

            return deserializeResponse(response, responseType);
        } catch (JsonException | HttpException e) {
            throw new ResourceException("Error during API request: " + e.getMessage(), e);
        }
    }

    /**
     * Deserializes a JSON response into an object of the specified type.
     *
     * @param response     The HTTP response containing the JSON data.
     * @param responseType The class of the object to deserialize to.
     * @param <R>          The type of the object.
     * @return The deserialized object.
     * @throws ResourceException If an error occurs during deserialization or if the response contains an error.
     * @since 0.2.0
     */
    protected <R> R deserializeResponse(HttpResponse response, Class<R> responseType) throws ResourceException {

        try {
            String responseBody = response.getBody();
            if (responseBody == null || responseBody.isEmpty() || "{}".equals(responseBody.trim())) {
                return null;
            }
            R deserializedResponse = jsonService.deserialize(responseBody, responseType);
            if (deserializedResponse instanceof AbstractResponse) {
                AbstractResponse geminiResponse = (AbstractResponse) deserializedResponse;
                if (geminiResponse.getError() != null) {
                    throw new ResourceException("Gemini API returned an error: " + geminiResponse.getError().getMessage());
                }
            }
            return deserializedResponse;
        } catch (JsonException e) {
            throw new ResourceException("Error deserializing response: " + e.getMessage(), e);
        }
    }

    /**
     * Builds the URL for an API endpoint without the API key.
     *
     * @param endpoint The API endpoint path.
     * @return The URL without the API key.
     * @since 0.2.0
     */
    protected String buildEndpointUrl(String endpoint) {
        StringBuilder stringBuilder = new StringBuilder(endpoint);
        addQueryParam(stringBuilder, "key", geminiClient.getApiKey());
        return geminiClient.getBaseUrl() + stringBuilder;
    }

    /**
     * Adds a query parameter to a URL.
     *
     * @param url        The URL builder.
     * @param paramName  The name of the parameter.
     * @param paramValue The value of the parameter.
     * @since 0.2.0
     */
    protected void addQueryParam(StringBuilder url, String paramName, Object paramValue) {
        if (paramValue != null) {
            if (url.indexOf("?") == -1) {
                url.append("?");
            } else {
                url.append("&");
            }
            url.append(paramName).append("=").append(paramValue);
        }
    }
}