package com.enovka.gemini4j.infrastructure.json.exception;

/**
 * Exception class representing an error during JSON serialization or
 * deserialization.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class JsonException extends Exception {

    /**
     * Constructs a new JsonException with the specified error message.
     *
     * @param message The error message.
     */
    public JsonException(String message) {
        super(message);
    }

    /**
     * Constructs a new JsonException with the specified error message and
     * cause.
     *
     * @param message The error message.
     * @param cause   The cause of the exception.
     */
    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}