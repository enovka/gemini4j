package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.HarmCategory;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;

/**
 * Builder for creating {@link HarmCategory} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class HarmCategoryBuilder {

    private final SafetySettingBuilder safetySettingBuilder;
    private HarmCategoryEnum harmCategory;

    /**
     * Constructor for the HarmCategoryBuilder.
     *
     * @param safetySettingBuilder The parent SafetySettingBuilder instance.
     */
    public HarmCategoryBuilder(SafetySettingBuilder safetySettingBuilder) {
        this.safetySettingBuilder = safetySettingBuilder;
    }

    /**
     * Sets the harm category enum for the harm category.
     *
     * @param harmCategory The harm category enum.
     * @return The builder instance for method chaining.
     */
    public HarmCategoryBuilder withHarmCategory(HarmCategoryEnum harmCategory) {
        if (harmCategory == null) {
            throw new IllegalArgumentException(
                    "Harm category enum is required.");
        }
        this.harmCategory = harmCategory;
        return this;
    }

    /**
     * Builds a {@link HarmCategory} instance based on the configured
     * parameters.
     *
     * @return The built {@link HarmCategory} instance.
     */
    public HarmCategory build() {
        return HarmCategory.builder()
                .withHarmCategory(harmCategory)
                .build();
    }

    /**
     * Sets the built {@link HarmCategory} instance as the category in the
     * parent builder.
     *
     * @return The parent {@link SafetySettingBuilder} instance for method
     * chaining.
     */
    public SafetySettingBuilder and() {
        safetySettingBuilder.withCategory(build().getHarmCategory());
        return safetySettingBuilder;
    }
}