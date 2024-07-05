package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * User provided metadata stored as key-value pairs.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomMetadata {

  /**
   * Required. The key of the metadata to store.
   */
  @JsonProperty("key")
  private String key;

  /**
   * The string value of the metadata to store.
   */
  @JsonProperty("stringValue")
  private String stringValue;

  /**
   * The StringList value of the metadata to store.
   */
  @JsonProperty("stringListValue")
  private StringList stringListValue;

  /**
   * The numeric value of the metadata to store.
   */
  @JsonProperty("numericValue")
  private Double numericValue;

}