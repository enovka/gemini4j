package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.CacheContent;
import com.enovka.gemini4j.model.CacheResponse;
import com.enovka.gemini4j.model.request.CacheRequest;
import com.enovka.gemini4j.model.request.spec.Request;
import com.enovka.gemini4j.model.response.EmptyResponse;
import com.enovka.gemini4j.model.response.ListCacheResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.CacheResource;
import com.enovka.gemini4j.resource.spec.base.AsyncResponse;
import com.enovka.gemini4j.resource.spec.base.BaseAbstractResource;

import java.util.List;

/**
 * <p>Concrete implementation of the {@link CacheResource} interface, providing a high-level
 * API for interacting with the Gemini API's cached content resource. This class extends
 * {@link BaseAbstractResource} and leverages its simplified request execution mechanism,
 * offering convenient methods for creating, retrieving, listing, updating, and deleting
 * cached content. Both synchronous and asynchronous operations are supported through the
 * use of {@link AsyncResponse} objects.</p>
 *
 * <p>This class abstracts away the low-level details of HTTP requests and responses, allowing
 * developers to focus on the specific actions they want to perform with cached content.
 * It handles request building, serialization, execution, deserialization, and error handling
 * internally, providing a clean and intuitive interface for managing cached content in the
 * Gemini API.</p>
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents">Gemini API Cached Contents Documentation</a>
 * @since 0.2.0
 */
public class CacheResourceImpl extends BaseAbstractResource<CacheResponse, CacheRequest> implements CacheResource {

    private static final String DEFAULT_CACHE_ENDPOINT = "cachedContents";

    /**
     * Constructs a new {@code CacheResourceImpl} instance with the given {@link GeminiClient}.
     *
     * @param geminiClient The Gemini client for API communication.
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
    protected String getEndpointForRequest(Request request) {
        if (request instanceof CacheRequest) {
            CacheContent cacheContent = ((CacheRequest) request).getCacheContent();
            return cacheContent.getName() != null ? cacheContent.getName() : DEFAULT_CACHE_ENDPOINT;
        }
        return null;
    }

    @Override
    public CacheResponse createCachedContent(CacheRequest request) throws ResourceException {
        return post(request, CacheResponse.class);
    }

    @Override
    public AsyncResponse<CacheResponse> createCachedContentAsync(CacheRequest request) {
        return postAsync(request, CacheResponse.class);
    }

    @Override
    public ListCacheResponse listCachedContents(Integer pageSize, String pageToken) throws ResourceException {
        return null;
    }

    @Override
    public AsyncResponse<ListCacheResponse> listCachedContentsAsync(Integer pageSize, String pageToken) {
        return null;
    }

    @Override
    public CacheResponse getCachedContent(String name) throws ResourceException {
        return get(name, CacheResponse.class);
    }

    @Override
    public AsyncResponse<CacheResponse> getCachedContentAsync(String name) {
        return getAsync(name, CacheResponse.class);
    }

    @Override
    public CacheResponse updateCachedContent(CacheRequest cache) throws ResourceException {
        return patch(cache, CacheResponse.class);
    }

    @Override
    public AsyncResponse<CacheResponse> updateCachedContentAsync(CacheRequest cache) {
        return patchAsync(cache, CacheResponse.class);
    }

    @Override
    public void deleteCachedContent(String name) throws ResourceException {
        delete(name, EmptyResponse.class);
    }

    @Override
    public AsyncResponse<EmptyResponse> deleteCachedContentAsync(String name) {
        return deleteAsync(name, EmptyResponse.class);
    }

    @Override
    public List<SupportedModelMethod> getSupportedMethods() {
        return List.of(SupportedModelMethod.CREATE_CACHED_CONTENT);
    }
}