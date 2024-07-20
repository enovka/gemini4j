package com.enovka.gemini4j.resource.builder.spec;

import com.enovka.gemini4j.domain.Blob;
import com.enovka.gemini4j.domain.FileData;
import com.enovka.gemini4j.domain.FunctionCall;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.response.FunctionResponse;

/**
 * Abstract class for builders handling {@link Part} objects, providing methods
 * for setting common part attributes.
 *
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractPartBuilder<T extends Part>
        extends AbstractComplexBuilder<T, AbstractContentBuilder<?>> {

    protected Blob inlineData;
    protected FunctionCall functionCall;
    protected FunctionResponse functionResponse;
    protected FileData fileData;

    /**
     * Constructor for the AbstractPartBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractPartBuilder(AbstractContentBuilder<?> parentBuilder) {
        super(parentBuilder);
    }
}