package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.model.CacheContent;
import com.enovka.gemini4j.model.CacheResponse;
import com.enovka.gemini4j.model.request.CacheRequest;
import com.enovka.gemini4j.model.response.EmptyResponse;
import com.enovka.gemini4j.model.response.ListCacheResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.AsyncResponse;
import com.enovka.gemini4j.resource.spec.base.Resource;

/**
 * Interface defining the contract for interacting with the cached content resource of the Gemini API.
 * This resource provides methods for creating, retrieving, listing, updating, and deleting cached
 * content.  It offers both synchronous and asynchronous operations, using the {@link AsyncResponse}
 * object for asynchronous calls, promoting a more fluent and type-safe approach to handling API
 * responses.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents">Gemini API Cached Contents Documentation</a>
 * @since 0.2.0
 */
public interface CacheResource extends Resource {

    /**
     * Creates a new cached content entry in the Gemini API.
     *
     * @param request The {@link CacheRequest} object containing the details of the cached content to create,
     *                including the model, contents, and expiration settings.
     * @return A {@link CacheResponse} object containing the newly created {@link CacheContent}.
     * @throws ResourceException If an error occurs during the creation process, such as network issues,
     *                           invalid request parameters, or API errors.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/create">Gemini API Cached Contents Create Documentation</a>
     * @since 0.2.0
     */
    CacheResponse createCachedContent(CacheRequest request) throws ResourceException;

    /**
     * Asynchronously creates a new cached content entry in the Gemini API.
     *
     * @param request The {@link CacheRequest} object containing the details of the cached content to create.
     * @return An {@link AsyncResponse} wrapping the {@link CacheResponse}, providing a fluent API for
     *         handling the asynchronous response. Use methods like {@code onSuccess}, {@code onError}, and {@code onCanceled}
     *         to handle different response scenarios.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/create">Gemini API Cached Contents Create Documentation</a>
     * @since 0.2.0
     */
    AsyncResponse<CacheResponse> createCachedContentAsync(CacheRequest request);

    /**
     * Retrieves a list of cached content entries from the Gemini API.
     *
     * @param pageSize  The maximum number of cached content entries to return per page.  If unspecified, a default
     *                  page size will be used.
     * @param pageToken An optional token for retrieving a specific page of results. This token is obtained from the
     *                  {@code nextPageToken} field of a previous {@link ListCacheResponse}.
     * @return A {@link ListCacheResponse} object containing a list of cached contents and a token for retrieving
     *         the next page of results (if available).
     * @throws ResourceException If an error occurs during the retrieval process, such as network issues or API errors.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/list">Gemini API Cached Contents List Documentation</a>
     * @since 0.2.0
     */
    ListCacheResponse listCachedContents(Integer pageSize, String pageToken) throws ResourceException;

    /**
     * Asynchronously retrieves a list of cached content entries from the Gemini API.
     *
     * @param pageSize  The maximum number of entries to return per page.
     * @param pageToken An optional token for retrieving a specific page.
     * @return An {@link AsyncResponse} wrapping the {@link ListCacheResponse}, providing a fluent and type-safe
     *         way to handle the asynchronous response.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/list">Gemini API Cached Contents List Documentation</a>
     * @since 0.2.0
     */
    AsyncResponse<ListCacheResponse> listCachedContentsAsync(Integer pageSize, String pageToken);

    /**
     * Retrieves a specific cached content entry from the Gemini API by its resource name.
     *
     * @param name The resource name of the cached content to retrieve.  This name should be in the format
     *             "cachedContents/{cached_content_id}".
     * @return A {@link CacheResponse} containing the retrieved {@link CacheContent}.
     * @throws ResourceException If an error occurs during the retrieval process, such as if the specified cached
     *                           content is not found, or if network or API errors occur.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/get">Gemini API Cached Contents Get Documentation</a>
     * @since 0.2.0
     */
    CacheResponse getCachedContent(String name) throws ResourceException;

    /**
     * Asynchronously retrieves a specific cached content entry by its resource name.
     *
     * @param name The resource name of the cached content.
     * @return An {@link AsyncResponse} containing the retrieved {@link CacheResponse}. Use methods like
     *         {@code onSuccess} and {@code onError} to handle the response asynchronously.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/get">Gemini API Cached Contents Get Documentation</a>
     * @since 0.2.0
     */
    AsyncResponse<CacheResponse> getCachedContentAsync(String name);

    /**
     * Updates a cached content entry in the Gemini API.  Currently, only the  `displayName`, `ttl`, and `expireTime`
     * fields of the cached content can be updated.
     *
     * @param cache The {@link CacheContent} containing the updated data. The `name` field of this
     *                object must match the resource name of the cached content to update.
     * @return A {@link CacheResponse} containing the updated {@link CacheContent}.
     * @throws ResourceException If an error occurs during the update process, such as if the specified cached content
     *                           doesn't exist, or if network or API errors occur.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/patch">Gemini API Cached Contents Patch Documentation</a>
     * @since 0.2.0
     */
    CacheResponse updateCachedContent(CacheRequest cache) throws ResourceException;

    /**
     * Updates a cached content entry asynchronously. Only the  `displayName`, `ttl` and `expireTime` fields of the
     * cached content can be updated.
     *
     * @param cache The {@link CacheContent} object with the updated data. The 'name' field is required and must
     *              match the resource name of the existing cached content to be updated.
     * @return An {@link AsyncResponse} wrapping the {@link CacheResponse}, which will contain the updated cached
     *         content upon successful completion.  Use methods like  {@code onSuccess}  and  {@code onError}  to handle
     *         the response.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/patch">Gemini API Cached Contents Patch Documentation</a>
     * @since 0.2.0
     */
    AsyncResponse<CacheResponse> updateCachedContentAsync(CacheRequest cache);

    /**
     * Deletes a cached content entry from the Gemini API.
     *
     * @param name The resource name of the cached content to delete in the format "cachedContents/{cached_content_id}".
     * @throws ResourceException If an error occurs during the deletion process, such as if the specified cached
     *                           content is not found or if network or API errors occur.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/delete">Gemini API Cached Contents Delete Documentation</a>
     * @since 0.2.0
     */
    void deleteCachedContent(String name) throws ResourceException;

    /**
     * Asynchronously deletes a cached content entry from the Gemini API.
     *
     * @param name The resource name of the entry to delete.
     * @return An {@link AsyncResponse} representing the asynchronous deletion operation, which resolves to an
     *         {@link EmptyResponse} upon successful completion.
     * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/cachedContents/delete">Gemini API Cached Contents Delete Documentation</a>
     * @since 0.2.0
     */
    AsyncResponse<EmptyResponse> deleteCachedContentAsync(String name);
}