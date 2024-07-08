package com.enovka.gemini4j.http.spec;

import com.enovka.gemini4j.http.exception.GeminiHttpException;

import java.util.Map;

/**
 * Validator for HTTP requests.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 */
public class HttpRequestValidator {

    private static final String ERROR_MESSAGE_INVALID_URL = "Invalid URL provided.";
    private static final String ERROR_MESSAGE_INVALID_HEADERS = "Invalid headers provided.";
    private static final String ERROR_MESSAGE_INVALID_REQUEST_BODY = "Invalid request body provided.";

    /**
     * Validates the provided URL.
     *
     * @param url The URL to validate.
     * @throws GeminiHttpException If the URL is invalid.
     */
    public void validateUrl(String url) throws GeminiHttpException {
        if (url == null || url.isEmpty()) {
            throw new GeminiHttpException(ERROR_MESSAGE_INVALID_URL);
        }
    }

    /**
     * Validates the provided headers.
     *
     * @param headers The headers to validate.
     * @throws GeminiHttpException If the headers are invalid.
     */
    public void validateHeaders(Map<String, String> headers) throws GeminiHttpException {
        if (headers == null) {
            throw new GeminiHttpException(ERROR_MESSAGE_INVALID_HEADERS);
        }
    }

    /**
     * Validates the request body.
     *
     * @param body The request body to validate.
     * @throws GeminiHttpException If the request body is invalid.
     */
    public void validateRequestBody(String body) throws GeminiHttpException {
        if (body == null || body.isEmpty()) {
            throw new GeminiHttpException(ERROR_MESSAGE_INVALID_REQUEST_BODY);
        }
    }
}