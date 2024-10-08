package com.enovka.gemini4j.infrastructure.http.spec;

import com.enovka.gemini4j.infrastructure.http.RateLimiter;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Abstract base class for HTTP client implementations, providing a foundation for handling
 * HTTP requests and responses, including rate limiting and asynchronous operations. This
 * class encapsulates common logic for different HTTP methods (GET, POST, PATCH, DELETE),
 * providing synchronous wrapper methods that utilize internal asynchronous operations. It
 * simplifies usage for clients while enabling efficient non-blocking execution and consistent
 * error handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public abstract class AbstractHttpClient extends BaseClass implements HttpClient {

    private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
    private static final int DEFAULT_RESPONSE_TIMEOUT = 10000;

    protected int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
    protected int responseTimeout = DEFAULT_RESPONSE_TIMEOUT;
    private RateLimiter rateLimiter;

    /**
     * {@inheritDoc}
     *
     * @since 0.0.1
     */
    @Override
    public HttpResponse get(String url, Map<String, String> headers) throws HttpException {
        acquireRateLimitPermit();
        return executeSyncRequest(() -> {
            try {
                return executeGetRequest(url, headers);
            } catch (HttpException e) { // Handle HttpException within the lambda
                throw new CompletionException(e); // Wrap in CompletionException
            }
        });
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.1
     */
    @Override
    public HttpResponse post(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException {
        acquireRateLimitPermit();
        return executeSyncRequest(() -> {
            try {
                return executePostRequest(url, body, headers, contentType);
            } catch (HttpException e) {
                throw new CompletionException(e);
            }
        });
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.1
     */
    @Override
    public HttpResponse patch(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException {
        acquireRateLimitPermit();
        return executeSyncRequest(() -> {
            try {
                return executePatchRequest(url, body, headers, contentType);
            } catch (HttpException e) {
                throw new CompletionException(e);
            }
        });
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.1
     */
    @Override
    public HttpResponse delete(String url, Map<String, String> headers) throws HttpException {
        acquireRateLimitPermit();
        return executeSyncRequest(() -> {
            try {
                return executeDeleteRequest(url, headers);
            } catch (HttpException e) {
                throw new CompletionException(e);
            }
        });
    }

    /**
     * {@inheritDoc}
     * @since 0.0.2
     */
    @Override
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * {@inheritDoc}
     * @since 0.0.2
     */
    @Override
    public int getResponseTimeout() {
        return responseTimeout;
    }


    /**
     * {@inheritDoc}
     * @since 0.0.2
     */
    @Override
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * {@inheritDoc}
     * @since 0.0.2
     */
    @Override
    public void setResponseTimeout(int responseTimeout) {
        this.responseTimeout = responseTimeout;
    }

    /**
     * Sets the rate limiter for this HTTP client.
     *
     * @param requestsPerWindow The maximum number of requests allowed per time window.
     * @param windowDuration    The duration of the sliding time window for rate limiting.
     * @since 0.0.2
     */
    public void setRateLimiter(int requestsPerWindow, Duration windowDuration) {
        this.rateLimiter = new RateLimiter(requestsPerWindow, windowDuration);
    }

    /**
     * Acquires a permit from the rate limiter, blocking if necessary until a permit
     * becomes available within the rate limit. This method is called before each HTTP
     * request to ensure that the rate limit is not exceeded.
     *
     * @since 0.1.1
     */
    protected synchronized void acquireRateLimitPermit() {
        if (rateLimiter != null) {
            rateLimiter.acquire();
        }
    }

    /**
     * Executes a synchronous request using the provided {@link Callable}, handling exceptions
     * and timeouts. This method wraps the asynchronous request execution in a synchronous
     * manner, simplifying client usage while benefiting from asynchronous capabilities. It
     * correctly unwraps {@link ExecutionException} and {@link CompletionException} to
     * retrieve the original exception and its status code.
     *
     * @param callable The callable that executes the asynchronous request and returns a
     *                 {@link Future<HttpResponse>}.
     * @return The {@link HttpResponse} from the completed future.
     * @throws HttpException If any error occurs during request execution.
     * @since 0.2.0
     */
    private HttpResponse executeSyncRequest(Callable<Future<HttpResponse>> callable) throws HttpException {
        try {
            return callable.call().get(responseTimeout, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | CompletionException e) { // Catch both ExecutionException and CompletionException
            Throwable cause = e.getCause();
            if (cause instanceof HttpException) {
                HttpException httpException = (HttpException) cause;
                throw new HttpException("HTTP error: " + httpException.getMessage(), httpException, httpException.getStatusCode());
            } else {
                throw new HttpException("HTTP execution error: " + e.getMessage(), e);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new HttpException("HTTP interrupted error: " + e.getMessage(), e);
        } catch (TimeoutException e) {
            throw new HttpException("HTTP timeout error: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new HttpException("Unexpected error during HTTP request: " + e.getMessage(), e);
        }
    }

    /**
     * Asynchronously executes a GET request.  Subclasses must implement this method to provide
     * the actual asynchronous execution logic using HttpClient 5's asynchronous API.
     *
     * @param url     The URL to send the request to.
     * @param headers The request headers.
     * @return A {@link CompletableFuture} representing the asynchronous GET request.
     * @throws HttpException If an error occurs during request setup.
     * @since 0.2.0
     */
    protected abstract CompletableFuture<HttpResponse> executeAsyncGetRequest(String url, Map<String, String> headers) throws HttpException;

    /**
     * Asynchronously executes a POST request. Subclasses must implement this method to provide
     * the actual asynchronous execution logic using HttpClient 5's asynchronous API.
     *
     * @param url         The URL to send the request to.
     * @param body        The request body.
     * @param headers     The request headers.
     * @param contentType The content type of the request.
     * @return A {@link CompletableFuture} representing the asynchronous POST request.
     * @throws HttpException If an error occurs during request setup.
     * @since 0.2.0
     */
    protected abstract CompletableFuture<HttpResponse> executeAsyncPostRequest(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException;

    /**
     * Asynchronously executes a PATCH request. Subclasses must implement this method to
     * provide the actual asynchronous execution logic using HttpClient 5's asynchronous API.
     *
     * @param url         The URL to send the request to.
     * @param body        The request body.
     * @param headers     The request headers.
     * @param contentType The content type of the request.
     * @return A {@link CompletableFuture} representing the asynchronous PATCH request.
     * @throws HttpException If an error occurs during request setup.
     * @since 0.2.0
     */
    protected abstract CompletableFuture<HttpResponse> executeAsyncPatchRequest(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException;

    /**
     * Asynchronously executes a DELETE request.  Subclasses must implement this method to
     * provide the actual asynchronous execution logic using HttpClient 5's asynchronous API.
     *
     * @param url     The URL to send the request to.
     * @param headers The request headers.
     * @return A {@link CompletableFuture} representing the asynchronous DELETE request.
     * @throws HttpException If an error occurs during request setup.
     * @since 0.2.0
     */
    protected abstract CompletableFuture<HttpResponse> executeAsyncDeleteRequest(String url, Map<String, String> headers) throws HttpException;

    /**
     * Executes a GET request synchronously. This method provides a synchronous wrapper around
     * the asynchronous implementation, simplifying usage for clients while handling potential
     * {@link HttpException}.
     *
     * @param url     The URL to send the request to.
     * @param headers The request headers.
     * @return A {@link Future} representing the asynchronous GET request.
     * @throws HttpException If an error occurs during request execution.
     * @since 0.2.0
     */
    protected Future<HttpResponse> executeGetRequest(String url, Map<String, String> headers) throws HttpException {
        return executeAsyncGetRequest(url, headers);
    }

    /**
     * Executes a POST request synchronously. This method provides a synchronous wrapper around
     * the asynchronous implementation, simplifying usage for clients while handling potential
     * {@link HttpException}.
     *
     * @param url         The URL to send the request to.
     * @param body        The request body.
     * @param headers     The request headers.
     * @param contentType The content type of the request.
     * @return A {@link Future} representing the asynchronous POST request.
     * @throws HttpException If an error occurs during request execution.
     * @since 0.2.0
     */
    protected Future<HttpResponse> executePostRequest(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException {
        return executeAsyncPostRequest(url, body, headers, contentType);
    }

    /**
     * Executes a PATCH request synchronously. This method provides a synchronous wrapper around
     * the asynchronous implementation, simplifying usage for clients while handling potential
     * {@link HttpException}.
     *
     * @param url         The URL to send the request to.
     * @param body        The request body.
     * @param headers     The request headers.
     * @param contentType The content type of the request.
     * @return A {@link Future} representing the asynchronous PATCH request.
     * @throws HttpException If an error occurs during request execution.
     * @since 0.2.0
     */
    protected Future<HttpResponse> executePatchRequest(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException {
        return executeAsyncPatchRequest(url, body, headers, contentType);
    }

    /**
     * Executes a DELETE request synchronously. This method provides a synchronous wrapper around
     * the asynchronous implementation, simplifying usage for clients while handling potential
     * {@link HttpException}.
     *
     * @param url     The URL to send the request to.
     * @param headers The request headers.
     * @return A {@link Future} representing the asynchronous DELETE request.
     * @throws HttpException If an error occurs during request execution.
     * @since 0.2.0
     */
    protected Future<HttpResponse> executeDeleteRequest(String url, Map<String, String> headers) throws HttpException {
        return executeAsyncDeleteRequest(url, headers);
    }

    /**
     * Creates an {@link HttpResponse} from a {@link SimpleHttpResponse}. This method
     * extracts the status code, headers, and response body from the provided
     * `SimpleHttpResponse` and constructs a new `HttpResponse` object, which is used for
     * consistent response handling throughout the library.  It handles potential HTTP errors
     * by throwing a {@link HttpException} and includes the error message and status code
     * for detailed error reporting.
     *
     * @param response The {@link SimpleHttpResponse} to convert.
     * @return An {@link HttpResponse} containing the status code, headers, and body of the
     * response, or null if the input response is null.
     * @throws HttpException If the response indicates an HTTP error (status code 400 or
     *                       higher).  The exception includes the error message and status
     *                       code from the response.
     * @since 0.2.0
     */
    protected HttpResponse createHttpResponse(SimpleHttpResponse response) throws HttpException {
        if (response == null) {
            return null;
        }
        Map<String, String> responseHeaders = new HashMap<>();
        for (Header header : response.getHeaders()) {
            responseHeaders.put(header.getName(), header.getValue());
        }
        String responseBody = response.getBodyText();
        if (response.getCode() >= 400) {
            throw new HttpException("HTTP Error! Error code " + response.getCode() + "\n" + responseBody, response.getCode());
        }
        return new HttpResponse(response.getCode(), responseHeaders, responseBody);
    }

    /**
     * Closes this HTTP client and releases any associated resources.  Subclasses must
     * implement this method to provide the specific resource cleanup logic, such as closing
     * connections and shutting down the asynchronous client.
     *
     * @throws IOException If an I/O error occurs during the closing process.
     * @since 0.2.0
     */
    public abstract void close() throws IOException;


    /**
     * A FutureCallback that simply logs the result or exception.
     *
     * @param <T> The result type.
     * @since 0.2.0
     */
    protected class LoggingFutureCallback<T> implements FutureCallback<T> {

        /**
         * {@inheritDoc}
         * @since 0.2.0
         */
        @Override
        public void completed(T result) {
            logDebug("Async operation completed successfully. Result: " + result);
        }

        /**
         * {@inheritDoc}
         * @since 0.2.0
         */
        @Override
        public void failed(Exception ex) {
            logError("Async operation failed.", ex);
        }

        /**
         * {@inheritDoc}
         * @since 0.2.0
         */
        @Override
        public void cancelled() {
            logWarn("Async operation cancelled.");
        }
    }
}