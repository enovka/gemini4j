package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Block at and beyond a specified harm probability.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum HarmBlockThresholdEnum {

  @JsonProperty("HARM_BLOCK_THRESHOLD_UNSPECIFIED")
  HARM_BLOCK_THRESHOLD_UNSPECIFIED,

  @JsonProperty("BLOCK_LOW_AND_ABOVE")
  BLOCK_LOW_AND_ABOVE,

  @JsonProperty("BLOCK_MEDIUM_AND_ABOVE")
  BLOCK_MEDIUM_AND_ABOVE,

  @JsonProperty("BLOCK_ONLY_HIGH")
  BLOCK_ONLY_HIGH,

  @JsonProperty("BLOCK_NONE")
  BLOCK_NONE
}