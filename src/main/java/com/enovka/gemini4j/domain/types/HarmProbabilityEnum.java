package com.enovka.gemini4j.domain.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represents the probability levels that content is classified with for each harm category.
 * <p>
 * The classification system assigns a probability to each piece of content for each harm category, indicating the likelihood of the content being harmful. This does not reflect the severity of the potential harm, only its probability.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum HarmProbabilityEnum {

  /**
   * The harm probability is unspecified.
   */
  @JsonProperty("HARM_PROBABILITY_UNSPECIFIED")
  HARM_PROBABILITY_UNSPECIFIED,

  /**
   * The content has a negligible chance of being harmful.
   */
  @JsonProperty("NEGLIGIBLE")
  NEGLIGIBLE,

  /**
   * The content has a low probability of being harmful.
   */
  @JsonProperty("LOW")
  LOW,

  /**
   * The content has a medium probability of being harmful.
   */
  @JsonProperty("MEDIUM")
  MEDIUM,

  /**
   * The content has a high probability of being harmful.
   */
  @JsonProperty("HIGH")
  HIGH
}