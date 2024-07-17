package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.BlockReasonEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the reason why a user input or prompt was blocked by the Gemini
 * API. This class provides a structured representation of the block reason,
 * making it easy to understand why a request was not processed.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class BlockReason {

    /**
     * The specific reason for blocking the input, as indicated by the
     * {@link BlockReasonEnum}. For example, a value of
     * {@link BlockReasonEnum#SAFETY} indicates that the input was blocked due
     * to safety concerns.
     */
    @JsonProperty("blockReason")
    private BlockReasonEnum blockReason;
}