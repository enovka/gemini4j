package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.OperatorEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Defines the valid operators that can be applied to a key-value pair.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Operator {

    /**
     * The default value. This value is unused.
     */
    @JsonProperty("OPERATOR_UNSPECIFIED")
    private com.enovka.gemini4j.domain.types.OperatorEnum operatorUnspecified;

    /**
     * Supported by numeric.
     */
    @JsonProperty("LESS")
    private com.enovka.gemini4j.domain.types.OperatorEnum less;

    /**
     * Supported by numeric.
     */
    @JsonProperty("LESS_EQUAL")
    private com.enovka.gemini4j.domain.types.OperatorEnum lessEqual;

    /**
     * Supported by numeric and string.
     */
    @JsonProperty("EQUAL")
    private com.enovka.gemini4j.domain.types.OperatorEnum equal;

    /**
     * Supported by numeric.
     */
    @JsonProperty("GREATER_EQUAL")
    private com.enovka.gemini4j.domain.types.OperatorEnum greaterEqual;

    /**
     * Supported by numeric.
     */
    @JsonProperty("GREATER")
    private OperatorEnum greater;

    /**
     * Supported by numeric and string.
     */
    @JsonProperty("NOT_EQUAL")
    private com.enovka.gemini4j.domain.types.OperatorEnum notEqual;

    /**
     * Supported by string only when CustomMetadata value type for the given key
     * has a stringListValue.
     */
    @JsonProperty("INCLUDES")
    private com.enovka.gemini4j.domain.types.OperatorEnum includes;

    /**
     * Supported by string only when CustomMetadata value type for the given key
     * has a stringListValue.
     */
    @JsonProperty("EXCLUDES")
    private com.enovka.gemini4j.domain.types.OperatorEnum excludes;

}