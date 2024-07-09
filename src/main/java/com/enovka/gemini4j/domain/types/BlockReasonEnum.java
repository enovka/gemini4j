package com.enovka.gemini4j.domain.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Specifies the reason why the prompt was blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum BlockReasonEnum {

    /**
     * The reason for blocking the prompt is unspecified.
     */
    @JsonProperty("BLOCK_REASON_UNSPECIFIED")
    BLOCK_REASON_UNSPECIFIED,

    /**
     * The prompt was blocked due to safety concerns.
     */
    @JsonProperty("SAFETY")
    SAFETY,

    /**
     * The prompt was blocked for reasons other than safety.
     */
    @JsonProperty("OTHER")
    OTHER
}