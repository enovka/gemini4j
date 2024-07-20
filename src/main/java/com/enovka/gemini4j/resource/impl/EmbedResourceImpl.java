package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.request.BatchEmbedContentsRequest;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.BatchEmbedContentsResponse;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.builder.BatchEmbedContentsRequestBuilder;
import com.enovka.gemini4j.resource.builder.EmbedContentRequestBuilder;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.EmbedResource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link EmbedResource} interface for interacting with
 * the embedding resource of the Gemini API. This class provides methods for
 * generating embeddings for text and other types of content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class EmbedResourceImpl extends AbstractResource
        implements EmbedResource {

    private static final String EMBED_CONTENT_ENDPOINT
            = "/models/%s:embedContent";
    private static final String BATCH_EMBED_CONTENTS_ENDPOINT
            = "/models/%s:batchEmbedContents";
    private final JsonService jsonService;

    /**
     * Constructs a new EmbedResourceImpl with the required GeminiClient and
     * JsonService.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     * @param jsonService The {@link JsonService} instance to use for JSON
     * serialization/deserialization.
     */
    public EmbedResourceImpl(GeminiClient geminiClient,
                             JsonService jsonService) {
        super(geminiClient);
        this.jsonService = jsonService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GeminiClient getGeminiClient() {
        return geminiClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmbedContentResponse embedContent(EmbedContentRequest request)
            throws GeminiApiException, JsonException {
        //validateGenerationMethodSupport("embedContent");
        logDebug("Generating embedding from endpoint: "
                + EMBED_CONTENT_ENDPOINT);

        String requestBody = jsonService.serialize(request);
        logDebug("Request Body: " + requestBody);

        String endpoint = String.format(EMBED_CONTENT_ENDPOINT,
                geminiClient.getModel());
        Map<String, String> headers = new HashMap<>(
                geminiClient.buildAuthHeaders());

        HttpResponse response = post(endpoint, requestBody, headers);
        logDebug("Response Body: " + response.getBody());

        return jsonService.deserialize(response.getBody(),
                EmbedContentResponse.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BatchEmbedContentsResponse batchEmbedContent(
            BatchEmbedContentsRequest request)
            throws GeminiApiException, JsonException {
        //validateGenerationMethodSupport("batchEmbedContents");
        logDebug("Generating batch embeddings from endpoint: "
                + BATCH_EMBED_CONTENTS_ENDPOINT);

        String requestBody = jsonService.serialize(request);
        logDebug("Request Body: " + requestBody);

        String endpoint = String.format(BATCH_EMBED_CONTENTS_ENDPOINT,
                geminiClient.getModel());
        Map<String, String> headers = new HashMap<>(
                geminiClient.buildAuthHeaders());

        HttpResponse response = post(endpoint, requestBody, headers);
        logDebug("Response Body: " + response.getBody());

        return jsonService.deserialize(response.getBody(),
                BatchEmbedContentsResponse.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmbedContentRequestBuilder embedContentBuilder(String text) {
        return EmbedContentRequestBuilder.builder(geminiClient)
                .withContent(Content.builder()
                        .withParts(Collections.singletonList(Part.builder()
                                .withText(text)
                                .build()))
                        .build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BatchEmbedContentsRequestBuilder batchEmbedContentsBuilder(
            List<String> texts) {
        return BatchEmbedContentsRequestBuilder.builder(geminiClient)
                .withTexts(texts);
    }

    /**
     * Validates if the generation method is supported by the current model.
     *
     * @param generationMethod The generation method to validate.
     * @throws GeminiApiException If the generation method is not supported.
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