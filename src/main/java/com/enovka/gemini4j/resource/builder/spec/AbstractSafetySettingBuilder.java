package com.enovka.gemini4j.resource.builder.spec;

import com.enovka.gemini4j.domain.SafetySetting;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;

/**
 * Specialized abstract class for builders handling {@link SafetySetting}
 * objects, providing methods for setting harm category and threshold.
 *
 * @param <T> The type of object this builder creates.
 * @param <B> The type of the parent builder.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractSafetySettingBuilder<T extends SafetySetting, B extends AbstractBuilder<?>>
        extends AbstractComplexBuilder<T, B> {

    protected HarmCategoryEnum category;
    protected HarmBlockThresholdEnum threshold;

    /**
     * Constructor for the AbstractSafetySettingBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractSafetySettingBuilder(B parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the harm category for the safety setting.
     *
     * @param category The harm category enum.
     * @return The builder instance for method chaining.
     */
    public AbstractSafetySettingBuilder<T, B> withCategory(
            HarmCategoryEnum category) {
        this.category = category;
        return this;
    }

    /**
     * Sets the harm block threshold for the safety setting.
     *
     * @param threshold The harm block threshold enum.
     * @return The builder instance for method chaining.
     */
    public AbstractSafetySettingBuilder<T, B> withThreshold(
            HarmBlockThresholdEnum threshold) {
        this.threshold = threshold;
        return this;
    }

    /**
     * Returns to the parent builder after configuring the safety setting.
     *
     * @return The parent builder instance for method chaining.
     */
    public B and() {
        return parentBuilder;
    }
}