package com.enovka.gemini4j.domain.request;

import com.enovka.gemini4j.domain.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Request to generate a completion from the model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateContentRequest {

  /**
   * Required. The name of the Model to use for generating the completion.
   * <p>
   * Format: `name=models/{model}`.
   */
  @JsonProperty("model")
  private String model;

  /**
   * Required. The content of the current conversation with the model.
   * <p>
   * For single-turn queries, this is a single instance. For multi-turn queries,
   * this is a repeated field that contains conversation history + the latest
   * request.
   */
  @JsonProperty("contents")
  private List<Content> contents;

  /**
   * Optional. A list of Tools the model may use to generate the next response.
   * <p>
   * A Tool is a piece of code that enables the system to interact with external
   * systems to perform an action, or set of actions, outside knowledge and
   * scope of the model. The only supported tool is currently Function.
   */
  @JsonProperty("tools")
  private List<Tool> tools;

  /**
   * Optional. Tool configuration for any Tool specified in the request.
   */
  @JsonProperty("toolConfig")
  private ToolConfig toolConfig;

  /**
   * Optional. A list of unique SafetySetting instances for blocking unsafe
   * content.
   * <p>
   * This will be enforced on the GenerateContentRequest.contents and
   * GenerateContentResponse.candidates. There should not be more than one setting
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
   * Optional. Developer set system instruction. Currently, text only.
   */
  @JsonProperty("systemInstruction")
  private Content systemInstruction;

  /**
   * Optional. Configuration options for model generation and outputs.
   */
  @JsonProperty("generationConfig")
  private GenerationConfig generationConfig;

  /**
   * Optional. The name of the cached content used as context to serve the
   * prediction. Note: only used in explicit caching, where users can have control
   * over caching (e.g., what content to cache) and enjoy guaranteed cost
   * savings. Format: `cachedContents/{cachedContent}`
   */
  @JsonProperty("cachedContent")
  private String cachedContent;

}