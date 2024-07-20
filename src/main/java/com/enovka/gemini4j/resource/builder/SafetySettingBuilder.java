package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.SafetySetting;
import com.enovka.gemini4j.resource.builder.spec.AbstractRequestBuilder;
import com.enovka.gemini4j.resource.builder.spec.AbstractSafetySettingBuilder;

/**
 * Builder class for constructing {@link SafetySetting} objects.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class SafetySettingBuilder extends
        AbstractSafetySettingBuilder<SafetySetting, AbstractRequestBuilder<?>> {

    /**
     * Constructor for the SafetySettingBuilder.
     *
     * @param parentBuilder The parent {@link AbstractRequestBuilder} instance.
     */
    public SafetySettingBuilder(AbstractRequestBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * Returns to the parent {@link AbstractRequestBuilder} after configuring
     * the safety setting.
     *
     * @return The parent {@link AbstractRequestBuilder} instance for method
     * chaining.
     */
    public AbstractRequestBuilder<?> and() {
        parentBuilder.withSafetySetting(build());
        return parentBuilder;
    }
}