package com.enovka.gemini4j.http;

import com.enovka.gemini4j.common.BaseClass;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.spec.HttpClientService;
import com.enovka.gemini4j.http.spec.HttpResponseWrapper;
import com.enovka.gemini4j.http.validator.HttpRequestValidator;

import java.util.Map;

/**
 * Custom HttpClientService implementation. This class is used to wrap an
 * existing HttpClientService implementation with a custom HttpResponseWrapper.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 1.0.0-beta
 */
public class CustomHttpClientService extends BaseClass
        implements HttpClientService {

    private final HttpClientService httpClientService;
    private final HttpResponseWrapper httpResponseWrapper;
    private final HttpRequestValidator validator;

    /**
     * Constructs a new CustomHttpClientService with the specified
     * HttpClientService, HttpResponseWrapper, and HttpRequestValidator.
     *
     * @param httpClientService The HttpClientService to wrap.
     * @param httpResponseWrapper The HttpResponseWrapper to use for response
     * handling.
     * @param validator The HttpRequestValidator to use for request validation.
     */
    public CustomHttpClientService(HttpClientService httpClientService,
                                   HttpResponseWrapper httpResponseWrapper,
                                   HttpRequestValidator validator) {
        this.httpClientService = httpClientService;
        this.httpResponseWrapper = httpResponseWrapper;
        this.validator = validator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponseWrapper get(String url, Map<String, String> headers)
            throws
            HttpException {
        logDebug("Executing GET request to: " + url);
        logDebug("Headers: " + headers);
        validator.validateUrl(url);
        return httpResponseWrapper.wrap(httpClientService.get(url, headers));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponseWrapper post(String url, String body,
                                    Map<String, String> headers) throws
            HttpException {
        logDebug("Executing POST request to: " + url);
        logDebug("Headers: " + headers);
        logDebug("Body: " + body);
        validator.validateUrl(url);
        validator.validateRequestBody(body);
        return httpResponseWrapper.wrap(
                httpClientService.post(url, body, headers));
    }
}