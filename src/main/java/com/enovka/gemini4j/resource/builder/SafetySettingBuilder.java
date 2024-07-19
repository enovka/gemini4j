package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.SafetySetting;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;

/**
 * Builder class for constructing {@link SafetySetting} objects.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class SafetySettingBuilder {

    private final GenerateContentRequestBuilder generateContentRequestBuilder;
    public HarmBlockThresholdEnum threshold;
    private HarmCategoryEnum category;

    /**
     * Constructor for the SafetySettingBuilder.
     *
     * @param generateContentRequestBuilder The parent
     * GenerateContentRequestBuilder instance.
     */
    public SafetySettingBuilder(
            GenerateContentRequestBuilder generateContentRequestBuilder) {
        this.generateContentRequestBuilder = generateContentRequestBuilder;
    }

    /**
     * Sets the harm category for the safety setting.
     *
     * @param category The harm category enum.
     * @return The builder instance for method chaining.
     */
    public SafetySettingBuilder withCategory(HarmCategoryEnum category) {
        this.category = category;
        return this;
    }

    /**
     * Sets the harm block threshold for the safety setting.
     *
     * @param threshold The harm block threshold enum.
     * @return The builder instance for method chaining.
     */
    public SafetySettingBuilder withThreshold(
            HarmBlockThresholdEnum threshold) {
        this.threshold = threshold;
        return this;
    }

    /**
     * Builds a {@link SafetySetting} instance based on the configured
     * parameters.
     *
     * @return The built {@link SafetySetting} instance.
     */
    public SafetySetting build() {
        if (category == null) {
            throw new IllegalArgumentException("Harm category is required.");
        }
        if (threshold == null) {
            throw new IllegalArgumentException(
                    "Harm block threshold is required.");
        }
        return new SafetySetting(category, threshold);
    }

    /**
     * Adds the built {@link SafetySetting} instance to the parent builder and
     * returns to the parent builder for continued chaining.
     *
     * @return The parent GenerateContentRequestBuilder instance for method
     * chaining.
     */
    public GenerateContentRequestBuilder and() {
        generateContentRequestBuilder.withSafetySetting(build());
        return generateContentRequestBuilder;
    }
}