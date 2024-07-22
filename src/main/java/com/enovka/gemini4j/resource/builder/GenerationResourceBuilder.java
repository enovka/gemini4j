package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.*;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.type.TypeEnum;
import com.enovka.gemini4j.resource.builder.spec.AbstractRequestBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Builder class for constructing {@link GenerateContentRequest} objects. This
 * builder provides a fluent and intuitive API for configuring all aspects of
 * the request, including nested objects and lists.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class GenerationResourceBuilder extends
        AbstractRequestBuilder<GenerateContentRequest> {

    private String userInput;
    private String systemInstruction;
    private List<FunctionDeclaration> functionDeclarations;
    private ToolConfig toolConfig;
    private String cachedContent;

    /**
     * Private constructor to enforce builder pattern.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    private GenerationResourceBuilder(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * Creates a new instance of the GenerationResourceBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     * @return A new GenerationResourceBuilder instance.
     */
    public static GenerationResourceBuilder builder(GeminiClient geminiClient) {
        return new GenerationResourceBuilder(geminiClient);
    }

    /**
     * Sets the required user input for the generation request.
     *
     * @param userInput The user's input text.
     * @return The builder instance for method chaining.
     */
    public GenerationResourceBuilder withUserInput(String userInput) {
        this.userInput = userInput;
        return this;
    }

    /**
     * Sets the optional system instructions for the generation request.
     *
     * @param systemInstruction The system instructions to guide the model's
     * response.
     * @return The builder instance for method chaining.
     */
    public GenerationResourceBuilder withSystemInstruction(
            String systemInstruction) {
        this.systemInstruction = systemInstruction;
        return this;
    }

    /**
     * Adds a function declaration to the list of function declarations for the
     * tool.
     *
     * @param name The name of the function.
     * @param description A brief description of the function.
     * @param type The data type of the function parameter.
     * @param format The format of the data type.
     * @param descriptionSchema A description of the function parameter schema.
     * @param nullable Whether the function parameter can be null.
     * @param enumValues Possible values of the function parameter if it's an
     * enum.
     * @param items The schema for the items in an array parameter.
     * @return The builder instance for method chaining.
     */
    public GenerationResourceBuilder withToolFunctionDeclaration(String name,
                                                                 String description,
                                                                 TypeEnum type,
                                                                 String format,
                                                                 String descriptionSchema,
                                                                 Boolean nullable,
                                                                 List<String> enumValues,
                                                                 Schema items) {
        if (this.functionDeclarations == null) {
            this.functionDeclarations = new ArrayList<>();
        }
        this.functionDeclarations.add(FunctionDeclaration.builder()
                .withName(name)
                .withDescription(description)
                .withParameters(Schema.builder()
                        .withType(Type.builder().withType(type).build())
                        .withFormat(format)
                        .withDescription(descriptionSchema)
                        .withNullable(nullable)
                        .withEnumValues(enumValues)
                        .withItems(items)
                        .build())
                .build());
        return this;
    }

    /**
     * Sets the optional tool configuration for the generation request.
     *
     * @param toolConfig The tool configuration to use.
     * @return The builder instance for method chaining.
     */
    public GenerationResourceBuilder withToolConfig(ToolConfig toolConfig) {
        this.toolConfig = toolConfig;
        return this;
    }

    /**
     * Sets the optional cached content name for the generation request.
     *
     * @param cachedContent The name of the cached content to use.
     * @return The builder instance for method chaining.
     */
    public GenerationResourceBuilder withCachedContent(String cachedContent) {
        this.cachedContent = cachedContent;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentRequest build() {
        if (userInput == null) {
            throw new IllegalArgumentException("User input is required.");
        }
        Content systemInstructionContent = null;
        if (systemInstruction != null && !systemInstruction.isEmpty()) {
            systemInstructionContent = Content.builder()
                    .withParts(Collections.singletonList(
                            Part.builder().withText(systemInstruction).build()))
                    .withRole("system")
                    .build();
        }

        Content userInputContent = Content.builder()
                .withParts(Collections.singletonList(
                        Part.builder().withText(userInput).build()))
                .withRole("user")
                .build();

        Tool tool = Tool.builder()
                .withFunctionDeclarations(functionDeclarations)
                .build();

        return GenerateContentRequest.builder()
                .withModel(geminiClient.getModel())
                .withContents(
                        List.of(userInputContent))
                .withTools(List.of(tool))
                .withSystemInstruction(systemInstructionContent)
                .withToolConfig(toolConfig)
                .withSafetySettings(safetySettings)
                .withGenerationConfig(generationConfig)
                .withCachedContent(cachedContent)
                .build();
    }
}