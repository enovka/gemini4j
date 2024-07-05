package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * A list of reasons why content may have been blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum BlockedReasonEnum {

  @JsonProperty("BLOCKED_REASON_UNSPECIFIED")
  BLOCKED_REASON_UNSPECIFIED,

  @JsonProperty("SAFETY")
  SAFETY,

  @JsonProperty("OTHER")
  OTHER
}