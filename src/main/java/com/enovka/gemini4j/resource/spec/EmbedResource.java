package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.model.request.BatchEmbedRequest;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.model.response.BatchEmbedResponse;
import com.enovka.gemini4j.model.response.EmbedResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.AsyncResponse;
import com.enovka.gemini4j.resource.spec.base.Resource;

/**
 * Interface defining the contract for interacting with the embedding resource of the Gemini API.
 * This resource provides methods for generating embeddings for text and other types of content
 * using the text-embedding-004 model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public interface EmbedResource extends Resource {

    /**
     * Executes an embedding generation request for the given content using the text-embedding-004 model.
     *
     * @param request The {@link EmbedRequest} containing the content to embed and other parameters.
     * @return An {@link EmbedResponse} containing the generated embedding.
     * @throws ResourceException If an error occurs during the embedding generation process.
     * @since 0.1.3
     */
    EmbedResponse execute(EmbedRequest request) throws ResourceException;

    /**
     * Executes an embedding generation request asynchronously for the given content using the
     * text-embedding-004 model.
     *
     * @param request  The {@link EmbedRequest} containing the content to embed and other parameters.
     * @return A {@link AsyncResponse} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @since 0.2.0
     */
    AsyncResponse<EmbedResponse> executeAsync(EmbedRequest request);

    /**
     * Executes a batch embedding generation request for multiple contents using the text-embedding-004 model.
     *
     * @param request The {@link BatchEmbedRequest} containing a list of {@link EmbedRequest} objects.
     * @return A {@link BatchEmbedResponse} containing a list of generated embeddings.
     * @throws ResourceException If an error occurs during the batch embedding generation process.
     * @since 0.1.3
     */
    BatchEmbedResponse execute(BatchEmbedRequest request) throws ResourceException;

    /**
     * Executes a batch embedding generation request asynchronously for multiple contents using the
     * text-embedding-004 model.
     *
     * @param request  The {@link BatchEmbedRequest} containing a list of {@link EmbedRequest} objects.
     * @return A {@link AsyncResponse} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @since 0.2.0
     */
    AsyncResponse<BatchEmbedResponse> executeAsync(BatchEmbedRequest request);
}