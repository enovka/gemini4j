package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The response to a BatchEmbedContentsRequest.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchEmbedContentsResponse {

  /**
   * Output only. The embeddings for each request, in the same order as provided
   * in the batch request.
   */
  @JsonProperty("embeddings")
  private List<ContentEmbedding> embeddings;

}