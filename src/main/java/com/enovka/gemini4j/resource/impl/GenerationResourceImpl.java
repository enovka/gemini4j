package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.builder.GenerateContentRequestBuilder;
import com.enovka.gemini4j.resource.builder.GenerateTextRequestBuilder;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.GenerationResource;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@link GenerationResource} interface for interacting
 * with the Generation resource of the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class GenerationResourceImpl extends AbstractResource
        implements GenerationResource {

    private static final String GENERATE_CONTENT_ENDPOINT
            = "/models/%s:generateContent";
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
            throws GeminiApiException, JsonException, HttpException {
        validateGenerationMethodSupport("generateContent");
        logDebug("Generating content from endpoint: "
                + GENERATE_CONTENT_ENDPOINT);

        String requestBody = jsonService.serialize(request);
        logDebug("Request Body: " + requestBody);

        String endpoint = String.format(GENERATE_CONTENT_ENDPOINT,
                geminiClient.getModel());
        Map<String, String> headers = new HashMap<>(
                geminiClient.buildAuthHeaders());

        HttpResponse response = post(endpoint, requestBody, headers);
        logDebug("Response Body: " + response.getBody());

        GenerateContentResponse contentResponse = jsonService.deserialize(
                response.getBody(), GenerateContentResponse.class);
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