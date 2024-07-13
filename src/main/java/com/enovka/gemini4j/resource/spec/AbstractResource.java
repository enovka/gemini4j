package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.common.BaseClass;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.spec.HttpResponse;

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

    /**
     * Constructs a new AbstractResource with the required GeminiClient.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     */
    protected AbstractResource(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    /**
     * Makes a GET request to the specified endpoint.
     *
     * @param endpoint The API endpoint to request.
     * @param headers The headers to include in the request.
     * @return The {@link HttpResponse} object.
     * @throws HttpException If an error occurs during the request.
     */
    protected HttpResponse get(String endpoint, Map<String, String> headers)
            throws HttpException {
        return geminiClient.getHttpClient()
                .get(geminiClient.getBaseUrl() + endpoint + "?key="
                        + geminiClient.getApiKey(), headers);
    }

    /**
     * Makes a POST request to the specified endpoint.
     *
     * @param endpoint The API endpoint to request.
     * @param body The request body.
     * @param headers The headers to include in the request.
     * @return The {@link HttpResponse} object.
     * @throws HttpException If an error occurs during the request.
     */
    protected HttpResponse post(String endpoint, String body,
                                Map<String, String> headers)
            throws HttpException {
        HttpResponse response;
        try {
            response = geminiClient.getHttpClient()
                    .post(geminiClient.getBaseUrl() + endpoint + "?key="
                            + geminiClient.getApiKey(), body, headers);
        } catch (Exception e) {
            throw new HttpException("Error executing POST request: "
                    + e.getMessage(), e);
        }
        return response;
    }
}