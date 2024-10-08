package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.request.BatchEmbedRequest;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.model.response.BatchEmbedResponse;
import com.enovka.gemini4j.model.response.EmbedResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.request.BatchEmbedRequestBuilder;
import com.enovka.gemini4j.resource.builder.request.EmbedRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.EmbedResource;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;

import java.util.List;

/**
 * Implementation of the {@link EmbedResource} interface for interacting with
 * the embedding resource of the Gemini API. This class provides methods for
 * generating embeddings for text and other types of content using the
 * text-embedding-004 model.  It leverages the functionality provided by the
 * {@link AbstractResource} base class for common HTTP operations and error handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class EmbedResourceImpl extends AbstractResource<EmbedResource>
        implements EmbedResource {

    private static final String EMBED_CONTENT_ENDPOINT = "%s:embedContent";
    private static final String BATCH_EMBED_CONTENTS_ENDPOINT = "%s:batchEmbedContents";
    private static final List<SupportedModelMethod> SUPPORTED_METHODS = List.of(SupportedModelMethod.EMBED_CONTENT);

    /**
     * Constructs a new EmbedResourceImpl with the required GeminiClient.
     *
     * @param geminiClient The Gemini client for API communication.
     * @since 0.2.0
     */
    public EmbedResourceImpl(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * Generates a single embedding.
     *
     * @param request The embedding request.
     * @return The embedding response.
     * @throws ResourceException If an error occurs.
     * @since 0.2.0
     */
    @Override
    public EmbedResponse execute(EmbedRequest request) throws ResourceException {
        String endpoint = String.format(EMBED_CONTENT_ENDPOINT, geminiClient.getModelName());
        return executeRequest("POST", endpoint, request, EmbedResponse.class);
    }

    /**
     * Generates multiple embeddings in a batch.
     *
     * @param request The batch embedding request.
     * @return The batch embedding response.
     * @throws ResourceException If an error occurs.
     * @since 0.2.0
     */
    @Override
    public BatchEmbedResponse execute(BatchEmbedRequest request) throws ResourceException {
        String endpoint = String.format(BATCH_EMBED_CONTENTS_ENDPOINT, geminiClient.getModelName());
        return executeRequest("POST", endpoint, request, BatchEmbedResponse.class);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public EmbedRequestBuilder embedContentBuilder(String text) {
        return EmbedRequestBuilder.builder().withText(text);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public BatchEmbedRequestBuilder batchEmbedContentsBuilder(List<String> texts) {
        return BatchEmbedRequestBuilder.builder().withTexts(texts);
    }

    @Override
    public List<SupportedModelMethod> getModelMethodList() {
        return SUPPORTED_METHODS;
    }
}