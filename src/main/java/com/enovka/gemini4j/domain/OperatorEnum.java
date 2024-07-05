package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the valid operators that can be applied to a key-value pair.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum OperatorEnum {

  @JsonProperty("OPERATOR_UNSPECIFIED")
  OPERATOR_UNSPECIFIED,

  @JsonProperty("LESS")
  LESS,

  @JsonProperty("LESS_EQUAL")
  LESS_EQUAL,

  @JsonProperty("EQUAL")
  EQUAL,

  @JsonProperty("GREATER_EQUAL")
  GREATER_EQUAL,

  @JsonProperty("GREATER")
  GREATER,

  @JsonProperty("NOT_EQUAL")
  NOT_EQUAL,

  @JsonProperty("INCLUDES")
  INCLUDES,

  @JsonProperty("EXCLUDES")
  EXCLUDES
}