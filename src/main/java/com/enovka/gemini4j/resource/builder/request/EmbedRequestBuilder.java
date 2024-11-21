package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.Part;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.resource.builder.request.spec.AbstractEmbedRequestBuilder;

import java.util.List;

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
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    protected EmbedRequestBuilder self() {
        return this;
    }
}