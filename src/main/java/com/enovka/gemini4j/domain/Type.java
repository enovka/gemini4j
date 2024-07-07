package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.TypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Type contains the list of OpenAPI data types as defined by
 * <a href="https://spec.openapis.org/oas/v3.0.3#data-types">...</a>
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Type {

  /**
   * Not specified, should not be used.
   */
  @JsonProperty("TYPE_UNSPECIFIED")
  private com.enovka.gemini4j.domain.types.TypeEnum typeUnspecified;

  /**
   * String type.
   */
  @JsonProperty("STRING")
  private TypeEnum string;

  /**
   * Number type.
   */
  @JsonProperty("NUMBER")
  private com.enovka.gemini4j.domain.types.TypeEnum number;

  /**
   * Integer type.
   */
  @JsonProperty("INTEGER")
  private com.enovka.gemini4j.domain.types.TypeEnum integer;

  /**
   * Boolean type.
   */
  @JsonProperty("BOOLEAN")
  private com.enovka.gemini4j.domain.types.TypeEnum booleanValue;

  /**
   * Array type.
   */
  @JsonProperty("ARRAY")
  private com.enovka.gemini4j.domain.types.TypeEnum array;

  /**
   * Object type.
   */
  @JsonProperty("OBJECT")
  private com.enovka.gemini4j.domain.types.TypeEnum object;

}