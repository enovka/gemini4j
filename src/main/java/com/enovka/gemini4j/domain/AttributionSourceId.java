package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Identifier for the source contributing to this attribution.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributionSourceId {

  /**
   * Identifier for an inline passage.
   */
  @JsonProperty("groundingPassage")
  private GroundingPassageId groundingPassage;

  /**
   * Identifier for a Chunk fetched via Semantic Retriever.
   */
  @JsonProperty("semanticRetrieverChunk")
  private SemanticRetrieverChunk semanticRetrieverChunk;

}