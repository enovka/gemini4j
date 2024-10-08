package com.enovka.gemini4j.model.response;

import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Represents a response from the `models.countTokens` endpoint in the Gemini
 * API. It provides the token count for the given input, including the prompt,
 * system instructions, and function declarations if a
 * {@link GenerateRequest} is provided.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class TokensResponse extends AbstractResponse {

    /**
     * The total number of tokens in the input, as determined by the Gemini
     * model's tokenizer. This count includes the tokens from the prompt, system
     * instructions, and function declarations if a
     * {@link GenerateRequest} is provided.
     * <p>
     * This value is always non-negative. When `cachedContent` is set in the
     * request, this count still represents the total effective prompt size,
     * including the tokens in the cached content.
     */
    @JsonProperty("totalTokens")
    private Integer totalTokens;
}