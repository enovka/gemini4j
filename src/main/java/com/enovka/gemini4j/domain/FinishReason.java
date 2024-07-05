package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Defines the reason why the model stopped generating tokens.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinishReason {

  /**
   * Default value. This value is unused.
   */
  @JsonProperty("FINISH_REASON_UNSPECIFIED")
  private FinishReasonEnum finishReasonUnspecified;

  /**
   * Natural stop point of the model or provided a stop sequence.
   */
  @JsonProperty("STOP")
  private FinishReasonEnum stop;

  /**
   * The maximum number of tokens as specified in the request was reached.
   */
  @JsonProperty("MAX_TOKENS")
  private FinishReasonEnum maxTokens;

  /**
   * The candidate content was flagged for safety reasons.
   */
  @JsonProperty("SAFETY")
  private FinishReasonEnum safety;

  /**
   * The candidate content was flagged for recitation reasons.
   */
  @JsonProperty("RECITATION")
  private FinishReasonEnum recitation;

  /**
   * Unknown reason.
   */
  @JsonProperty("OTHER")
  private FinishReasonEnum other;

}