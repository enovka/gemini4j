package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.response.GenerateResponse;
import com.enovka.gemini4j.resource.builder.request.spec.AbstractGenerateRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.GenerateResource;

import java.util.concurrent.CompletableFuture;

/**
 * Builder for creating {@link GenerateRequest} instances. This builder provides methods
 * for setting all parameters of the request, allowing for flexible and controlled construction of
 * content generation requests. It leverages the functionality provided by the
 * {@link AbstractGenerateRequestBuilder} for common generation-related parameters and adds specialized
 * handling for cached content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class GenerateRequestBuilder extends AbstractGenerateRequestBuilder<GenerateRequestBuilder, GenerateRequest> {

    private GenerateResource generateResource;
    private AsyncCallback<GenerateResponse> asyncCallback;

    /**
     * Constructor for the GenerateRequestBuilder.
     *
     * @since 0.2.0
     */
    private GenerateRequestBuilder() {
    }

    /**
     * Creates a new instance of the {@link GenerateRequestBuilder}.
     *
     * @return A new {@code GenerateRequestBuilder} instance.
     * @since 0.2.0
     */
    public static GenerateRequestBuilder builder() {
        return new GenerateRequestBuilder();
    }

    /**
     * Sets the {@link GenerateResource} instance to be used for executing the request.
     * This method is used internally by the {@link GenerateTextRequestBuilder} to provide
     * the necessary resource for executing the built request.
     *
     * @param generateResource The GenerateResource instance.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected GenerateRequestBuilder withGenerateResource(GenerateResource generateResource) {
        this.generateResource = generateResource;
        return this;
    }

    /**
     * Sets the identifier of existing cached content to be used in the request.
     *
     * @param cachedContent The identifier of the cached content.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public GenerateRequestBuilder withCachedContent(String cachedContent) {
        this.cachedContent = cachedContent;
        return this;
    }

    /**
     * Sets the {@link AsyncCallback} to handle the asynchronous response.
     *
     * @param asyncCallback The AsyncCallback instance.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public GenerateRequestBuilder withAsyncCallback(AsyncCallback<GenerateResponse> asyncCallback) {
        this.asyncCallback = asyncCallback;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.2.0
     */
    @Override
    protected GenerateRequest createRequestInstance() {
        return GenerateRequest.builder()
                .withCachedContent(this.cachedContent)
                .build();
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public GenerateRequest build() {
        return super.build();
    }

    /**
     * Executes the generate request asynchronously and returns a {@link CompletableFuture}
     * representing the operation. This method allows for more flexible cancellation handling.
     *
     * @return A CompletableFuture that will resolve to a {@link GenerateResponse} upon
     *         successful completion.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<GenerateResponse> executeAsync() throws ResourceException {
        if (generateResource == null) {
            throw new IllegalStateException("GenerateResource is required for asynchronous execution.");
        }
        return generateResource.executeAsync(build(), asyncCallback);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    protected GenerateRequestBuilder self() {
        return this;
    }
}