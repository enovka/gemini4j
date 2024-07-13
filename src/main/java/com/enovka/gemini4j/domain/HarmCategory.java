package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.HarmCategoryEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The category of a rating.
 * <p>
 * These categories cover various kinds of harms that developers may wish to
 * adjust.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@AllArgsConstructor
@Getter
public class HarmCategory {

    private HarmCategoryEnum value;

    /**
     * Creates a HarmCategory from a string value.
     *
     * @param value The string representation of the harm category.
     * @return The corresponding HarmCategoryEnum value.
     */
    @JsonCreator
    public static HarmCategory fromValue(String value) {
        return new HarmCategory(HarmCategoryEnum.valueOf(value.toUpperCase()));
    }

    /**
     * Returns the string representation of the harm category.
     *
     * @return The string representation of the harm category.
     */
    @JsonValue
    public String getValue() {
        return value.name();
    }
}