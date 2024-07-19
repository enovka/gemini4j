package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The response to an {@link EmbedContentRequest}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
public class EmbedContentResponse {

    /**
     * Output only. The embedding generated from the input content.
     */
    @JsonProperty("embedding")
    private ContentEmbedding embedding;

    public EmbedContentResponse(
            @JsonProperty("embedding") ContentEmbedding embedding) {
        this.embedding = embedding;
    }
}