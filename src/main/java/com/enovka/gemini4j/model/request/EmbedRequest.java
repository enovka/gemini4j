package com.enovka.gemini4j.model.request;

import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.request.spec.AbstractContentRequest;
import com.enovka.gemini4j.model.request.spec.AbstractEmbedRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Request containing the {@link Content} for the model to embed.  This class
 * now extends {@link AbstractContentRequest} and inherits common request
 * parameters, simplifying its structure and promoting code reuse.  It retains
 * fields specific to embedding requests, such as {@code taskType},
 * {@code title}, and {@code outputDimensionality}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class EmbedRequest extends AbstractEmbedRequest {
    /**
     * Required. The content of the request. This field typically contains
     * of {@link Content} object, representing the input data for the request.
     * Each {@link Content} object encapsulates the text, images, or other media
     * elements that form the request's input.  The structure and format of the
     * content should adhere to the Gemini API specifications for the specific
     * request type.
     */
    @JsonProperty("content")
    private Content content;

}