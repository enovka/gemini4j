package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Style for grounded answers.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum AnswerStyleEnum {

  @JsonProperty("ANSWER_STYLE_UNSPECIFIED")
  ANSWER_STYLE_UNSPECIFIED,

  @JsonProperty("ABSTRACTIVE")
  ABSTRACTIVE,

  @JsonProperty("EXTRACTIVE")
  EXTRACTIVE,

  @JsonProperty("VERBOSE")
  VERBOSE
}