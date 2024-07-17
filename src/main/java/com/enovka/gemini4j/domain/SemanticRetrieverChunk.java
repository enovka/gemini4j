package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Identifier for a Chunk retrieved via Semantic Retriever specified in the
 * GenerateAnswerRequest using SemanticRetrieverConfig.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class SemanticRetrieverChunk {

    /**
     * Output only. Name of the source matching the request's
     * SemanticRetrieverConfig.source. Example: `corpora/123` or
     * `corpora/123/documents/abc`
     */
    @JsonProperty("source")
    private String source;

    /**
     * Output only. Name of the Chunk containing the attributed text. Example:
     * `corpora/123/documents/abc/chunks/xyz`
     */
    @JsonProperty("chunk")
    private String chunk;

}