package com.enovka.gemini4j.http.spec;

import com.enovka.gemini4j.http.exception.GeminiHttpException;
import com.enovka.gemini4j.http.validator.HttpRequestValidator;

import java.util.Map;

/**
 * Abstract class that provides common methods for HTTP client implementations,
 * abstracting common functionalities like URL and header validation.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 */
public abstract class AbstractHttpClientService implements HttpClientService {

    private static final String ERROR_MESSAGE_UNEXPECTED_HTTP_ERROR = "An unexpected HTTP error occurred.";
    private final HttpRequestValidator validator;

    /**
     * Constructs a new AbstractHttpClientService with the specified validator.
     *
     * @param validator The validator to use for request validation.
     */
    public AbstractHttpClientService(HttpRequestValidator validator) {
        this.validator = validator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponseWrapper get(String url, Map<String, String> headers) throws GeminiHttpException {
        validator.validateUrl(url);
        validator.validateHeaders(headers);
        try {
            return doGET(url, headers);
        } catch (Exception e) {
            throw new GeminiHttpException(ERROR_MESSAGE_UNEXPECTED_HTTP_ERROR, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponseWrapper post(String url, String body, Map<String, String> headers) throws GeminiHttpException {
        validator.validateUrl(url);
        validator.validateHeaders(headers);
        validator.validateRequestBody(body);
        try {
            return doPOST(url, body, headers);
        } catch (Exception e) {
            throw new GeminiHttpException(ERROR_MESSAGE_UNEXPECTED_HTTP_ERROR, e);
        }
    }

    /**
     * Executes a GET request.
     *
     * @param url     The URL to send the request to.
     * @param headers The headers to include in the request.
     * @return The HTTP response.
     * @throws Exception If an error occurs during the request.
     */
    protected abstract HttpResponseWrapper doGET(String url, Map<String, String> headers) throws Exception;

    /**
     * Executes a POST request.
     *
     * @param url     The URL to send the request to.
     * @param body    The request body.
     * @param headers The headers to include in the request.
     * @return The HTTP response.
     * @throws Exception If an error occurs during the request.
     */
    protected abstract HttpResponseWrapper doPOST(String url, String body, Map<String, String> headers) throws Exception;
}