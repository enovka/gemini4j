package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Defines the valid operators that can be applied to a key-value pair.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Operator {

  /**
   * The default value. This value is unused.
   */
  @JsonProperty("OPERATOR_UNSPECIFIED")
  private OperatorEnum operatorUnspecified;

  /**
   * Supported by numeric.
   */
  @JsonProperty("LESS")
  private OperatorEnum less;

  /**
   * Supported by numeric.
   */
  @JsonProperty("LESS_EQUAL")
  private OperatorEnum lessEqual;

  /**
   * Supported by numeric & string.
   */
  @JsonProperty("EQUAL")
  private OperatorEnum equal;

  /**
   * Supported by numeric.
   */
  @JsonProperty("GREATER_EQUAL")
  private OperatorEnum greaterEqual;

  /**
   * Supported by numeric.
   */
  @JsonProperty("GREATER")
  private OperatorEnum greater;

  /**
   * Supported by numeric & string.
   */
  @JsonProperty("NOT_EQUAL")
  private OperatorEnum notEqual;

  /**
   * Supported by string only when CustomMetadata value type for the given key
   * has a stringListValue.
   */
  @JsonProperty("INCLUDES")
  private OperatorEnum includes;

  /**
   * Supported by string only when CustomMetadata value type for the given key
   * has a stringListValue.
   */
  @JsonProperty("EXCLUDES")
  private OperatorEnum excludes;

}