package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * The response to an EmbedContentRequest.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbedContentResponse {

  /**
   * Output only. The embedding generated from the input content.
   */
  @JsonProperty("embedding")
  private ContentEmbedding embedding;

}