package com.enovka.gemini4j.infrastructure.http.impl;

import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.AbstractHttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManager;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManagerBuilder;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

/**
 * Default implementation of the {@link com.enovka.gemini4j.infrastructure.http.spec.HttpClient}
 * interface, utilizing Apache HttpClient 5's asynchronous API. This class executes HTTP
 * requests (GET, POST, PATCH, DELETE) asynchronously, managing headers, timeouts, and
 * responses efficiently. It leverages non-blocking operations and connection pooling for
 * optimal performance.  This implementation incorporates best practices from the HttpClient
 * tutorial and examples, including correct usage of {@link CompletableFuture} and
 * {@link FutureCallback}, streamlined request creation with {@link SimpleRequestBuilder}, and
 * robust error handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class DefaultHttpClient extends AbstractHttpClient {

    private static final int DEFAULT_MAX_CONNECTIONS = 50;
    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 12000;
    private static final int DEFAULT_RESPONSE_TIMEOUT_MS = 600000; // 10 minutes
    private static final int DEFAULT_VALIDATE_AFTER_INACTIVITY_MS = 2000;
    private static final TimeValue DEFAULT_CONNECTION_TTL = TimeValue.ofMinutes(5);


    private final CloseableHttpAsyncClient httpAsyncClient;
    private final PoolingAsyncClientConnectionManager connectionManager;


    /**
     * Constructs a new DefaultHttpClient with default settings, including a connection pool
     * optimized for multithreading. Uses default values for connection timeout, response
     * timeout, and maximum connections.
     *
     * @since 0.0.1
     */
    public DefaultHttpClient() {
        this(DEFAULT_CONNECTION_TIMEOUT_MS, DEFAULT_RESPONSE_TIMEOUT_MS, DEFAULT_MAX_CONNECTIONS);
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
        this.connectionTimeout = connectionTimeout;
        this.responseTimeout = responseTimeout;

        this.connectionManager = createConnectionManager(maxConnections, connectionTimeout);
        this.httpAsyncClient = createHttpAsyncClient(connectionManager, responseTimeout);
        this.httpAsyncClient.start();
    }

    /**
     * Creates and configures a {@link PoolingAsyncClientConnectionManager} for managing the
     * connection pool. This method sets the maximum total connections, default maximum
     * connections per route, connection time-to-live, and validation parameters. It uses the
     * builder pattern for clear and concise configuration.  HTTP/2 specific configurations are
     * not applied here, as this connection manager is intended for general use and might be
     * used with HTTP/1.1 as well.
     *
     * @param maxConnections    The maximum number of concurrent connections allowed.
     * @param connectionTimeout The connection timeout in milliseconds.
     * @return A configured {@link PoolingAsyncClientConnectionManager} instance.
     * @since 0.2.0
     */
    private PoolingAsyncClientConnectionManager createConnectionManager(int maxConnections, int connectionTimeout) {
        return PoolingAsyncClientConnectionManagerBuilder.create()
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setConnPoolPolicy(PoolReusePolicy.LIFO)
                .setDefaultConnectionConfig(
                        org.apache.hc.client5.http.config.ConnectionConfig.custom()
                                .setConnectTimeout(Timeout.ofMilliseconds(connectionTimeout))
                                .setSocketTimeout(Timeout.ofMilliseconds(connectionTimeout))
                                .setTimeToLive(DEFAULT_CONNECTION_TTL)
                                .build())
                .setMaxConnTotal(maxConnections) // Set the maximum total connections
                .setMaxConnPerRoute(maxConnections) // Set the maximum connections per route
                .setValidateAfterInactivity(TimeValue.ofMilliseconds(DEFAULT_VALIDATE_AFTER_INACTIVITY_MS))
                .build();
    }

    /**
     * Creates and configures a {@link CloseableHttpAsyncClient} with the provided connection
     * manager and response timeout. This method sets up the I/O reactor configuration and request
     * configuration for the asynchronous HTTP client.
     *
     * @param connectionManager The connection manager to use.
     * @param responseTimeout   The response timeout in milliseconds.
     * @return A configured {@link CloseableHttpAsyncClient} instance.
     * @since 0.2.0
     */
    private CloseableHttpAsyncClient createHttpAsyncClient(PoolingAsyncClientConnectionManager connectionManager, int responseTimeout) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setResponseTimeout(Timeout.ofMilliseconds(responseTimeout))
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(connectionTimeout))
                .build();

        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setSoTimeout(Timeout.ofMilliseconds(responseTimeout))
                .build();

        return HttpAsyncClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setIOReactorConfig(ioReactorConfig)
                .build();
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
     * {@inheritDoc}
     *
     * @since 0.2.0
     */
    @Override
    protected CompletableFuture<HttpResponse> executeAsyncGetRequest(String url, Map<String, String> headers) throws HttpException {
        return executeAsyncRequest("GET", url, null, headers, ContentType.TEXT_PLAIN);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.2.0
     */
    @Override
    protected CompletableFuture<HttpResponse> executeAsyncPostRequest(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException {
        return executeAsyncRequest("POST", url, body, headers, contentType);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.2.0
     */
    @Override
    protected CompletableFuture<HttpResponse> executeAsyncPatchRequest(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException {
        return executeAsyncRequest("PATCH", url, body, headers, contentType);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.2.0
     */
    @Override
    protected CompletableFuture<HttpResponse> executeAsyncDeleteRequest(String url, Map<String, String> headers) throws HttpException {
        return executeAsyncRequest("DELETE", url, null, headers, ContentType.TEXT_PLAIN);
    }


    /**
     * Executes an asynchronous HTTP request. This method handles the request creation,
     * execution, and asynchronous response handling using {@link CompletableFuture} and
     * {@link FutureCallback}. It incorporates error handling, resource cleanup, and ensures
     * that the {@link HttpHost} is correctly set in the request. The method uses
     * {@link SimpleRequestBuilder} for efficient request creation and sets the request body,
     * headers, and URI in the correct order, avoiding the "Target host is not specified"
     * error.
     *
     * @param method      The HTTP method (GET, POST, PATCH, DELETE).
     * @param url         The request URL.
     * @param body        The request body (for POST and PATCH).
     * @param headers     The request headers.
     * @param contentType The content type of the request body.
     * @return A {@link CompletableFuture} that will resolve to the {@link HttpResponse}.
     * @since 0.2.0
     */
    private CompletableFuture<HttpResponse> executeAsyncRequest(
            String method, String url, String body, Map<String, String> headers,
            ContentType contentType) {

        CompletableFuture<HttpResponse> completableFuture = new CompletableFuture<>();

        URI uri = createURI(url);
        HttpHost httpHost = new HttpHost(uri.getScheme(), uri.getHost(), uri.getPort());

        SimpleRequestBuilder requestBuilder = SimpleRequestBuilder.create(method)
                .setHttpHost(httpHost)
                .setUri(uri);

        if (body != null) {
            requestBuilder.setBody(body, contentType);
        }

        addHeadersToRequest(requestBuilder, headers);

        SimpleHttpRequest request = requestBuilder.build();

        httpAsyncClient.execute(request, new FutureCallback<>() {
            @Override
            public void completed(SimpleHttpResponse result) {
                try {
                    completableFuture.complete(createHttpResponse(result));
                } catch (HttpException e) {
                    completableFuture.completeExceptionally(e);
                }
            }

            @Override
            public void failed(Exception ex) {
                completableFuture.completeExceptionally(new HttpException("Error executing " + method + " request: " + ex.getMessage(), ex));
            }

            @Override
            public void cancelled() {
                completableFuture.completeExceptionally(new HttpException(method + " request cancelled"));
            }
        });

        return completableFuture;
    }
    /**
     * Adds the provided headers to the given HTTP request builder. This method iterates over
     * the provided headers map and adds each header to the request builder. It handles the
     * case where the headers map is null.
     *
     * @param requestBuilder The {@link SimpleRequestBuilder} to add headers to.
     * @param headers        The headers to add, as a map of header names to values.
     * @since 0.2.0
     */
    private void addHeadersToRequest(SimpleRequestBuilder requestBuilder, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(requestBuilder::addHeader);
        }
    }

    /**
     * Creates a URI from the given URL string, handling potential URISyntaxException.  This
     * method uses the {@link URIBuilder} class to safely construct a URI, ensuring proper
     * encoding and handling of URL components.  It throws an {@link HttpException} if the
     * provided URL is invalid.
     *
     * @param url The URL string.
     * @return The created URI.
     * @since 0.2.0
     */
    private URI createURI(String url) {
        try {
            return new URIBuilder(url).build();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URI: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CompletableFuture<HttpResponse> getAsync(String url, Map<String, String> headers, AsyncCallback<HttpResponse> callback) {
        acquireRateLimitPermit();
        CompletableFuture<HttpResponse> future = executeAsyncRequest("GET", url, null, headers, ContentType.TEXT_PLAIN);
        future.whenComplete((response, exception) -> handleResponse(response, exception, callback));
        return future;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CompletableFuture<HttpResponse> postAsync(String url, String body, Map<String, String> headers, ContentType contentType, AsyncCallback<HttpResponse> callback) {
        acquireRateLimitPermit();
        CompletableFuture<HttpResponse> future = executeAsyncRequest("POST", url, body, headers, contentType);
        future.whenComplete((response, exception) -> handleResponse(response, exception, callback));
        return future;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CompletableFuture<HttpResponse> patchAsync(String url, String body, Map<String, String> headers, ContentType contentType, AsyncCallback<HttpResponse> callback) {
        acquireRateLimitPermit();
        CompletableFuture<HttpResponse> future = executeAsyncRequest("PATCH", url, body, headers, contentType);
        future.whenComplete((response, exception) -> handleResponse(response, exception, callback));
        return future;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CompletableFuture<HttpResponse> deleteAsync(String url, Map<String, String> headers, AsyncCallback<HttpResponse> callback) {
        acquireRateLimitPermit();
        CompletableFuture<HttpResponse> future = executeAsyncRequest("DELETE", url, null, headers, ContentType.TEXT_PLAIN);
        future.whenComplete((response, exception) -> handleResponse(response, exception, callback));
        return future;
    }

    private void handleResponse(HttpResponse response, Throwable exception, AsyncCallback<HttpResponse> callback) {
        if (exception != null) {
            if (exception instanceof CancellationException) {
                callback.onCanceled();
            } else {
                callback.onError(exception);
            }
        } else {
            callback.onSuccess(response);
        }
        try {
            close();
        } catch (IOException e) {
            logError("Error on handleResponse", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        this.httpAsyncClient.close(CloseMode.GRACEFUL);
        this.connectionManager.close();
    }
}