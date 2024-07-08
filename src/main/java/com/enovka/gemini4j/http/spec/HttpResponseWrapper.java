package com.enovka.gemini4j.http.spec;

import com.enovka.gemini4j.http.exception.GeminiHttpException;

import java.util.Map;

/**
 * Interface representing a wrapper for HTTP response.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 */
public interface HttpResponseWrapper {

    /**
     * Returns the HTTP status code of the response.
     *
     * @return The HTTP status code.
     */
    int statusCode();

    /**
     * Returns the headers of the response as a map.
     *
     * @return The response headers.
     */
    Map<String, String> headers();

    /**
     * Returns the body of the response as a String.
     *
     * @return The response body.
     * @throws GeminiHttpException If an error occurs reading the response body.
     */
    String body() throws GeminiHttpException;

    /**
     * Wraps an existing HttpResponseWrapper with this HttpResponseWrapper.
     * This is used to delegate response handling to the wrapped HttpResponseWrapper.
     *
     * @param response The HttpResponseWrapper to wrap.
     * @return The wrapped HttpResponseWrapper.
     */
    HttpResponseWrapper wrap(HttpResponseWrapper response);
}