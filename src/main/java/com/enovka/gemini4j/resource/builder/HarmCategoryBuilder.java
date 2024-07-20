package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.HarmCategory;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;
import com.enovka.gemini4j.resource.builder.spec.AbstractComplexBuilder;

/**
 * Builder for creating {@link HarmCategory} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class HarmCategoryBuilder extends
        AbstractComplexBuilder<HarmCategory, SafetySettingBuilder> {

    private HarmCategoryEnum harmCategory;

    /**
     * Constructor for the HarmCategoryBuilder.
     *
     * @param parentBuilder The parent {@link SafetySettingBuilder} instance.
     */
    public HarmCategoryBuilder(SafetySettingBuilder parentBuilder) {
        super(parentBuilder);
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
     * {@inheritDoc}
     */
    @Override
    public HarmCategory build() {
        return HarmCategory.builder()
                .withHarmCategory(harmCategory)
                .build();
    }
}