package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.Part;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.model.response.EmbedResponse;
import com.enovka.gemini4j.resource.builder.request.spec.AbstractEmbedRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.EmbedResource;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Builder for creating {@link EmbedRequest} instances. This builder, inheriting from
 * {@link AbstractEmbedRequestBuilder}, simplifies the construction of requests for
 * generating embeddings from text content. It provides a fluent API for configuring the
 * necessary parameters.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class EmbedRequestBuilder extends AbstractEmbedRequestBuilder<EmbedRequestBuilder, EmbedRequest> {

    private Content content;
    private EmbedResource embedResource;
    private AsyncCallback<EmbedResponse> asyncCallback;

    /**
     * Private constructor to enforce a builder pattern. Instances of this builder should be
     * created using the {@link #builder()} method.
     *
     * @since 0.2.0
     */
    private EmbedRequestBuilder() {
        super();
    }

    /**
     * Creates a new instance of the {@link EmbedRequestBuilder}.
     *
     * @return A new {@code EmbedRequestBuilder} instance.
     * @since 0.2.0
     */
    public static EmbedRequestBuilder builder() {
        return new EmbedRequestBuilder();
    }

    /**
     * Sets the {@link EmbedResource} instance to be used for executing the request.
     *
     * @param embedResource The EmbedResource instance.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected EmbedRequestBuilder withEmbedResource(EmbedResource embedResource) {
        this.embedResource = embedResource;
        return this;
    }

    /**
     * Sets the text content for which to generate an embedding. This method creates a
     * {@link Content} object containing the provided text, which is used as the input
     * for the embedding generation process. Ensure the text is not null or empty.
     *
     * @param text The text content to embed.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided text is null or empty.
     * @since 0.2.0
     */
    public EmbedRequestBuilder withText(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text content cannot be null or empty.");
        }
        this.content = Content.builder()
                .withParts(List.of(Part.builder().withText(text).build()))
                .build();
        return self();
    }

    /**
     * Sets the {@link AsyncCallback} to handle the asynchronous response.
     *
     * @param asyncCallback The AsyncCallback instance.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public EmbedRequestBuilder withAsyncCallback(AsyncCallback<EmbedResponse> asyncCallback) {
        this.asyncCallback = asyncCallback;
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public EmbedRequest build() {
        if (model == null) {
            throw new IllegalStateException("Model is required for EmbedRequest.");
        }
        if (content == null) {
            throw new IllegalStateException("Content is required for EmbedRequest.");
        }

        return EmbedRequest.builder()
                .withModel(model)
                .withContent(content)
                .withTaskType(taskType) // Inherited from AbstractEmbedRequestBuilder
                .withTitle(title)       // Inherited from AbstractEmbedRequestBuilder
                .withOutputDimensionality(outputDimensionality) // Inherited from AbstractEmbedRequestBuilder
                .build();
    }

    /**
     * Executes the embed request asynchronously and returns a {@link CompletableFuture}
     * representing the operation. This method allows for more flexible cancellation handling.
     *
     * @return A CompletableFuture that will resolve to an {@link EmbedResponse} upon
     *         successful completion.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<EmbedResponse> executeAsync() throws ResourceException {
        if (embedResource == null) {
            throw new IllegalStateException("EmbedResource is required for asynchronous execution.");
        }
        return embedResource.executeAsync(build(), asyncCallback);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    protected EmbedRequestBuilder self() {
        return this;
    }
}