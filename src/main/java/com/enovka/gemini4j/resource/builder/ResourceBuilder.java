package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.resource.impl.*;
import com.enovka.gemini4j.resource.spec.*;

/**
 * Builder for creating resource instances for interacting with the Gemini API.
 * This builder provides a convenient way to construct various resource
 * instances, such as {@link EmbedResource}, {@link GenerateResource},
 * {@link ModelResource}, {@link CacheResource}, and
 * {@link TokensResource}, using a fluent API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class ResourceBuilder {

    private final GeminiClient geminiClient;

    /**
     * Private constructor to enforce a builder pattern.
     *
     * @param geminiClient The GeminiClient instance to use for API
     *                     communication.
     */
    private ResourceBuilder(GeminiClient geminiClient) {
        if (geminiClient == null) {
            throw new IllegalArgumentException("GeminiClient is required.");
        }
        this.geminiClient = geminiClient;
    }

    /**
     * Creates a new instance of the ResourceBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     *                     communication.
     * @return A new ResourceBuilder instance.
     */
    public static ResourceBuilder builder(GeminiClient geminiClient) {
        return new ResourceBuilder(geminiClient);
    }


    /**
     * Creates a new {@link EmbedResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link EmbedResource} instance.
     * @since 0.0.2
     */
    public EmbedResource buildEmbedResource() {
        return new EmbedResourceImpl(geminiClient);
    }

    /**
     * Creates a new {@link GenerateResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link GenerateResource} instance.
     */
    public GenerateResource buildGenerationResource() {
        return new GenerateResourceImpl(geminiClient);
    }

    /**
     * Creates a new {@link ModelResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link ModelResource} instance.
     */
    public ModelResource buildModelResource() {
        return new ModelResourceImpl(geminiClient);
    }

    /**
     * Creates a new {@link CacheResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link CacheResource} instance.
     * @since 0.0.2
     */
    public CacheResource buildCachedContentResource() {
        return new CacheResourceImpl(geminiClient);
    }

    /**
     * Creates a new {@link TokensResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link TokensResource} instance.
     * @since 0.1.3
     */
    public TokensResource buildCountTokensResource() {
        return new TokensResourceImpl(geminiClient);
    }
}