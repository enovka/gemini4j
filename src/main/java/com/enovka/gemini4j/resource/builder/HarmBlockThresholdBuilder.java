package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.HarmBlockThreshold;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.resource.builder.spec.AbstractComplexBuilder;

/**
 * Builder for creating {@link HarmBlockThreshold} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class HarmBlockThresholdBuilder extends
        AbstractComplexBuilder<HarmBlockThreshold, SafetySettingBuilder> {

    private HarmBlockThresholdEnum harmBlockThreshold;

    /**
     * Constructor for the HarmBlockThresholdBuilder.
     *
     * @param parentBuilder The parent {@link SafetySettingBuilder} instance.
     */
    public HarmBlockThresholdBuilder(SafetySettingBuilder parentBuilder) {
        super(parentBuilder);
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
     * {@inheritDoc}
     */
    @Override
    public HarmBlockThreshold build() {
        return HarmBlockThreshold.builder()
                .withHarmBlockThreshold(harmBlockThreshold)
                .build();
    }
}