package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * States for the lifecycle of a Chunk.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum StateEnum {

  @JsonProperty("STATE_UNSPECIFIED")
  STATE_UNSPECIFIED,

  @JsonProperty("STATE_PENDING_PROCESSING")
  STATE_PENDING_PROCESSING,

  @JsonProperty("STATE_ACTIVE")
  STATE_ACTIVE,

  @JsonProperty("STATE_FAILED")
  STATE_FAILED
}