package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.model.CacheContent;
import com.enovka.gemini4j.model.request.CacheRequest;
import com.enovka.gemini4j.model.response.ListCacheResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.request.CacheRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.CacheResource;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;
import org.apache.hc.core5.http.ContentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the {@link CacheResource} interface for interacting with the cached
 * content resource of the Gemini API. This class provides methods for creating, listing, getting,
 * updating, and deleting cached content. It leverages the functionality provided by the
 * {@link AbstractResource} base class for common HTTP operations and error handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class CacheResourceImpl extends AbstractResource<CacheResource>
        implements CacheResource {

    private static final String CACHED_CONTENTS_ENDPOINT = "cachedContents";
    private static final String UPDATE_MASK_QUERY_PARAM = "updateMask";

    /**
     * Constructs a new {@code CacheResourceImpl} with the provided {@link GeminiClient}.
     *
     * @param geminiClient The Gemini client for making API calls.
     * @since 0.2.0
     */
    public CacheResourceImpl(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CacheContent execute(CacheRequest request) throws ResourceException {
        return executeRequest("POST", CACHED_CONTENTS_ENDPOINT, request.getCacheContent(), CacheContent.class);
    }

    /**
     * Executes a request asynchronously to create cached content.
     *
     * @param request  The {@link CacheRequest} containing the content to cache and other parameters.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<CacheContent> executeAsync(CacheRequest request, AsyncCallback<CacheContent> callback) throws ResourceException {
        try {
            // Chain the CompletableFuture returned by postAsync
            CompletableFuture<HttpResponse> httpResponseFuture = httpClient.postAsync(buildEndpointUrl(CACHED_CONTENTS_ENDPOINT), jsonService.serialize(request.getCacheContent()), buildHeaders(), ContentType.APPLICATION_JSON, new AsyncCallback<>() {
                @Override
                public void onSuccess(HttpResponse httpResponse) {
                    try {
                        callback.onSuccess(deserializeResponse(httpResponse, CacheContent.class));
                    } catch (ResourceException e) {
                        callback.onError(e);
                    }
                }

                @Override
                public void onError(Throwable exception) {
                    callback.onError(new ResourceException("Error creating cached content", exception));
                }

                @Override
                public void onCanceled() {
                    callback.onCanceled();
                }
            });

            // Create a CompletableFuture<CacheContent> that completes when the HttpResponseFuture completes
            CompletableFuture<CacheContent> cacheContentFuture = new CompletableFuture<>();
            httpResponseFuture.whenComplete((httpResponse, throwable) -> {
                if (throwable != null) {
                    cacheContentFuture.completeExceptionally(throwable);
                } else {
                    try {
                        cacheContentFuture.complete(deserializeResponse(httpResponse, CacheContent.class));
                    } catch (ResourceException e) {
                        cacheContentFuture.completeExceptionally(e);
                    }
                }
            });

            return cacheContentFuture;
        } catch (JsonException e) {
            // Complete the CompletableFuture exceptionally if serialization fails
            CompletableFuture<CacheContent> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public ListCacheResponse listCachedContents(Integer pageSize, String pageToken) throws ResourceException {
        StringBuilder uri = new StringBuilder(CACHED_CONTENTS_ENDPOINT);
        addQueryParam(uri, "pageSize", pageSize);
        addQueryParam(uri, "pageToken", pageToken);
        return executeRequest("GET", uri.toString(), null, ListCacheResponse.class);
    }

    /**
     * Lists cached contents asynchronously.
     *
     * @param pageSize  The maximum number of cached contents to return.
     * @param pageToken A page token, received from a previous cachedContents.list call.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<ListCacheResponse> listCachedContentsAsync(Integer pageSize, String pageToken, AsyncCallback<ListCacheResponse> callback) throws ResourceException {
        StringBuilder uri = new StringBuilder(CACHED_CONTENTS_ENDPOINT);
        addQueryParam(uri, "pageSize", pageSize);
        addQueryParam(uri, "pageToken", pageToken);

        // Chain the CompletableFuture returned by getAsync
        CompletableFuture<HttpResponse> httpResponseFuture = httpClient.getAsync(buildEndpointUrl(uri.toString()), buildHeaders(), new AsyncCallback<>() {
            @Override
            public void onSuccess(HttpResponse httpResponse) {
                try {
                    callback.onSuccess(deserializeResponse(httpResponse, ListCacheResponse.class));
                } catch (ResourceException e) {
                    callback.onError(e);
                }
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(new ResourceException("Error listing cached contents", exception));
            }

            @Override
            public void onCanceled() {
                callback.onCanceled();
            }
        });

        // Create a CompletableFuture<ListCacheResponse> that completes when the HttpResponseFuture completes
        CompletableFuture<ListCacheResponse> listCacheResponseFuture = new CompletableFuture<>();
        httpResponseFuture.whenComplete((httpResponse, throwable) -> {
            if (throwable != null) {
                listCacheResponseFuture.completeExceptionally(throwable);
            } else {
                try {
                    listCacheResponseFuture.complete(deserializeResponse(httpResponse, ListCacheResponse.class));
                } catch (ResourceException e) {
                    listCacheResponseFuture.completeExceptionally(e);
                }
            }
        });

        return listCacheResponseFuture;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CacheContent getCachedContent(String name) throws ResourceException {
        String endpoint = CACHED_CONTENTS_ENDPOINT + "/" + name.replace("cachedContents/", "");
        return executeRequest("GET", endpoint, null, CacheContent.class);
    }

    /**
     * Reads a cached content asynchronously.
     *
     * @param name     The resource name referring to the content cache entry.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<CacheContent> getCachedContentAsync(String name, AsyncCallback<CacheContent> callback) throws ResourceException {
        String endpoint = CACHED_CONTENTS_ENDPOINT + "/" + name.replace("cachedContents/", "");

        // Chain the CompletableFuture returned by getAsync
        CompletableFuture<HttpResponse> httpResponseFuture = httpClient.getAsync(buildEndpointUrl(endpoint), buildHeaders(), new AsyncCallback<>() {
            @Override
            public void onSuccess(HttpResponse httpResponse) {
                try {
                    callback.onSuccess(deserializeResponse(httpResponse, CacheContent.class));
                } catch (ResourceException e) {
                    callback.onError(e);
                }
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(new ResourceException("Error getting cached content", exception));
            }

            @Override
            public void onCanceled() {
                callback.onCanceled();
            }
        });

        // Create a CompletableFuture<CacheContent> that completes when the HttpResponseFuture completes
        CompletableFuture<CacheContent> cacheContentFuture = new CompletableFuture<>();
        httpResponseFuture.whenComplete((httpResponse, throwable) -> {
            if (throwable != null) {
                cacheContentFuture.completeExceptionally(throwable);
            } else {
                try {
                    cacheContentFuture.complete(deserializeResponse(httpResponse, CacheContent.class));
                } catch (ResourceException e) {
                    cacheContentFuture.completeExceptionally(e);
                }
            }
        });

        return cacheContentFuture;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CacheContent updateCachedContent(CacheContent cacheContent, String updateMask, String name) throws ResourceException {
        StringBuilder uri = new StringBuilder(CACHED_CONTENTS_ENDPOINT + "/" + name.replace("cachedContents/", ""));
        addQueryParam(uri, UPDATE_MASK_QUERY_PARAM, updateMask);
        clearAttributesForUpdate(cacheContent);
        return executeRequest("PATCH", uri.toString(), cacheContent, CacheContent.class);
    }

    /**
     * Updates a cached content asynchronously.
     *
     * @param cacheContent The {@link CacheContent} containing the content to update and other parameters.
     * @param updateMask    The list of fields to update.
     * @param name         The resource name referring to the content cache entry.
     * @param callback     The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<CacheContent> updateCachedContentAsync(CacheContent cacheContent, String updateMask, String name, AsyncCallback<CacheContent> callback) throws ResourceException {
        StringBuilder uri = new StringBuilder(CACHED_CONTENTS_ENDPOINT + "/" + name.replace("cachedContents/", ""));
        addQueryParam(uri, UPDATE_MASK_QUERY_PARAM, updateMask);
        clearAttributesForUpdate(cacheContent);

        try {
            // Chain the CompletableFuture returned by patchAsync
            CompletableFuture<HttpResponse> httpResponseFuture = httpClient.patchAsync(buildEndpointUrl(uri.toString()), jsonService.serialize(cacheContent), buildHeaders(), ContentType.APPLICATION_JSON, new AsyncCallback<>() {
                @Override
                public void onSuccess(HttpResponse httpResponse) {
                    try {
                        callback.onSuccess(deserializeResponse(httpResponse, CacheContent.class));
                    } catch (ResourceException e) {
                        callback.onError(e);
                    }
                }

                @Override
                public void onError(Throwable exception) {
                    callback.onError(new ResourceException("Error updating cached content", exception));
                }

                @Override
                public void onCanceled() {
                    callback.onCanceled();
                }
            });

            // Create a CompletableFuture<CacheContent> that completes when the HttpResponseFuture completes
            CompletableFuture<CacheContent> cacheContentFuture = new CompletableFuture<>();
            httpResponseFuture.whenComplete((httpResponse, throwable) -> {
                if (throwable != null) {
                    cacheContentFuture.completeExceptionally(throwable);
                } else {
                    try {
                        cacheContentFuture.complete(deserializeResponse(httpResponse, CacheContent.class));
                    } catch (ResourceException e) {
                        cacheContentFuture.completeExceptionally(e);
                    }
                }
            });

            return cacheContentFuture;
        } catch (JsonException e) {
            // Complete the CompletableFuture exceptionally if serialization fails
            CompletableFuture<CacheContent> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }

    private void clearAttributesForUpdate(CacheContent cacheContent) {
        cacheContent.setUsageMetadata(null);
        cacheContent.setCreateTime(null);
        cacheContent.setUpdateTime(null);
        cacheContent.setName(null);
        cacheContent.setContents(null);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public void deleteCachedContent(String name) throws ResourceException {
        String endpoint = CACHED_CONTENTS_ENDPOINT + "/" + name.replace("cachedContents/", "");
        executeRequest("DELETE", endpoint, null, Void.class);
    }

    /**
     * Deletes a cached content asynchronously.
     *
     * @param name     The resource name referring to the content cache entry.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<Void> deleteCachedContentAsync(String name, AsyncCallback<Void> callback) throws ResourceException {
        String endpoint = CACHED_CONTENTS_ENDPOINT + "/" + name.replace("cachedContents/", "");

        // Chain the CompletableFuture returned by deleteAsync
        CompletableFuture<HttpResponse> httpResponseFuture = httpClient.deleteAsync(buildEndpointUrl(endpoint), buildHeaders(), new AsyncCallback<>() {
            @Override
            public void onSuccess(HttpResponse httpResponse) {
                callback.onSuccess(null);
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(new ResourceException("Error deleting cached content", exception));
            }

            @Override
            public void onCanceled() {
                callback.onCanceled();
            }
        });

        // Create a CompletableFuture<Void> that completes when the HttpResponseFuture completes
        CompletableFuture<Void> voidCompletableFuture = new CompletableFuture<>();
        httpResponseFuture.whenComplete((httpResponse, throwable) -> {
            if (throwable != null) {
                voidCompletableFuture.completeExceptionally(throwable);
            } else {
                voidCompletableFuture.complete(null);
            }
        });

        return voidCompletableFuture;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CacheRequestBuilder createCachedContentBuilder(String modelName) {
        return CacheRequestBuilder.builder().withModel(modelName);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public List<SupportedModelMethod> getModelMethodList() {
        return List.of(SupportedModelMethod.CREATE_CACHED_CONTENT);
    }

    private Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>(geminiClient.buildAuthHeaders());
        headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        return headers;
    }
}