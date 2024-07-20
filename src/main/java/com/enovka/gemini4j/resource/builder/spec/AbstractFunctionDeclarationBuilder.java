package com.enovka.gemini4j.resource.builder.spec;

import com.enovka.gemini4j.domain.FunctionDeclaration;
import com.enovka.gemini4j.resource.builder.ToolBuilder;

/**
 * Abstract class for builders handling {@link FunctionDeclaration} objects.
 *
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractFunctionDeclarationBuilder<T extends FunctionDeclaration>
        extends AbstractComplexBuilder<T, ToolBuilder> {

    protected String name;
    protected String description;

    /**
     * Constructor for the AbstractFunctionDeclarationBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractFunctionDeclarationBuilder(ToolBuilder parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the name of the function.
     *
     * @param name The name of the function.
     * @return The builder instance for method chaining.
     */
    public AbstractFunctionDeclarationBuilder<T> withName(String name) {
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
    public AbstractFunctionDeclarationBuilder<T> withDescription(
            String description) {
        this.description = description;
        return this;
    }
}