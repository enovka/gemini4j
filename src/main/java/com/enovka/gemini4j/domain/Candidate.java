package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * A response candidate generated from the model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Candidate {

  /**
   * Output only. Generated content returned from the model.
   */
  @JsonProperty("content")
  private Content content;

  /**
   * Optional. Output only. The reason why the model stopped generating tokens.
   * <p>
   * If empty, the model has not stopped generating the tokens.
   */
  @JsonProperty("finishReason")
  private FinishReason finishReason;

  /**
   * List of ratings for the safety of a response candidate.
   * <p>
   * There is at most one rating per category.
   */
  @JsonProperty("safetyRatings")
  private List<SafetyRating> safetyRatings;

  /**
   * Output only. Citation information for a model-generated candidate.
   * <p>
   * This field may be populated with recitation information for any text
   * included in the content. These are passages that are "recited" from
   * copyrighted material in the foundational LLM's training data.
   */
  @JsonProperty("citationMetadata")
  private CitationMetadata citationMetadata;

  /**
   * Output only. Token count for this candidate.
   */
  @JsonProperty("tokenCount")
  private Integer tokenCount;

  /**
   * Output only. Attribution information for sources that contributed to a
   * grounded answer.
   * <p>
   * This field is populated for GenerateAnswer calls.
   */
  @JsonProperty("groundingAttributions")
  private List<GroundingAttribution> groundingAttributions;

  /**
   * Output only. Index of the candidate in the list of candidates.
   */
  @JsonProperty("index")
  private Integer index;

}