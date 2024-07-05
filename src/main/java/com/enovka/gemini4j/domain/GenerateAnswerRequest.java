package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Request to generate a grounded answer from the model given an input
 * GenerateAnswerRequest.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateAnswerRequest {

  /**
   * Required. The content of the current conversation with the model. For
   * single-turn queries, this is a single question to answer. For multi-turn
   * queries, this is a repeated field that contains conversation history and
   * the last Content in the list containing the question.
   * <p>
   * Note: models.generateAnswer currently only supports queries in English.
   */
  @JsonProperty("contents")
  private List<Content> contents;

  /**
   * Required. Style in which answers should be returned.
   */
  @JsonProperty("answerStyle")
  private AnswerStyle answerStyle;

  /**
   * Optional. A list of unique SafetySetting instances for blocking unsafe
   * content.
   * <p>
   * This will be enforced on the GenerateAnswerRequest.contents and
   * GenerateAnswerResponse.candidate. There should not be more than one setting
   * for each SafetyCategory type. The API will block any contents and responses
   * that fail to meet the thresholds set by these settings. This list overrides
   * the default settings for each SafetyCategory specified in the
   * safetySettings. If there is no SafetySetting for a given SafetyCategory
   * provided in the list, the API will use the default safety setting for that
   * category. Harm categories HARM_CATEGORY_HATE_SPEECH,
   * HARM_CATEGORY_SEXUALLY_EXPLICIT, HARM_CATEGORY_DANGEROUS_CONTENT,
   * HARM_CATEGORY_HARASSMENT are supported.
   */
  @JsonProperty("safetySettings")
  private List<SafetySetting> safetySettings;

  /**
   * Passages provided inline with the request.
   */
  @JsonProperty("inlinePassages")
  private GroundingPassages inlinePassages;

  /**
   * Content retrieved from resources created via the Semantic Retriever API.
   */
  @JsonProperty("semanticRetriever")
  private SemanticRetrieverConfig semanticRetriever;

  /**
   * Optional. Controls the randomness of the output.
   * <p>
   * Values can range from [0.0,1.0], inclusive. A value closer to 1.0 will
   * produce responses that are more varied and creative, while a value closer to
   * 0.0 will typically result in more straightforward responses from the
   * model. A low temperature (~0.2) is usually recommended for
   * Attributed-Question-Answering use cases.
   */
  @JsonProperty("temperature")
  private Double temperature;

}