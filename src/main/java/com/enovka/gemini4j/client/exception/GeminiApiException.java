package com.enovka.gemini4j.client.exception;

import lombok.Getter;

/**
 * Base class for all exceptions thrown by the Gemini4J library.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1-beta
 */
@Getter
public class GeminiApiException extends Exception {

    /**
     * Returns the HTTP error code returned by the Gemini API.
     */
    private final int errorCode;
    /**
     * Returns the error message returned by the Gemini API.
     */
    private final String errorMessage;

    /**
     * Constructs a new GeminiApiException with the specified error code and
     * message.
     *
     * @param errorCode The HTTP error code returned by the Gemini API.
     * @param errorMessage The error message returned by the Gemini API.
     * @see <a href="https://ai.google.dev/api/rest/v1beta/models">Gemini API
     * Documentation</a>
     */
    public GeminiApiException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Constructs a new GeminiApiException with the specified error code and
     * message.
     *
     * @param errorCode The HTTP error code returned by the Gemini API.
     * @param errorMessage The error message returned by the Gemini API.
     * @param cause The original exception that caused this exception.
     * @see <a href="https://ai.google.dev/api/rest/v1beta/models">Gemini API
     * Documentation</a>
     */
    public GeminiApiException(int errorCode, String errorMessage,
                              Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}