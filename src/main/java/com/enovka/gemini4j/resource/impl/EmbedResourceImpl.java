package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.BatchEmbedContentsRequest;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.BatchEmbedContentsResponse;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.builder.BatchEmbedContentsRequestBuilder;
import com.enovka.gemini4j.resource.builder.EmbedContentRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.EmbedResource;

import java.util.List;

/**
 * Implementation of the {@link EmbedResource} interface for interacting with
 * the embedding resource of the Gemini API. This class provides methods for
 * generating embeddings for text and other types of content using the
 * text-embedding-004 model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class EmbedResourceImpl extends AbstractResource<EmbedResource>
        implements EmbedResource {

    private static final String EMBED_CONTENT_ENDPOINT
            = "/%s:embedContent";
    private static final String BATCH_EMBED_CONTENTS_ENDPOINT
            = "/%s:batchEmbedContents";

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
        super(geminiClient, jsonService);
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
     *
     * @since 0.1.3
     */
    @Override
    public EmbedContentResponse execute(EmbedContentRequest request)
            throws ResourceException {
        String endpoint = String.format(EMBED_CONTENT_ENDPOINT,
                geminiClient.getModel());
        return executePostRequest(endpoint, request,
                EmbedContentResponse.class);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.1.3
     */
    @Override
    public BatchEmbedContentsResponse execute(
            BatchEmbedContentsRequest request)
            throws ResourceException {
        String endpoint = String.format(BATCH_EMBED_CONTENTS_ENDPOINT,
                geminiClient.getModel());
        return executePostRequest(endpoint, request,
                BatchEmbedContentsResponse.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmbedContentRequestBuilder embedContentBuilder(String text) {
        return EmbedContentRequestBuilder.builder(this)
                .withText(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BatchEmbedContentsRequestBuilder batchEmbedContentsBuilder(
            List<String> texts) {
        return BatchEmbedContentsRequestBuilder.builder(this)
                .withTexts(texts);
    }
}