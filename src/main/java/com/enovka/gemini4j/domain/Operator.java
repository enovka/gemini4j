package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.OperatorEnum;
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
 * @author Everson Novka <enovka@gmail.com>
 */
@Data
@Builder(setterPrefix = "with")
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