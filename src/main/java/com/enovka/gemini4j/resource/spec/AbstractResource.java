package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.*;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import com.enovka.gemini4j.infrastructure.tool.ModelTool;

import java.util.Map;

/**
 * Abstract base class for resource implementations, providing shared
 * functionality.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public abstract class AbstractResource extends BaseClass {

    protected final GeminiClient geminiClient;
    protected final JsonService jsonService;
    protected final ModelTool modelTool = ModelTool.getInstance();

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
    }

    /**
     * Executes a GET request to the specified endpoint and returns the
     * deserialized response.
     *
     * @param endpoint The API endpoint to request.
     * @param type The class of the response object.
     * @return The deserialized response object.
     * @throws GeminiApiException If an error occurs during the request.
     */
    protected <R> R executeGetRequest(String endpoint, Class<R> type)
            throws GeminiApiException {
        logDebug("Executing GET request to endpoint: " + endpoint);
        HttpResponse response = get(endpoint, geminiClient.buildAuthHeaders());
        return deserializeResponse(response, type);
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
     * @throws GeminiApiException If an error occurs during the request.
     */
    protected <R, S> R executePostRequest(String endpoint, S requestObject,
                                          Class<R> type)
            throws GeminiApiException {
        logDebug("Executing POST request to endpoint: " + endpoint);
        String requestBody = serializeRequest(requestObject);
        logDebug("Request Body: " + requestBody);
        HttpResponse response = post(endpoint, requestBody,
                geminiClient.buildAuthHeaders());
        logDebug("Response Body: " + response.getBody());
        return deserializeResponse(response, type);
    }

    /**
     * Makes a GET request to the specified endpoint and handles potential
     * errors.
     *
     * @param endpoint The API endpoint to request.
     * @param headers The headers to include in the request.
     * @return The {@link HttpResponse} object.
     * @throws GeminiApiException If an error occurs during the request.
     */
    protected HttpResponse get(String endpoint, Map<String, String> headers)
            throws GeminiApiException {
        try {
            HttpResponse response = geminiClient.getHttpClient()
                    .get(buildEndpointUrl(endpoint), headers);
            handleHttpResponseError(response);
            return response;
        } catch (HttpException e) {
            throw handleHttpException(e);
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
     * @throws GeminiApiException If an error occurs during the request.
     */
    protected HttpResponse post(String endpoint, String body,
                                Map<String, String> headers)
            throws GeminiApiException {
        try {
            HttpResponse response = geminiClient.getHttpClient()
                    .post(buildEndpointUrl(endpoint), body, headers);
            handleHttpResponseError(response);
            return response;
        } catch (HttpException e) {
            throw handleHttpException(e);
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
     * @throws GeminiApiException If an error occurs during serialization.
     */
    private <S> String serializeRequest(S requestObject)
            throws GeminiApiException {
        try {
            return jsonService.serialize(requestObject);
        } catch (JsonException e) {
            throw new GeminiApiException(500,
                    "Error serializing request object: " + e.getMessage(), e);
        }
    }

    /**
     * Deserializes the given HTTP response body to the specified response
     * object type.
     *
     * @param response The HTTP response.
     * @param type The class of the response object.
     * @return The deserialized response object.
     * @throws GeminiApiException If an error occurs during deserialization.
     */
    private <R> R deserializeResponse(HttpResponse response, Class<R> type)
            throws GeminiApiException {
        try {
            return jsonService.deserialize(response.getBody(), type);
        } catch (JsonException e) {
            throw new GeminiApiException(500,
                    "Error deserializing response: " + e.getMessage(), e);
        }
    }

    /**
     * Handles potential errors in the HTTP response.
     *
     * @param response The HTTP response to check for errors.
     * @throws GeminiApiException If the response indicates an error.
     */
    private void handleHttpResponseError(HttpResponse response)
            throws GeminiApiException {
        int statusCode = response.getStatusCode();
        if (statusCode >= 400) {
            throw handleHttpException(new HttpException(
                    "Request failed with status code: " + statusCode, null,
                    statusCode));
        }
    }

    /**
     * Maps a {@link HttpException} to a specific {@link GeminiApiException}
     * based on the status code.
     *
     * @param e The {@link HttpException} to handle.
     * @return A specific {@link GeminiApiException} corresponding to the HTTP
     * status code.
     */
    private GeminiApiException handleHttpException(HttpException e) {
        switch (e.getStatusCode()) {
        case 400:
            return new GeminiInvalidArgumentException(e.getStatusCode(),
                    e.getMessage());
        case 403:
            return new GeminiPermissionDeniedException(e.getStatusCode(),
                    e.getMessage());
        case 404:
            return new GeminiNotFoundException(e.getStatusCode(),
                    e.getMessage());
        case 429:
            return new GeminiResourceExhaustedException(e.getStatusCode(),
                    e.getMessage());
        case 500:
            return new GeminiInternalException(e.getStatusCode(),
                    e.getMessage());
        case 503:
            return new GeminiUnavailableException(e.getStatusCode(),
                    e.getMessage());
        default:
            return new GeminiApiException(e.getStatusCode(), e.getMessage(), e);
        }
    }

    protected ModelTool getModelTool() {
        return modelTool;
    }
}