package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.builder.GenerateContentRequestBuilder;
import com.enovka.gemini4j.resource.builder.GenerateTextRequestBuilder;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.GenerationResource;

/**
 * Implementation of the {@link GenerationResource} interface for interacting
 * with the Generation resource of the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class GenerationResourceImpl
        extends AbstractResource
        implements GenerationResource {

    private static final String GENERATE_CONTENT_ENDPOINT
            = "/models/%s:generateContent";

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
        super(geminiClient, jsonService);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GeminiClient getGeminiClient() {
        return geminiClient;
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GeminiResult generateContent(GenerateContentRequest request)
            throws GeminiApiException, JsonException {
        validateGenerationMethodSupport("generateContent");
        String endpoint = String.format(GENERATE_CONTENT_ENDPOINT,
                geminiClient.getModel());
        GenerateContentResponse contentResponse = executePostRequest(endpoint,
                request, GenerateContentResponse.class);
        return GeminiResult.builder()
                .withGenerateContentResponse(contentResponse)
                .build();
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateContentRequestBuilder generateContentBuilder(
            String userInput) {
        return GenerateContentRequestBuilder.builder(geminiClient)
                .withUserInput(userInput);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public GenerateTextRequestBuilder generateTextBuilder(String userInput) {
        return new GenerateTextRequestBuilder(this)
                .withUserInput(userInput);
    }

    /**
     * Validates if the generation method is supported by the current model.
     *
     * @param generationMethod The generation method to validate.
     * @throws GeminiApiException If the generation method is not supported.
     * @since 0.0.2
     */
    private void validateGenerationMethodSupport(String generationMethod)
            throws GeminiApiException {
        if (!getModelTool().isGenerationMethodSupported(geminiClient.getModel(),
                generationMethod)) {
            throw new GeminiApiException(450,
                    "Generation method '" + generationMethod
                            + "' is not supported by the model.");
        }
    }
}