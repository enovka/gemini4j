package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.FunctionCallingConfig;
import com.enovka.gemini4j.domain.ToolConfig;

/**
 * Builder for creating {@link ToolConfig} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class ToolConfigBuilder {

    private final GenerateContentRequestBuilder generateContentRequestBuilder;
    private FunctionCallingConfig functionCallingConfig;

    /**
     * Constructor for the ToolConfigBuilder.
     *
     * @param generateContentRequestBuilder The parent
     * GenerateContentRequestBuilder instance.
     */
    public ToolConfigBuilder(
            GenerateContentRequestBuilder generateContentRequestBuilder) {
        this.generateContentRequestBuilder = generateContentRequestBuilder;
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
     * Builds a {@link ToolConfig} instance based on the configured parameters.
     *
     * @return The built {@link ToolConfig} instance.
     */
    public ToolConfig build() {
        return ToolConfig.builder()
                .withFunctionCallingConfig(functionCallingConfig)
                .build();
    }

    /**
     * Sets the built {@link ToolConfig} instance as the tool config in the
     * parent builder.
     *
     * @return The parent {@link GenerateContentRequestBuilder} instance for
     * method chaining.
     */
    public GenerateContentRequestBuilder and() {
        generateContentRequestBuilder.withToolConfig(build());
        return generateContentRequestBuilder;
    }
}