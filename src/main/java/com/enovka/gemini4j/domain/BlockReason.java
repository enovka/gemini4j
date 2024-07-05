package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Specifies what was the reason why input was blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockReason {

  /**
   * Default value. This value is unused.
   */
  @JsonProperty("BLOCK_REASON_UNSPECIFIED")
  private BlockReasonEnum blockReasonUnspecified;

  /**
   * Input was blocked due to safety reasons. You can inspect safetyRatings to
   * understand which safety category blocked it.
   */
  @JsonProperty("SAFETY")
  private BlockReasonEnum safety;

  /**
   * Input was blocked due to other reasons.
   */
  @JsonProperty("OTHER")
  private BlockReasonEnum other;

}