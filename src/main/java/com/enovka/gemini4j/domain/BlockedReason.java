package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.BlockedReasonEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A list of reasons why content may have been blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockedReason {

    /**
     * A blocked reason was not specified.
     */
    @JsonProperty("BLOCKED_REASON_UNSPECIFIED")
    private BlockedReasonEnum blockedReasonUnspecified;

    /**
     * Content was blocked by safety settings.
     */
    @JsonProperty("SAFETY")
    private com.enovka.gemini4j.domain.types.BlockedReasonEnum safety;

    /**
     * Content was blocked, but the reason is uncategorized.
     */
    @JsonProperty("OTHER")
    private com.enovka.gemini4j.domain.types.BlockedReasonEnum other;

}