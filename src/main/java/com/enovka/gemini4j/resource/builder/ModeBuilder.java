package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Mode;
import com.enovka.gemini4j.domain.type.ModeEnum;

/**
 * Builder for creating {@link Mode} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class ModeBuilder {

    private final FunctionCallingConfigBuilder functionCallingConfigBuilder;
    private ModeEnum mode;

    /**
     * Constructor for the ModeBuilder.
     *
     * @param functionCallingConfigBuilder The parent
     * FunctionCallingConfigBuilder instance.
     */
    public ModeBuilder(
            FunctionCallingConfigBuilder functionCallingConfigBuilder) {
        this.functionCallingConfigBuilder = functionCallingConfigBuilder;
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
     * Builds a {@link Mode} instance based on the configured parameters.
     *
     * @return The built {@link Mode} instance.
     */
    public Mode build() {
        return Mode.builder()
                .withMode(mode)
                .build();
    }

    /**
     * Sets the built {@link Mode} instance as the mode in the parent builder.
     *
     * @return The parent {@link FunctionCallingConfigBuilder} instance for
     * method chaining.
     */
    public FunctionCallingConfigBuilder and() {
        functionCallingConfigBuilder.withMode(build());
        return functionCallingConfigBuilder;
    }
}