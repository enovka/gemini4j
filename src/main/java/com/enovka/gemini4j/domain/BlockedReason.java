package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.BlockedReasonEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the reason why a piece of content was blocked by the Gemini API's
 * content filtering system. This class provides a structured representation of
 * the block reason, making it easier to understand why content was flagged as
 * inappropriate.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BlockedReason {

    /**
     * The specific reason why the content was blocked, as indicated by the
     * {@link BlockedReasonEnum}. For example, a value of
     * {@link BlockedReasonEnum#SAFETY} indicates that the content was blocked
     * due to safety settings.
     */
    @JsonProperty("blockedReason")
    private BlockedReasonEnum blockedReason;
}