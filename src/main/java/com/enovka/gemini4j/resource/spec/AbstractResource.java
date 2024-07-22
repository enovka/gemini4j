package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.GeminiApiError;
import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.response.AbstractGeminiResponse;
import com.enovka.gemini4j.infrastructure.exception.GeminiInfrastructureException;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import com.enovka.gemini4j.infrastructure.tool.ModelTool;
import com.enovka.gemini4j.infrastructure.tool.MultiTurnConversation;
import com.enovka.gemini4j.resource.exception.ResourceException;

import java.util.Map;

/**
 * Abstract base class for resource implementations, providing shared
 * functionality.
 *
 * @param <T> The type of response object this resource handles.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public abstract class AbstractResource<T> extends BaseClass {

    protected final GeminiClient geminiClient;
    protected final JsonService jsonService;
    protected final ModelTool modelTool = ModelTool.getInstance();
    protected final MultiTurnConversation multiTurnConversation;

    /**
     * Constructs a new AbstractResource with the required GeminiClient and
     * JsonService.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     * @param jsonService The {@link JsonService} instance to use for JSON
     * serialization/deserialization.
     */
    public AbstractResource(GeminiClient geminiClient,
                            JsonService jsonService) {
        this.geminiClient = geminiClient;
        this.jsonService = jsonService;
        this.multiTurnConversation = new MultiTurnConversation();
    }

    /**
     * Executes a GET request to the specified endpoint and returns the
     * deserialized response.
     *
     * @param endpoint The API endpoint to request.
     * @param type The class of the response object.
     * @return The deserialized response object.
     * @throws ResourceException If an error occurs during the request.
     * @since 0.1.0
     */
    protected <R extends AbstractGeminiResponse> R executeGetRequest(
            String endpoint, Class<R> type)
            throws ResourceException {
        try {
            logDebug("Executing GET request to endpoint: " + endpoint);
            HttpResponse response = get(endpoint,
                    geminiClient.buildAuthHeaders());
            return deserializeResponse(response, type);
        } catch (JsonException e) {
            throw new ResourceException("Error deserializing response", e);
        } catch (GeminiInfrastructureException e) {
            throw new ResourceException(
                    "Infrastructure error executing GET request", e);
        } catch (GeminiApiException e) {
            throw new ResourceException("API error executing GET request", e);
        }
    }

    /**
     * Executes a POST request to the specified endpoint with the given request
     * object and returns the deserialized response.
     *
     * @param endpoint The API endpoint to request.
     * @param requestObject The request object to serialize and send in the
     * request body.
     * @param type The class of the response object.
     * @return The deserialized response object.
     * @throws ResourceException If an error occurs during the request.
     * @since 0.1.0
     */
    protected <R extends AbstractGeminiResponse, S> R executePostRequest(
            String endpoint, S requestObject,
            Class<R> type)
            throws ResourceException {
        try {
            logDebug("Executing POST request to endpoint: " + endpoint);
            String requestBody = serializeRequest(requestObject);
            logDebug("Request Body: " + requestBody);
            HttpResponse response = post(endpoint, requestBody,
                    geminiClient.buildAuthHeaders());
            logDebug("Response Body: " + response.getBody());
            return deserializeResponse(response, type);
        } catch (JsonException e) {
            throw new ResourceException("Error deserializing response", e);
        } catch (GeminiInfrastructureException e) {
            throw new ResourceException(
                    "Infrastructure error executing POST request", e);
        } catch (GeminiApiException e) {
            throw new ResourceException("API error executing POST request", e);
        }
    }

    /**
     * Builds the complete endpoint URL with the base URL and API key.
     *
     * @param endpoint The API endpoint path.
     * @return The complete endpoint URL.
     */
    private String buildEndpointUrl(String endpoint) {
        return geminiClient.getBaseUrl() + endpoint + "?key="
                + geminiClient.getApiKey();
    }

    /**
     * Serializes the given request object to a JSON string.
     *
     * @param requestObject The request object to serialize.
     * @return The JSON string representation of the request object.
     * @throws JsonException If an error occurs during serialization.
     */
    private <S> String serializeRequest(S requestObject)
            throws JsonException {
        try {
            return jsonService.serialize(requestObject);
        } catch (JsonException e) {
            throw new JsonException(
                    "Error serializing request object: " + e.getMessage(), e);
        }
    }

    /**
     * Makes a GET request to the specified endpoint and handles potential
     * errors.
     *
     * @param endpoint The API endpoint to request.
     * @param headers The headers to include in the request.
     * @return The {@link HttpResponse} object.
     * @throws GeminiInfrastructureException If an error occurs during the HTTP
     * request.
     */
    protected HttpResponse get(String endpoint, Map<String, String> headers)
            throws GeminiInfrastructureException {
        try {
            return geminiClient.getHttpClient()
                    .get(buildEndpointUrl(endpoint), headers);
        } catch (HttpException e) {
            throw new GeminiInfrastructureException(
                    "Error executing GET request: " + e.getMessage(), e);
        }
    }

    /**
     * Makes a POST request to the specified endpoint and handles potential
     * errors.
     *
     * @param endpoint The API endpoint to request.
     * @param body The request body.
     * @param headers The headers to include in the request.
     * @return The {@link HttpResponse} object.
     * @throws GeminiInfrastructureException If an error occurs during the HTTP
     * request.
     */
    protected HttpResponse post(String endpoint, String body,
                                Map<String, String> headers)
            throws GeminiInfrastructureException {
        try {
            return geminiClient.getHttpClient()
                    .post(buildEndpointUrl(endpoint), body, headers);
        } catch (HttpException e) {
            throw new GeminiInfrastructureException(
                    "Error executing POST request: " + e.getMessage(), e);
        }
    }

    /**
     * Deserializes the given HTTP response body to the specified response
     * object type.
     *
     * @param response The HTTP response.
     * @param type The class of the response object.
     * @return The deserialized response object.
     * @throws GeminiApiException If the API returns an error.
     * @throws JsonException If an error occurs during deserialization.
     * @since 0.1.0
     */
    private <R extends AbstractGeminiResponse> R deserializeResponse(
            HttpResponse response, Class<R> type)
            throws GeminiApiException, JsonException {
        R deserializedResponse = jsonService.deserialize(response.getBody(),
                type);
        if (deserializedResponse.getError() != null) {
            throw new GeminiApiException(GeminiApiError.UNKNOWN,
                    "Gemini API returned an error: "
                            + deserializedResponse.getError().getMessage());
        }
        return deserializedResponse;
    }

    protected ModelTool getModelTool() {
        return modelTool;
    }
}