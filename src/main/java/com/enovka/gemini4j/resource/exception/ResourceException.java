package com.enovka.gemini4j.resource.exception;

/**
 * Exception class representing an error that occurred within the resource layer
 * of the Gemini4j library.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.0
 */
public class ResourceException extends Exception {

    /**
     * Constructs a new ResourceException with the specified error message.
     *
     * @param message The error message.
     */
    public ResourceException(String message) {
        super(message);
    }

    /**
     * Constructs a new ResourceException with the specified error message and
     * cause.
     *
     * @param message The error message.
     * @param cause The cause of the exception.
     */
    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}