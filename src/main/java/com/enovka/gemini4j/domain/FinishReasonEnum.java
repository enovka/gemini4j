package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the reason why the model stopped generating tokens.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum FinishReasonEnum {

  @JsonProperty("FINISH_REASON_UNSPECIFIED")
  FINISH_REASON_UNSPECIFIED,

  @JsonProperty("STOP")
  STOP,

  @JsonProperty("MAX_TOKENS")
  MAX_TOKENS,

  @JsonProperty("SAFETY")
  SAFETY,

  @JsonProperty("RECITATION")
  RECITATION,

  @JsonProperty("OTHER")
  OTHER
}