package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.FinishReasonEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Defines the reason why the model stopped generating tokens.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@AllArgsConstructor
@Getter
public class FinishReason {

    private FinishReasonEnum value;

    /**
     * Creates a FinishReason from a string value.
     *
     * @param value The string representation of the finish reason.
     * @return The corresponding FinishReasonEnum value.
     */
    @JsonCreator
    public static FinishReason fromValue(String value) {
        return new FinishReason(FinishReasonEnum.valueOf(value.toUpperCase()));
    }

    /**
     * Returns the string representation of the finish reason.
     *
     * @return The string representation of the finish reason.
     */
    @JsonValue
    public String getNameValue() {
        return value.name();
    }
}