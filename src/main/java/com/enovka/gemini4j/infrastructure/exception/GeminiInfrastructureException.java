package com.enovka.gemini4j.infrastructure.exception;

/**
 * Exception class representing an error that occurred within the infrastructure
 * layer of the Gemini4j library. This can include network errors, HTTP errors,
 * or JSON processing errors.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.0
 */
public class GeminiInfrastructureException extends Exception {

    /**
     * Constructs a new GeminiInfrastructureException with the specified error
     * message.
     *
     * @param message The error message.
     */
    public GeminiInfrastructureException(String message) {
        super(message);
    }

    /**
     * Constructs a new GeminiInfrastructureException with the specified error
     * message and cause.
     *
     * @param message The error message.
     * @param cause The cause of the exception.
     */
    public GeminiInfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}