package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.Embedding;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * The response to an {@link EmbedContentRequest}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
@NoArgsConstructor
public class EmbedContentResponse extends AbstractGeminiResponse {

    /**
     * Output only. The embedding generated from the input content.
     */
    @JsonProperty("embedding")
    private Embedding embedding;

    public EmbedContentResponse(
            @JsonProperty("embedding") Embedding embedding) {
        this.embedding = embedding;
    }
}