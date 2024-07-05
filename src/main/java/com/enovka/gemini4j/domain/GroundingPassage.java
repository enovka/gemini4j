package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Passage included inline with a grounding configuration.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroundingPassage {

  /**
   * Identifier for the passage for attributing this passage in grounded
   * answers.
   */
  @JsonProperty("id")
  private String id;

  /**
   * Content of the passage.
   */
  @JsonProperty("content")
  private Content content;

}