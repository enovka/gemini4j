package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * A set of the feedback metadata the prompt specified in
 * GenerateContentRequest.content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromptFeedback {

  /**
   * Optional. If set, the prompt was blocked and no candidates are returned.
   * Rephrase your prompt.
   */
  @JsonProperty("blockReason")
  private BlockReason blockReason;

  /**
   * Ratings for safety of the prompt. There is at most one rating per category.
   */
  @JsonProperty("safetyRatings")
  private List<SafetyRating> safetyRatings;

}