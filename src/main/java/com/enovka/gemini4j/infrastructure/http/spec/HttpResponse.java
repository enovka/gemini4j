package com.enovka.gemini4j.infrastructure.http.spec;

import java.util.Map;

/**
 * Represents an HTTP response.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class HttpResponse {

    private final int statusCode;
    private final Map<String, String> headers;
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

    /**
     * Returns the HTTP status code.
     *
     * @return The HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Returns the response headers.
     *
     * @return The response headers.
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Returns the response body.
     *
     * @return The response body.
     */
    public String getBody() {
        return body;
    }
}