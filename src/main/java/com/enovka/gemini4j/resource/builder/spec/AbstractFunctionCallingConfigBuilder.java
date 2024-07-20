package com.enovka.gemini4j.resource.builder.spec;

/**
 * Abstract class for builders handling
 * {@link com.enovka.gemini4j.domain.FunctionCallingConfig} objects.
 *
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractFunctionCallingConfigBuilder<T>
        extends AbstractComplexBuilder<T, AbstractToolConfigBuilder<?>> {

    /**
     * Constructor for the AbstractFunctionCallingConfigBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractFunctionCallingConfigBuilder(
            AbstractToolConfigBuilder<?> parentBuilder) {
        super(parentBuilder);
    }
}