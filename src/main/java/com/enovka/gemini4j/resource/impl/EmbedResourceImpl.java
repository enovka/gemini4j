package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
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
import org.apache.hc.core5.http.ContentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the {@link EmbedResource} interface for interacting with the embedding
 * resource of the Gemini API. This class provides methods for generating embeddings for text and
 * other types of content using the text-embedding-004 model. It leverages the functionality
 * provided by the {@link AbstractResource} base class for common HTTP operations and error handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class EmbedResourceImpl extends AbstractResource<EmbedResource> implements EmbedResource {

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
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public EmbedResponse execute(EmbedRequest request) throws ResourceException {
        String endpoint = String.format(EMBED_CONTENT_ENDPOINT, geminiClient.getModelName());
        return executeRequest("POST", endpoint, request, EmbedResponse.class);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CompletableFuture<EmbedResponse> executeAsync(EmbedRequest request, AsyncCallback<EmbedResponse> callback) throws ResourceException {
        String endpoint = String.format(EMBED_CONTENT_ENDPOINT, geminiClient.getModelName());

        try {
            // Chain the CompletableFuture returned by postAsync
            CompletableFuture<HttpResponse> httpResponseFuture = httpClient.postAsync(buildEndpointUrl(endpoint), jsonService.serialize(request), buildHeaders(), ContentType.APPLICATION_JSON, new AsyncCallback<>() {
                @Override
                public void onSuccess(HttpResponse httpResponse) {
                    try {
                        callback.onSuccess(deserializeResponse(httpResponse, EmbedResponse.class));
                    } catch (ResourceException e) {
                        callback.onError(e);
                    }
                }

                @Override
                public void onError(Throwable exception) {
                    callback.onError(new ResourceException("Error embedding content", exception));
                }

                @Override
                public void onCanceled() {
                    callback.onCanceled();
                }
            });

            // Create a CompletableFuture<EmbedResponse> that completes when the HttpResponseFuture completes
            CompletableFuture<EmbedResponse> embedResponseFuture = new CompletableFuture<>();
            httpResponseFuture.whenComplete((httpResponse, throwable) -> {
                if (throwable != null) {
                    embedResponseFuture.completeExceptionally(throwable);
                } else {
                    try {
                        embedResponseFuture.complete(deserializeResponse(httpResponse, EmbedResponse.class));
                    } catch (ResourceException e) {
                        embedResponseFuture.completeExceptionally(e);
                    }
                }
            });

            return embedResponseFuture;
        } catch (JsonException e) {
            // Complete the CompletableFuture exceptionally if serialization fails
            CompletableFuture<EmbedResponse> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }
    /**
     * {@inheritDoc}
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
    public CompletableFuture<BatchEmbedResponse> executeAsync(BatchEmbedRequest request, AsyncCallback<BatchEmbedResponse> callback) throws ResourceException {
        String endpoint = String.format(BATCH_EMBED_CONTENTS_ENDPOINT, geminiClient.getModelName());

        try {
            // Chain the CompletableFuture returned by postAsync
            CompletableFuture<HttpResponse> httpResponseFuture = httpClient.postAsync(buildEndpointUrl(endpoint), jsonService.serialize(request), buildHeaders(), ContentType.APPLICATION_JSON, new AsyncCallback<>() {
                @Override
                public void onSuccess(HttpResponse httpResponse) {
                    try {
                        callback.onSuccess(deserializeResponse(httpResponse, BatchEmbedResponse.class));
                    } catch (ResourceException e) {
                        callback.onError(e);
                    }
                }

                @Override
                public void onError(Throwable exception) {
                    callback.onError(new ResourceException("Error batch embedding contents", exception));
                }

                @Override
                public void onCanceled() {
                    callback.onCanceled();
                }
            });

            // Create a CompletableFuture<BatchEmbedResponse> that completes when the HttpResponseFuture completes
            CompletableFuture<BatchEmbedResponse> batchEmbedResponseFuture = new CompletableFuture<>();
            httpResponseFuture.whenComplete((httpResponse, throwable) -> {
                if (throwable != null) {
                    batchEmbedResponseFuture.completeExceptionally(throwable);
                } else {
                    try {
                        batchEmbedResponseFuture.complete(deserializeResponse(httpResponse, BatchEmbedResponse.class));
                    } catch (ResourceException e) {
                        batchEmbedResponseFuture.completeExceptionally(e);
                    }
                }
            });

            return batchEmbedResponseFuture;
        } catch (JsonException e) {
            // Complete the CompletableFuture exceptionally if serialization fails
            CompletableFuture<BatchEmbedResponse> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }
    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public EmbedRequestBuilder embedContentBuilder(String text) {
        return EmbedRequestBuilder.builder()
                .withText(text);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public BatchEmbedRequestBuilder batchEmbedContentsBuilder(List<String> texts) {
        return BatchEmbedRequestBuilder.builder().withTexts(texts);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public List<SupportedModelMethod> getModelMethodList() {
        return SUPPORTED_METHODS;
    }

    private Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>(geminiClient.buildAuthHeaders());
        headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        return headers;
    }
}