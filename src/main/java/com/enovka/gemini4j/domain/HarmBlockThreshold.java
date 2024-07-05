package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Block at and beyond a specified harm probability.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HarmBlockThreshold {

  /**
   * The Threshold is unspecified.
   */
  @JsonProperty("HARM_BLOCK_THRESHOLD_UNSPECIFIED")
  private HarmBlockThresholdEnum harmBlockThresholdUnspecified;

  /**
   * Content with NEGLIGIBLE will be allowed.
   */
  @JsonProperty("BLOCK_LOW_AND_ABOVE")
  private HarmBlockThresholdEnum blockLowAndAbove;

  /**
   * Content with NEGLIGIBLE and LOW will be allowed.
   */
  @JsonProperty("BLOCK_MEDIUM_AND_ABOVE")
  private HarmBlockThresholdEnum blockMediumAndAbove;

  /**
   * Content with NEGLIGIBLE, LOW, and MEDIUM will be allowed.
   */
  @JsonProperty("BLOCK_ONLY_HIGH")
  private HarmBlockThresholdEnum blockOnlyHigh;

  /**
   * All content will be allowed.
   */
  @JsonProperty("BLOCK_NONE")
  private HarmBlockThresholdEnum blockNone;

}