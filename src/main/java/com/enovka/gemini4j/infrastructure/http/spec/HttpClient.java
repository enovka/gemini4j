package com.enovka.gemini4j.infrastructure.http.spec;

import com.enovka.gemini4j.infrastructure.http.exception.HttpException;

import java.util.Map;

/**
 * Interface defining the contract for HTTP client implementations.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface HttpClient {

    /**
     * Sends a GET request to the specified URL with the provided headers.
     *
     * @param url The URL to send the request to.
     * @param headers The headers to include in the request.
     * @return The HTTP response wrapped in an {@link HttpResponse}.
     * @throws HttpException If an error occurs during the request.
     */
    HttpResponse get(String url, Map<String, String> headers)
            throws HttpException;

    /**
     * Sends a POST request to the specified URL with the provided headers and
     * body.
     *
     * @param url The URL to send the request to.
     * @param body The request body.
     * @param headers The headers to include in the request.
     * @return The HTTP response wrapped in an {@link HttpResponse}.
     * @throws HttpException If an error occurs during the request.
     */
    HttpResponse post(String url, String body, Map<String, String> headers)
            throws HttpException;

    /**
     * Sets the connection timeout in milliseconds.
     *
     * @param connectionTimeout The connection timeout in milliseconds.
     * @since 0.0.2
     */
    void setConnectionTimeout(int connectionTimeout);

    /**
     * Sets the response timeout in milliseconds.
     *
     * @param responseTimeout The response timeout in milliseconds.
     * @since 0.0.2
     */
    void setResponseTimeout(int responseTimeout);
}