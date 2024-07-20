package com.enovka.gemini4j.resource.builder.spec;

import com.enovka.gemini4j.client.spec.GeminiClient;

/**
 * Generic abstract base class for all builders, providing the most basic
 * functionality and common fields. It serves as the root of the builder
 * hierarchy.
 *
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractBuilder<T> {

    protected final GeminiClient geminiClient;
    protected AbstractBuilder<?> parentBuilder;
    // Reference to the parent builder, if any

    /**
     * Constructor for the AbstractBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    protected AbstractBuilder(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    /**
     * Constructor for nested builders, setting the parent builder reference.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractBuilder(AbstractBuilder<?> parentBuilder) {
        this.parentBuilder = parentBuilder;
        this.geminiClient
                = parentBuilder.geminiClient; // Inherit GeminiClient from parent
    }

    /**
     * Abstract method to build the target object. Concrete builders must
     * implement this method.
     *
     * @return The built object of type T.
     */
    public abstract T build();
}