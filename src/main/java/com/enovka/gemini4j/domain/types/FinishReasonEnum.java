package com.enovka.gemini4j.domain.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the possible reasons why the model stopped generating tokens.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum FinishReasonEnum {

    /**
     * The reason for stopping token generation is unspecified.
     */
    @JsonProperty("FINISH_REASON_UNSPECIFIED")
    FINISH_REASON_UNSPECIFIED,

    /**
     * The model reached a natural stopping point or encountered a provided stop
     * sequence.
     */
    @JsonProperty("STOP")
    STOP,

    /**
     * The model reached the maximum number of tokens specified in the request.
     */
    @JsonProperty("MAX_TOKENS")
    MAX_TOKENS,

    /**
     * The generated content was flagged for safety violations.
     */
    @JsonProperty("SAFETY")
    SAFETY,

    /**
     * The generated content was flagged for potential copyright violations
     * (recitation).
     */
    @JsonProperty("RECITATION")
    RECITATION,

    /**
     * The reason for stopping token generation is unknown.
     */
    @JsonProperty("OTHER")
    OTHER
}