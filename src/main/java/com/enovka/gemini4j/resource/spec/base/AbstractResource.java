package com.enovka.gemini4j.resource.spec.base;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import com.enovka.gemini4j.infrastructure.tool.MultiTurnConversation;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;
import org.apache.hc.core5.http.ContentType;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for resource implementations, providing shared functionality for
 * handling API requests and responses, including asynchronous operations and multi-turn
 * conversations. This class encapsulates common logic for interacting with the Gemini API,
 * such as request execution, response deserialization, and error handling.  It integrates
 * with the asynchronous {@link HttpClient} and provides support for managing conversation
 * history through {@link MultiTurnConversation}.
 *
 * @param <T> The type of resource.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public abstract class AbstractResource<T> extends BaseClass implements Resource {

    protected final GeminiClient geminiClient;
    protected final HttpClient httpClient;
    protected final JsonService jsonService;
    protected final MultiTurnConversation multiTurnConversation = new MultiTurnConversation();

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
     *
     * @since 0.2.0
     */
    @Override
    public GeminiClient getGeminiClient() {
        return geminiClient;
    }


    /**
     * Executes a request to the Gemini API. Handles serialization, deserialization, and
     * error handling. This method supports different HTTP methods and uses the synchronous
     * methods of the {@link HttpClient}.
     *
     * @param method        The HTTP method ("GET", "POST", "PATCH", "DELETE").
     * @param endpoint      The API endpoint.
     * @param requestObject The request object (can be null for GET and DELETE).
     * @param responseType  The expected response type.
     * @param <R>           The type of the response object.
     * @return The deserialized response object, or null if responseType is Void.class.
     * @throws ResourceException If an error occurs during the API request or response
     *                          processing.
     * @since 0.2.0
     */
    protected <R> R executeRequest(String method, String endpoint, Object requestObject, Class<R> responseType) throws ResourceException {
        try {
            String uri = buildEndpointUrl(endpoint);
            HttpResponse response;
            Map<String, String> headers = new HashMap<>(geminiClient.buildAuthHeaders());

            switch (method) {
                case "GET":
                    headers.put("Accept", ContentType.APPLICATION_JSON.getMimeType());
                    response = httpClient.get(uri, headers);
                    break;
                case "POST":
                    headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
                    response = httpClient.post(uri, jsonService.serialize(requestObject), headers, ContentType.APPLICATION_JSON);
                    break;
                case "PATCH":
                    headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
                    response = httpClient.patch(uri, jsonService.serialize(requestObject), headers, ContentType.APPLICATION_JSON);
                    break;
                case "DELETE":
                    headers.put("Accept", ContentType.APPLICATION_JSON.getMimeType());
                    response = httpClient.delete(uri, headers);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            }

            if (responseType == Void.class) {
                return null;
            }
            return deserializeResponse(response, responseType);

        } catch (Throwable e) {
            throw new ResourceException("Error during API request: " + e.getMessage(), e);
        }
    }

    /**
     * Deserializes a JSON response into an object of the specified type. This method handles
     * potential errors during deserialization and checks for errors returned by the Gemini API.
     * It also handles empty or null responses gracefully.
     *
     * @param response     The HTTP response containing the JSON data.
     * @param responseType The class of the object to deserialize to.
     * @param <R>          The type of the object.
     * @return The deserialized object, or null if the response body is null, empty, or "{}".
     * @throws ResourceException If an error occurs during deserialization or if the Gemini API
     *                          returns an error.
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
     * Builds the URL for an API endpoint, including the API key as a query parameter.  This
     * method constructs the full URL by appending the endpoint to the base URL and adding the
     * API key. It uses a {@link StringBuilder} for efficient string concatenation.
     *
     * @param endpoint The API endpoint path.
     * @return The complete URL with the API key.
     * @since 0.2.0
     */
    protected String buildEndpointUrl(String endpoint) {
        StringBuilder urlBuilder = new StringBuilder(geminiClient.getBaseUrl());
        urlBuilder.append(endpoint);
        addQueryParam(urlBuilder, "key", geminiClient.getApiKey());
        return urlBuilder.toString();
    }

    /**
     * Adds a query parameter to the provided URL. This method handles the addition of query
     * parameters, correctly appending "?" for the first parameter and "amp;" for subsequent
     * parameters.  It ensures that null parameters are not added to the URL.
     *
     * @param url        The {@link StringBuilder} representing the URL to which the parameter
     *                   should be added.
     * @param paramName  The name of the query parameter.
     * @param paramValue The value of the query parameter.
     * @since 0.2.0
     */
    protected void addQueryParam(StringBuilder url, String paramName, Object paramValue) {
        if (paramValue != null) {
            url.append(url.indexOf("?") == -1 ? "?" : "&");
            url.append(paramName).append("=").append(paramValue);
        }
    }


}