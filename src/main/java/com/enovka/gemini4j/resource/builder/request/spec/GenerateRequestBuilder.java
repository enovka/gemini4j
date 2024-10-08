package com.enovka.gemini4j.resource.builder.request.spec;

import com.enovka.gemini4j.model.*;
import com.enovka.gemini4j.model.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.model.type.HarmCategoryEnum;
import com.enovka.gemini4j.model.type.ModeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Abstract builder class for generation-related requests, extending the {@link ContentRequestBuilder}.
 * This class provides specialized fields and methods for configuring parameters specific to text
 * generation requests in the Gemini API.
 *
 * @param <B> The type of the concrete builder class.
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class GenerateRequestBuilder<B extends GenerateRequestBuilder<B, T>, T>
        extends ContentRequestBuilder<B, T> {

    protected List<SafetySetting> safetySettings = new ArrayList<>();
    protected GenerateConfig generateConfig;
    protected List<Tool> tools = new ArrayList<>();
    protected ToolConfig toolConfig;

    /**
     * Constructor for the GenerateRequestBuilder.
     *
     * @since 0.2.0
     */
    protected GenerateRequestBuilder() {
        super();
    }

    /**
     * Adds a safety setting to the request. Safety settings allow controlling the filtering of
     * potentially harmful content in the generated response, as described in the Gemini API
     * documentation:
     * [<a href="https://ai.google.dev/gemini-api/docs/safety-guidance">...</a>](https://ai.google.dev/gemini-api/docs/safety-guidance)
     *
     * @param safetySetting The safety setting to add.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withSafetySetting(SafetySetting safetySetting) {
        this.safetySettings.add(safetySetting);
        return self();
    }

    /**
     * Configures a safety setting for the request using a consumer and builder. This allows for
     * more flexible and concise configuration of safety settings.
     *
     * @param safetySettingConfigurer A consumer to configure the safety setting.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withSafetySetting(Consumer<SafetySetting.SafetySettingBuilder> safetySettingConfigurer) {
        SafetySetting.SafetySettingBuilder safetySettingBuilder = SafetySetting.builder();
        safetySettingConfigurer.accept(safetySettingBuilder);
        return withSafetySetting(safetySettingBuilder.build());
    }

    /**
     * Adds a safety setting for a specific harm category with the specified threshold.  This is a
     * convenience method for configuring safety settings for common harm categories.
     *
     * @param category  The harm category to configure.
     * @param threshold The harm block threshold for the category.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withSafetySetting(HarmCategoryEnum category, HarmBlockThresholdEnum threshold) {
        return withSafetySetting(builder -> builder
                .withCategory(category)
                .withThreshold(threshold));
    }

    /**
     * Sets the generation config for the request. Generation config allows fine-tuning the
     * generation process, such as controlling randomness and maximum output length, as described
     * in the Gemini API documentation:
     * [<a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1/GenerationConfig">...</a>](https://ai.google.dev/gemini-api/docs/reference/rest/v1/GenerationConfig)
     *
     * @param generateConfig The generation config to use.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withGenerationConfig(GenerateConfig generateConfig) {
        this.generateConfig = generateConfig;
        return self();
    }

    /**
     * Configures the generation config for the request using a consumer and builder. This provides
     * a more flexible way to configure generation parameters.
     *
     * @param generationConfigConfigurer A consumer to configure the generation config.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withGenerationConfig(Consumer<GenerateConfig.GenerateConfigBuilder> generationConfigConfigurer) {
        GenerateConfig.GenerateConfigBuilder generateConfigBuilder = GenerateConfig.builder();
        generationConfigConfigurer.accept(generateConfigBuilder);
        return withGenerationConfig(generateConfigBuilder.build());
    }


    /**
     * Adds a tool to the request. Tools allow the model to interact with external systems or
     * perform specific actions, as described in the Gemini API documentation:
     * [<a href="https://ai.google.dev/gemini-api/docs/function-calling">...</a>](https://ai.google.dev/gemini-api/docs/function-calling)
     *
     * @param tool The tool to add.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withTool(Tool tool) {
        this.tools.add(tool);
        return self();
    }

    /**
     * Configures a tool for the request using a consumer and builder. This allows for more
     * concise and readable tool configuration.
     *
     * @param toolConfigurer A consumer to configure the tool.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withTool(Consumer<Tool.ToolBuilder> toolConfigurer) {
        Tool.ToolBuilder toolBuilder = Tool.builder();
        toolConfigurer.accept(toolBuilder);
        return withTool(toolBuilder.build());
    }

    /**
     * Sets the tool config for the request. Tool config provides additional parameters for
     * controlling tool behavior, such as function calling configuration.
     *
     * @param toolConfig The tool config to use.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withToolConfig(ToolConfig toolConfig) {
        this.toolConfig = toolConfig;
        return self();
    }

    /**
     * Configures the tool config for the request using a consumer and builder. This allows for
     * more flexible configuration of tool parameters.
     *
     * @param toolConfigConfigurer A consumer to configure the tool config.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withToolConfig(Consumer<ToolConfig.ToolConfigBuilder> toolConfigConfigurer) {
        ToolConfig.ToolConfigBuilder toolConfigBuilder = ToolConfig.builder();
        toolConfigConfigurer.accept(toolConfigBuilder);
        return withToolConfig(toolConfigBuilder.build());
    }

    /**
     * Sets the function calling mode within the tool config.  This method simplifies the
     * configuration of function calling behavior.
     *
     * @param mode The function calling mode to use.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withFunctionCallingMode(ModeEnum mode) {
        return withToolConfig(builder -> builder
                .withFunctionCallingConfig(
                        FunctionCallingConfig.builder().withMode(mode).build()));
    }
}