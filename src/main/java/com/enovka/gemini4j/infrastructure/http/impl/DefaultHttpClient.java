package com.enovka.gemini4j.infrastructure.http.impl;

import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.AbstractHttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Default implementation of the {@link com.enovka.gemini4j.infrastructure.http.spec.HttpClient}
 * interface using Apache HttpClient. This class provides methods for executing various HTTP requests
 * (GET, POST, PATCH, DELETE) and handles common HTTP-related tasks such as setting headers, managing
 * timeouts, and processing responses. It is designed to be thread-safe and efficient for
 * concurrent use.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class DefaultHttpClient extends AbstractHttpClient {

    private static final int DEFAULT_MAX_CONNECTIONS = 50;
    private static final int DEFAULT_CONNECTION_TIMEOUT = 12000;
    private static final int DEFAULT_RESPONSE_TIMEOUT = 600000; //10 minutes
    private static final int DEFAULT_VALIDATE_AFTER_INACTIVITY = 2000;
    private static final int DEFAULT_CONNECTION_TTL = 5;

    private final CloseableHttpClient httpClient;

    /**
     * Constructs a new DefaultHttpClient with default settings, including a connection pool optimized
     * for multithreading. Uses default values for connection timeout, response timeout, and maximum
     * connections.
     *
     * @since 0.0.1
     */
    public DefaultHttpClient() {
        this(DEFAULT_CONNECTION_TIMEOUT, DEFAULT_RESPONSE_TIMEOUT, DEFAULT_MAX_CONNECTIONS);
    }

    /**
     * Constructs a new DefaultHttpClient with custom connection and response timeouts and a
     * configurable connection pool size.
     *
     * @param connectionTimeout The connection timeout in milliseconds.
     * @param responseTimeout   The response timeout in milliseconds.
     * @param maxConnections    The maximum number of concurrent connections allowed.
     * @throws IllegalArgumentException if maxConnections is less than or equal to zero.
     * @since 0.0.2
     */
    public DefaultHttpClient(int connectionTimeout, int responseTimeout, int maxConnections) {
        validateMaxConnections(maxConnections);
        //TODO need fix responseTimeout
        this.httpClient = createHttpClient(connectionTimeout, responseTimeout, maxConnections);
        this.connectionTimeout = connectionTimeout;
        this.responseTimeout = responseTimeout;
    }

    /**
     * Validates the maximum number of connections, throwing an exception if it's invalid.
     *
     * @param maxConnections The maximum number of connections to validate.
     * @throws IllegalArgumentException if maxConnections is less than or equal to zero.
     * @since 0.2.0
     */
    private void validateMaxConnections(int maxConnections) {
        if (maxConnections <= 0) {
            throw new IllegalArgumentException("maxConnections must be greater than zero.");
        }
    }

    /**
     * Creates and configures a thread-safe {@link CloseableHttpClient} instance with a connection
     * pool and timeouts.
     *
     * @param connectionTimeout The connection timeout in milliseconds.
     * @param responseTimeout   The response timeout in milliseconds.
     * @param maxConnections    The maximum number of concurrent connections allowed.
     * @return A configured {@link CloseableHttpClient} instance.
     * @since 0.2.0
     */
    private CloseableHttpClient createHttpClient(int connectionTimeout, int responseTimeout, int maxConnections) {
        PoolingHttpClientConnectionManager connectionManager = createConnectionManager(maxConnections);

        RequestConfig requestConfig = createRequestConfig(connectionTimeout, responseTimeout);
        return HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * Creates and configures a {@link PoolingHttpClientConnectionManager} for managing the connection
     * pool.
     *
     * @param maxConnections The maximum number of concurrent connections allowed.
     * @return A configured {@link PoolingHttpClientConnectionManager} instance.
     * @since 0.2.0
     */
    private PoolingHttpClientConnectionManager createConnectionManager(int maxConnections) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(DEFAULT_CONNECTION_TTL, TimeUnit.MINUTES);
        connectionManager.setMaxTotal(maxConnections);
        connectionManager.setDefaultMaxPerRoute(maxConnections);
        connectionManager.setValidateAfterInactivity(DEFAULT_VALIDATE_AFTER_INACTIVITY);
        return connectionManager;
    }

    /**
     * Creates a {@link RequestConfig} with the specified timeouts.
     *
     * @param connectionTimeout The connection timeout in milliseconds.
     * @param responseTimeout   The response timeout in milliseconds.
     * @return A configured {@link RequestConfig} instance.
     * @since 0.2.0
     */
    private RequestConfig createRequestConfig(int connectionTimeout, int responseTimeout) {
        return RequestConfig.custom()
                .setConnectTimeout(connectionTimeout)
                .setSocketTimeout(responseTimeout)
                .setConnectionRequestTimeout(connectionTimeout)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponse executeGetRequest(String url, Map<String, String> headers) throws HttpException {
        HttpGet request = new HttpGet(url);
        addHeadersToRequest(request, headers);
        try {
            return executeRequest(request);
        } catch (IOException e) {
            throw new HttpException("Error executing GET request: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponse executePostRequest(String url, String body, Map<String, String> headers) throws HttpException {
        HttpPost request = new HttpPost(url);
        try {
            preparePostRequest(request, body, headers);
            return executeRequest(request);
        } catch (IOException e) {
            throw new HttpException("Error executing POST request: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponse executePatchRequest(String url, String body, Map<String, String> headers) throws HttpException {
        HttpPatch request = new HttpPatch(url);
        try {
            preparePostRequest(request, body, headers);
            return executeRequest(request);
        } catch (IOException e) {
            throw new HttpException("Error executing PATCH request: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponse executeDeleteRequest(String url, Map<String, String> headers) throws HttpException {
        HttpDelete request = new HttpDelete(url);
        addHeadersToRequest(request, headers);
        try {
            return executeRequest(request);
        } catch (IOException e) {
            throw new HttpException("Error executing DELETE request: " + e.getMessage(), e);
        }
    }


    /**
     * Prepares a POST or PATCH request by setting headers and the request body.
     *
     * @param request The {@link HttpEntityEnclosingRequestBase} (HttpPost or HttpPatch).
     * @param body    The request body as a String.
     * @param headers The request headers.
     * @throws IOException If an I/O error occurs while setting the request entity.
     * @since 0.2.0
     */
    private void preparePostRequest(HttpEntityEnclosingRequestBase request, String body, Map<String, String> headers) throws IOException {
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");

        addHeadersToRequest(request, headers);
        request.setEntity(new StringEntity(body));
    }

    /**
     * Executes the given HTTP request and handles the response.
     *
     * @param request The HTTP request to execute.
     * @return An {@link HttpResponse} object representing the response.
     * @throws HttpException If an error occurs during request execution or response handling.
     * @throws IOException   If an I/O error occurs during request execution or response handling.
     * @since 0.2.0
     */
    private HttpResponse executeRequest(HttpUriRequest request) throws HttpException, IOException {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return handleResponse(response);
        }
    }


    /**
     * Adds the provided headers to the given HTTP request.
     *
     * @param request The HTTP request to add headers to.
     * @param headers The headers to add.
     * @since 0.2.0
     */
    private void addHeadersToRequest(HttpUriRequest request, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
    }


    /**
     * Handles the HTTP response, checking for errors and creating an {@link HttpResponse} object.
     *
     * @param response The Apache HTTP client response.
     * @return An {@link HttpResponse} object representing the response.
     * @throws HttpException If an error occurs during response processing or if the response status code is 400 or higher.
     * @throws IOException   If an I/O error occurs during response processing.
     * @since 0.2.0
     */
    private HttpResponse handleResponse(CloseableHttpResponse response) throws HttpException, IOException {
        if (response == null) {
            throw new HttpException("Bad request. Response is null.");
        }

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode >= 400) {
            String errorMessage = EntityUtils.toString(response.getEntity());
            throw new HttpException("Bad request. " + errorMessage + " Status code: " + statusCode, null, statusCode);
        }

        return createHttpResponse(response);
    }

    /**
     * Creates an {@link HttpResponse} from a {@link CloseableHttpResponse}.
     *
     * @param response The CloseableHttpResponse to convert.
     * @return An HttpResponse containing the status code, headers, and body of the response.
     * @throws IOException If an I/O error occurs while reading the response body or headers.
     * @since 0.2.0
     */
    private HttpResponse createHttpResponse(CloseableHttpResponse response) throws IOException {
        Map<String, String> responseHeaders = new HashMap<>();
        for (Header header : response.getAllHeaders()) {
            responseHeaders.put(header.getName(), header.getValue());
        }
        String responseBody = EntityUtils.toString(response.getEntity());
        return new HttpResponse(response.getStatusLine().getStatusCode(), responseHeaders, responseBody);
    }
}