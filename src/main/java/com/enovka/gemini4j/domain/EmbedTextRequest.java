package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Request to get a text embedding from the model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbedTextRequest {

  /**
   * Required. The model name to use with the format `model=models/{model}`.
   */
  @JsonProperty("model")
  private String model;

  /**
   * Optional. The free-form input text that the model will turn into an
   * embedding.
   */
  @JsonProperty("text")
  private String text;

}