package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Safety rating for a piece of content.
 * <p>
 * The safety rating contains the category of harm and the harm probability
 * level in that category for a piece of content. Content is classified for
 * safety across a number of harm categories, and the probability of the harm
 * classification is included here.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SafetyRating {

  /**
   * Required. The category for this rating.
   */
  @JsonProperty("category")
  private HarmCategory category;

  /**
   * Required. The probability of harm for this content.
   */
  @JsonProperty("probability")
  private HarmProbability probability;

  /**
   * Was this content blocked because of this rating?
   */
  @JsonProperty("blocked")
  private Boolean blocked;

}