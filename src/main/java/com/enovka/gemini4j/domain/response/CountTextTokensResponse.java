package com.enovka.gemini4j.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * A response from models.countTextTokens.
 * <p>
 * It returns the model's tokenCount for the prompt.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
public class CountTextTokensResponse extends AbstractGeminiResponse {

    /**
     * The number of tokens that the model tokenizes the prompt into.
     * <p>
     * Always non-negative.
     */
    @JsonProperty("tokenCount")
    private Integer tokenCount;

}