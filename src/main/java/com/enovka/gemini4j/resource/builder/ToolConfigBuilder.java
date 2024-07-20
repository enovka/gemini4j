package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.FunctionCallingConfig;
import com.enovka.gemini4j.domain.ToolConfig;
import com.enovka.gemini4j.resource.builder.spec.AbstractBuilder;
import com.enovka.gemini4j.resource.builder.spec.AbstractToolConfigBuilder;

/**
 * Builder for creating {@link ToolConfig} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class ToolConfigBuilder extends AbstractToolConfigBuilder<ToolConfig> {

    private FunctionCallingConfig functionCallingConfig;

    /**
     * Constructor for the ToolConfigBuilder.
     *
     * @param parentBuilder The parent {@link AbstractBuilder} instance.
     */
    public ToolConfigBuilder(AbstractBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the function calling configuration for the tool config.
     *
     * @param functionCallingConfig The function calling configuration.
     * @return The builder instance for method chaining.
     */
    public ToolConfigBuilder withFunctionCallingConfig(
            FunctionCallingConfig functionCallingConfig) {
        this.functionCallingConfig = functionCallingConfig;
        return this;
    }

    /**
     * Creates a new {@link FunctionCallingConfigBuilder} for building the
     * function calling configuration.
     *
     * @return A new {@link FunctionCallingConfigBuilder} instance.
     */
    public FunctionCallingConfigBuilder withFunctionCallingConfig() {
        return new FunctionCallingConfigBuilder(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ToolConfig build() {
        return ToolConfig.builder()
                .withFunctionCallingConfig(functionCallingConfig)
                .build();
    }
}