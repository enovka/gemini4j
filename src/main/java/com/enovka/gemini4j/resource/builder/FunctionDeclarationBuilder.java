package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.FunctionDeclaration;
import com.enovka.gemini4j.domain.Schema;
import com.enovka.gemini4j.resource.builder.spec.AbstractFunctionDeclarationBuilder;

/**
 * Builder for creating {@link FunctionDeclaration} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class FunctionDeclarationBuilder extends
        AbstractFunctionDeclarationBuilder<FunctionDeclaration> {

    private Schema parameters;

    /**
     * Constructor for the FunctionDeclarationBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    public FunctionDeclarationBuilder(ToolBuilder parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the parameters for the function.
     *
     * @param parameters The parameters of the function.
     * @return The builder instance for method chaining.
     */
    public FunctionDeclarationBuilder withParameters(Schema parameters) {
        this.parameters = parameters;
        return this;
    }

    /**
     * Creates a new {@link SchemaBuilder} for building the function
     * parameters.
     *
     * @return A new {@link SchemaBuilder} instance.
     */
    public SchemaBuilder withParameters() {
        return new SchemaBuilder(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionDeclaration build() {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Function name is required.");
        }
        return FunctionDeclaration.builder()
                .withName(name)
                .withDescription(description)
                .withParameters(parameters)
                .build();
    }
}