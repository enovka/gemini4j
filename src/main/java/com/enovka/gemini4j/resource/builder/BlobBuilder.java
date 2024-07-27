package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Blob;

/**
 * Builder for creating {@link Blob} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class BlobBuilder extends AbstractDataBuilder<Blob, PartBuilder> {

    /**
     * Constructor for the BlobBuilder.
     *
     * @param parentBuilder The parent {@link PartBuilder} instance.
     */
    public BlobBuilder(PartBuilder parentBuilder) {
        super(parentBuilder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blob build() {
        return Blob.builder()
                .withMimeType(mimeType)
                .withData(data)
                .build();
    }
}