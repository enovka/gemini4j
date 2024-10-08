package com.enovka.gemini4j.model.response;

import com.enovka.gemini4j.model.Embedding;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * The response to an {@link EmbedRequest}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Getter
@Setter
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class EmbedResponse extends AbstractResponse {
    /**
     * Output only. The embedding generated from the input content.
     */
    @JsonProperty("embedding")
    private Embedding embedding;

}