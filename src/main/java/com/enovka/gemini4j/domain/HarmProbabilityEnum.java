package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * The probability that a piece of content is harmful.
 * <p>
 * The classification system gives the probability of the content being unsafe.
 * This does not indicate the severity of harm for a piece of content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum HarmProbabilityEnum {

  @JsonProperty("HARM_PROBABILITY_UNSPECIFIED")
  HARM_PROBABILITY_UNSPECIFIED,

  @JsonProperty("NEGLIGIBLE")
  NEGLIGIBLE,

  @JsonProperty("LOW")
  LOW,

  @JsonProperty("MEDIUM")
  MEDIUM,

  @JsonProperty("HIGH")
  HIGH;
}