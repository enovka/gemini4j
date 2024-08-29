package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.BatchEmbedContentsRequest;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.BatchEmbedContentsResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.EmbedResource;
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
    private final EmbedResource embedResource;
    private final List<EmbedContentRequest> requests;

    /**
     * Constructor for the BatchEmbedContentsRequestBuilder.
     *
     * @param embedResource The EmbedResource instance to use for API
     * communication.
     * @since 0.1.3
     */
    public BatchEmbedContentsRequestBuilder(EmbedResource embedResource) {
        this.embedResource = embedResource;
        this.geminiClient = embedResource.getGeminiClient();
        this.requests = new ArrayList<>();
    }

    /**
     * Creates a new instance of the BatchEmbedContentsRequestBuilder.
     *
     * @param embedResource The EmbedResource instance to use for API
     * communication.
     * @return A new BatchEmbedContentsRequestBuilder instance.
     * @since 0.1.3
     */
    public static BatchEmbedContentsRequestBuilder builder(
            EmbedResource embedResource) {
        return new BatchEmbedContentsRequestBuilder(embedResource);
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
                EmbedContentRequestBuilder.builder(embedResource)
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

    /**
     * Executes the batch embed content request and returns the response.
     *
     * @return The {@link BatchEmbedContentsResponse} from the Gemini API.
     * @throws ResourceException If an error occurs during the API request.
     * @since 0.1.3
     */
    public BatchEmbedContentsResponse execute() throws ResourceException {
        return embedResource.execute(build());
    }
}