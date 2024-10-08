package com.enovka.gemini4j.model;

import com.enovka.gemini4j.model.type.OperatorEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a comparison operator used in metadata filters for the Gemini API.
 * This class provides a structured way to specify the operator used to compare
 * a metadata key with a given value, enabling flexible filtering of content
 * based on metadata attributes.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Operator {

    /**
     * The specific comparison operator from the {@link OperatorEnum}. For
     * example, setting this to {@link OperatorEnum#EQUAL} indicates an equality
     * comparison.
     */
    @JsonProperty("operator")
    private OperatorEnum operator;
}