package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.CachedContent;
import com.enovka.gemini4j.domain.request.CachedContentRequest;
import com.enovka.gemini4j.domain.response.ListCachedContentsResponse;
import com.enovka.gemini4j.resource.builder.CachedContentRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;

/**
 * Interface defining the contract for interacting with the cached content
 * resource of the Gemini API. This resource provides methods for creating,
 * listing, getting, updating and deleting cached content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public interface CachedContentResource {

    /**
     * Returns the associated {@link GeminiClient}.
     *
     * @return The {@link GeminiClient} instance.
     */
    GeminiClient getGeminiClient();

    /**
     * Executes a request to create cached content.
     *
     * @param request The {@link CachedContentRequest} containing the content to
     * cache and other parameters.
     * @return An {@link CachedContent} containing the generated cached content.
     * @throws ResourceException If an error occurs during the cached content
     * generation process.
     * @since 0.1.3
     */
    CachedContent execute(CachedContentRequest request)
            throws ResourceException;

    /**
     * Lists cached contents.
     *
     * @param pageSize The maximum number of cached contents to return.
     * @param pageToken A page token, received from a previous
     * cachedContents.list call.
     * @return A {@link ListCachedContentsResponse} containing a list of cached
     * contents.
     * @throws ResourceException If an error occurs during the cached contents
     * listing process.
     * @since 0.1.0
     */
    ListCachedContentsResponse listCachedContents(Integer pageSize,
                                                  String pageToken)
            throws ResourceException;

    /**
     * Reads a cached content.
     *
     * @param name The resource name referring to the content cache entry.
     * @return A {@link CachedContent} containing the cached content.
     * @throws ResourceException If an error occurs during the cached content
     * reading process.
     * @since 0.1.0
     */
    CachedContent getCachedContent(String name)
            throws ResourceException;

    /**
     * Updates a cached content.
     *
     * @param cachedContent The {@link CachedContent} containing the content to
     * update and other parameters.
     * @param updateMask The list of fields to update.
     * @return An {@link CachedContent} containing the updated cached content.
     * @throws ResourceException If an error occurs during the cached content
     * updating process.
     * @since 0.1.0
     */
    CachedContent updateCachedContent(CachedContent cachedContent,
                                      String updateMask, String name)
            throws ResourceException;

    /**
     * Deletes a cached content.
     *
     * @param name The resource name referring to the content cache entry.
     * @throws ResourceException If an error occurs during the cached content
     * deleting process.
     * @since 0.1.0
     */
    void deleteCachedContent(String name)
            throws ResourceException;

    /**
     * Creates a new {@link CachedContentRequestBuilder} instance to build an
     * {@link CachedContent} for generating a cached content for the given
     * model.
     *
     * @param model The model to be used for cached content.
     * @return A new {@link CachedContentRequestBuilder} instance.
     */
    CachedContentRequestBuilder createCachedContentBuilder(String model);
}