package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Tool;
import com.enovka.gemini4j.resource.builder.spec.AbstractBuilder;
import com.enovka.gemini4j.resource.builder.spec.AbstractToolBuilder;

/**
 * Builder for creating {@link Tool} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class ToolBuilder extends AbstractToolBuilder<Tool> {

    /**
     * Constructor for the ToolBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    public ToolBuilder(AbstractBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Creates a new {@link FunctionDeclarationBuilder} for building a function
     * declaration.
     *
     * @return A new {@link FunctionDeclarationBuilder} instance.
     */
    public FunctionDeclarationBuilder withFunctionDeclaration() {
        return new FunctionDeclarationBuilder(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tool build() {
        return Tool.builder()
                .withFunctionDeclarations(functionDeclarations)
                .build();
    }
}