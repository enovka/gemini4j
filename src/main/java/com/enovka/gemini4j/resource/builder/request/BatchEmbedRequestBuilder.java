package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.Part;
import com.enovka.gemini4j.model.request.BatchEmbedRequest;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.model.response.BatchEmbedResponse;
import com.enovka.gemini4j.resource.builder.request.spec.AbstractEmbedRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.EmbedResource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Builder for creating {@link BatchEmbedRequest} instances. This builder, inheriting from
 * {@link AbstractEmbedRequestBuilder}, facilitates the creation of batch requests for generating
 * embeddings from multiple text inputs. It simplifies the process of embedding multiple pieces
 * of text in a single API call, reducing overhead and improving efficiency.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class BatchEmbedRequestBuilder extends AbstractEmbedRequestBuilder<BatchEmbedRequestBuilder, BatchEmbedRequest> {

    private final List<EmbedRequest> requests = new ArrayList<>();
    private EmbedResource embedResource;
    private AsyncCallback<BatchEmbedResponse> asyncCallback;

    /**
     * Private constructor to enforce a builder pattern. Instances of this builder should be
     * created using the {@link #builder()} method.
     *
     * @since 0.2.0
     */
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
     * Sets the {@link EmbedResource} instance to be used for executing the request.
     *
     * @param embedResource The EmbedResource instance.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected BatchEmbedRequestBuilder withEmbedResource(EmbedResource embedResource) {
        this.embedResource = embedResource;
        return this;
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
     * Sets the {@link AsyncCallback} to handle the asynchronous response.
     *
     * @param asyncCallback The AsyncCallback instance.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public BatchEmbedRequestBuilder withAsyncCallback(AsyncCallback<BatchEmbedResponse> asyncCallback) {
        this.asyncCallback = asyncCallback;
        return this;
    }

    /**
     * {@inheritDoc}
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
     * Executes the batch embed request asynchronously and returns a {@link CompletableFuture}
     * representing the operation. This method allows for more flexible cancellation handling.
     *
     * @return A CompletableFuture that will resolve to a {@link BatchEmbedResponse} upon
     *         successful completion.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<BatchEmbedResponse> executeAsync() throws ResourceException {
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
    protected BatchEmbedRequestBuilder self() {
        return this;
    }
}