package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.GenerationConfig;
import com.enovka.gemini4j.domain.SafetySetting;
import com.enovka.gemini4j.domain.ToolConfig;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;
import com.enovka.gemini4j.resource.builder.spec.AbstractRequestBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link GenerateContentRequest} instances in a fluent and
 * intuitive way. This builder provides a comprehensive set of methods to
 * configure all aspects of the request, including nested objects and lists.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class GenerateContentRequestBuilder
        extends AbstractRequestBuilder<GenerateContentRequest> {

    private String userInput;
    private ToolConfig toolConfig;
    private String cachedContent;

    /**
     * Private constructor to enforce builder pattern.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    private GenerateContentRequestBuilder(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * Creates a new instance of the GenerateContentRequestBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     * @return A new GenerateContentRequestBuilder instance.
     */
    public static GenerateContentRequestBuilder builder(
            GeminiClient geminiClient) {
        return new GenerateContentRequestBuilder(geminiClient);
    }

    /**
     * Sets the required user input for the generation request.
     *
     * @param userInput The user's input text.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withUserInput(String userInput) {
        if (userInput == null || userInput.isEmpty()) {
            throw new IllegalArgumentException("User input is required.");
        }
        this.userInput = userInput;
        return this;
    }

    /**
     * Sets the optional system instructions for the generation request. If a
     * string is provided, it will be automatically wrapped in a
     * {@link com.enovka.gemini4j.domain.Content} object with the role
     * "system".
     *
     * @param systemInstructions The system instructions, either as a string or
     * a {@link com.enovka.gemini4j.domain.Content} object.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withSystemInstructions(
            String systemInstructions) {
        if (systemInstructions != null && !systemInstructions.isEmpty()) {
            this.withSystemInstruction(ContentBuilder.builder(this)
                    .withText(systemInstructions)
                    .withRole("system")
                    .build());
        }
        return this;
    }

    /**
     * Creates a new {@link ContentBuilder} for building the system
     * instruction.
     *
     * @return A new {@link ContentBuilder} instance.
     */
    public ContentBuilder withSystemInstruction() {
        return new ContentBuilder(this);
    }

    /**
     * Creates a new {@link ToolBuilder} for building a tool.
     *
     * @return A new {@link ToolBuilder} instance.
     */
    public ToolBuilder withTool() {
        return new ToolBuilder(this);
    }

    /**
     * Sets the optional tool configuration for the generation request.
     *
     * @param toolConfig The tool configuration to use.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withToolConfig(ToolConfig toolConfig) {
        this.toolConfig = toolConfig;
        return this;
    }

    /**
     * Creates a new {@link ToolConfigBuilder} for building the tool
     * configuration.
     *
     * @return A new {@link ToolConfigBuilder} instance.
     */
    public ToolConfigBuilder withToolConfig() {
        return new ToolConfigBuilder(this);
    }

    /**
     * Adds a safety setting for toxicity with the specified block threshold.
     *
     * @param harmBlockThreshold The harm block threshold for toxicity.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withSafetySettingForToxicity(
            HarmBlockThresholdEnum harmBlockThreshold) {
        return this.withSafetySetting(SafetySetting.builder()
                .withCategory(HarmCategoryEnum.HARM_CATEGORY_TOXICITY)
                .withThreshold(harmBlockThreshold).build());
    }

    /**
     * Sets the optional generation configuration for the generation request.
     *
     * @param generationConfig The generation configuration to use.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withGenerationConfig(
            GenerationConfig generationConfig) {
        this.generationConfig = generationConfig;
        return this;
    }

    /**
     * Creates a new {@link GenerationConfigBuilder} for building the generation
     * configuration.
     *
     * @return A new {@link GenerationConfigBuilder} instance.
     */
    public GenerationConfigBuilder withGenerationConfig() {
        return new GenerationConfigBuilder(this);
    }

    /**
     * Sets the optional cached content name for the generation request.
     *
     * @param cachedContent The name of the cached content to use.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withCachedContent(
            String cachedContent) {
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

        ContentBuilder contentBuilder = ContentBuilder.builder(this);
        contentBuilder.withText(userInput);
        contentBuilder.withRole("user");

        // Use ArrayList for a mutable list
        List<Content> contents = new ArrayList<>();
        contents.add(contentBuilder.build());

        return GenerateContentRequest.builder()
                .withModel(geminiClient.getModel())
                .withContents(contents)
                .withSystemInstruction(systemInstruction)
                .withTools(tools)
                .withToolConfig(toolConfig)
                .withSafetySettings(safetySettings)
                .withGenerationConfig(generationConfig)
                .withCachedContent(cachedContent)
                .build();
    }
}