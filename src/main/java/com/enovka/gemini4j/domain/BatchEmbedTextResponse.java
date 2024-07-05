package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The response to an EmbedTextRequest.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchEmbedTextResponse {

  /**
   * Output only. The embeddings generated from the input text.
   */
  @JsonProperty("embeddings")
  private List<Embedding> embeddings;

}