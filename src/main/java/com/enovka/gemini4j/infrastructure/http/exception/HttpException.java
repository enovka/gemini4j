package com.enovka.gemini4j.infrastructure.http.exception;

import lombok.Getter;

/**
 * Exception class representing an error during a Gemini API request.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @see <a href="https://ai.google.dev/api/rest/v1beta/models">Gemini API
 * Documentation</a>
 * @since 0.0.1-beta
 */
public class HttpException extends Throwable {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final Throwable cause;
    /**
     * Returns the HTTP status code.
     */
    @Getter
    private final int statusCode;

    /**
     * Constructs a new HttpException with the specified error message.
     *
     * @param message The error message.
     */
    public HttpException(String message) {
        this(message, null, -1);
    }

    /**
     * Constructs a new HttpException with the specified error message and
     * cause.
     *
     * @param message The error message.
     * @param cause   The cause of the exception.
     */
    public HttpException(String message, Throwable cause) {
        this(message, cause, -1);
    }

    /**
     * Constructs a new HttpException with the specified error message, cause,
     * and HTTP status code.
     *
     * @param message    The error message.
     * @param cause      The cause of the exception.
     * @param statusCode The HTTP status code.
     */
    public HttpException(String message, Throwable cause, int statusCode) {
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

}