package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.Embedding;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * The response to an EmbedTextRequest.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class BatchEmbedTextResponse extends AbstractGeminiResponse {

    /**
     * Output only. The embeddings generated from the input text.
     */
    @JsonProperty("embeddings")
    private List<Embedding> embeddings;

}