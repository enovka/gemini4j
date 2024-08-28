package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Represents a response from the `models.countTokens` endpoint in the Gemini
 * API. It provides the token count for the given input, including the prompt,
 * system instructions, and function declarations if a
 * {@link GenerateContentRequest} is provided.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
@NoArgsConstructor
public class CountTokensResponse extends AbstractGeminiResponse {

    /**
     * The total number of tokens in the input, as determined by the Gemini
     * model's tokenizer. This count includes the tokens from the prompt, system
     * instructions, and function declarations if a
     * {@link GenerateContentRequest} is provided.
     * <p>
     * This value is always non-negative. When `cachedContent` is set in the
     * request, this count still represents the total effective prompt size,
     * including the tokens in the cached content.
     */
    @JsonProperty("totalTokens")
    private Integer totalTokens;
}