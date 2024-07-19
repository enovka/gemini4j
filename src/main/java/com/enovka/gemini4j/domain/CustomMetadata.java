package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User provided metadata stored as key-value pairs.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class CustomMetadata {

    /**
     * Required. The key of the metadata to store.
     */
    @JsonProperty("key")
    private String key;

    /**
     * The string value of the metadata to store.
     */
    @JsonProperty("stringValue")
    private String stringValue;

    /**
     * The StringList value of the metadata to store.
     */
    @JsonProperty("stringListValue")
    private StringList stringListValue;

    /**
     * The numeric value of the metadata to store.
     */
    @JsonProperty("numericValue")
    private Double numericValue;

}