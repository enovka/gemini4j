package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.FileData;

/**
 * Builder for creating {@link FileData} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class FileDataBuilder {

    private final PartBuilder partBuilder;
    private String mimeType;
    private String fileUri;

    /**
     * Constructor for the FileDataBuilder.
     *
     * @param partBuilder The parent PartBuilder instance.
     */
    public FileDataBuilder(PartBuilder partBuilder) {
        this.partBuilder = partBuilder;
    }

    /**
     * Sets the MIME type for the file data.
     *
     * @param mimeType The MIME type of the file data.
     * @return The builder instance for method chaining.
     */
    public FileDataBuilder withMimeType(String mimeType) {
        if (mimeType == null || mimeType.isEmpty()) {
            throw new IllegalArgumentException("MIME type is required.");
        }
        this.mimeType = mimeType;
        return this;
    }

    /**
     * Sets the file URI for the file data.
     *
     * @param fileUri The URI of the file.
     * @return The builder instance for method chaining.
     */
    public FileDataBuilder withFileUri(String fileUri) {
        if (fileUri == null || fileUri.isEmpty()) {
            throw new IllegalArgumentException("File URI is required.");
        }
        this.fileUri = fileUri;
        return this;
    }

    /**
     * Builds a {@link FileData} instance based on the configured parameters.
     *
     * @return The built {@link FileData} instance.
     */
    public FileData build() {
        return FileData.builder()
                .withMimeType(mimeType)
                .withFileUri(fileUri)
                .build();
    }

    /**
     * Sets the built {@link FileData} instance as the file data in the parent
     * builder.
     *
     * @return The parent {@link PartBuilder} instance for method chaining.
     */
    public PartBuilder and() {
        partBuilder.fileData = build();
        return partBuilder;
    }
}