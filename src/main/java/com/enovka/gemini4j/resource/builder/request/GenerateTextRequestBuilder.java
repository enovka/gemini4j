package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.response.GenerateResponse;
import com.enovka.gemini4j.model.response.internal.GenerateContentResponse;
import com.enovka.gemini4j.resource.builder.request.spec.GenerateRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.GenerateResource;

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

    private final GenerateResource generateResource;
    private String userInput;

    /**
     * Constructor for the GenerateTextRequestBuilder.
     *
     * @param generateResource The {@link GenerateResource} instance to use for executing the
     *                           request.
     * @since 0.2.0
     */
    public GenerateTextRequestBuilder(GenerateResource generateResource) {
        super();
        this.generateResource = generateResource;
    }

    /**
     * Creates a new instance of the {@link GenerateTextRequestBuilder}.
     *
     * @param generateResource The {@link GenerateResource} instance to use for executing the
     *                           request.
     * @return A new {@link GenerateTextRequestBuilder} instance.
     * @since 0.2.0
     */
    public static GenerateTextRequestBuilder builder(GenerateResource generateResource) {
        return new GenerateTextRequestBuilder(generateResource);
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
     *
     * @since 0.2.0
     */
    @Override
    public GenerateRequest buildRequest() {
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
     * Executes the generateText request synchronously and returns the response. This method
     * builds the request using the parameters set in the builder and then executes it using the
     * associated {@link GenerateResource}.
     *
     * @return The {@link GenerateContentResponse} wrapped in a {@link GenerateResponse} from the Gemini API.
     * @throws ResourceException If an error occurs during the API request.
     * @since 0.2.0
     */
    public GenerateResponse execute() throws ResourceException {
        return generateResource.execute(buildRequest());
    }


    /**
     * {@inheritDoc}
     *
     * @since 0.2.0
     */
    @Override
    protected GenerateTextRequestBuilder self() {
        return this;
    }
}