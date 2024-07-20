package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.response.FunctionResponse;
import com.enovka.gemini4j.resource.builder.spec.AbstractComplexBuilder;

/**
 * Builder for creating {@link FunctionResponse} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class FunctionResponseBuilder extends
        AbstractComplexBuilder<FunctionResponse, PartBuilder> {

    private String name;
    private Object response;

    /**
     * Constructor for the FunctionResponseBuilder.
     *
     * @param parentBuilder The parent {@link PartBuilder} instance.
     */
    public FunctionResponseBuilder(PartBuilder parentBuilder) {
        super(parentBuilder);
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
     * {@inheritDoc}
     */
    @Override
    public FunctionResponse build() {
        return FunctionResponse.builder()
                .withName(name)
                .withResponse(response)
                .build();
    }
}