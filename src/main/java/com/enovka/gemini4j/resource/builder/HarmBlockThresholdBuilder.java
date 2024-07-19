package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.HarmBlockThreshold;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;

/**
 * Builder for creating {@link HarmBlockThreshold} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class HarmBlockThresholdBuilder {

    private final SafetySettingBuilder safetySettingBuilder;
    private HarmBlockThresholdEnum harmBlockThreshold;

    /**
     * Constructor for the HarmBlockThresholdBuilder.
     *
     * @param safetySettingBuilder The parent SafetySettingBuilder instance.
     */
    public HarmBlockThresholdBuilder(
            SafetySettingBuilder safetySettingBuilder) {
        this.safetySettingBuilder = safetySettingBuilder;
    }

    /**
     * Sets the harm block threshold enum for the harm block threshold.
     *
     * @param harmBlockThreshold The harm block threshold enum.
     * @return The builder instance for method chaining.
     */
    public HarmBlockThresholdBuilder withHarmBlockThreshold(
            HarmBlockThresholdEnum harmBlockThreshold) {
        if (harmBlockThreshold == null) {
            throw new IllegalArgumentException(
                    "Harm block threshold enum is required.");
        }
        this.harmBlockThreshold = harmBlockThreshold;
        return this;
    }

    /**
     * Builds a {@link HarmBlockThreshold} instance based on the configured
     * parameters.
     *
     * @return The built {@link HarmBlockThreshold} instance.
     */
    public HarmBlockThreshold build() {
        return HarmBlockThreshold.builder()
                .withHarmBlockThreshold(harmBlockThreshold)
                .build();
    }

    /**
     * Sets the built {@link HarmBlockThreshold} instance as the threshold in
     * the parent builder.
     *
     * @return The parent {@link SafetySettingBuilder} instance for method
     * chaining.
     */
    public SafetySettingBuilder and() {
        safetySettingBuilder.threshold = build().getHarmBlockThreshold();
        return safetySettingBuilder;
    }
}