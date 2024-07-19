package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.FunctionCall;

/**
 * Builder for creating {@link FunctionCall} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class FunctionCallBuilder {

    private final PartBuilder partBuilder;
    private String name;
    private Object args;

    /**
     * Constructor for the FunctionCallBuilder.
     *
     * @param partBuilder The parent PartBuilder instance.
     */
    public FunctionCallBuilder(PartBuilder partBuilder) {
        this.partBuilder = partBuilder;
    }

    /**
     * Sets the name of the function to call.
     *
     * @param name The name of the function.
     * @return The builder instance for method chaining.
     */
    public FunctionCallBuilder withName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Function name is required.");
        }
        this.name = name;
        return this;
    }

    /**
     * Sets the arguments for the function call.
     *
     * @param args The function arguments in JSON object format.
     * @return The builder instance for method chaining.
     */
    public FunctionCallBuilder withArgs(Object args) {
        this.args = args;
        return this;
    }

    /**
     * Builds a {@link FunctionCall} instance based on the configured
     * parameters.
     *
     * @return The built {@link FunctionCall} instance.
     */
    public FunctionCall build() {
        return FunctionCall.builder()
                .withName(name)
                .withArgs(args)
                .build();
    }

    /**
     * Sets the built {@link FunctionCall} instance as the function call in the
     * parent builder.
     *
     * @return The parent {@link PartBuilder} instance for method chaining.
     */
    public PartBuilder and() {
        partBuilder.functionCall = build();
        return partBuilder;
    }
}