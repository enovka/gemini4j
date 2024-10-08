package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.CacheContent;
import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.request.CacheRequest;
import com.enovka.gemini4j.model.response.ListCacheResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.request.CacheRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;
import com.enovka.gemini4j.resource.spec.CacheResource;

import java.util.ArrayList;
import java.util.List;

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
     * Creates a new cached content entry in the Gemini API.
     *
     * @param request The {@link CacheRequest} containing the content and parameters.
     * @return The created {@link CacheContent} object.
     * @throws ResourceException If an error occurs during the API call or response processing.
     * @since 0.2.0
     */
    @Override
    public CacheContent execute(CacheRequest request) throws ResourceException {
        return executeRequest("POST", CACHED_CONTENTS_ENDPOINT, request.getCacheContent(), CacheContent.class);
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
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CacheContent getCachedContent(String name) throws ResourceException {
        String endpoint = CACHED_CONTENTS_ENDPOINT + "/" + name.replace("cachedContents/", "");
        return executeRequest("GET", endpoint, null, CacheContent.class);
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
}