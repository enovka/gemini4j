package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.common.BaseClass;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.spec.HttpResponse;
import com.enovka.gemini4j.json.spec.JsonService;

import java.util.Map;

/**
 * Abstract base class for resource implementations, providing shared
 * functionality.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public abstract class AbstractResource extends BaseClass {

    protected static final String DEFAULT_BASE_URL
            = "https://generativelanguage.googleapis.com/v1beta";

    protected final GeminiClient geminiClient;
    protected final String baseUrl;
    protected final JsonService jsonService;

    /**
     * Constructs a new AbstractResource with the required GeminiClient and
     * JsonService.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     * @param jsonService The {@link JsonService} instance to use for JSON
     * serialization/deserialization.
     */
    protected AbstractResource(GeminiClient geminiClient,
                               JsonService jsonService) {
        this.geminiClient = geminiClient;
        this.jsonService = jsonService;
        this.baseUrl
                = DEFAULT_BASE_URL; // You can make this configurable if needed
    }

    /**
     * Makes a GET request to the specified endpoint.
     *
     * @param endpoint The API endpoint to request.
     * @param headers The headers to include in the request.
     * @return The {@link HttpResponse} object.
     * @throws GeminiApiException If an error occurs during the request.
     */
    protected HttpResponse get(String endpoint, Map<String, String> headers)
            throws GeminiApiException {
        try {
            return geminiClient.getHttpClient().get(baseUrl + endpoint + "?key="
                    + geminiClient.getApiKey(), headers);
        } catch (HttpException e) {
            logError("Error making GET request: " + e.getMessage(), e);
            throw new GeminiApiException(e.getStatusCode(), e.getMessage(), e);
        }
    }

    /**
     * Makes a POST request to the specified endpoint.
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
            return geminiClient.getHttpClient()
                    .post(baseUrl + endpoint + "?key="
                            + geminiClient.getApiKey(), body, headers);
        } catch (HttpException e) {
            logError("Error making POST request: " + e.getMessage(), e);
            throw new GeminiApiException(e.getStatusCode(), e.getMessage(), e);
        }
    }
}