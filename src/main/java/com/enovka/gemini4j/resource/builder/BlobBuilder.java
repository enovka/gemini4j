package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Blob;
import com.enovka.gemini4j.resource.builder.spec.AbstractComplexBuilder;

/**
 * Builder for creating {@link Blob} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class BlobBuilder extends AbstractComplexBuilder<Blob, PartBuilder> {

    private String mimeType;
    private String data;

    /**
     * Constructor for the BlobBuilder.
     *
     * @param parentBuilder The parent {@link PartBuilder} instance.
     */
    public BlobBuilder(PartBuilder parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the MIME type for the blob.
     *
     * @param mimeType The MIME type of the data.
     * @return The builder instance for method chaining.
     */
    public BlobBuilder withMimeType(String mimeType) {
        if (mimeType == null || mimeType.isEmpty()) {
            throw new IllegalArgumentException("MIME type is required.");
        }
        this.mimeType = mimeType;
        return this;
    }

    /**
     * Sets the data for the blob.
     *
     * @param data The base64-encoded data.
     * @return The builder instance for method chaining.
     */
    public BlobBuilder withData(String data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data is required.");
        }
        this.data = data;
        return this;
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