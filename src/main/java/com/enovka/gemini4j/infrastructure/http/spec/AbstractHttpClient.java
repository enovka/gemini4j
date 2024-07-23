package com.enovka.gemini4j.infrastructure.http.spec;

import com.enovka.gemini4j.infrastructure.http.RateLimiter;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;

import java.time.Duration;
import java.util.Map;

/**
 * {@inheritDoc}
 */
public abstract class AbstractHttpClient extends BaseClass
        implements HttpClient {

    private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
    private static final int DEFAULT_RESPONSE_TIMEOUT = 10000;

    protected int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
    protected int responseTimeout = DEFAULT_RESPONSE_TIMEOUT;
    private RateLimiter rateLimiter;

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponse get(String url, Map<String, String> headers)
            throws HttpException {
        acquireRateLimitPermit();
        return executeGetRequest(url, headers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponse post(String url, String body,
                             Map<String, String> headers) throws HttpException {
        acquireRateLimitPermit();
        return executePostRequest(url, body, headers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResponseTimeout(int responseTimeout) {
        this.responseTimeout = responseTimeout;
    }

    /**
     * Sets the rate limiter for this HTTP client.
     *
     * @param requestsPerWindow The maximum number of requests allowed per time
     * window.
     * @param windowDuration The duration of the sliding time window for rate
     * limiting.
     */
    public void setRateLimiter(int requestsPerWindow, Duration windowDuration) {
        this.rateLimiter = new RateLimiter(requestsPerWindow, windowDuration);
    }

    /**
     * Acquires a permit from the rate limiter, blocking if necessary until a
     * permit becomes available within the rate limit. This method is called
     * before each HTTP request to ensure that the rate limit is not exceeded.
     */
    protected void acquireRateLimitPermit() {
        if (rateLimiter != null) {
            rateLimiter.acquire();
        }
    }

    /**
     * Executes the actual GET request logic. This method is called after the
     * rate limit permit is acquired.
     *
     * @param url The URL to send the GET request to.
     * @param headers The headers to include in the request.
     * @return An {@link HttpResponse} object containing the response.
     * @throws HttpException If an error occurs during the request execution.
     */
    protected abstract HttpResponse executeGetRequest(String url,
                                                      Map<String, String> headers)
            throws HttpException;

    /**
     * Executes the actual POST request logic. This method is called after the
     * rate limit permit is acquired.
     *
     * @param url The URL to send the POST request to.
     * @param body The request body.
     * @param headers The headers to include in the request.
     * @return An {@link HttpResponse} object containing the response.
     * @throws HttpException If an error occurs during the request execution.
     */
    protected abstract HttpResponse executePostRequest(String url, String body,
                                                       Map<String, String> headers)
            throws HttpException;
}