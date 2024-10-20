package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.request.BatchEmbedRequest;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.model.request.spec.Request;
import com.enovka.gemini4j.model.response.BatchEmbedResponse;
import com.enovka.gemini4j.model.response.EmbedResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.EmbedResource;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;
import com.enovka.gemini4j.resource.spec.base.AsyncResponse;
import com.enovka.gemini4j.resource.spec.base.BaseAbstractResource;
import org.apache.hc.core5.http.ContentType;

import java.util.List;

/**
 * Implementation of the {@link EmbedResource} interface for interacting with the embedding
 * resource of the Gemini API. This class provides methods for generating embeddings for text and
 * other types of content using the text-embedding-004 model. It leverages the functionality
 * provided by the {@link AbstractResource} base class for common HTTP operations and error handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class EmbedResourceImpl extends BaseAbstractResource<EmbedResponse, EmbedRequest> implements EmbedResource {

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

    @Override
    protected String getEndpointForRequest(Request request) {
        return EMBED_CONTENT_ENDPOINT;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public EmbedResponse execute(EmbedRequest request) throws ResourceException {
        return post(request, EmbedResponse.class);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public AsyncResponse<EmbedResponse> executeAsync(EmbedRequest request){
        String endpoint = String.format(EMBED_CONTENT_ENDPOINT, geminiClient.getModelName());
        return executeRequestAsync("POST", endpoint, request, ContentType.APPLICATION_JSON, EmbedResponse.class);
    }
    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public BatchEmbedResponse execute(BatchEmbedRequest request) throws ResourceException {
        String endpoint = String.format(BATCH_EMBED_CONTENTS_ENDPOINT, geminiClient.getModelName());
        return executeRequest("POST", endpoint, request, ContentType.APPLICATION_JSON, BatchEmbedResponse.class);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public AsyncResponse<BatchEmbedResponse> executeAsync(BatchEmbedRequest request) {
        String endpoint = String.format(BATCH_EMBED_CONTENTS_ENDPOINT, geminiClient.getModelName());
        return executeRequestAsync("POST", endpoint, request, ContentType.APPLICATION_JSON, BatchEmbedResponse.class);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public List<SupportedModelMethod> getSupportedMethods() {
        return SUPPORTED_METHODS;
    }

}