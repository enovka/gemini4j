package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.exception.GeminiApiError;
import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.infrastructure.exception.GeminiInfrastructureException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.builder.GenerateContentRequestBuilder;
import com.enovka.gemini4j.resource.builder.GenerateTextRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.AbstractMultiTurnConversationResource;
import com.enovka.gemini4j.resource.spec.GenerationResource;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@link GenerationResource} interface for interacting
 * with the Generation resource of the Gemini API. This class provides methods
 * for generating text, chat messages, and other content using the Gemini Pro,
 * Gemini Flash, and other compatible models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class GenerationResourceImpl
        extends AbstractMultiTurnConversationResource<GenerateContentResponse>
        implements GenerationResource {

    private static final String GENERATE_CONTENT_ENDPOINT
            = "%s:generateContent";

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
            throws ResourceException {
        try {
            validateGenerationMethodSupport("generateContent");
            logDebug("Generating content from endpoint: "
                    + GENERATE_CONTENT_ENDPOINT);

            request = prepareMultiTurnRequest(request);

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

            if (contentResponse.getError() != null) {
                throw new GeminiApiException(GeminiApiError.UNKNOWN,
                        "Gemini API returned an error: "
                                + contentResponse.getError().getMessage());
            }

            // Add the generated response to the conversation history
            multiTurnConversation.addContent(
                    contentResponse.getCandidates().get(0).getContent());

            return GeminiResult.builder()
                    .withGenerateContentResponse(contentResponse)
                    .build();
        } catch (JsonException e) {
            throw new ResourceException(
                    "Error deserializing generation response", e);
        } catch (GeminiInfrastructureException e) {
            throw new ResourceException(
                    "Infrastructure error generating content", e);
        } catch (GeminiApiException e) {
            throw new ResourceException("Api error generating content", e);
        }
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
            throw new GeminiApiException(GeminiApiError.INVALID_ARGUMENT,
                    "Generation method '" + generationMethod
                            + "' is not supported by the model.");
        }
    }
}