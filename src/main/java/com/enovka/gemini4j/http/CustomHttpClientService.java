package com.enovka.gemini4j.http;

import com.enovka.gemini4j.http.exception.GeminiHttpException;
import com.enovka.gemini4j.http.spec.HttpClientService;
import com.enovka.gemini4j.http.spec.HttpResponseWrapper;
import com.enovka.gemini4j.http.validator.HttpRequestValidator;

import java.util.Map;

/**
 * Custom HttpClientService implementation.
 * This class is used to wrap an existing HttpClientService implementation with a custom HttpResponseWrapper.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 */
public class CustomHttpClientService implements HttpClientService {

    private final HttpClientService httpClientService;
    private final HttpResponseWrapper httpResponseWrapper;
    private final HttpRequestValidator validator;

    /**
     * Constructs a new CustomHttpClientService with the specified HttpClientService, HttpResponseWrapper, and HttpRequestValidator.
     *
     * @param httpClientService     The HttpClientService to wrap.
     * @param httpResponseWrapper The HttpResponseWrapper to use for response handling.
     * @param validator           The HttpRequestValidator to use for request validation.
     */
    public CustomHttpClientService(HttpClientService httpClientService, HttpResponseWrapper httpResponseWrapper, HttpRequestValidator validator) {
        this.httpClientService = httpClientService;
        this.httpResponseWrapper = httpResponseWrapper;
        this.validator = validator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponseWrapper get(String url, Map<String, String> headers) throws GeminiHttpException {
        validator.validateUrl(url);
        validator.validateHeaders(headers);
        return httpResponseWrapper.wrap(httpClientService.get(url, headers));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponseWrapper post(String url, String body, Map<String, String> headers) throws GeminiHttpException {
        validator.validateUrl(url);
        validator.validateHeaders(headers);
        validator.validateRequestBody(body);
        return httpResponseWrapper.wrap(httpClientService.post(url, body, headers));
    }
}