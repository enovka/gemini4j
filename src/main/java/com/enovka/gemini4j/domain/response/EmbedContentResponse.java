package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.ContentEmbedding;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The response to an EmbedContentRequest.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with")
public class EmbedContentResponse {

    /**
     * Output only. The embedding generated from the input content.
     */
    @JsonProperty("embedding")
    private ContentEmbedding embedding;

}