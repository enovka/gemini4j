package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.response.FunctionResponse;

/**
 * Builder for creating {@link FunctionResponse} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class FunctionResponseBuilder {

    private final PartBuilder partBuilder;
    private String name;
    private Object response;

    /**
     * Constructor for the FunctionResponseBuilder.
     *
     * @param partBuilder The parent PartBuilder instance.
     */
    public FunctionResponseBuilder(PartBuilder partBuilder) {
        this.partBuilder = partBuilder;
    }

    /**
     * Sets the name of the function that produced the response.
     *
     * @param name The name of the function.
     * @return The builder instance for method chaining.
     */
    public FunctionResponseBuilder withName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Function name is required.");
        }
        this.name = name;
        return this;
    }

    /**
     * Sets the response from the function.
     *
     * @param response The function response in JSON object format.
     * @return The builder instance for method chaining.
     */
    public FunctionResponseBuilder withResponse(Object response) {
        this.response = response;
        return this;
    }

    /**
     * Builds a {@link FunctionResponse} instance based on the configured
     * parameters.
     *
     * @return The built {@link FunctionResponse} instance.
     */
    public FunctionResponse build() {
        return FunctionResponse.builder()
                .withName(name)
                .withResponse(response)
                .build();
    }

    /**
     * Sets the built {@link FunctionResponse} instance as the function response
     * in the parent builder.
     *
     * @return The parent {@link PartBuilder} instance for method chaining.
     */
    public PartBuilder and() {
        partBuilder.functionResponse = build();
        return partBuilder;
    }
}