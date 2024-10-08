package com.enovka.gemini4j.model.response;

import com.enovka.gemini4j.model.Embedding;
import com.enovka.gemini4j.model.request.BatchEmbedRequest;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * The response to a {@link BatchEmbedRequest}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
public class BatchEmbedResponse extends AbstractResponse {

    /**
     * Output only. The embeddings for each request, in the same order as
     * provided in the batch request.
     */
    @JsonProperty("embeddings")
    private List<Embedding> embeddings;

    public BatchEmbedResponse(
            @JsonProperty("embeddings") List<Embedding> embeddings) {
        super();
        this.embeddings = embeddings;
    }
}