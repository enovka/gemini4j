package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.BlockReasonEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Specifies what was the reason why input was blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@AllArgsConstructor
@Getter
public class BlockReason {

    private BlockReasonEnum value;

    /**
     * Creates a BlockReason from a string value.
     *
     * @param value The string representation of the block reason.
     * @return The corresponding BlockReasonEnum value.
     */
    @JsonCreator
    public static BlockReason fromValue(String value) {
        return new BlockReason(BlockReasonEnum.valueOf(value.toUpperCase()));
    }

    /**
     * Returns the string representation of the block reason.
     *
     * @return The string representation of the block reason.
     */
    @JsonValue
    public String getValue() {
        return value.name();
    }
}