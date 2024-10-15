// com.enovka.gemini4j.resource.spec.CacheResource
package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.model.CacheContent;
import com.enovka.gemini4j.model.request.CacheRequest;
import com.enovka.gemini4j.model.response.ListCacheResponse;
import com.enovka.gemini4j.resource.builder.request.CacheRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.Resource;

import java.util.concurrent.CompletableFuture;

/**
 * Interface defining the contract for interacting with the cached content resource of the Gemini
 * API. This resource provides methods for creating, listing, getting, updating, and deleting
 * cached content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public interface CacheResource extends Resource {

    /**
     * Executes a request to create cached content.
     *
     * @param request The {@link CacheRequest} containing the content to cache and other parameters.
     * @return An {@link CacheContent} containing the generated cached content.
     * @throws ResourceException If an error occurs during the cached content generation process.
     * @since 0.1.3
     */
    CacheContent execute(CacheRequest request) throws ResourceException;

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
    CompletableFuture<CacheContent> executeAsync(CacheRequest request, AsyncCallback<CacheContent> callback) throws ResourceException;

    /**
     * Lists cached contents.
     *
     * @param pageSize  The maximum number of cached contents to return.
     * @param pageToken A page token, received from a previous cachedContents.list call.
     * @return A {@link ListCacheResponse} containing a list of cached contents.
     * @throws ResourceException If an error occurs during the cached contents listing process.
     * @since 0.1.0
     */
    ListCacheResponse listCachedContents(Integer pageSize, String pageToken) throws ResourceException;

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
    CompletableFuture<ListCacheResponse> listCachedContentsAsync(Integer pageSize, String pageToken, AsyncCallback<ListCacheResponse> callback) throws ResourceException;

    /**
     * Reads a cached content.
     *
     * @param name The resource name referring to the content cache entry.
     * @return A {@link CacheContent} containing the cached content.
     * @throws ResourceException If an error occurs during the cached content reading process.
     * @since 0.1.0
     */
    CacheContent getCachedContent(String name) throws ResourceException;

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
    CompletableFuture<CacheContent> getCachedContentAsync(String name, AsyncCallback<CacheContent> callback) throws ResourceException;

    /**
     * Updates a cached content.
     *
     * @param cacheContent The {@link CacheContent} containing the content to update and other
     *                      parameters.
     * @param updateMask    The list of fields to update.
     * @param name         The resource name referring to the content cache entry.
     * @return An {@link CacheContent} containing the updated cached content.
     * @throws ResourceException If an error occurs during the cached content updating process.
     * @since 0.1.0
     */
    CacheContent updateCachedContent(CacheContent cacheContent, String updateMask, String name) throws ResourceException;

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
    CompletableFuture<CacheContent> updateCachedContentAsync(CacheContent cacheContent, String updateMask, String name, AsyncCallback<CacheContent> callback) throws ResourceException;

    /**
     * Deletes a cached content.
     *
     * @param name The resource name referring to the content cache entry.
     * @throws ResourceException If an error occurs during the cached content deleting process.
     * @since 0.1.0
     */
    void deleteCachedContent(String name) throws ResourceException;

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
    CompletableFuture<Void> deleteCachedContentAsync(String name, AsyncCallback<Void> callback) throws ResourceException;

    /**
     * Creates a builder for constructing {@link CacheContent} requests. This method returns
     * a {@link CacheRequestBuilder}, which simplifies the process
     * of setting the necessary parameters for creating or updating cached content.  The builder
     * ensures type safety and a fluent API for constructing requests.
     *
     * @param model The name of the model to be associated with the cached content.
     * @return A {@link CacheRequestBuilder} instance for building the cached content request.
     * @since 0.2.0
     */
    CacheRequestBuilder createCachedContentBuilder(String model);
}