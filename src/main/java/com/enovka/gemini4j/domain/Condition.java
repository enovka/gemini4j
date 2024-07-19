package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Filter condition applicable to a single key.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Condition {

    /**
     * Required. Operator applied to the given key-value pair to trigger the
     * condition.
     */
    @JsonProperty("operation")
    private Operator operation;

    /**
     * The string value to filter the metadata on.
     */
    @JsonProperty("stringValue")
    private String stringValue;

    /**
     * The numeric value to filter the metadata on.
     */
    @JsonProperty("numericValue")
    private Double numericValue;

}