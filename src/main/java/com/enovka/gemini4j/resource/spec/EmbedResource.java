package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.BatchEmbedContentsRequest;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.BatchEmbedContentsResponse;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.resource.builder.BatchEmbedContentsRequestBuilder;
import com.enovka.gemini4j.resource.builder.EmbedContentRequestBuilder;

import java.util.List;

/**
 * Interface defining the contract for interacting with the embedding resource
 * of the Gemini API. This resource provides methods for generating embeddings
 * for text and other types of content.
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
     * Generates an embedding for the given content.
     *
     * @param request The {@link EmbedContentRequest} containing the content to
     * embed and other parameters.
     * @return An {@link EmbedContentResponse} containing the generated
     * embedding.
     * @throws GeminiApiException If an error occurs during the embedding
     * generation process.
     * @throws JsonException If an error occurs during JSON processing.
     * @throws HttpException If an error occurs during the HTTP request.
     */
    EmbedContentResponse embedContent(EmbedContentRequest request)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates embeddings for multiple contents in a batch request.
     *
     * @param request The {@link BatchEmbedContentsRequest} containing a list of
     * {@link EmbedContentRequest} objects.
     * @return A {@link BatchEmbedContentsResponse} containing a list of
     * generated embeddings.
     * @throws GeminiApiException If an error occurs during the batch embedding
     * generation process.
     * @throws JsonException If an error occurs during JSON processing.
     * @throws HttpException If an error occurs during the HTTP request.
     */
    BatchEmbedContentsResponse batchEmbedContent(
            BatchEmbedContentsRequest request)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Creates a new {@link EmbedContentRequestBuilder} instance to build an
     * {@link EmbedContentRequest} for generating an embedding for the given
     * text.
     *
     * @param text The text to embed.
     * @return A new {@link EmbedContentRequestBuilder} instance.
     */
    EmbedContentRequestBuilder embedContentBuilder(String text);

    /**
     * Creates a new {@link BatchEmbedContentsRequestBuilder} instance to build
     * a {@link BatchEmbedContentsRequestBuilder} for generating embeddings for
     * the given list of texts.
     *
     * @param texts The list of texts to embed.
     * @return A new {@link BatchEmbedContentsRequestBuilder} instance.
     */
    BatchEmbedContentsRequestBuilder batchEmbedContentsBuilder(
            List<String> texts);
}