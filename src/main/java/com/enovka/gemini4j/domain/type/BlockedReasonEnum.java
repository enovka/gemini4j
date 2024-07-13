package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Lists the reasons why content may have been blocked during processing.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum BlockedReasonEnum {

    /**
     * The reason for blocking the content is unspecified.
     */
    @JsonProperty("BLOCKED_REASON_UNSPECIFIED")
    BLOCKED_REASON_UNSPECIFIED,

    /**
     * The content was blocked due to safety concerns.
     */
    @JsonProperty("SAFETY")
    SAFETY,

    /**
     * The content was blocked for reasons other than safety.
     */
    @JsonProperty("OTHER")
    OTHER
}