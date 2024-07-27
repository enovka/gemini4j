package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.resource.builder.spec.AbstractBuilder;
import com.enovka.gemini4j.resource.builder.spec.AbstractComplexBuilder;

/**
 * Abstract builder for creating data objects with MIME type and data.
 *
 * @param <T> The type of object this builder creates.
 * @param <P> The type of the parent builder.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractDataBuilder<T, P extends AbstractBuilder<?>>
        extends AbstractComplexBuilder<T, P> {

    protected String mimeType;
    protected String data;

    /**
     * Constructor for the AbstractDataBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractDataBuilder(P parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the MIME type for the data object.
     *
     * @param mimeType The MIME type of the data.
     * @return The builder instance for method chaining.
     */
    public AbstractDataBuilder<T, P> withMimeType(String mimeType) {
        if (mimeType == null || mimeType.isEmpty()) {
            throw new IllegalArgumentException("MIME type is required.");
        }
        this.mimeType = mimeType;
        return this;
    }

    /**
     * Sets the data for the data object.
     *
     * @param data The data.
     * @return The builder instance for method chaining.
     */
    public AbstractDataBuilder<T, P> withData(String data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data is required.");
        }
        this.data = data;
        return this;
    }
}