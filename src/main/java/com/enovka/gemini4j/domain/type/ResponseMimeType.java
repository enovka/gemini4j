package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines common MIME types used in the Gemini API for specifying the format of
 * content and responses.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.0
 */
@Getter
public enum ResponseMimeType {
    /**
     * Represents plain text content.
     */
    @JsonProperty("text/plain")
    TEXT_PLAIN("text/plain"),

    /**
     * Represents JSON formatted content.
     */
    @JsonProperty("application/json")
    APPLICATION_JSON("application/json");

    /**
     * The string representation of the MIME type.
     */
    private final String mimeType;

    ResponseMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}