package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Style for grounded answers.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerStyle {

  /**
   * Unspecified answer style.
   */
  @JsonProperty("ANSWER_STYLE_UNSPECIFIED")
  private AnswerStyleEnum answerStyleUnspecified;

  /**
   * Succinct but abstract style.
   */
  @JsonProperty("ABSTRACTIVE")
  private AnswerStyleEnum abstractive;

  /**
   * Very brief and extractive style.
   */
  @JsonProperty("EXTRACTIVE")
  private AnswerStyleEnum extractive;

  /**
   * Verbose style including extra details. The response may be formatted as a
   * sentence, paragraph, multiple paragraphs, or bullet points, etc.
   */
  @JsonProperty("VERBOSE")
  private AnswerStyleEnum verbose;

}