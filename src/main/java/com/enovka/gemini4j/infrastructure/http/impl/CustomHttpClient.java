package com.enovka.gemini4j.infrastructure.http.impl;

import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;

import java.util.Map;

/**
 * Custom HTTP client implementation that wraps a user-provided HttpClient.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class CustomHttpClient extends BaseClass implements HttpClient {

    private final HttpClient customClient;

    /**
     * Constructs a new CustomHttpClient with the provided custom client.
     *
     * @param customClient The user-provided HttpClient implementation.
     */
    public CustomHttpClient(HttpClient customClient) {
        this.customClient = customClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponse get(String url, Map<String, String> headers)
            throws HttpException {
        logDebug("Delegating GET request to custom client.");
        return customClient.get(url, headers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponse post(String url, String body,
                             Map<String, String> headers) throws HttpException {
        logDebug("Delegating POST request to custom client.");
        return customClient.post(url, body, headers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConnectionTimeout(int connectionTimeout) {
        logDebug("Setting connection timeout on custom client: "
                + connectionTimeout + "ms.");
        customClient.setConnectionTimeout(connectionTimeout);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResponseTimeout(int responseTimeout) {
        logDebug("Setting response timeout on custom client: " + responseTimeout
                + "ms.");
        customClient.setResponseTimeout(responseTimeout);
    }
}