package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.FunctionDeclaration;
import com.enovka.gemini4j.domain.Schema;

/**
 * Builder for creating {@link FunctionDeclaration} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class FunctionDeclarationBuilder {

    private final ToolBuilder toolBuilder;
    public Schema parameters; // Made public for access from SchemaBuilder
    private String name;
    private String description;

    /**
     * Constructor for the FunctionDeclarationBuilder.
     *
     * @param toolBuilder The parent ToolBuilder instance.
     */
    public FunctionDeclarationBuilder(ToolBuilder toolBuilder) {
        this.toolBuilder = toolBuilder;
    }

    /**
     * Sets the name of the function.
     *
     * @param name The name of the function.
     * @return The builder instance for method chaining.
     */
    public FunctionDeclarationBuilder withName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Function name is required.");
        }
        this.name = name;
        return this;
    }

    /**
     * Sets the description of the function.
     *
     * @param description The description of the function.
     * @return The builder instance for method chaining.
     */
    public FunctionDeclarationBuilder withDescription(String description) {
        this.description = description;
        return this;
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
     * Builds a {@link FunctionDeclaration} instance based on the configured
     * parameters.
     *
     * @return The built {@link FunctionDeclaration} instance.
     */
    public FunctionDeclaration build() {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Function name is required.");
        }
        // Parameters are optional, so no validation is needed here
        return FunctionDeclaration.builder()
                .withName(name)
                .withDescription(description)
                .withParameters(parameters)
                .build();
    }

    /**
     * Adds the built {@link FunctionDeclaration} instance to the list of
     * function declarations in the parent builder.
     *
     * @return The parent {@link ToolBuilder} instance for method chaining.
     */
    public ToolBuilder and() {
        toolBuilder.withFunctionDeclaration(build());
        return toolBuilder;
    }
}