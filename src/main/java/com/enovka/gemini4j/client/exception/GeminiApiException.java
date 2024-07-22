package com.enovka.gemini4j.client.exception;

/**
 * Base class for all exceptions thrown by the Gemini4J library.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1-beta
 */
public class GeminiApiException extends Exception {

    /**
     * The error type for this exception.
     *
     * @since 0.1.0
     */
    private final GeminiApiError error;

    /**
     * Constructs a new GeminiApiException with the specified error type and
     * message.
     *
     * @param error The error type for this exception.
     * @param message The error message.
     * @since 0.1.0
     */
    public GeminiApiException(GeminiApiError error, String message) {
        super(message);
        this.error = error;
    }

    /**
     * Constructs a new GeminiApiException with the specified error type,
     * message, and cause.
     *
     * @param error The error type for this exception.
     * @param message The error message.
     * @param cause The cause of the exception.
     * @since 0.1.0
     */
    public GeminiApiException(GeminiApiError error, String message,
                              Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    /**
     * Returns the error type for this exception.
     *
     * @return The error type.
     * @since 0.1.0
     */
    public GeminiApiError getError() {
        return error;
    }
}