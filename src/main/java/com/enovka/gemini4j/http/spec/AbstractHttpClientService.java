package com.enovka.gemini4j.http.spec;

import com.enovka.gemini4j.common.BaseClass;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.validator.HttpRequestValidator;

import java.util.Map;

/**
 * Abstract class that provides common methods for HTTP client implementations,
 * abstracting common functionalities like URL and header validation.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 1.0.0-beta
 */
public abstract class AbstractHttpClientService extends BaseClass
        implements HttpClientService {

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
    public HttpResponseWrapper get(String url, Map<String, String> headers)
            throws HttpException {
        logDebug("Executing GET request to: " + url);
        logDebug("Headers: " + headers);
        validator.validateUrl(url);
        try {
            return doGET(url, headers);
        } catch (HttpException e) {
            throw e; // Re-throw the original HttpException
        } catch (Exception e) {
            logError("An unexpected error occurred.", e);
            throw new HttpException("An unexpected error occurred.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponseWrapper post(String url, String body,
                                    Map<String, String> headers)
            throws HttpException {
        logDebug("Executing POST request to: " + url);
        logDebug("Headers: " + headers);
        logDebug("Body: " + body);
        validator.validateUrl(url);
        validator.validateRequestBody(body);
        try {
            return doPOST(url, body, headers);
        } catch (HttpException e) {
            throw e; // Re-throw the original HttpException
        } catch (Exception e) {
            logError("An unexpected error occurred.", e);
            throw new HttpException("An unexpected error occurred.", e);
        }
    }

    /**
     * Executes a GET request.
     *
     * @param url The URL to send the request to.
     * @param headers The headers to include in the request.
     * @return The HTTP response.
     * @throws HttpException If an error occurs during the request.
     */
    protected abstract HttpResponseWrapper doGET(String url,
                                                 Map<String, String> headers)
            throws HttpException;

    /**
     * Executes a POST request.
     *
     * @param url The URL to send the request to.
     * @param body The request body.
     * @param headers The headers to include in the request.
     * @return The HTTP response.
     * @throws HttpException If an error occurs during the request.
     */
    protected abstract HttpResponseWrapper doPOST(String url, String body,
                                                  Map<String, String> headers)
            throws HttpException;
}