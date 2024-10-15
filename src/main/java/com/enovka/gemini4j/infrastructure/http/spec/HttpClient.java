package com.enovka.gemini4j.infrastructure.http.spec;

import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import org.apache.hc.core5.http.ContentType;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Interface defining the contract for HTTP client implementations, supporting both
 * synchronous and asynchronous request execution.  This interface provides methods
 * for performing various HTTP operations (GET, POST, PATCH, DELETE) with both
 * blocking and non-blocking behavior.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface HttpClient {

    /**
     * Sends a GET request to the specified URL with the provided headers synchronously.
     *
     * @param url     The URL to send the request to.
     * @param headers The headers to include in the request.
     * @return The HTTP response wrapped in an {@link HttpResponse}.
     * @throws HttpException If an error occurs during the request.
     */
    HttpResponse get(String url, Map<String, String> headers) throws HttpException;

    /**
     * Sends a POST request to the specified URL with the provided headers and body synchronously.
     *
     * @param url         The URL to send the request to.
     * @param body        The request body.
     * @param headers     The headers to include in the request.
     * @param contentType The content type of the request body.
     * @return The HTTP response wrapped in an {@link HttpResponse}.
     * @throws HttpException If an error occurs during the request.
     */
    HttpResponse post(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException;

    /**
     * Sends a PATCH request to the specified URL with the provided headers and body synchronously.
     *
     * @param url         The URL to send the request to.
     * @param body        The request body.
     * @param headers     The headers to include in the request.
     * @param contentType The content type of the request body.
     * @return The HTTP response wrapped in an {@link HttpResponse}.
     * @throws HttpException If an error occurs during the request.
     */
    HttpResponse patch(String url, String body, Map<String, String> headers, ContentType contentType) throws HttpException;


    /**
     * Sends a DELETE request to the specified URL with the provided headers synchronously.
     *
     * @param url     The URL to send the request to.
     * @param headers The headers to include in the request.
     * @return The HTTP response wrapped in an {@link HttpResponse}.
     * @throws HttpException If an error occurs during the request.
     */
    HttpResponse delete(String url, Map<String, String> headers) throws HttpException;


    /**
     * Sets the connection timeout in milliseconds.
     *
     * @param connectionTimeout The connection timeout in milliseconds.
     * @since 0.0.2
     */
    void setConnectionTimeout(int connectionTimeout);

    /**
     * Gets the response timeout in milliseconds.
     *
     * @since 0.0.2
     */
    int getResponseTimeout();

    /**
     * Gets the connection timeout in milliseconds.
     *
     * @since 0.0.2
     */
    int getConnectionTimeout();

    /**
     * Sets the response timeout in milliseconds.
     *
     * @param responseTimeout The response timeout in milliseconds.
     * @since 0.0.2
     */
    void setResponseTimeout(int responseTimeout);

    /**
     * Closes this HTTP client and releases any associated resources.  Subclasses must
     * implement this method to provide the specific resource cleanup logic, such as closing
     * connections and shutting down the asynchronous client.
     *
     * @throws IOException If an I/O error occurs during the closing process.
     * @since 0.2.0
     */
    void close() throws IOException;

    /**
     * Sends an asynchronous GET request to the specified URL with the provided headers.
     *
     * @param url     The URL to send the request to.
     * @param headers The headers to include in the request.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @since 0.2.0
     */
    CompletableFuture<HttpResponse> getAsync(String url, Map<String, String> headers, AsyncCallback<HttpResponse> callback);

    /**
     * Sends an asynchronous POST request to the specified URL with the provided headers and body.
     *
     * @param url         The URL to send the request to.
     * @param body        The request body.
     * @param headers     The headers to include in the request.
     * @param contentType The content type of the request body.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @since 0.2.0
     */
    CompletableFuture<HttpResponse> postAsync(String url, String body, Map<String, String> headers, ContentType contentType, AsyncCallback<HttpResponse> callback);

    /** Sends an asynchronous PATCH request to the specified URL with the provided headers and body.
     *
     * @param url         The URL to send the request to.
     * @param body        The request body.
     * @param headers     The headers to include in the request.
     * @param contentType The content type of the request body.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     * used to cancel the request.
     * @since 0.2.0
     */
    CompletableFuture<HttpResponse> patchAsync(String url, String body, Map<String, String> headers, ContentType contentType, AsyncCallback<HttpResponse> callback);

    /**
     * Sends an asynchronous DELETE request to the specified URL with the provided headers.
     *
     * @param url     The URL to send the request to.
     * @param headers The headers to include in the request.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @since 0.2.0
     */
    CompletableFuture<HttpResponse> deleteAsync(String url, Map<String, String> headers, AsyncCallback<HttpResponse> callback);
}