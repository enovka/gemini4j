package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The free-form input text given to the model as a prompt.
 * <p>
 * Given a prompt, the model will generate a TextCompletion response it predicts
 * as the completion of the input text.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextPrompt {

  /**
   * Required. The free-form input text given to the model as a prompt.
   * <p>
   * Given a prompt, the model will generate a TextCompletion response it
   * predicts as the completion of the input text.
   */
  @JsonProperty("text")
  private String text;

  /**
   * Optional. Context text. This is used to provide context to the model,
   * which can help improve the quality of the generated text.
   */
  @JsonProperty("context")
  private String context;

}