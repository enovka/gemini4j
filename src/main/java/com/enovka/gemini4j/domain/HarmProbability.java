package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * The probability that a piece of content is harmful.
 * <p>
 * The classification system gives the probability of the content being unsafe.
 * This does not indicate the severity of harm for a piece of content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HarmProbability {

  /**
   * The Probability is unspecified.
   */
  @JsonProperty("HARM_PROBABILITY_UNSPECIFIED")
  private HarmProbabilityEnum harmProbabilityUnspecified;

  /**
   * Content has a negligible chance of being unsafe.
   */
  @JsonProperty("NEGLIGIBLE")
  private HarmProbabilityEnum negligible;

  /**
   * Content has a low chance of being unsafe.
   */
  @JsonProperty("LOW")
  private HarmProbabilityEnum low;

  /**
   * Content has a medium chance of being unsafe.
   */
  @JsonProperty("MEDIUM")
  private HarmProbabilityEnum medium;

  /**
   * Content has a high chance of being unsafe.
   */
  @JsonProperty("HIGH")
  private HarmProbabilityEnum high;

}