package com.enovka.gemini4j.resource.builder.spec;

/**
 * Intermediate abstract class for builders that handle more complex objects
 * with nested structures, providing common functionality for managing nested
 * builders.
 *
 * @param <T> The type of object this builder creates.
 * @param <P> The type of the parent builder.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractComplexBuilder<T, P extends AbstractBuilder<?>>
        extends AbstractBuilder<T> {

    protected final P parentBuilder; // Reference to the parent builder

    /**
     * Constructor for nested builders, setting the parent builder reference.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractComplexBuilder(P parentBuilder) {
        super(parentBuilder);
        this.parentBuilder = parentBuilder;
    }
}