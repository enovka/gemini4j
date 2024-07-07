package com.enovka.gemini4j.domain.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the operators that can be applied to key-value pairs when filtering content based on metadata.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum OperatorEnum {
  /**
   * The operator is unspecified. This is the default value and should not be used.
   */
  @JsonProperty("OPERATOR_UNSPECIFIED")
  OPERATOR_UNSPECIFIED,

  /**
   * Less than. Only supported for numeric values.
   */
  @JsonProperty("LESS")
  LESS,

  /**
   * Less than or equal to. Only supported for numeric values.
   */
  @JsonProperty("LESS_EQUAL")
  LESS_EQUAL,

  /**
   * Equal to. Supported for both numeric and string values.
   */
  @JsonProperty("EQUAL")
  EQUAL,

  /**
   * Greater than or equal to. Only supported for numeric values.
   */
  @JsonProperty("GREATER_EQUAL")
  GREATER_EQUAL,

  /**
   * Greater than. Only supported for numeric values.
   */
  @JsonProperty("GREATER")
  GREATER,

  /**
   * Not equal to. Supported for both numeric and string values.
   */
  @JsonProperty("NOT_EQUAL")
  NOT_EQUAL,

  /**
   * Includes. Only supported for string values when the CustomMetadata value for the given key has a StringList value.
   */
  @JsonProperty("INCLUDES")
  INCLUDES,

  /**
   * Excludes. Only supported for string values when the CustomMetadata value for the given key has a StringList value.
   */
  @JsonProperty("EXCLUDES")
  EXCLUDES
}