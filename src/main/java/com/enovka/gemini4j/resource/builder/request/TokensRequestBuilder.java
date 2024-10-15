package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.request.TokensRequest;
import com.enovka.gemini4j.model.response.TokensResponse;
import com.enovka.gemini4j.resource.builder.request.spec.AbstractContentRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.TokensResource;

import java.util.concurrent.CompletableFuture;

/**
 * Builder for creating {@link TokensRequest} instances.  This builder allows you to configure
 * the model and the content for which you want to count tokens.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class TokensRequestBuilder extends AbstractContentRequestBuilder<TokensRequestBuilder, TokensRequest> {

    private GenerateRequest generateRequest;
    private TokensResource tokensResource;
    private AsyncCallback<TokensResponse> asyncCallback;

    /**
     * Private constructor to enforce a builder pattern. Instances of this builder should be
     * created using the {@link #builder()} method.
     *
     * @since 0.2.0
     */
    private TokensRequestBuilder() {
        super();
    }

    /**
     * Creates a new instance of the {@link TokensRequestBuilder}.
     *
     * @return A new {@code TokensRequestBuilder} instance.
     * @since 0.2.0
     */
    public static TokensRequestBuilder builder() {
        return new TokensRequestBuilder();
    }

    /**
     * Sets the {@link TokensResource} instance to be used for executing the request.
     *
     * @param tokensResource The TokensResource instance.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected TokensRequestBuilder withTokensResource(TokensResource tokensResource) {
        this.tokensResource = tokensResource;
        return this;
    }

    /**
     * Sets the {@link GenerateRequest} for token counting.  If this is provided, the
     * 'contents' field will be ignored, and tokens will be counted based on the content within
     * the GenerateRequest, including the prompt, system instructions, and tools.
     *
     * @param generateRequest The GenerateRequest to use for token counting.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public TokensRequestBuilder withGenerateContentRequest(GenerateRequest generateRequest) {
        this.generateRequest = generateRequest;
        return this;
    }

    /**
     * Sets the {@link AsyncCallback} to handle the asynchronous response.
     *
     * @param asyncCallback The AsyncCallback instance.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public TokensRequestBuilder withAsyncCallback(AsyncCallback<TokensResponse> asyncCallback) {
        this.asyncCallback = asyncCallback;
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public TokensRequest build() {
        if ((contents == null || contents.isEmpty()) && generateRequest == null) {
            throw new IllegalArgumentException("Either contents or generateRequest must be provided.");
        }

        return TokensRequest.builder()
                .withModel(model)
                .withContents(contents)
                .withGenerateRequest(generateRequest)
                .build();
    }

    /**
     * Executes the token counting request asynchronously and returns a {@link CompletableFuture}
     * representing the operation. This method allows for more flexible cancellation handling.
     *
     * @return A CompletableFuture that will resolve to a {@link TokensResponse} upon
     *         successful completion.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<TokensResponse> executeAsync() throws ResourceException {
        if (tokensResource == null) {
            throw new IllegalStateException("TokensResource is required for asynchronous execution.");
        }
        return tokensResource.executeAsync(build(), asyncCallback);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    protected TokensRequestBuilder self() {
        return this;
    }
}