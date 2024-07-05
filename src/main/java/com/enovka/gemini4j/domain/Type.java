package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Type contains the list of OpenAPI data types as defined by
 * <a href="https://spec.openapis.org/oas/v3.0.3#data-types">...</a>
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Type {

  /**
   * Not specified, should not be used.
   */
  @JsonProperty("TYPE_UNSPECIFIED")
  private TypeEnum typeUnspecified;

  /**
   * String type.
   */
  @JsonProperty("STRING")
  private TypeEnum string;

  /**
   * Number type.
   */
  @JsonProperty("NUMBER")
  private TypeEnum number;

  /**
   * Integer type.
   */
  @JsonProperty("INTEGER")
  private TypeEnum integer;

  /**
   * Boolean type.
   */
  @JsonProperty("BOOLEAN")
  private TypeEnum booleanValue;

  /**
   * Array type.
   */
  @JsonProperty("ARRAY")
  private TypeEnum array;

  /**
   * Object type.
   */
  @JsonProperty("OBJECT")
  private TypeEnum object;

}