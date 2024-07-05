package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Identifier for a part within a GroundingPassage.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroundingPassageId {

  /**
   * Output only. ID of the passage matching the GenerateAnswerRequest's
   * GroundingPassage.id.
   */
  @JsonProperty("passageId")
  private String passageId;

  /**
   * Output only. Index of the part within the GenerateAnswerRequest's
   * GroundingPassage.content.
   */
  @JsonProperty("partIndex")
  private Integer partIndex;

}