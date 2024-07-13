package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Block at and beyond a specified harm probability.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@AllArgsConstructor
@Getter
public class HarmBlockThreshold {

    private HarmBlockThresholdEnum value;

    /**
     * Creates a HarmBlockThreshold from a string value.
     *
     * @param value The string representation of the harm block threshold.
     * @return The corresponding HarmBlockThresholdEnum value.
     */
    @JsonCreator
    public static HarmBlockThreshold fromValue(String value) {
        return new HarmBlockThreshold(
                HarmBlockThresholdEnum.valueOf(value.toUpperCase()));
    }

    /**
     * Returns the string representation of the harm block threshold.
     *
     * @return The string representation of the harm block threshold.
     */
    @JsonValue
    public String getValue() {
        return value.name();
    }
}