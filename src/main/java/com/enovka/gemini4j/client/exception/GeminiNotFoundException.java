package com.enovka.gemini4j.client.exception;

/**
 * Thrown when the requested resource is not found.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @see <a href="https://ai.google.dev/api/rest/v1beta/models">Gemini API
 * Documentation</a>
 * @see GeminiApiException
 * @since 0.0.1-beta
 */
public class GeminiNotFoundException extends GeminiApiException {

    /**
     * Constructs a new GeminiNotFoundException with the specified error code
     * and message.
     *
     * @param errorCode The HTTP error code (404).
     * @param errorMessage The error message returned by the Gemini API.
     * @see <a href="https://ai.google.dev/api/rest/v1beta/models#errors">Gemini
     * API Error Codes</a>
     */
    public GeminiNotFoundException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}