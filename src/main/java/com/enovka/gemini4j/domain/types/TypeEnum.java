package com.enovka.gemini4j.domain.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Enumerates the supported OpenAPI data types, as defined by
 * <a href="https://spec.openapis.org/oas/v3.0.3#data-types">OpenAPI Specification v3.0.3</a>.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum TypeEnum {

  /**
   * The data type is unspecified. This value should not be used.
   */
  @JsonProperty("TYPE_UNSPECIFIED")
  TYPE_UNSPECIFIED,

  /**
   * A string data type.
   */
  @JsonProperty("STRING")
  STRING,

  /**
   * A numeric data type.
   */
  @JsonProperty("NUMBER")
  NUMBER,

  /**
   * An integer data type.
   */
  @JsonProperty("INTEGER")
  INTEGER,

  /**
   * A boolean data type.
   */
  @JsonProperty("BOOLEAN")
  BOOLEAN,

  /**
   * An array data type.
   */
  @JsonProperty("ARRAY")
  ARRAY,

  /**
   * An object data type.
   */
  @JsonProperty("OBJECT")
  OBJECT
}