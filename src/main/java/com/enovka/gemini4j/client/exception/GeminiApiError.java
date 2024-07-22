package com.enovka.gemini4j.client.exception;

/**
 * Enum representing different error types for Gemini API exceptions.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.0
 */
public enum GeminiApiError {
    /**
     * Represents an error where the requested resource is not found.
     */
    NOT_FOUND,

    /**
     * Represents an error where an invalid argument is provided in the
     * request.
     */
    INVALID_ARGUMENT,

    /**
     * Represents an error where the service is temporarily overloaded or
     * unavailable.
     */
    UNAVAILABLE,

    /**
     * Represents an unexpected error that occurred on the server side.
     */
    INTERNAL,

    /**
     * Represents an error where the API key doesn't have sufficient
     * permissions.
     */
    PERMISSION_DENIED,

    /**
     * Represents an error where a precondition for the request is not met.
     */
    FAILED_PRECONDITION,

    /**
     * Represents an error where the rate limit for the API is exceeded.
     */
    RESOURCE_EXHAUSTED,

    /**
     * Represents an unknown or unspecified error type.
     */
    UNKNOWN
}