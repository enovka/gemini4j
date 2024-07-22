package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.Embedding;
import com.enovka.gemini4j.domain.request.BatchEmbedContentsRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * The response to a {@link BatchEmbedContentsRequest}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)

public class BatchEmbedContentsResponse extends AbstractGeminiResponse {

    /**
     * Output only. The embeddings for each request, in the same order as
     * provided in the batch request.
     */
    @JsonProperty("embeddings")
    private List<Embedding> embeddings;

    public BatchEmbedContentsResponse(
            @JsonProperty("embeddings") List<Embedding> embeddings) {
        super();
        this.embeddings = embeddings;
    }
}