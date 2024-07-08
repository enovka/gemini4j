package com.enovka.gemini4j.http;

import com.enovka.gemini4j.http.spec.AbstractHttpClientService;
import com.enovka.gemini4j.http.spec.HttpResponseWrapper;
import com.enovka.gemini4j.http.validator.HttpRequestValidator;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Map;

/**
 * Default implementation of the HttpClientService using Apache HttpClient.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 */
public class DefaultHttpClientService extends AbstractHttpClientService {

    private HttpClient httpClient;

    /**
     * Constructs a new DefaultHttpClientService with a default HttpClient instance and default validator.
     */
    public DefaultHttpClientService() {
        this(HttpClientBuilder.create().build(), new HttpRequestValidator());
    }

    /**
     * Constructs a new DefaultHttpClientService with the specified HttpClient instance and default validator.
     *
     * @param httpClient The HttpClient instance to use.
     */
    public DefaultHttpClientService(HttpClient httpClient) {
        this(httpClient, new HttpRequestValidator());
    }

    /**
     * Constructs a new DefaultHttpClientService with the specified HttpClient instance and validator.
     *
     * @param httpClient The HttpClient instance to use.
     * @param validator The validator to use for request validation.
     */
    public DefaultHttpClientService(HttpClient httpClient, HttpRequestValidator validator) {
        super(validator);
        this.httpClient = httpClient;
    }

    /**
     * {@inheritDoc}
     */
    public DefaultHttpClientService(HttpRequestValidator validator) {
        super(validator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponseWrapper doGET(String url,
                                        Map<String, String> headers)
            throws Exception {
        HttpGet request = new HttpGet(url);
        headers.forEach(request::addHeader);
        return new DefaultHttpResponseWrapper(httpClient.execute(request));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponseWrapper doPOST(String url, String body,
                                        Map<String, String> headers)
            throws Exception {
        HttpPost request = new HttpPost(url);
        headers.forEach(request::addHeader);
        request.setEntity(new StringEntity(body));
        return new DefaultHttpResponseWrapper(httpClient.execute(request));
    }
}