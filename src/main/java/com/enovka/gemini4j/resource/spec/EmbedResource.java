package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.BatchEmbedContentsRequest;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.BatchEmbedContentsResponse;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;
import com.enovka.gemini4j.resource.builder.BatchEmbedContentsRequestBuilder;
import com.enovka.gemini4j.resource.builder.EmbedContentRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;

import java.util.List;

/**
 * Interface defining the contract for interacting with the embedding resource
 * of the Gemini API. This resource provides methods for generating embeddings
 * for text and other types of content using the text-embedding-004 model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public interface EmbedResource {

    /**
     * Returns the associated {@link GeminiClient}.
     *
     * @return The {@link GeminiClient} instance.
     */
    GeminiClient getGeminiClient();

    /**
     * Executes an embedding generation request for the given content using the
     * text-embedding-004 model.
     *
     * @param request The {@link EmbedContentRequest} containing the content to
     * embed and other parameters.
     * @return An {@link EmbedContentResponse} containing the generated
     * embedding.
     * @throws ResourceException If an error occurs during the embedding
     * generation process.
     * @since 0.1.3
     */
    EmbedContentResponse execute(EmbedContentRequest request)
            throws ResourceException;

    /**
     * Executes a batch embedding generation request for multiple contents using
     * the text-embedding-004 model.
     *
     * @param request The {@link BatchEmbedContentsRequest} containing a list of
     * {@link EmbedContentRequest} objects.
     * @return A {@link BatchEmbedContentsResponse} containing a list of
     * generated embeddings.
     * @throws ResourceException If an error occurs during the batch embedding
     * generation process.
     * @since 0.1.3
     */
    BatchEmbedContentsResponse execute(
            BatchEmbedContentsRequest request)
            throws ResourceException;

    /**
     * Creates a new {@link EmbedContentRequestBuilder} instance to build an
     * {@link EmbedContentRequest} for generating an embedding for the given
     * text using the text-embedding-004 model.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * String text = "This is a test sentence.";
     * EmbedContentRequestBuilder builder = embedResource.embedContentBuilder(text);
     * EmbedContentRequest request = builder.build();
     * EmbedContentResponse response = embedResource.execute(request);
     * List<Double> embeddingValues = response.getEmbedding().getValues();
     * System.out.println(embeddingValues);
     * }</pre>
     *
     * @param text The text to embed.
     * @return A new {@link EmbedContentRequestBuilder} instance.
     */
    EmbedContentRequestBuilder embedContentBuilder(String text);

    /**
     * Creates a new {@link BatchEmbedContentsRequestBuilder} instance to build
     * a {@link BatchEmbedContentsRequestBuilder} for generating embeddings for
     * the given list of texts using the text-embedding-004 model.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * List<String> texts = Arrays.asList("This is a test sentence.", "Another sentence for embedding.");
     * BatchEmbedContentsRequestBuilder builder = embedResource.batchEmbedContentsBuilder(texts);
     * BatchEmbedContentsRequest request = builder.build();
     * BatchEmbedContentsResponse response = embedResource.execute(request);
     * List<Embedding> embeddings = response.getEmbeddings();
     * System.out.println(embeddings);
     * }</pre>
     *
     * @param texts The list of texts to embed.
     * @return A new {@link BatchEmbedContentsRequestBuilder} instance.
     */
    BatchEmbedContentsRequestBuilder batchEmbedContentsBuilder(
            List<String> texts);
}