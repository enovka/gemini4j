package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Identifier for the source contributing to this attribution.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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