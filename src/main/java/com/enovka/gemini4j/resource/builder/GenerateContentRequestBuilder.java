package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.*;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;
import com.enovka.gemini4j.domain.type.ModeEnum;
import com.enovka.gemini4j.domain.type.TypeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * Builder for creating {@link GenerateContentRequest} instances in a fluent and
 * intuitive way. This builder provides a comprehensive set of methods to
 * configure all aspects of the request, including nested objects and lists.
 *
 * <p>This builder supports various Gemini models, including Gemini Pro, Gemini
 * Flash, and other compatible models, allowing you to generate text, chat
 * messages, and other content.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * String userInput = "Tell me a joke.";
 * GenerateContentRequestBuilder builder = generationResource.generateContentBuilder(userInput);
 * builder.withSystemInstruction("You are a funny AI assistant.");
 * builder.withSafetySetting(safety -> safety
 *     .withHarassment(HarmBlockThresholdEnum.BLOCK_NONE)
 *     .withHateSpeech(HarmBlockThresholdEnum.BLOCK_NONE));
 * GenerateContentRequest request = builder.build();
 * GeminiResult result = generationResource.generateContent(request);
 * String generatedText = result.getGeneratedText();
 * System.out.println(generatedText);
 * }</pre>
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Accessors(chain = true)
public class GenerateContentRequestBuilder {

    private final GeminiClient geminiClient;
    private String userInput;
    private String systemInstruction;
    private List<Tool> tools;
    private ToolConfig toolConfig;
    private String cachedContent;
    private List<SafetySetting> safetySettings;
    private GenerationConfig generationConfig;

    /**
     * Private constructor to enforce builder pattern.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    private GenerateContentRequestBuilder(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
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
     * Sets the required user input (text prompt) for the content generation
     * request.
     *
     * @param userInput The user's input text prompt.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withUserInput(String userInput) {
        this.userInput = userInput;
        return this;
    }

    /**
     * Sets the optional system instruction for the content generation request.
     *
     * @param systemInstruction The system instruction to guide the model.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withSystemInstruction(
            String systemInstruction) {
        this.systemInstruction = systemInstruction;
        return this;
    }

    /**
     * Sets the optional cached content name for the content generation
     * request.
     *
     * @param cachedContent The name of the cached content to use as context.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withCachedContent(
            String cachedContent) {
        this.cachedContent = cachedContent;
        return this;
    }

    /**
     * Adds a tool with function declarations to the request.
     *
     * @param name The name of the tool.
     * @param description A brief description of the tool.
     * @param functionDeclarationConfigurer A consumer to configure the function
     * declarations for the tool.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withTool(String name,
                                                  String description,
                                                  Consumer<FunctionDeclarationBuilder> functionDeclarationConfigurer) {
        FunctionDeclarationBuilder functionDeclarationBuilder
                = new FunctionDeclarationBuilder();
        functionDeclarationConfigurer.accept(functionDeclarationBuilder);
        this.tools = List.of(Tool.builder()
                .withFunctionDeclarations(functionDeclarationBuilder.build())
                .build());
        return this;
    }

    /**
     * Configures the tool configuration for the request.
     *
     * @param toolConfigConfigurer A consumer to configure the tool
     * configuration.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withToolConfig(
            Consumer<ToolConfigBuilder> toolConfigConfigurer) {
        ToolConfigBuilder toolConfigBuilder = new ToolConfigBuilder();
        toolConfigConfigurer.accept(toolConfigBuilder);
        this.toolConfig = toolConfigBuilder.build();
        return this;
    }

    /**
     * Adds a safety setting to the request.
     *
     * @param safetySettingConfigurer A consumer to configure the safety
     * setting.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withSafetySetting(
            Consumer<SafetySettingBuilder> safetySettingConfigurer) {
        SafetySettingBuilder safetySettingBuilder = new SafetySettingBuilder();
        safetySettingConfigurer.accept(safetySettingBuilder);
        this.safetySettings = safetySettingBuilder.build();
        return this;
    }

    /**
     * Configures the generation configuration for the request.
     *
     * @param generationConfigConfigurer A consumer to configure the generation
     * configuration.
     * @return The builder instance for method chaining.
     */
    public GenerateContentRequestBuilder withGenerationConfig(
            Consumer<GenerationConfigBuilder> generationConfigConfigurer) {
        GenerationConfigBuilder generationConfigBuilder
                = new GenerationConfigBuilder();
        generationConfigConfigurer.accept(generationConfigBuilder);
        this.generationConfig = generationConfigBuilder.build();
        return this;
    }

    /**
     * Builds the {@link GenerateContentRequest} instance based on the
     * configured parameters.
     *
     * @return The built {@link GenerateContentRequest} instance.
     */
    public GenerateContentRequest build() {
        if (userInput == null) {
            throw new IllegalArgumentException("User input is required.");
        }

        Content userInputContent = Content.builder()
                .withParts(Collections.singletonList(
                        Part.builder().withText(userInput).build()))
                .withRole("user")
                .build();

        // Use ArrayList for a mutable list
        List<Content> contents = new ArrayList<>();
        Content sysContent = null;
        // Adding system instruction before user input
        if (systemInstruction != null && !systemInstruction.isEmpty()) {

            sysContent = Content.builder()
                    .withParts(
                            List.of(Part.builder().withText(systemInstruction)
                                    .build()))
                    .build();
        }
        contents.add(userInputContent);

        return GenerateContentRequest.builder()
                .withModel("models/" + geminiClient.getModel()
                        .replaceAll("^(/)?models(/)?", ""))
                .withContents(contents)
                .withTools(tools)
                .withSystemInstruction(sysContent)
                .withToolConfig(toolConfig)
                .withSafetySettings(safetySettings)
                .withGenerationConfig(generationConfig)
                .withCachedContent(cachedContent)
                .build();
    }

    /**
     * Builds the {@link GenerateContentRequest} instance based on the
     * configured parameters, without requiring user input. This method is
     * specifically designed for use in {@link CountTokensRequestBuilder} where
     * user input is optional.
     *
     * @return The built {@link GenerateContentRequest} instance.
     */
    public GenerateContentRequest buildWithoutUserInput() {
        // Use ArrayList for a mutable list
        List<Content> contents = new ArrayList<>();
        // Adding system instruction before user input
        if (systemInstruction != null && !systemInstruction.isEmpty()) {
            contents.add(Content.builder()
                    .withParts(Collections.singletonList(
                            Part.builder().withText(systemInstruction).build()))
                    .build()); // Role is not required for system instructions
        }

        return GenerateContentRequest.builder()
                .withModel(geminiClient.getModel())
                .withContents(contents)
                .withTools(tools)
                .withToolConfig(toolConfig)
                .withSafetySettings(safetySettings)
                .withGenerationConfig(generationConfig)
                .withCachedContent(cachedContent)
                .build();
    }

    /**
     * Inner builder class for configuring function declarations within a tool.
     */
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class FunctionDeclarationBuilder {
        private List<FunctionDeclaration> functionDeclarations;

        /**
         * Adds a function declaration to the tool.
         *
         * @param name The name of the function.
         * @param description A brief description of the function.
         * @param parameterConfigurer A consumer to configure the parameters for
         * the function declaration.
         * @return The builder instance for method chaining.
         */
        public FunctionDeclarationBuilder withFunctionDeclaration(
                String name, String description,
                Consumer<ParameterBuilder> parameterConfigurer) {
            ParameterBuilder parameterBuilder = new ParameterBuilder();
            parameterConfigurer.accept(parameterBuilder);
            addFunctionDeclaration(FunctionDeclaration.builder()
                    .withName(name)
                    .withDescription(description)
                    .withParameters(parameterBuilder.build())
                    .build());
            return this;
        }

        private FunctionDeclarationBuilder addFunctionDeclaration(
                FunctionDeclaration functionDeclaration) {
            if (this.functionDeclarations == null) {
                this.functionDeclarations = new ArrayList<>();
            }
            this.functionDeclarations.add(functionDeclaration);
            return this;
        }

        List<FunctionDeclaration> build() {
            return functionDeclarations;
        }

        /**
         * Inner builder class for configuring parameters within a function
         * declaration.
         */
        @Getter(AccessLevel.PRIVATE)
        @Setter(AccessLevel.PRIVATE)
        @Accessors(chain = true)
        public static class ParameterBuilder {
            private Schema parameters = Schema.builder()
                    .withType(TypeEnum.OBJECT)
                    .withProperties(new HashMap<>())
                    .build();

            /**
             * Adds a parameter to the function declaration schema.
             *
             * @param parameterName The name of the parameter.
             * @param type The data type of the parameter.
             * @param description A description of the parameter.
             * @return The builder instance for method chaining.
             */
            public ParameterBuilder withParameter(String parameterName,
                                                  TypeEnum type,
                                                  String description) {
                parameters.getProperties().put(parameterName, Schema.builder()
                        .withType(type)
                        .withDescription(description)
                        .build());
                return this;
            }

            /**
             * Marks the specified parameter as required in the function
             * declaration schema.
             *
             * @param parameterName The name of the parameter to mark as
             * required.
             * @return The builder instance for method chaining.
             */
            public ParameterBuilder withRequiredParameter(
                    String parameterName) {
                if (parameters.getRequired() == null) {
                    parameters.setRequired(new ArrayList<>());
                }
                parameters.getRequired().add(parameterName);
                return this;
            }

            private Schema build() {
                return parameters;
            }
        }
    }

    /**
     * Inner builder class for configuring the tool configuration.
     */
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class ToolConfigBuilder {
        private FunctionCallingConfig functionCallingConfig;

        /**
         * Configures the function calling configuration within the tool
         * configuration.
         *
         * @param functionCallingConfigConfigurer A consumer to configure the
         * function calling configuration.
         * @return The builder instance for method chaining.
         */
        public ToolConfigBuilder withFunctionCallingConfig(
                Consumer<FunctionCallingConfigBuilder> functionCallingConfigConfigurer) {
            FunctionCallingConfigBuilder functionCallingConfigBuilder
                    = new FunctionCallingConfigBuilder();
            functionCallingConfigConfigurer.accept(
                    functionCallingConfigBuilder);
            this.functionCallingConfig = functionCallingConfigBuilder.build();
            return this;
        }

        ToolConfig build() {
            return ToolConfig.builder()
                    .withFunctionCallingConfig(functionCallingConfig)
                    .build();
        }

        /**
         * Inner builder class for configuring the function calling
         * configuration.
         */
        @Getter(AccessLevel.PRIVATE)
        @Setter(AccessLevel.PRIVATE)
        @Accessors(chain = true)
        public static class FunctionCallingConfigBuilder {
            private ModeEnum mode;
            private List<String> allowedFunctionNames;

            /**
             * Sets the function calling mode.
             *
             * @param mode The function calling mode.
             * @return The builder instance for method chaining.
             */
            public FunctionCallingConfigBuilder withMode(ModeEnum mode) {
                this.mode = mode;
                return this;
            }

            /**
             * Adds an allowed function name to the configuration.
             *
             * @param allowedFunctionName The allowed function name.
             * @return The builder instance for method chaining.
             */
            public FunctionCallingConfigBuilder withAllowedFunctionName(
                    String allowedFunctionName) {
                if (this.allowedFunctionNames == null) {
                    this.allowedFunctionNames = new ArrayList<>();
                }
                this.allowedFunctionNames.add(allowedFunctionName);
                return this;
            }

            private FunctionCallingConfig build() {
                return FunctionCallingConfig.builder()
                        .withMode(mode)
                        .withAllowedFunctionNames(allowedFunctionNames)
                        .build();
            }
        }
    }

    /**
     * Inner builder class for configuring safety settings.
     */
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class SafetySettingBuilder {
        private List<SafetySetting> safetySettings;

        /**
         * Adds a civic integrity safety setting to the request.
         *
         * @param threshold The harm block threshold for civic integrity.
         * @return The builder instance for method chaining.
         */
        public SafetySettingBuilder withCivicIntegrity(
                HarmBlockThresholdEnum threshold) {
            addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_CIVIC_INTEGRITY,
                    threshold);
            return this;
        }

        /**
         * Adds a harassment safety setting to the request.
         *
         * @param threshold The harm block threshold for harassment.
         * @return The builder instance for method chaining.
         */
        public SafetySettingBuilder withHarassment(
                HarmBlockThresholdEnum threshold) {
            addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_HARASSMENT,
                    threshold);
            return this;
        }

        /**
         * Adds a hate speech safety setting to the request.
         *
         * @param threshold The harm block threshold for hate speech.
         * @return The builder instance for method chaining.
         */
        public SafetySettingBuilder withHateSpeech(
                HarmBlockThresholdEnum threshold) {
            addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_HATE_SPEECH,
                    threshold);
            return this;
        }

        /**
         * Adds a sexually explicit content safety setting to the request.
         *
         * @param threshold The harm block threshold for sexually explicit
         * content.
         * @return The builder instance for method chaining.
         */
        public SafetySettingBuilder withSexuallyExplicit(
                HarmBlockThresholdEnum threshold) {
            addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_SEXUALLY_EXPLICIT,
                    threshold);
            return this;
        }

        /**
         * Adds a dangerous content safety setting to the request.
         *
         * @param threshold The harm block threshold for dangerous content.
         * @return The builder instance for method chaining.
         */
        public SafetySettingBuilder withDangerousContent(
                HarmBlockThresholdEnum threshold) {
            addSafetySetting(HarmCategoryEnum.HARM_CATEGORY_DANGEROUS_CONTENT,
                    threshold);
            return this;
        }

        private void addSafetySetting(HarmCategoryEnum category,
                                      HarmBlockThresholdEnum threshold) {
            if (this.safetySettings == null) {
                this.safetySettings = new ArrayList<>();
            }
            this.safetySettings.add(SafetySetting.builder()
                    .withCategory(category)
                    .withThreshold(threshold)
                    .build());
        }

        private List<SafetySetting> build() {
            return safetySettings;
        }
    }

    /**
     * Inner builder class for configuring the generation configuration.
     */
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class GenerationConfigBuilder {
        private Double temperature;
        private Integer candidateCount;
        private Integer maxOutputTokens;
        private Double topP;
        private Integer topK;
        private List<String> stopSequences;
        private String responseMimeType;
        private Schema responseSchema;

        /**
         * Sets the temperature for the generation.
         *
         * @param temperature The temperature value.
         * @return The builder instance for method chaining.
         */
        public GenerationConfigBuilder withTemperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }

        /**
         * Sets the candidate count for the generation.
         *
         * @param candidateCount The candidate count value.
         * @return The builder instance for method chaining.
         */
        public GenerationConfigBuilder withCandidateCount(
                Integer candidateCount) {
            this.candidateCount = candidateCount;
            return this;
        }

        /**
         * Sets the maximum output tokens for the generation.
         *
         * @param maxOutputTokens The maximum output tokens value.
         * @return The builder instance for method chaining.
         */
        public GenerationConfigBuilder withMaxOutputTokens(
                Integer maxOutputTokens) {
            this.maxOutputTokens = maxOutputTokens;
            return this;
        }

        /**
         * Sets the top-p value for the generation.
         *
         * @param topP The top-p value.
         * @return The builder instance for method chaining.
         */
        public GenerationConfigBuilder withTopP(Double topP) {
            this.topP = topP;
            return this;
        }

        /**
         * Sets the top-k value for the generation.
         *
         * @param topK The top-k value.
         * @return The builder instance for method chaining.
         */
        public GenerationConfigBuilder withTopK(Integer topK) {
            this.topK = topK;
            return this;
        }

        /**
         * Adds a stop sequence to the generation configuration.
         *
         * @param stopSequence The stop sequence to add.
         * @return The builder instance for method chaining.
         */
        public GenerationConfigBuilder withStopSequence(String stopSequence) {
            if (this.stopSequences == null) {
                this.stopSequences = new ArrayList<>();
            }
            this.stopSequences.add(stopSequence);
            return this;
        }

        /**
         * Sets the response MIME type for the generation.
         *
         * @param responseMimeType The response MIME type.
         * @return The builder instance for method chaining.
         */
        public GenerationConfigBuilder withResponseMimeType(
                String responseMimeType) {
            this.responseMimeType = responseMimeType;
            return this;
        }

        /**
         * Configures the response schema for the generation.
         *
         * @param responseSchemaConfigurer A consumer to configure the response
         * schema.
         * @return The builder instance for method chaining.
         */
        public GenerationConfigBuilder withResponseSchema(
                Consumer<SchemaBuilder> responseSchemaConfigurer) {
            SchemaBuilder schemaBuilder = new SchemaBuilder();
            responseSchemaConfigurer.accept(schemaBuilder);
            this.responseSchema = schemaBuilder.build();
            return this;
        }

        private GenerationConfig build() {
            return GenerationConfig.builder()
                    .withTemperature(temperature)
                    .withCandidateCount(candidateCount)
                    .withMaxOutputTokens(maxOutputTokens)
                    .withTopP(topP)
                    .withTopK(topK)
                    .withStopSequences(stopSequences)
                    .withResponseMimeType(responseMimeType)
                    .withResponseSchema(responseSchema)
                    .build();
        }

        /**
         * Inner builder class for configuring the response schema.
         */
        @Getter(AccessLevel.PRIVATE)
        @Setter(AccessLevel.PRIVATE)
        @Accessors(chain = true)
        public static class SchemaBuilder {
            private TypeEnum type;
            private String format;
            private String description;
            private Boolean nullable;
            private List<String> enumValues;
            private Schema items;

            /**
             * Sets the data type for the schema.
             *
             * @param type The data type.
             * @return The builder instance for method chaining.
             */
            public SchemaBuilder withType(TypeEnum type) {
                this.type = type;
                return this;
            }

            /**
             * Sets the format for the schema.
             *
             * @param format The format value.
             * @return The builder instance for method chaining.
             */
            public SchemaBuilder withFormat(String format) {
                this.format = format;
                return this;
            }

            /**
             * Sets the description for the schema.
             *
             * @param description The description value.
             * @return The builder instance for method chaining.
             */
            public SchemaBuilder withDescription(String description) {
                this.description = description;
                return this;
            }

            /**
             * Sets the nullable flag for the schema.
             *
             * @param nullable The nullable flag.
             * @return The builder instance for method chaining.
             */
            public SchemaBuilder withNullable(Boolean nullable) {
                this.nullable = nullable;
                return this;
            }

            /**
             * Adds an enum value to the schema.
             *
             * @param enumValue The enum value to add.
             * @return The builder instance for method chaining.
             */
            public SchemaBuilder withEnumValue(String enumValue) {
                if (this.enumValues == null) {
                    this.enumValues = new ArrayList<>();
                }
                this.enumValues.add(enumValue);
                return this;
            }

            /**
             * Sets the items schema for the schema.
             *
             * @param items The items schema.
             * @return The builder instance for method chaining.
             */
            public SchemaBuilder withItems(Schema items) {
                this.items = items;
                return this;
            }

            private Schema build() {
                return new Schema(type,
                        format, description, nullable, enumValues, items);
            }
        }
    }
}