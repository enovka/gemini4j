package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.HarmProbabilityEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The probability that a piece of content is harmful.
 * <p>
 * The classification system gives the probability of the content being unsafe.
 * This does not indicate the severity of harm for a piece of content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HarmProbability {

  /**
   * The Probability is unspecified.
   */
  @JsonProperty("HARM_PROBABILITY_UNSPECIFIED")
  private com.enovka.gemini4j.domain.types.HarmProbabilityEnum
          harmProbabilityUnspecified;

  /**
   * Content has a negligible chance of being unsafe.
   */
  @JsonProperty("NEGLIGIBLE")
  private com.enovka.gemini4j.domain.types.HarmProbabilityEnum negligible;

  /**
   * Content has a low chance of being unsafe.
   */
  @JsonProperty("LOW")
  private HarmProbabilityEnum low;

  /**
   * Content has a medium chance of being unsafe.
   */
  @JsonProperty("MEDIUM")
  private com.enovka.gemini4j.domain.types.HarmProbabilityEnum medium;

  /**
   * Content has a high chance of being unsafe.
   */
  @JsonProperty("HIGH")
  private com.enovka.gemini4j.domain.types.HarmProbabilityEnum high;

}