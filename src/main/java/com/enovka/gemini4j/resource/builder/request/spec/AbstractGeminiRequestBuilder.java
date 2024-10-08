package com.enovka.gemini4j.resource.builder.request.spec;

/**
 * Abstract base class for all Gemini Request Builders.  This class defines the common structure and
 * functionality for building requests to the Gemini API, including setting the model and handling
 * potential exceptions. It serves as the root of the builder hierarchy, ensuring consistency and
 * promoting code reuse across all request types.
 *
 * @param <B> The type of the concrete builder class, enabling method chaining.
 * @param <T> The type of request object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class AbstractGeminiRequestBuilder<B extends AbstractGeminiRequestBuilder<B, T>, T> {

    protected String model;


    /**
     * Sets the model to be used for the request. The model name should follow the format
     * 'models/{model}', as specified in the Gemini API documentation.
     *
     * @param model The model identifier, including the 'models/' prefix.  For example,
     *              "models/gemini-1.5-pro".
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided model identifier is null or empty.
     * @since 0.2.0
     */
    public B withModel(String model) {
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty.");
        }
        this.model = model;
        return self();
    }

    /**
     * Returns the current builder instance. This method is essential for method chaining and must
     * be implemented by all subclasses to return the correct builder type.
     *
     * @return The current builder instance.
     * @since 0.2.0
     */
    protected abstract B self();

    /**
     * Builds the request object. This abstract method must be implemented by all subclasses to
     * construct the specific request object they are designed for.
     *
     * @return The built request object.
     * @since 0.2.0
     */
    public abstract T build();
}