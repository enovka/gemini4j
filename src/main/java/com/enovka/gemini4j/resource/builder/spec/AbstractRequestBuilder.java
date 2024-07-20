package com.enovka.gemini4j.resource.builder.spec;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.GenerationConfig;
import com.enovka.gemini4j.domain.SafetySetting;
import com.enovka.gemini4j.domain.Tool;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.resource.builder.SafetySettingBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Specialized abstract class for builders handling
 * {@link GenerateContentRequest} objects and its variations, providing common
 * methods for setting request parameters.
 *
 * @param <T> The type of request object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractRequestBuilder<T extends GenerateContentRequest>
        extends AbstractBuilder<T> {

    protected Content systemInstruction;
    protected List<SafetySetting> safetySettings;
    protected GenerationConfig generationConfig;
    protected List<Tool> tools;

    /**
     * Constructor for the AbstractRequestBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    protected AbstractRequestBuilder(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * Adds a safety setting to the list of safety settings for the request.
     *
     * @param safetySetting The safety setting to add.
     * @return The builder instance for method chaining.
     */
    @SuppressWarnings("unchecked")
    public <B extends AbstractRequestBuilder<T>> B withSafetySetting(
            SafetySetting safetySetting) {
        if (this.safetySettings == null) {
            this.safetySettings = new ArrayList<>();
        }
        this.safetySettings.add(safetySetting);
        return (B) this;
    }

    /**
     * Creates a new {@link SafetySettingBuilder} for building a safety
     * setting.
     *
     * @return A new {@link SafetySettingBuilder} instance.
     */
    public SafetySettingBuilder withSafetySetting() {
        return new SafetySettingBuilder(this);
    }

    /**
     * Sets the optional system instruction for the request.
     *
     * @param systemInstruction The system instruction to set.
     * @return The builder instance for method chaining.
     */
    @SuppressWarnings("unchecked")
    public <B extends AbstractRequestBuilder<T>> B withSystemInstruction(
            Content systemInstruction) {
        this.systemInstruction = systemInstruction;
        return (B) this;
    }

    /**
     * Sets the optional generation configuration for the request.
     *
     * @param generationConfig The generation configuration to set.
     * @return The builder instance for method chaining.
     */
    @SuppressWarnings("unchecked")
    public <B extends AbstractRequestBuilder<T>> B withGenerationConfig(
            GenerationConfig generationConfig) {
        this.generationConfig = generationConfig;
        return (B) this;
    }

    /**
     * Adds a tool to the list of tools for the generation request.
     *
     * @param tool The tool to add.
     * @return The builder instance for method chaining.
     */
    @SuppressWarnings("unchecked")
    public <B extends AbstractRequestBuilder<T>> B withTool(Tool tool) {
        if (this.tools == null) {
            this.tools = new ArrayList<>();
        }
        this.tools.add(tool);
        return (B) this;
    }
}