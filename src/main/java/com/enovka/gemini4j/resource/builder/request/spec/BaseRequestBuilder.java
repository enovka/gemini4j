package com.enovka.gemini4j.resource.builder.request.spec;

/**
 * Generic abstract base class for all builders, providing basic functionality and common fields.
 * It serves as the root of the builder hierarchy, promoting code reuse and consistency across
 * builders.
 * <p>
 * This abstract builder provides methods for setting common request parameters such as the model.
 * It aims to simplify the creation of concrete builder classes by handling shared logic and
 * promoting a fluent API through method chaining.
 *
 * @param <B> The type of the concrete builder class.
 * @param <T> The type of *request* object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class BaseRequestBuilder<B extends BaseRequestBuilder<B, T>, T> {

    protected String model;

    /**
     * Constructor for the AbstractBuilder.
     *
     * @since 0.2.0
     */
    protected BaseRequestBuilder() {
    }

    /**
     * Sets the model to be used for the request.  The model name should follow the format
     * `models/{model}`, as described in the Gemini API documentation:
     * [https://ai.google.dev/gemini-api/docs/reference/rest/v1/models](https://ai.google.dev/gemini-api/docs/reference/rest/v1/models)
     *
     * @param model The model to use.  For example, "models/gemini-1.5-pro".
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withModel(String model) {
        this.model = model;
        return self();
    }

    /**
     * Builds the request object.
     *
     * @return The built request object.
     * @since 0.2.0
     */
    public abstract T build();

    /**
     * Returns the current builder instance. This method is used for method chaining and should be
     * overridden in concrete builder classes to return the correct type.
     *
     * @return The current builder instance.
     * @since 0.2.0
     */
    protected abstract B self();
}