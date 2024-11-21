package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.request.TokensRequest;
import com.enovka.gemini4j.resource.builder.request.spec.AbstractContentRequestBuilder;

/**
 * Builder for creating {@link TokensRequest} instances.  This builder allows you to configure
 * the model and the content for which you want to count tokens.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class TokensRequestBuilder extends AbstractContentRequestBuilder<TokensRequestBuilder, TokensRequest> {

    private GenerateRequest generateRequest;

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
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    protected TokensRequestBuilder self() {
        return this;
    }
}