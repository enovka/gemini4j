package com.enovka.gemini4j.infrastructure.http.spec;

import lombok.Getter;

import java.util.Map;

/**
 * Represents an HTTP response.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
@Getter
public class HttpResponse {

    /**
     * -- GETTER --
     *  Returns the HTTP status code.
     *
     * @return The HTTP status code.
     */
    private final int statusCode;
    /**
     * -- GETTER --
     *  Returns the response headers.
     *
     * @return The response headers.
     */
    private final Map<String, String> headers;
    /**
     * -- GETTER --
     *  Returns the response body.
     *
     * @return The response body.
     */
    private final String body;

    /**
     * Constructs a new HttpResponse.
     *
     * @param statusCode The HTTP status code.
     * @param headers The response headers.
     * @param body The response body.
     */
    public HttpResponse(int statusCode, Map<String, String> headers,
                        String body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

}