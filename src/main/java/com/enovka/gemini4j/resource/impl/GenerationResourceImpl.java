package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.http.spec.HttpResponse;
import com.enovka.gemini4j.json.spec.JsonService;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.GenerationResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            = "/{model}:generateContent";

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
     */
    @Override
    public GenerateContentResponse generateContent(
            GenerateContentRequest request) throws GeminiApiException {
        String model = request.getModel();
        if (model == null || model.isEmpty()) {
            throw new GeminiApiException(400,
                    "Model is required in the GenerateContentRequest.");
        }

        String endpoint = GENERATE_CONTENT_ENDPOINT.replace("{model}", model);
        logDebug("Generating content from endpoint: " + endpoint);

        try {
            String requestBody = jsonService.serialize(request);
            HttpResponse response = post(endpoint, requestBody,
                    geminiClient.buildAuthHeaders());
            return jsonService.deserialize(response.getBody(),
                    GenerateContentResponse.class);
        } catch (Exception e) {
            logError("Error generating content: " + e.getMessage(), e);
            throw new GeminiApiException(500,
                    "Error generating content: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput)
            throws GeminiApiException {
        return generateContent(userInput, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentResponse generateContent(String userInput,
                                                   String systemInstructions)
            throws GeminiApiException {
        Content systemContent = null;
        Content content = Content.builder()
                .withRole("user")
                .withParts(Collections.singletonList(
                        Part.builder().withText(userInput).build()))
                .build();

        if (systemInstructions != null) {
            systemContent = Content.builder()
                    .withRole("system")
                    .withParts(Collections.singletonList(
                            Part.builder().withText(systemInstructions)
                                    .build()))
                    .build();
        }

        GenerateContentRequest request = GenerateContentRequest.builder()
                .withModel(geminiClient.getModel())
                .withContents(new ArrayList<>(List.of(content)))
                .withSystemInstruction(systemContent)
                .build();

        return generateContent(request);
    }
}