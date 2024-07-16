package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.HarmProbabilityEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * The probability that a piece of content is harmful.
 * <p>
 * The classification system gives the probability of the content being unsafe.
 * This does not indicate the severity of harm for a piece of content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@AllArgsConstructor
@Getter
@Builder(setterPrefix = "with")
public class HarmProbability {

    private HarmProbabilityEnum value;

    /**
     * Creates a HarmProbability from a string value.
     *
     * @param value The string representation of the harm probability.
     * @return The corresponding HarmProbabilityEnum value.
     */
    @JsonCreator
    public static HarmProbability fromValue(String value) {
        return new HarmProbability(
                HarmProbabilityEnum.valueOf(value.toUpperCase()));
    }

    /**
     * Returns the string representation of the harm probability.
     *
     * @return The string representation of the harm probability.
     */
    @JsonValue
    public String getValueName() {
        return value.name();
    }
}