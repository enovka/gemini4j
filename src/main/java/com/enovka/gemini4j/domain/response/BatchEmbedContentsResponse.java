package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.ContentEmbedding;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * The response to a BatchEmbedContentsRequest.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchEmbedContentsResponse {

  /**
   * Output only. The embeddings for each request, in the same order as provided
   * in the batch request.
   */
  @JsonProperty("embeddings")
  private List<ContentEmbedding> embeddings;

}