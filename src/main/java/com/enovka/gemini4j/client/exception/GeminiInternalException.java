package com.enovka.gemini4j.client.exception;

/**
 * Thrown when an unexpected error occurs on the server side.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @see <a href="https://ai.google.dev/api/rest/v1beta/models">Gemini API
 * Documentation</a>
 * @see GeminiApiException
 * @since 0.0.1-beta
 */
public class GeminiInternalException extends GeminiApiException {

    /**
     * Constructs a new GeminiInternalException with the specified error code
     * and message.
     *
     * @param errorCode The HTTP error code (500).
     * @param errorMessage The error message returned by the Gemini API.
     * @see <a href="https://ai.google.dev/api/rest/v1beta/models#errors">Gemini
     * API Error Codes</a>
     */
    public GeminiInternalException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}