package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.resource.builder.request.spec.GenerateRequestBuilder;

/**
 * Builder for creating {@link GenerateRequest} instances specifically for generating text
 * content. This builder provides a fluent API for configuring text generation requests, leveraging
 * the {@link GenerateRequestBuilder} for common generation-related parameters and adding
 * specialized handling for user input.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class GenerateTextRequestBuilder extends GenerateRequestBuilder<GenerateTextRequestBuilder, GenerateRequest> {

    private String userInput;

    /**
     * Creates a new instance of the {@link GenerateTextRequestBuilder}.
     *
     * @return A new {@link GenerateTextRequestBuilder} instance.
     * @since 0.2.0
     */
    public static GenerateTextRequestBuilder builder() {
        return new GenerateTextRequestBuilder();
    }

    /**
     * Sets the user input for the text generation request. This input will be added as a
     * {@link Content} object with the role "user".
     *
     * @param userInput The user input text.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public GenerateTextRequestBuilder withUserInput(String userInput) {
        this.userInput = userInput;
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public GenerateRequest build() {
        if (userInput == null) {
            throw new IllegalArgumentException("User input is required for text generation.");
        }

        withUserContent(userInput);

        return GenerateRequest.builder()
                .withModel(model)
                .withContents(contents)
                .withTools(tools)
                .withSystemInstruction(systemInstruction)
                .withToolConfig(toolConfig)
                .withSafetySettings(safetySettings)
                .withGenerateConfig(generateConfig)
                .build();
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    protected GenerateTextRequestBuilder self() {
        return this;
    }
}