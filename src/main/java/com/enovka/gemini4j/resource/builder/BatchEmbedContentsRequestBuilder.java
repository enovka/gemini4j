package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.BatchEmbedContentsRequest;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link BatchEmbedContentsRequest} instances. This
 * builder provides a fluent API for configuring batch embedding requests,
 * allowing you to add multiple embedding requests or texts to be embedded in a
 * single batch.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Accessors(chain = true)
public class BatchEmbedContentsRequestBuilder {

    private final GeminiClient geminiClient;
    private final List<EmbedContentRequest> requests;

    /**
     * Private constructor to enforce builder pattern.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    private BatchEmbedContentsRequestBuilder(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
        this.requests = new ArrayList<>();
    }

    /**
     * Creates a new instance of the BatchEmbedContentsRequestBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     * @return A new BatchEmbedContentsRequestBuilder instance.
     */
    public static BatchEmbedContentsRequestBuilder builder(
            GeminiClient geminiClient) {
        return new BatchEmbedContentsRequestBuilder(geminiClient);
    }

    /**
     * Adds an individual {@link EmbedContentRequest} to the batch request.
     *
     * @param request The EmbedContentRequest to add.
     * @return The builder instance for method chaining.
     */
    public BatchEmbedContentsRequestBuilder withRequest(
            EmbedContentRequest request) {
        this.requests.add(request);
        return this;
    }

    /**
     * Adds multiple texts to be embedded in the batch request. Each text will
     * be wrapped in a {@link com.enovka.gemini4j.domain.Content} object and
     * added as an individual {@link EmbedContentRequest}.
     *
     * @param texts The list of texts to embed.
     * @return The builder instance for method chaining.
     */
    public BatchEmbedContentsRequestBuilder withTexts(List<String> texts) {
        texts.forEach(text -> this.requests.add(
                EmbedContentRequestBuilder.builder(geminiClient)
                        .withText(text)
                        .build()
        ));
        return this;
    }

    /**
     * Builds the {@link BatchEmbedContentsRequest} instance based on the
     * configured parameters.
     *
     * @return The built {@link BatchEmbedContentsRequest} instance.
     */
    public BatchEmbedContentsRequest build() {
        if (requests.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one request is required.");
        }
        return BatchEmbedContentsRequest.builder()
                .withModel(geminiClient.getModel())
                .withRequests(requests)
                .build();
    }
}