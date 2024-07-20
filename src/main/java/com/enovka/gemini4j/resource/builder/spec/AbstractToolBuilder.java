package com.enovka.gemini4j.resource.builder.spec;

import com.enovka.gemini4j.domain.FunctionDeclaration;
import com.enovka.gemini4j.domain.Tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for builders handling {@link Tool} objects.
 *
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractToolBuilder<T extends Tool>
        extends AbstractComplexBuilder<T, AbstractBuilder<?>> {
    protected List<FunctionDeclaration> functionDeclarations;

    /**
     * Constructor for the AbstractToolBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractToolBuilder(AbstractBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Adds a function declaration to the list of function declarations for the
     * tool.
     *
     * @param functionDeclaration The function declaration to add.
     * @return The builder instance for method chaining.
     */
    public AbstractToolBuilder<T> withFunctionDeclaration(
            FunctionDeclaration functionDeclaration) {
        if (this.functionDeclarations == null) {
            this.functionDeclarations = new ArrayList<>();
        }
        this.functionDeclarations.add(functionDeclaration);
        return this;
    }
}