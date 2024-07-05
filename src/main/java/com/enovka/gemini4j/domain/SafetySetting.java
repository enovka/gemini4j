package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Safety setting, affecting the safety-blocking behavior.
 * <p>
 * Passing a safety setting for a category changes the allowed probability that
 * content is blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SafetySetting {

  /**
   * Required. The category for this setting.
   */
  @JsonProperty("category")
  private HarmCategory category;

  /**
   * Required. Controls the probability threshold at which harm is blocked.
   */
  @JsonProperty("threshold")
  private HarmBlockThreshold threshold;

}