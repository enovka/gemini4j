package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Type contains the list of OpenAPI data types as defined by
 * <a href="https://spec.openapis.org/oas/v3.0.3#data-types">...</a>
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum TypeEnum {

  @JsonProperty("TYPE_UNSPECIFIED")
  TYPE_UNSPECIFIED,

  @JsonProperty("STRING")
  STRING,

  @JsonProperty("NUMBER")
  NUMBER,

  @JsonProperty("INTEGER")
  INTEGER,

  @JsonProperty("BOOLEAN")
  BOOLEAN,

  @JsonProperty("ARRAY")
  ARRAY,

  @JsonProperty("OBJECT")
  OBJECT
}