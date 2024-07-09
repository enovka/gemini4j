package com.enovka.gemini4j.client.exception;

/**
 * Thrown when a precondition is not met.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @see <a href="https://ai.google.dev/api/rest/v1beta/models">Gemini API
 * Documentation</a>
 * @see GeminiApiException
 * @since 1.0.0-beta
 */
public class GeminiFailedPreconditionException extends GeminiApiException {

    /**
     * Constructs a new GeminiFailedPreconditionException with the specified
     * error code and message.
     *
     * @param errorCode The HTTP error code (400).
     * @param errorMessage The error message returned by the Gemini API.
     * @see <a href="https://ai.google.dev/api/rest/v1beta/models#errors">Gemini
     * API Error Codes</a>
     */
    public GeminiFailedPreconditionException(int errorCode,
                                             String errorMessage) {
        super(errorCode, errorMessage);
    }
}