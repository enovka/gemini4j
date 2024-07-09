package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.ContentEmbedding;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The response to an EmbedContentRequest.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbedContentResponse {

    /**
     * Output only. The embedding generated from the input content.
     */
    @JsonProperty("embedding")
    private ContentEmbedding embedding;

}