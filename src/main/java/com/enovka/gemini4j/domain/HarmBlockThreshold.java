package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.HarmBlockThresholdEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Block at and beyond a specified harm probability.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HarmBlockThreshold {

  /**
   * The Threshold is unspecified.
   */
  @JsonProperty("HARM_BLOCK_THRESHOLD_UNSPECIFIED")
  private com.enovka.gemini4j.domain.types.HarmBlockThresholdEnum
          harmBlockThresholdUnspecified;

  /**
   * Content with NEGLIGIBLE will be allowed.
   */
  @JsonProperty("BLOCK_LOW_AND_ABOVE")
  private HarmBlockThresholdEnum blockLowAndAbove;

  /**
   * Content with NEGLIGIBLE and LOW will be allowed.
   */
  @JsonProperty("BLOCK_MEDIUM_AND_ABOVE")
  private com.enovka.gemini4j.domain.types.HarmBlockThresholdEnum
          blockMediumAndAbove;

  /**
   * Content with NEGLIGIBLE, LOW, and MEDIUM will be allowed.
   */
  @JsonProperty("BLOCK_ONLY_HIGH")
  private com.enovka.gemini4j.domain.types.HarmBlockThresholdEnum blockOnlyHigh;

  /**
   * All content will be allowed.
   */
  @JsonProperty("BLOCK_NONE")
  private com.enovka.gemini4j.domain.types.HarmBlockThresholdEnum blockNone;

}