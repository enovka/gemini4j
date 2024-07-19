package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.FunctionDeclaration;
import com.enovka.gemini4j.domain.Tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link Tool} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class ToolBuilder {

    private final GenerateContentRequestBuilder generateContentRequestBuilder;
    private List<FunctionDeclaration> functionDeclarations;

    /**
     * Constructor for the ToolBuilder.
     *
     * @param generateContentRequestBuilder The parent
     * GenerateContentRequestBuilder instance.
     */
    public ToolBuilder(
            GenerateContentRequestBuilder generateContentRequestBuilder) {
        this.generateContentRequestBuilder = generateContentRequestBuilder;
    }

    /**
     * Adds a function declaration to the list of function declarations for the
     * tool.
     *
     * @param functionDeclaration The function declaration to add.
     * @return The builder instance for method chaining.
     */
    public ToolBuilder withFunctionDeclaration(
            FunctionDeclaration functionDeclaration) {
        if (this.functionDeclarations == null) {
            this.functionDeclarations = new ArrayList<>();
        }
        this.functionDeclarations.add(functionDeclaration);
        return this;
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
     * Builds a {@link Tool} instance based on the configured parameters.
     *
     * @return The built {@link Tool} instance.
     */
    public Tool build() {
        return Tool.builder()
                .withFunctionDeclarations(functionDeclarations)
                .build();
    }

    /**
     * Adds the built {@link Tool} instance to the list of tools in the parent
     * builder.
     *
     * @return The parent {@link GenerateContentRequestBuilder} instance for
     * method chaining.
     */
    public GenerateContentRequestBuilder and() {
        generateContentRequestBuilder.withTool(build());
        return generateContentRequestBuilder;
    }
}