package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Mode;
import com.enovka.gemini4j.domain.type.ModeEnum;
import com.enovka.gemini4j.resource.builder.spec.AbstractComplexBuilder;

/**
 * Builder for creating {@link Mode} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class ModeBuilder extends
        AbstractComplexBuilder<Mode, FunctionCallingConfigBuilder> {

    private ModeEnum mode;

    /**
     * Constructor for the ModeBuilder.
     *
     * @param parentBuilder The parent {@link FunctionCallingConfigBuilder}
     * instance.
     */
    public ModeBuilder(FunctionCallingConfigBuilder parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the mode enum for the function calling mode.
     *
     * @param mode The mode enum.
     * @return The builder instance for method chaining.
     */
    public ModeBuilder withMode(ModeEnum mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Mode enum is required.");
        }
        this.mode = mode;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mode build() {
        return Mode.builder()
                .withMode(mode)
                .build();
    }
}