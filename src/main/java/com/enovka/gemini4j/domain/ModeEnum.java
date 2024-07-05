package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the execution behavior for function calling by defining the
 * execution mode.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum ModeEnum {

  @JsonProperty("MODE_UNSPECIFIED")
  MODE_UNSPECIFIED,

  @JsonProperty("AUTO")
  AUTO,

  @JsonProperty("ANY")
  ANY,

  @JsonProperty("NONE")
  NONE
}