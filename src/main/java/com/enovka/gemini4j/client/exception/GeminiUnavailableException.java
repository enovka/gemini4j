package com.enovka.gemini4j.client.exception;

/**
 * Thrown when the service is temporarily overloaded or unavailable.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 * @see <a href="https://ai.google.dev/api/rest/v1beta/models">Gemini API Documentation</a>
 * @see GeminiApiException
 */
public class GeminiUnavailableException extends GeminiApiException {

    /**
     * Constructs a new GeminiUnavailableException with the specified error code and message.
     *
     * @param errorCode    The HTTP error code (503).
     * @param errorMessage The error message returned by the Gemini API.
     * @see <a href="https://ai.google.dev/api/rest/v1beta/models#errors">Gemini API Error Codes</a>
     */
    public GeminiUnavailableException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}