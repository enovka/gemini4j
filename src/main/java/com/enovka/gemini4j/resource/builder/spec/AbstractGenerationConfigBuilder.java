package com.enovka.gemini4j.resource.builder.spec;

/**
 * Abstract class for builders handling
 * {@link com.enovka.gemini4j.domain.GenerationConfig} objects.
 *
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractGenerationConfigBuilder<T>
        extends AbstractComplexBuilder<T, AbstractRequestBuilder<?>> {

    /**
     * Constructor for the AbstractGenerationConfigBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractGenerationConfigBuilder(
            AbstractRequestBuilder<?> parentBuilder) {
        super(parentBuilder);
    }
}