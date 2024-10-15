// com.enovka.gemini4j.resource.spec.EmbedResource
package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.model.request.BatchEmbedRequest;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.model.response.BatchEmbedResponse;
import com.enovka.gemini4j.model.response.EmbedResponse;
import com.enovka.gemini4j.resource.builder.request.BatchEmbedRequestBuilder;
import com.enovka.gemini4j.resource.builder.request.EmbedRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.Resource;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    CompletableFuture<EmbedResponse> executeAsync(EmbedRequest request, AsyncCallback<EmbedResponse> callback) throws ResourceException;

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
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    CompletableFuture<BatchEmbedResponse> executeAsync(BatchEmbedRequest request, AsyncCallback<BatchEmbedResponse> callback) throws ResourceException;

    /**
     * Creates a new {@link EmbedRequestBuilder} instance to build an {@link EmbedRequest} for
     * generating an embedding for the given text using the text-embedding-004 model.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * String text = "This is a test sentence.";
     * EmbedRequestBuilder builder = embedResource.embedContentBuilder(text);
     * EmbedRequest request = builder.buildRequest();
     * EmbedResponse response = embedResource.execute(request);
     * List<Double> embeddingValues = response.getEmbedding().getValues();
     * System.out.println(embeddingValues);
     * }</pre>
     *
     * @param text The text to embed.
     * @return A new {@link EmbedRequestBuilder} instance.
     */
    EmbedRequestBuilder embedContentBuilder(String text);

    /**
     * Creates a new {@link BatchEmbedRequestBuilder} instance to build a
     * {@link BatchEmbedRequestBuilder} for generating embeddings for the given list of texts using
     * the text-embedding-004 model.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * List<String> texts = Arrays.asList("This is a test sentence.", "Another sentence for embedding.");
     * BatchEmbedRequestBuilder builder = embedResource.batchEmbedContentsBuilder(texts);
     * BatchEmbedRequest request = builder.buildRequest();
     * BatchEmbedResponse response = embedResource.execute(request);
     * List<Embedding> embeddings = response.getEmbeddings();
     * System.out.println(embeddings);
     * }</pre>
     *
     * @param texts The list of texts to embed.
     * @return A new {@link BatchEmbedRequestBuilder} instance.
     */
    BatchEmbedRequestBuilder batchEmbedContentsBuilder(List<String> texts);
}