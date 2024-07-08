package com.enovka.gemini4j.http.exception;

import java.io.Serializable;

/**
 * Exception class representing an error during a Gemini API request.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 * @see <a href="https://ai.google.dev/api/rest/v1beta/models">Gemini API Documentation</a>
 */
public class GeminiHttpException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final Throwable cause;
    private final int statusCode;

    /**
     * Constructs a new GeminiHttpException with the specified error message.
     *
     * @param message The error message.
     */
    public GeminiHttpException(String message) {
        this(message, null, -1);
    }

    /**
     * Constructs a new GeminiHttpException with the specified error message and cause.
     *
     * @param message The error message.
     * @param cause   The cause of the exception.
     */
    public GeminiHttpException(String message, Throwable cause) {
        this(message, cause, -1);
    }

    /**
     * Constructs a new GeminiHttpException with the specified error message, cause, and HTTP status code.
     *
     * @param message    The error message.
     * @param cause      The cause of the exception.
     * @param statusCode The HTTP status code.
     */
    public GeminiHttpException(String message, Throwable cause, int statusCode) {
        this.message = message;
        this.cause = cause;
        this.statusCode = statusCode;
    }

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Returns the cause of the exception.
     *
     * @return The cause of the exception.
     */
    @Override
    public Throwable getCause() {
        return cause;
    }

    /**
     * Returns the HTTP status code.
     *
     * @return The HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }
}