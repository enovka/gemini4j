package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * A list of reasons why content may have been blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockedReason {

  /**
   * A blocked reason was not specified.
   */
  @JsonProperty("BLOCKED_REASON_UNSPECIFIED")
  private BlockedReasonEnum blockedReasonUnspecified;

  /**
   * Content was blocked by safety settings.
   */
  @JsonProperty("SAFETY")
  private BlockedReasonEnum safety;

  /**
   * Content was blocked, but the reason is uncategorized.
   */
  @JsonProperty("OTHER")
  private BlockedReasonEnum other;

}