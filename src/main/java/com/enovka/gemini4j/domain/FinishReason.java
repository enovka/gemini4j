package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.FinishReasonEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Defines the reason why the model stopped generating tokens.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinishReason {

  /**
   * Default value. This value is unused.
   */
  @JsonProperty("FINISH_REASON_UNSPECIFIED")
  private com.enovka.gemini4j.domain.types.FinishReasonEnum
          finishReasonUnspecified;

  /**
   * Natural stop point of the model or provided a stop sequence.
   */
  @JsonProperty("STOP")
  private FinishReasonEnum stop;

  /**
   * The maximum number of tokens as specified in the request was reached.
   */
  @JsonProperty("MAX_TOKENS")
  private com.enovka.gemini4j.domain.types.FinishReasonEnum maxTokens;

  /**
   * The candidate content was flagged for safety reasons.
   */
  @JsonProperty("SAFETY")
  private com.enovka.gemini4j.domain.types.FinishReasonEnum safety;

  /**
   * The candidate content was flagged for recitation reasons.
   */
  @JsonProperty("RECITATION")
  private com.enovka.gemini4j.domain.types.FinishReasonEnum recitation;

  /**
   * Unknown reason.
   */
  @JsonProperty("OTHER")
  private com.enovka.gemini4j.domain.types.FinishReasonEnum other;

}