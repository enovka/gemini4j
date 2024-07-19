package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.*;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
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
    protected final ModelTool modelTool = ModelTool.getInstance();

    /**
     * Constructs a new AbstractResource with the required GeminiClient.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     */
    public AbstractResource(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
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
                    .get(geminiClient.getBaseUrl() + endpoint + "?key="
                            + geminiClient.getApiKey(), headers);
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
                    .post(geminiClient.getBaseUrl() + endpoint + "?key="
                            + geminiClient.getApiKey(), body, headers);
            handleHttpResponseError(response);
            return response;
        } catch (HttpException e) {
            throw handleHttpException(e);
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