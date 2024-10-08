package com.enovka.gemini4j.infrastructure.http.spec;

import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import org.apache.hc.core5.http.ContentType;

import java.util.Map;

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
}