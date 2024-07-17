package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The information for a chunk relevant to a query.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class RelevantChunk {

    /**
     * Chunk relevance to the query.
     */
    @JsonProperty("chunkRelevanceScore")
    private Double chunkRelevanceScore;

    /**
     * Chunk associated with the query.
     */
    @JsonProperty("chunk")
    private Chunk chunk;

}