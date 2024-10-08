package com.enovka.gemini4j.resource.builder.request.spec;

import com.enovka.gemini4j.infrastructure.Constants;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.tool.SchemaTool;
import com.enovka.gemini4j.model.*;
import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.model.type.HarmCategoryEnum;
import com.enovka.gemini4j.model.type.ModeEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Abstract builder class for generation-related requests, extending the {@link ContentRequestBuilder}.
 * This class provides specialized fields and methods for configuring parameters specific to text
 * generation requests in the Gemini API, including safety settings, generation configuration, tools, and JSON mode.
 *
 * @param <B> The type of the concrete builder class.
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class AbstractGenerateRequestBuilder<B extends AbstractGenerateRequestBuilder<B, T>, T extends GenerateRequest>
        extends AbstractContentRequestBuilder<B, T> {

    protected List<SafetySetting> safetySettings = new ArrayList<>();
    protected final GenerateConfig.GenerateConfigBuilder generationConfigBuilder = GenerateConfig.builder();
    protected List<Tool> tools = new ArrayList<>();
    protected ToolConfig.ToolConfigBuilder toolConfigBuilder;
    protected JsonModeConfig jsonModeConfig;
    protected String cachedContent;

    /**
     * Inner class to encapsulate JSON Mode configuration.
     *
     * @since 0.2.0
     */
    @Data
    public static class JsonModeConfig {
        private boolean enabled;
        private String schema;
        private Schema schemaObject;
    }

    /**
     * Adds a safety setting to the request. This method is protected, encouraging the use of more
     * specific 'withXXX' methods for different harm categories.
     *
     * @param safetySetting The safety setting to add.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided safetySetting is null.
     * @see SafetySetting
     * @since 0.2.0
     */
    protected B withSafetySetting(SafetySetting safetySetting) {
        if (safetySetting == null) {
            throw new IllegalArgumentException("Safety setting cannot be null.");
        }
        this.safetySettings.add(safetySetting);
        return self();
    }

    /**
     * Adds a safety setting using a lambda expression. This method is protected, encouraging the use
     * of more specific methods for each harm category.
     *
     * @param safetySettingConfigurer A consumer that configures the SafetySetting.
     * @return The builder instance for method chaining.
     * @see SafetySetting
     * @since 0.2.0
     */
    protected B withSafetySetting(Consumer<SafetySetting.SafetySettingBuilder> safetySettingConfigurer) {
        SafetySetting.SafetySettingBuilder safetySettingBuilder = SafetySetting.builder();
        safetySettingConfigurer.accept(safetySettingBuilder);
        return withSafetySetting(safetySettingBuilder.build());
    }

    /**
     * Configures safety settings using a lambda expression. This method provides a more concise and
     * readable way to configure multiple safety settings at once.
     *
     * @param safetySettingsConfigurer A consumer that configures the list of SafetySettings.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withSafetySettings(Consumer<SafetySettingsBuilder> safetySettingsConfigurer) {
        SafetySettingsBuilder builder = new SafetySettingsBuilder(self());
        safetySettingsConfigurer.accept(builder);
        return self();
    }

    /**
     * Adds a Harassment safety setting with the specified threshold.
     *
     * @param threshold The harm probability threshold for Harassment.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected B withHarassment(HarmBlockThresholdEnum threshold) {
        return addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_HARASSMENT, threshold);
    }

    /**
     * Adds a Hate Speech safety setting with the specified threshold.
     *
     * @param threshold The harm probability threshold for Hate Speech.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected B withHateSpeech(HarmBlockThresholdEnum threshold) {
        return addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_HATE_SPEECH, threshold);
    }

    /**
     * Adds a Sexually Explicit safety setting with the specified threshold.
     *
     * @param threshold The harm probability threshold for Sexually Explicit content.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected B withSexuallyExplicit(HarmBlockThresholdEnum threshold) {
        return addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_SEXUALLY_EXPLICIT, threshold);
    }

    /**
     * Adds a Dangerous Content safety setting with the specified threshold.
     *
     * @param threshold The harm probability threshold for Dangerous Content.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected B withDangerousContent(HarmBlockThresholdEnum threshold) {
        return addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_DANGEROUS_CONTENT, threshold);
    }

    /**
     * Adds a Civic Integrity safety setting with the specified threshold.
     *
     * @param threshold The harm probability threshold for Civic Integrity.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected B withCivicIntegrity(HarmBlockThresholdEnum threshold) {
        return addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_CIVIC_INTEGRITY, threshold);
    }

    /**
     * Adds a safety setting for a specific harm category and threshold.  This method is private and used
     * internally by the public 'withXXX' methods.
     *
     * @param category  The harm category.
     * @param threshold The harm probability threshold.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    private B addSafetySetting(HarmCategoryEnum category, HarmBlockThresholdEnum threshold) {
        safetySettings.add(SafetySetting.builder().withCategory(category).withThreshold(threshold).build());
        return self();
    }

    /**
     * Sets the temperature for the generation request.
     *
     * @param temperature The temperature value.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withTemperature(Double temperature) {
        generationConfigBuilder.withTemperature(temperature);
        return self();
    }

    /**
     * Sets the system instruction for the request.
     *
     * @param systemInstruction The system instruction text.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withSystemInstruction(String systemInstruction) {
        withSystemInstruction(Content.builder()
                .withParts(Collections.singletonList(Part.builder().withText(systemInstruction).build()))
                .build());
        return self();
    }

    /**
     * Configures JSON mode settings using a lambda expression.
     *
     * @param jsonModeConfigurer A consumer that configures the JsonModeConfig.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withJsonMode(Consumer<JsonModeConfigBuilder> jsonModeConfigurer) {
        JsonModeConfigBuilder builder = new JsonModeConfigBuilder(self());
        jsonModeConfigurer.accept(builder);
        return self();
    }


    /**
     * Adds a stop sequence to the generation configuration.
     *
     * @param stopSequence The stop sequence to add.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withStopSequence(String stopSequence) {
        generationConfigBuilder.withStopSequences(Collections.singletonList(stopSequence));
        return self();
    }

    /**
     * Sets the maximum output length in tokens.
     *
     * @param maxOutputTokens The maximum output length.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withOutputLength(Integer maxOutputTokens) {
        generationConfigBuilder.withMaxOutputTokens(maxOutputTokens);
        return self();
    }


    /**
     * Sets the top-k value for sampling.
     *
     * @param topK The top-k value.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withTopK(Integer topK) {
        generationConfigBuilder.withTopK(topK);
        return self();
    }

    /**
     * Sets the top-p value for sampling.
     *
     * @param topP The top-p value.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withTopP(Double topP) {
        generationConfigBuilder.withTopP(topP);
        return self();
    }

    /**
     * Adds a tool to the request.  This method remains protected, encouraging the use of more specific
     * tool-related builder methods.
     *
     * @param tool The tool object to add to the request.
     * @return The builder instance for method chaining.
     * @see Tool
     * @since 0.2.0
     */
    protected B withTool(Tool tool) {
        this.tools.add(tool);
        return self();
    }

    /**
     * Configures a tool using a lambda expression. This method remains protected, promoting more specific
     * tool configuration methods.
     *
     * @param toolConfigurer A consumer that configures the Tool object.
     * @return The builder instance for method chaining.
     * @see Tool
     * @since 0.2.0
     */
    protected B withTool(Consumer<Tool.ToolBuilder> toolConfigurer) {
        Tool.ToolBuilder toolBuilder = Tool.builder();
        toolConfigurer.accept(toolBuilder);
        return withTool(toolBuilder.build());
    }

    /**
     * Sets the tool configuration for the request. This method remains protected, encouraging more
     * specific tool configuration methods.
     *
     * @param toolConfig The tool configuration object to set.
     * @return The builder instance for method chaining.
     * @see ToolConfig
     * @since 0.2.0
     */
    protected B withToolConfig(ToolConfig toolConfig) {
        this.toolConfigBuilder = toolConfig.toBuilder();
        return self();
    }

    /**
     * Configures the tool configuration using a lambda expression. This method remains protected, promoting
     * more specific tool configuration methods.
     *
     * @param toolConfigConfigurer A consumer that configures the ToolConfig object.
     * @return The builder instance for method chaining.
     * @see ToolConfig
     * @since 0.2.0
     */
    protected B withToolConfig(Consumer<ToolConfig.ToolConfigBuilder> toolConfigConfigurer) {
        if (toolConfigBuilder == null) {
            toolConfigBuilder = ToolConfig.builder();
        }
        toolConfigConfigurer.accept(toolConfigBuilder);
        return self();
    }

    /**
     * Sets the function calling mode within the tool configuration.
     *
     * @param mode The function calling mode to set.
     * @return The builder instance for method chaining.
     * @see ModeEnum
     * @since 0.2.0
     */
    protected B withFunctionCallingMode(ModeEnum mode) {
        return withToolConfig(builder -> builder.withFunctionCallingConfig(
                FunctionCallingConfig.builder().withMode(mode).build()));
    }

    /**
     * Sets the identifier of existing cached content to be used in the request.
     *
     * @param cachedContent The identifier of the cached content.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected B withCachedContent(String cachedContent) {
        this.cachedContent = cachedContent;
        return self();
    }

    /**
     * Builds the generate content request object.  This method performs validation and assembles the request
     * object with the configured parameters. It provides a default implementation that can be extended or
     * overridden by concrete subclasses to add specific request fields.
     *
     * @return The built generate a content request object.
     * @throws IllegalStateException If required parameters are missing.
     * @since 0.2.0
     */
    public T build() {
        if (model == null) {
            throw new IllegalStateException("Model is required for GenerateRequest.");
        }
        if (contents == null || contents.isEmpty()) {
            throw new IllegalStateException("Contents cannot be null or empty for GenerateRequest.");
        }

        if (jsonModeConfig != null && jsonModeConfig.enabled) {
            generationConfigBuilder.withResponseMimeType("application/json");
            try {
                if (jsonModeConfig.schema != null) {
                    generationConfigBuilder.withResponseSchema(getSchemaFromJson(jsonModeConfig.schema));
                } else if (jsonModeConfig.schemaObject != null) {
                    generationConfigBuilder.withResponseSchema(jsonModeConfig.schemaObject);
                }
            } catch (JsonException e) {
                throw new RuntimeException(e);
            }
        } else {
            generationConfigBuilder.withResponseMimeType(Constants.MIME_TEXT_PLAIN);
            // Clear schema if JSON mode is disabled or not configured
            generationConfigBuilder.withResponseSchema(null);
        }


        T request = createRequestInstance();

        request.setModel(model);
        request.setContents(contents);
        request.setTools(tools);
        request.setToolConfig(toolConfigBuilder != null ? toolConfigBuilder.build() : null);
        request.setSafetySettings(safetySettings);
        request.setSystemInstruction(systemInstruction);
        request.setGenerateConfig(generationConfigBuilder.build());
        request.setCachedContent(cachedContent);

        return request;
    }


    /**
     * Abstract method to create an instance of the request object.  Concrete subclasses must implement
     * this method to return a specific instance of their request type.
     *
     * @return A new instance of the request object.
     * @since 0.2.0
     */
    protected abstract T createRequestInstance();


    /**
     * Helper class for building safety settings.
     *
     * @since 0.2.0
     */
    public static class SafetySettingsBuilder {
        private final AbstractGenerateRequestBuilder<?, ?> builder;

        /**
         * Constructor for the SafetySettingsBuilder.
         *
         * @param builder The parent builder instance.
         * @since 0.2.0
         */
        private SafetySettingsBuilder(AbstractGenerateRequestBuilder<?, ?> builder) {
            this.builder = builder;
        }


        /**
         * Adds a Harassment safety setting with the specified threshold.
         *
         * @param threshold The harm probability threshold for Harassment.
         * @return The SafetySettingsBuilder instance for method chaining.
         * @since 0.2.0
         */
        public SafetySettingsBuilder withHarassment(HarmBlockThresholdEnum threshold) {
            builder.withHarassment(threshold); // Calls the outer class's method
            return this;
        }

        /**
         * Adds a Hate Speech safety setting with the specified threshold.
         *
         * @param threshold The harm probability threshold for Hate Speech.
         * @return The SafetySettingsBuilder instance for method chaining.
         * @since 0.2.0
         */
        public SafetySettingsBuilder withHateSpeech(HarmBlockThresholdEnum threshold) {
            builder.withHateSpeech(threshold);
            return this;
        }

        /**
         * Adds a Sexually Explicit safety setting with the specified threshold.
         *
         * @param threshold The harm probability threshold for Sexually Explicit content.
         * @return The SafetySettingsBuilder instance for method chaining.
         * @since 0.2.0
         */
        public SafetySettingsBuilder withSexuallyExplicit(HarmBlockThresholdEnum threshold) {
            builder.withSexuallyExplicit(threshold);
            return this;
        }

        /**
         * Adds a Dangerous Content safety setting with the specified threshold.
         *
         * @param threshold The harm probability threshold for Dangerous Content.
         * @return The SafetySettingsBuilder instance for method chaining.
         * @since 0.2.0
         */
        public SafetySettingsBuilder withDangerousContent(HarmBlockThresholdEnum threshold) {
            builder.withDangerousContent(threshold);
            return this;
        }

        /**
         * Adds a Civic Integrity safety setting with the specified threshold.
         *
         * @param threshold The harm probability threshold for Civic Integrity.
         * @return The SafetySettingsBuilder instance for method chaining.
         * @since 0.2.0
         */
        public SafetySettingsBuilder withCivicIntegrity(HarmBlockThresholdEnum threshold) {
            builder.withCivicIntegrity(threshold);
            return this;
        }
    }


    /**
     * Helper class for building JSON mode configuration.
     *
     * @since 0.2.0
     */
    public static class JsonModeConfigBuilder {
        private final AbstractGenerateRequestBuilder<?, ?> builder;

        /**
         * Constructor for the JsonModeConfigBuilder.
         *
         * @param builder The parent builder instance.
         * @since 0.2.0
         */
        private JsonModeConfigBuilder(AbstractGenerateRequestBuilder<?, ?> builder) {
            this.builder = builder;
        }

        /**
         * Enables or disables JSON mode.
         *
         * @param enabled Whether JSON mode should be enabled.
         * @return The JsonModeConfigBuilder instance for method chaining.
         * @since 0.2.0
         */
        public JsonModeConfigBuilder withEnable(boolean enabled) {
            if (builder.jsonModeConfig == null) {
                builder.jsonModeConfig = new JsonModeConfig();
            }
            builder.jsonModeConfig.setEnabled(enabled);
            return this;
        }

        /**
         * Sets the JSON schema as a string.
         *
         * @param schema The JSON schema string.
         * @return The JsonModeConfigBuilder instance for method chaining.
         * @since 0.2.0
         */
        public JsonModeConfigBuilder withSchema(String schema) {
            if (builder.jsonModeConfig == null) {
                builder.jsonModeConfig = new JsonModeConfig();
            }
            builder.jsonModeConfig.setSchema(schema);
            builder.jsonModeConfig.setSchemaObject(null); // Clear any existing Schema object
            return this;
        }

        /**
         * Sets the JSON schema as a Schema object.
         *
         * @param schema The JSON schema object.
         * @return The JsonModeConfigBuilder instance for method chaining.
         * @since 0.2.0
         */
        public JsonModeConfigBuilder withSchema(Schema schema) {
            if (builder.jsonModeConfig == null) {
                builder.jsonModeConfig = new JsonModeConfig();
            }
            builder.jsonModeConfig.setSchemaObject(schema);
            builder.jsonModeConfig.setSchema(null); // Clear any existing schema string
            return this;
        }
    }


    protected Schema getSchemaFromJson(String jsonSchema) throws JsonException {
        return SchemaTool.convertToSchema(jsonSchema);
    }
}