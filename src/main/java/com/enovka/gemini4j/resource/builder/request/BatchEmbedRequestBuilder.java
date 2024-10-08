package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.Part;
import com.enovka.gemini4j.model.request.BatchEmbedRequest;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.resource.builder.request.spec.AbstractEmbedRequestBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link BatchEmbedRequest} instances. This builder, inheriting from
 * {@link AbstractEmbedRequestBuilder}, facilitates the creation of batch requests for generating
 * embeddings from multiple text inputs. It simplifies the process of embedding multiple pieces of text
 * in a single API call, reducing overhead and improving efficiency.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class BatchEmbedRequestBuilder extends AbstractEmbedRequestBuilder<BatchEmbedRequestBuilder, BatchEmbedRequest> {

    private final List<EmbedRequest> requests = new ArrayList<>();

    private BatchEmbedRequestBuilder() {
        super();
    }

    /**
     * Creates a new instance of the {@link BatchEmbedRequestBuilder}.
     *
     * @return A new {@code BatchEmbedRequestBuilder} instance.
     * @since 0.2.0
     */
    public static BatchEmbedRequestBuilder builder() {
        return new BatchEmbedRequestBuilder();
    }


    /**
     * Adds a text input to the batch of texts to be embedded.  Each text added through this method
     * will be treated as an individual embedding request within the batch.  Ensure the provided
     * text is not null or empty.
     *
     * @param text The text content to include in the batch embedding request.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided text is null or empty.
     * @since 0.2.0
     */
    protected BatchEmbedRequestBuilder withText(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty.");
        }

        Content content = Content.builder()
                .withParts(List.of(Part.builder().withText(text).build()))
                .build();

        EmbedRequest embedRequest = EmbedRequest.builder()
                .withModel(model)
                .withContent(content)
                .withTaskType(taskType)
                .withTitle(title)
                .withOutputDimensionality(outputDimensionality)
                .build();

        requests.add(embedRequest);
        return this;
    }


    /**
     * Adds multiple text strings to the batch of texts to be embedded, processing each string as
     * an individual embedding request.
     *
     * @param texts A list of text strings to be embedded.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the list of texts or any text string is null or empty.
     * @since 0.2.0
     */
    public BatchEmbedRequestBuilder withTexts(List<String> texts) {
        if (texts == null || texts.isEmpty()) {
            throw new IllegalArgumentException("List of texts cannot be null or empty.");
        }
        texts.forEach(this::withText);
        return this;
    }


    /**
     * {@inheritDoc}
     *
     * @since 0.2.0
     */
    @Override

    public BatchEmbedRequest build() {
        if (requests.isEmpty()) {
            throw new IllegalStateException(
                    "At least one embed content request is required for batch embedding.");
        }


        // Apply inherited properties to individual requests if not set individually.
        requests.forEach(request -> {
            request.setModel(model); // Use the model from the main builder
            if (request.getTaskType() == null) {
                request.setTaskType(taskType);
            }
            if (request.getTitle() == null) {
                request.setTitle(title);
            }
            if (request.getOutputDimensionality() == null) {
                request.setOutputDimensionality(outputDimensionality);
            }
        });

        return BatchEmbedRequest.builder()
                .withRequests(requests)
                .build();
    }


    /**
     * {@inheritDoc}
     *
     * @since 0.2.0
     */
    @Override
    protected BatchEmbedRequestBuilder self() {
        return this;
    }
}
