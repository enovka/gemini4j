package com.enovka.gemini4j.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A response from models.countMessageTokens.
 * <p>
 * It returns the model's tokenCount for the prompt.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with")
public class CountMessageTokensResponse {

    /**
     * The number of tokens that the model tokenizes the prompt into.
     * <p>
     * Always non-negative.
     */
    @JsonProperty("tokenCount")
    private Integer tokenCount;

}