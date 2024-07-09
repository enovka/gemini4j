package com.enovka.gemini4j.http.validator;

import com.enovka.gemini4j.http.exception.HttpException;

/**
 * Validator for HTTP requests.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 1.0.0-beta
 */
public class HttpRequestValidator {

    private static final String ERROR_MESSAGE_INVALID_URL
            = "Invalid URL provided.";
    private static final String ERROR_MESSAGE_INVALID_REQUEST_BODY
            = "Invalid request body provided.";

    /**
     * Validates the provided URL.
     *
     * @param url The URL to validate.
     * @throws HttpException If the URL is invalid.
     */
    public void validateUrl(String url) throws HttpException {
        if (url == null || url.isEmpty()) {
            throw new HttpException(ERROR_MESSAGE_INVALID_URL);
        }
    }

    /**
     * Validates the request body.
     *
     * @param body The request body to validate.
     * @throws HttpException If the request body is invalid.
     */
    public void validateRequestBody(String body) throws HttpException {
        if (body == null || body.isEmpty()) {
            throw new HttpException(ERROR_MESSAGE_INVALID_REQUEST_BODY);
        }
    }
}