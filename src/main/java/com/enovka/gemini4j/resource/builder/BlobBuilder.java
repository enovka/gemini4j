package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Blob;

/**
 * Builder for creating {@link Blob} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class BlobBuilder {

    private final PartBuilder partBuilder;
    private String mimeType;
    private String data;

    /**
     * Constructor for the BlobBuilder.
     *
     * @param partBuilder The parent PartBuilder instance.
     */
    public BlobBuilder(PartBuilder partBuilder) {
        this.partBuilder = partBuilder;
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
     * Builds a {@link Blob} instance based on the configured parameters.
     *
     * @return The built {@link Blob} instance.
     */
    public Blob build() {
        return Blob.builder()
                .withMimeType(mimeType)
                .withData(data)
                .build();
    }

    /**
     * Sets the built {@link Blob} instance as the inline data in the parent
     * builder.
     *
     * @return The parent {@link PartBuilder} instance for method chaining.
     */
    public PartBuilder and() {
        partBuilder.inlineData = build();
        return partBuilder;
    }
}