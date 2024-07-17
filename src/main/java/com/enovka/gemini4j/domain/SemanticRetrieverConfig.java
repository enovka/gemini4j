package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Configuration for retrieving grounding content from a Corpus or Document
 * created using the Semantic Retriever API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class SemanticRetrieverConfig {

    /**
     * Required. Name of the resource for retrieval, e.g. `corpora/123` or
     * `corpora/123/documents/abc`.
     */
    @JsonProperty("source")
    private String source;

    /**
     * Required. Query to use for similarity matching Chunks in the given
     * resource.
     */
    @JsonProperty("query")
    private Content query;

    /**
     * Optional. Filters for selecting Documents and/or Chunks from the
     * resource.
     */
    @JsonProperty("metadataFilters")
    private List<MetadataFilter> metadataFilters;

    /**
     * Optional. Maximum number of relevant Chunks to retrieve.
     */
    @JsonProperty("maxChunksCount")
    private Integer maxChunksCount;

    /**
     * Optional. Minimum relevance score for retrieved relevant Chunks.
     */
    @JsonProperty("minimumRelevanceScore")
    private Double minimumRelevanceScore;

}