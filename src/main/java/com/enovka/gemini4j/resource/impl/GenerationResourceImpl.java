package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.*;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.spec.HttpResponse;
import com.enovka.gemini4j.json.exception.JsonException;
import com.enovka.gemini4j.json.spec.JsonService;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.GenerationResource;

import java.util.*;

/**
 * Implementation of the {@link GenerationResource} interface for interacting
 * with the Generation resource of the Gemini API.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 0.0.1
 */
public class GenerationResourceImpl extends AbstractResource
        implements GenerationResource {

    private static final String GENERATE_CONTENT_ENDPOINT = "/models/%s:generateContent";
    private final JsonService jsonService;

    /**
     * Constructs a new GenerationResourceImpl with the required GeminiClient
     * and JsonService.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     * @param jsonService The {@link JsonService} instance to use for JSON
     * serialization/deserialization.
     */
    public GenerationResourceImpl(GeminiClient geminiClient,
                                  JsonService jsonService) {
        super(geminiClient);
        this.jsonService = jsonService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(
            GenerateContentRequest request)
            throws GeminiApiException, JsonException, HttpException {
        validateGenerationMethodSupport("generateContent");
        logDebug("Generating content from endpoint: "
                + GENERATE_CONTENT_ENDPOINT);
        String requestBody = jsonService.serialize(request);
        logDebug("Request Body: " + requestBody);
        String endpoint = String.format(GENERATE_CONTENT_ENDPOINT, geminiClient.getModel());
        Map<String, String> headers = new HashMap<>(geminiClient.buildAuthHeaders());
        HttpResponse response = post(endpoint, requestBody,
                headers);
        logDebug("Response Body: " + response.getBody());
        return jsonService.deserialize(response.getBody(),
                GenerateContentResponse.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating content for user input: " + userInput);
        return generateContent(buildDefaultRequest(userInput));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating content with system instructions: " + systemInstructions);
        return generateContent(buildRequestWithSystemInstructions(userInput, systemInstructions));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating content with temperature: " + temperature);
        return generateContent(buildRequestWithTemperature(userInput, systemInstructions, temperature));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating content with outputJson: " + outputJson);
        return generateContent(buildRequestWithOutputJson(userInput, systemInstructions, temperature, outputJson));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson,
                                            double topP)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating content with topP: " + topP);
        return generateContent(buildRequestWithTopP(userInput, systemInstructions, temperature, outputJson, topP));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson,
                                            double topP,
                                            List<SafetySetting> safetySettings)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating content with safety settings.");
        return generateContent(buildRequestWithSafetySettings(userInput, systemInstructions, temperature, outputJson, topP, safetySettings));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson,
                                            double topP,
                                            List<SafetySetting> safetySettings,
                                            List<Tool> tools)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating content with tools.");
        return generateContent(buildRequestWithTools(userInput, systemInstructions, temperature, outputJson, topP, safetySettings, tools));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson,
                                            double topP,
                                            List<SafetySetting> safetySettings,
                                            List<Tool> tools,
                                            GenerationConfig generationConfig)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating content with generation config.");
        return generateContent(buildRequestWithGenerationConfig(userInput, systemInstructions, temperature, outputJson, topP, safetySettings, tools, generationConfig));
    }

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating text for prompt: " + textPrompt);
        return generateText(textPrompt, null, 0.0, 0, 0, 0.0, 0, null);
    }

    /**
     * Generates text from the model given an input text prompt and safety
     * settings.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating text with safety settings.");
        return generateText(textPrompt, safetySettings, 0.0, 0, 0, 0.0, 0, null);
    }

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, and temperature.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating text with temperature: " + temperature);
        return generateText(textPrompt, safetySettings, temperature, 0, 0, 0.0, 0, null);
    }

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, and candidate count.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating text with candidate count: " + candidateCount);
        return generateText(textPrompt, safetySettings, temperature, candidateCount, 0, 0.0, 0, null);
    }

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, candidate count, and max output tokens.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating text with max output tokens: " + maxOutputTokens);
        return generateText(textPrompt, safetySettings, temperature, candidateCount, maxOutputTokens, 0.0, 0, null);
    }

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, candidate count, max output tokens, and topP
     * value.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating text with topP: " + topP);
        return generateText(textPrompt, safetySettings, temperature, candidateCount, maxOutputTokens, topP, 0, null);
    }

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, candidate count, max output tokens, topP value,
     * and topK value.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @param topK The maximum number of tokens to consider when sampling.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP,
                                         int topK)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating text with topK: " + topK);
        return generateText(textPrompt, safetySettings, temperature, candidateCount, maxOutputTokens, topP, topK, null);
    }

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, candidate count, max output tokens, topP value,
     * topK value, and stop sequences.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @param topK The maximum number of tokens to consider when sampling.
     * @param stopSequences The stop sequences to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP,
                                         int topK,
                                         List<String> stopSequences)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Generating text with stop sequences.");
        GenerationConfig generationConfig = GenerationConfig.builder()
                .withTemperature(temperature)
                .withTopP(topP)
                .withTopK(topK)
                .withCandidateCount(candidateCount)
                .withMaxOutputTokens(maxOutputTokens)
                .withStopSequences(stopSequences)
                .build();
        return generateContent(buildCustomRequest(textPrompt, generationConfig, null, null, safetySettings, null));
    }

    /**
     * Builds a default {@link GenerateContentRequest} with user input.
     *
     * @param userInput The user's input text.
     * @return A default {@link GenerateContentRequest}.
     */
    private GenerateContentRequest buildDefaultRequest(String userInput) {
        logDebug("Building default GenerateContentRequest.");
        Content content = Content.builder()
                .withParts(Collections.singletonList(
                        Part.builder().withText(userInput).build()))
                .withRole("user")
                .build();
        return GenerateContentRequest.builder()
                .withModel(geminiClient.getModel())
                .withContents(Collections.singletonList(content))
                .build();
    }

    /**
     * Builds a {@link GenerateContentRequest} with user input and system
     * instructions.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @return A {@link GenerateContentRequest} with system instructions.
     */
    private GenerateContentRequest buildRequestWithSystemInstructions(String userInput,
                                                                    String systemInstructions) {
        logDebug("Building GenerateContentRequest with system instructions.");
        return buildCustomRequest(userInput, null, systemInstructions, null, null, null);
    }

    /**
     * Builds a {@link GenerateContentRequest} with user input, system
     * instructions, and temperature.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @return A {@link GenerateContentRequest} with temperature.
     */
    private GenerateContentRequest buildRequestWithTemperature(String userInput,
                                                                String systemInstructions,
                                                                double temperature) {
        logDebug("Building GenerateContentRequest with temperature.");
        GenerationConfig generationConfig = GenerationConfig.builder()
                .withTemperature(temperature)
                .build();
        return buildCustomRequest(userInput, generationConfig, systemInstructions, null, null, null);
    }

    /**
     * Builds a {@link GenerateContentRequest} with user input, system
     * instructions, temperature, and outputJson flag.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @return A {@link GenerateContentRequest} with outputJson flag.
     */
    private GenerateContentRequest buildRequestWithOutputJson(String userInput,
                                                                String systemInstructions,
                                                                double temperature,
                                                                boolean outputJson) {
        logDebug("Building GenerateContentRequest with outputJson.");
        GenerationConfig generationConfig = GenerationConfig.builder()
                .withTemperature(temperature)
                .withResponseMimeType(outputJson ? "application/json" : "text/plain")
                .build();
        return buildCustomRequest(userInput, generationConfig, systemInstructions, null, null, null);
    }

    /**
     * Builds a {@link GenerateContentRequest} with user input, system
     * instructions, temperature, outputJson flag, and topP value.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @return A {@link GenerateContentRequest} with topP value.
     */
    private GenerateContentRequest buildRequestWithTopP(String userInput,
                                                                String systemInstructions,
                                                                double temperature,
                                                                boolean outputJson,
                                                                double topP) {
        logDebug("Building GenerateContentRequest with topP.");
        GenerationConfig generationConfig = GenerationConfig.builder()
                .withTemperature(temperature)
                .withResponseMimeType(outputJson ? "application/json" : "text/plain")
                .withTopP(topP)
                .build();
        return buildCustomRequest(userInput, generationConfig, systemInstructions, null, null, null);
    }

    /**
     * Builds a {@link GenerateContentRequest} with user input, system
     * instructions, temperature, outputJson flag, topP value, and safety
     * settings.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @param safetySettings The safety settings to apply.
     * @return A {@link GenerateContentRequest} with safety settings.
     */
    private GenerateContentRequest buildRequestWithSafetySettings(String userInput,
                                                                    String systemInstructions,
                                                                    double temperature,
                                                                    boolean outputJson,
                                                                    double topP,
                                                                    List<SafetySetting> safetySettings) {
        logDebug("Building GenerateContentRequest with safety settings.");
        GenerationConfig generationConfig = GenerationConfig.builder()
                .withTemperature(temperature)
                .withResponseMimeType(outputJson ? "application/json" : "text/plain")
                .withTopP(topP)
                .build();
        return buildCustomRequest(userInput, generationConfig, systemInstructions, null, safetySettings, null);
    }

    /**
     * Builds a {@link GenerateContentRequest} with user input, system
     * instructions, temperature, outputJson flag, topP value, safety
     * settings, and tools.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @param safetySettings The safety settings to apply.
     * @param tools The tools to use for generation.
     * @return A {@link GenerateContentRequest} with tools.
     */
    private GenerateContentRequest buildRequestWithTools(String userInput,
                                                                    String systemInstructions,
                                                                    double temperature,
                                                                    boolean outputJson,
                                                                    double topP,
                                                                    List<SafetySetting> safetySettings,
                                                                    List<Tool> tools) {
        logDebug("Building GenerateContentRequest with tools.");
        GenerationConfig generationConfig = GenerationConfig.builder()
                .withTemperature(temperature)
                .withResponseMimeType(outputJson ? "application/json" : "text/plain")
                .withTopP(topP)
                .build();
        return buildCustomRequest(userInput, generationConfig, systemInstructions, tools, safetySettings, null);
    }

    /**
     * Builds a {@link GenerateContentRequest} with user input, system
     * instructions, temperature, outputJson flag, topP value, safety
     * settings, tools, and generation config.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @param safetySettings The safety settings to apply.
     * @param tools The tools to use for generation.
     * @param generationConfig The generation configuration.
     * @return A {@link GenerateContentRequest} with generation config.
     */
    private GenerateContentRequest buildRequestWithGenerationConfig(String userInput,
                                                                    String systemInstructions,
                                                                    double temperature,
                                                                    boolean outputJson,
                                                                    double topP,
                                                                    List<SafetySetting> safetySettings,
                                                                    List<Tool> tools,
                                                                    GenerationConfig generationConfig) {
        logDebug("Building GenerateContentRequest with generation config.");
        return buildCustomRequest(userInput, generationConfig, systemInstructions, tools, safetySettings, null);
    }

    /**
     * Builds a custom {@link GenerateContentRequest} with provided
     * parameters.
     *
     * @param userInput The user's input text.
     * @param generationConfig The generation configuration.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param tools The tools to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param cachedContent The cached content to use as context.
     * @return A custom {@link GenerateContentRequest}.
     */
    private GenerateContentRequest buildCustomRequest(String userInput,
                                                      GenerationConfig generationConfig,
                                                      String systemInstructions,
                                                      List<Tool> tools,
                                                      List<SafetySetting> safetySettings,
                                                      String cachedContent) {
        logDebug("Building custom GenerateContentRequest.");
        List<Content> contents = new ArrayList<>();
        Content systemInstructionsContent = null;
        if (systemInstructions != null) {
            systemInstructionsContent = Content.builder()
                    .withParts(Collections.singletonList(
                            Part.builder().withText(systemInstructions).build()))
                    .withRole("system")
                    .build();
        }
        contents.add(Content.builder()
                .withParts(Collections.singletonList(
                        Part.builder().withText(userInput).build()))
                .withRole("user")
                .build());
        return GenerateContentRequest.builder()
                .withModel(geminiClient.getModel())
                .withContents(contents)
                .withGenerationConfig(generationConfig)
                .withSystemInstruction(systemInstructionsContent)
                .withTools(tools)
                .withSafetySettings(safetySettings)
                .withCachedContent(cachedContent)
                .build();
    }

    /**
     * Validates if the generation method is supported by the current model.
     *
     * @param generationMethod The generation method to validate.
     * @throws GeminiApiException If the generation method is not supported.
     */
    private void validateGenerationMethodSupport(String generationMethod)
            throws GeminiApiException {
        if (!modelTool.isGenerationMethodSupported(geminiClient.getModel(), generationMethod)) {
            throw new GeminiApiException(450, "Generation method '" + generationMethod + "' is not supported by the model.");
        }
    }
}