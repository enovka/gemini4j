package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.impl.*;
import com.enovka.gemini4j.resource.spec.*;

/**
 * Builder for creating resource instances for interacting with the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class ResourceBuilder {

    private final GeminiClient geminiClient;
    private JsonService jsonService;

    /**
     * Private constructor to enforce a builder pattern.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    private ResourceBuilder(GeminiClient geminiClient) {
        if (geminiClient == null) {
            throw new IllegalArgumentException("GeminiClient is required.");
        }
        this.geminiClient = geminiClient;
        this.jsonService = JsonServiceBuilder.builder().build().build();
    }

    /**
     * Creates a new instance of the ResourceBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
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
        return new EmbedResourceImpl(geminiClient, jsonService);
    }

    /**
     * Sets the JsonService to use for JSON serialization/deserialization.
     *
     * @param jsonService The JsonService instance.
     * @return The builder instance for method chaining.
     */
    public ResourceBuilder withJsonService(JsonService jsonService) {
        this.jsonService = jsonService;
        return this;
    }

    /**
     * Creates a new {@link GenerationResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link GenerationResource} instance.
     */
    public GenerationResource buildGenerationResource() {
        return new GenerationResourceImpl(geminiClient, jsonService);
    }

    /**
     * Creates a new {@link ModelResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link ModelResource} instance.
     */
    public ModelResource buildModelResource() {
        return new ModelResourceImpl(geminiClient, jsonService);
    }

    /**
     * Creates a new {@link CachedContentResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link CachedContentResource} instance.
     * @since 0.0.2
     */
    public CachedContentResource buildCachedContentResource() {
        return new CachedContentResourceImpl(geminiClient, jsonService);
    }

    /**
     * Creates a new {@link CountTokensResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link CountTokensResource} instance.
     * @since 0.1.3
     */
    public CountTokensResource buildCountTokensResource() {
        return new CountTokensResourceImpl(geminiClient, jsonService);
    }
}