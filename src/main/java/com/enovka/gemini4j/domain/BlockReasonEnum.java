package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Specifies what was the reason why input was blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum BlockReasonEnum {

  @JsonProperty("BLOCK_REASON_UNSPECIFIED")
  BLOCK_REASON_UNSPECIFIED,

  @JsonProperty("SAFETY")
  SAFETY,

  @JsonProperty("OTHER")
  OTHER
}