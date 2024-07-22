package com.enovka.gemini4j.client.exception;

import lombok.Getter;

/**
 * Exception class representing an error that occurred within the Gemini client.
 * This can include network errors, JSON processing errors, or issues with the
 * client configuration.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.0
 */
@Getter
public class GeminiClientException extends Exception {

    /**
     * The original exception that caused this exception. Returns the original
     * exception that caused this exception.
     */
    private final Throwable originalException;

    /**
     * Constructs a new GeminiClientException with the specified error message.
     *
     * @param message The error message.
     */
    public GeminiClientException(String message) {
        this(message, null);
    }

    /**
     * Constructs a new GeminiClientException with the specified error message
     * and cause.
     *
     * @param message The error message.
     * @param cause The cause of the exception.
     */
    public GeminiClientException(String message, Throwable cause) {
        super(message, cause);
        this.originalException = cause;
    }

}