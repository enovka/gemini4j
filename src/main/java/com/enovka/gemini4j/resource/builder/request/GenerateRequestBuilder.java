package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.resource.builder.request.spec.AbstractGenerateRequestBuilder;

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
    protected GenerateRequestBuilder self() {
        return this;
    }
}