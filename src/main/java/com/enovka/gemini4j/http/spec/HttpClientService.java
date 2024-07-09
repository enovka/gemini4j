package com.enovka.gemini4j.http.spec;

import com.enovka.gemini4j.http.exception.HttpException;

import java.util.Map;

/**
 * Interface that defines methods for making HTTP requests, abstracting the HTTP
 * response handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 1.0.0-beta
 */
public interface HttpClientService {

    /**
     * Sends a GET request to the specified URL with the provided headers.
     *
     * @param url The URL to send the request to.
     * @param headers The headers to include in the request.
     * @return The HTTP response.
     * @throws HttpException If an error occurs during the request.
     */
    HttpResponseWrapper get(String url, Map<String, String> headers)
            throws HttpException;

    /**
     * Sends a POST request to the specified URL with the provided headers and
     * body.
     *
     * @param url The URL to send the request to.
     * @param body The request body.
     * @param headers The headers to include in the request.
     * @return The HTTP response.
     * @throws HttpException If an error occurs during the request.
     */
    HttpResponseWrapper post(String url, String body,
                             Map<String, String> headers) throws HttpException;
}