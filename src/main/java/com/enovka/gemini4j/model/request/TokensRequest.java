package com.enovka.gemini4j.model.request;

import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.request.spec.AbstractSimpleRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Request for counting the number of tokens in text and other types of content
 * using a specified Gemini model. This class now extends
 * {@link AbstractSimpleRequest}, inheriting the common 'model' parameter and
 * simplifying its structure.
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
public class TokensRequest extends AbstractSimpleRequest {

    @JsonProperty("generateContentRequest")
    private GenerateRequest generateRequest;

    /**
     * Optional. The input given to the model as a prompt.  This field contains
     * the content for which the token count is requested.  It can be used for
     * standalone token counting or within the context of a GenerateRequest.
     */
    @Singular
    @JsonProperty("contents")
    private List<Content> contents;
}