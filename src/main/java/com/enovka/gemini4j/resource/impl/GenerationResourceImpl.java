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
 * @author Everson Novka &lt;enovka@gmail.com&gt;
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
        if (!modelTool.isGenerationMethodSupported(geminiClient.getModel(), "generateContent"))
            throw new GeminiApiException(450, "Generation method not supported");
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
        GenerateContentRequest request = buildDefaultRequest(userInput);
        return generateContent(request);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   GenerationConfig generationConfig,
                                                   String systemInstructions,
                                                   List<Tool> tools,
                                                   List<SafetySetting> safetySettings,
                                                   String cachedContent)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        GenerateContentRequest request = buildCustomRequest(userInput,
                generationConfig, systemInstructions, tools, safetySettings,
                cachedContent);
        return generateContent(request);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   GenerationConfig generationConfig,
                                                   String systemInstructions,
                                                   List<Tool> tools,
                                                   List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, generationConfig, systemInstructions,
                tools, safetySettings, null);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   GenerationConfig generationConfig,
                                                   String systemInstructions,
                                                   List<Tool> tools)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, generationConfig, systemInstructions,
                tools, null, null);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   GenerationConfig generationConfig,
                                                   String systemInstructions)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, generationConfig, systemInstructions,
                null, null, null);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   GenerationConfig generationConfig)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, generationConfig, null, null, null,
                null);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   String systemInstructions,
                                                   List<Tool> tools,
                                                   List<SafetySetting> safetySettings,
                                                   String cachedContent)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, null, systemInstructions, tools,
                safetySettings, cachedContent);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   String systemInstructions,
                                                   List<Tool> tools,
                                                   List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, null, systemInstructions, tools,
                safetySettings, null);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   String systemInstructions,
                                                   List<Tool> tools)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, null, systemInstructions, tools, null,
                null);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   List<Tool> tools,
                                                   List<SafetySetting> safetySettings,
                                                   String cachedContent)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, null, null, tools, safetySettings,
                cachedContent);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   List<Tool> tools,
                                                   List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, null, null, tools, safetySettings,
                null);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   List<SafetySetting> safetySettings,
                                                   String cachedContent)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, null, null, null, safetySettings,
                cachedContent);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, null, null, null, safetySettings,
                null);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   String cachedContent)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating content with custom configuration.");
        return generateContent(userInput, null, null, null, null,
                cachedContent);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings,
                                                List<String> stopSequences,
                                                double temperature,
                                                int candidateCount,
                                                int maxOutputTokens,
                                                double topP,
                                                int topK)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        GenerationConfig generationConfig = GenerationConfig.builder()
                .withTemperature(temperature)
                .withTopP(topP)
                .withTopK(topK)
                .withCandidateCount(candidateCount)
                .withMaxOutputTokens(maxOutputTokens)
                .withStopSequences(stopSequences)
                .build();
        GenerateContentRequest request = buildCustomRequest(textPrompt,
                generationConfig, null, null, safetySettings, null);
        return generateContent(request);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings,
                                                List<String> stopSequences,
                                                double temperature,
                                                int candidateCount,
                                                int maxOutputTokens,
                                                double topP)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, safetySettings, stopSequences,
                temperature, candidateCount, maxOutputTokens, topP, 0);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings,
                                                List<String> stopSequences,
                                                double temperature,
                                                int candidateCount,
                                                int maxOutputTokens)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, safetySettings, stopSequences,
                temperature, candidateCount, maxOutputTokens, 0.0, 0);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings,
                                                List<String> stopSequences,
                                                double temperature,
                                                int candidateCount)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, safetySettings, stopSequences,
                temperature, candidateCount, 0, 0.0, 0);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings,
                                                List<String> stopSequences,
                                                double temperature)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, safetySettings, stopSequences,
                temperature, 0, 0, 0.0, 0);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings,
                                                List<String> stopSequences)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, safetySettings, stopSequences, 0.0, 0,
                0, 0.0, 0);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings,
                                                double temperature,
                                                int candidateCount,
                                                int maxOutputTokens,
                                                double topP,
                                                int topK)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, safetySettings, null, temperature,
                candidateCount, maxOutputTokens, topP, topK);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings,
                                                double temperature,
                                                int candidateCount,
                                                int maxOutputTokens,
                                                double topP)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, safetySettings, null, temperature,
                candidateCount, maxOutputTokens, topP, 0);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<String> stopSequences,
                                                double temperature,
                                                int candidateCount)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, null, stopSequences, temperature,
                candidateCount, 0, 0.0, 0);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings,
                                                double temperature)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, safetySettings, null, temperature, 0,
                0, 0.0, 0);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, safetySettings, null, 0.0, 0, 0, 0.0,
                0);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentResponse generateText(String textPrompt,
                                                List<String> stopSequences,
                                                double temperature,
                                                int candidateCount,
                                                int maxOutputTokens)
            throws GeminiApiException, JsonException, HttpException {
        logDebug("Generating text with custom configuration.");
        return generateText(textPrompt, null, stopSequences, temperature,
                candidateCount, maxOutputTokens, 0.0, 0);
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
     * @since 0.0.2
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
}