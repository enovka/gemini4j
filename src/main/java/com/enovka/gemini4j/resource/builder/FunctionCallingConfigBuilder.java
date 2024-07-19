package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.FunctionCallingConfig;
import com.enovka.gemini4j.domain.Mode;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link FunctionCallingConfig} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class FunctionCallingConfigBuilder {

    private final ToolConfigBuilder toolConfigBuilder;
    private Mode mode;
    private List<String> allowedFunctionNames;

    /**
     * Constructor for the FunctionCallingConfigBuilder.
     *
     * @param toolConfigBuilder The parent ToolConfigBuilder instance.
     */
    public FunctionCallingConfigBuilder(ToolConfigBuilder toolConfigBuilder) {
        this.toolConfigBuilder = toolConfigBuilder;
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
     * Builds a {@link FunctionCallingConfig} instance based on the configured
     * parameters.
     *
     * @return The built {@link FunctionCallingConfig} instance.
     */
    public FunctionCallingConfig build() {
        return FunctionCallingConfig.builder()
                .withMode(mode)
                .withAllowedFunctionNames(allowedFunctionNames)
                .build();
    }

    /**
     * Sets the built {@link FunctionCallingConfig} instance as the function
     * calling config in the parent builder.
     *
     * @return The parent {@link ToolConfigBuilder} instance for method
     * chaining.
     */
    public ToolConfigBuilder and() {
        toolConfigBuilder.withFunctionCallingConfig(build());
        return toolConfigBuilder;
    }
}