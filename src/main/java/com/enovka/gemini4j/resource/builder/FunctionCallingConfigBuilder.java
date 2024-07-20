package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.FunctionCallingConfig;
import com.enovka.gemini4j.domain.Mode;
import com.enovka.gemini4j.resource.builder.spec.AbstractFunctionCallingConfigBuilder;
import com.enovka.gemini4j.resource.builder.spec.AbstractToolConfigBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link FunctionCallingConfig} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class FunctionCallingConfigBuilder extends
        AbstractFunctionCallingConfigBuilder<FunctionCallingConfig> {

    private Mode mode;
    private List<String> allowedFunctionNames;

    /**
     * Constructor for the FunctionCallingConfigBuilder.
     *
     * @param parentBuilder The parent {@link AbstractToolConfigBuilder}
     * instance.
     */
    public FunctionCallingConfigBuilder(
            AbstractToolConfigBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the mode for the function calling configuration.
     *
     * @param mode The function calling mode.
     * @return The builder instance for method chaining.
     */
    public FunctionCallingConfigBuilder withMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Creates a new {@link ModeBuilder} for building the function calling
     * mode.
     *
     * @return A new {@link ModeBuilder} instance.
     */
    public ModeBuilder withMode() {
        return new ModeBuilder(this);
    }

    /**
     * Adds an allowed function name to the list of allowed function names for
     * the function calling configuration.
     *
     * @param allowedFunctionName The allowed function name to add.
     * @return The builder instance for method chaining.
     */
    public FunctionCallingConfigBuilder withAllowedFunctionName(
            String allowedFunctionName) {
        if (this.allowedFunctionNames == null) {
            this.allowedFunctionNames = new ArrayList<>();
        }
        this.allowedFunctionNames.add(allowedFunctionName);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionCallingConfig build() {
        return FunctionCallingConfig.builder()
                .withMode(mode)
                .withAllowedFunctionNames(allowedFunctionNames)
                .build();
    }
}