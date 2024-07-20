package com.enovka.gemini4j.resource.builder.spec;

import com.enovka.gemini4j.domain.ToolConfig;

/**
 * Abstract class for builders handling {@link ToolConfig} objects.
 *
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractToolConfigBuilder<T extends ToolConfig>
        extends AbstractComplexBuilder<T, AbstractBuilder<?>> {

    /**
     * Constructor for the AbstractToolConfigBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractToolConfigBuilder(AbstractBuilder<?> parentBuilder) {
        super(parentBuilder);
    }
}