package com.enovka.gemini4j.resource.builder.request.spec;

import com.enovka.gemini4j.model.Blob;
import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.FileData;
import com.enovka.gemini4j.model.Part;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Abstract base class for builders that handle content creation, providing common functionality
 * for adding and managing content parts. This abstract class extends {@link BaseRequestBuilder}
 * and introduces fields and methods specifically for handling content within Gemini API requests.
 *
 * @param <B> The type of the concrete builder class.
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class ContentRequestBuilder<B extends ContentRequestBuilder<B, T>, T>
        extends BaseRequestBuilder<B, T> {

    protected List<Content> contents = new ArrayList<>();
    protected Content systemInstruction;

    /**
     * Constructor for the AbstractContentBuilder.
     *
     * @since 0.2.0
     */
    protected ContentRequestBuilder() {
        super();
    }

    /**
     * Adds a {@link Content} object to the request.  Content objects represent a structured message
     * within a conversation, as detailed in the Gemini API documentation:
     * [<a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1/Content">...</a>](https://ai.google.dev/gemini-api/docs/reference/rest/v1/Content)
     *
     * @param content The content to add.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withContent(Content content) {
        this.contents.add(content);
        return self();
    }

    /**
     * Adds a text content to the request. This method creates a {@link Content} object with the
     * specified text and role and adds it to the request. The created {@link Part} object within
     * the {@link Content} will have its `text` field populated.  The role should be either "user"
     * or "model", as defined in the Gemini API documentation.
     *
     * @param text The text content to add.
     * @param role The role of the content (e.g., "user", "model").
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withTextContent(String text, String role) {
        return withContent(Content.builder()
                .withRole(role)
                .withParts(List.of(Part.builder()
                        .withText(text)
                        .build()))
                .build());
    }

    /**
     * Adds text content with the role "user" to the request. This is a convenience method for
     * adding user input to the conversation.
     *
     * @param text The user input text.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withUserContent(String text) {
        return withTextContent(text, "user");
    }


    /**
     * Adds an image from a {@link File} to the request content.  The image is encoded as a Base64
     * string and included as inline data within a {@link Part} object. The MIME type is determined
     * from the file extension.  Supported image formats are listed in the Gemini API documentation:
     * [<a href="https://ai.google.dev/gemini-api/docs/prompting_with_media#supported_file_formats">...</a>](https://ai.google.dev/gemini-api/docs/prompting_with_media#supported_file_formats)
     *
     * @param imageFile The image file to add.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the file is not a valid image file or if an error occurs
     *                                  during file processing.
     * @since 0.2.0
     */
    public B withImage(File imageFile) {
        try {
            String mimeType = Files.probeContentType(imageFile.toPath());
            if (!mimeType.startsWith("image/")) {
                throw new IllegalArgumentException("Invalid image file: " + imageFile.getName());
            }
            byte[] fileContent = Files.readAllBytes(imageFile.toPath());
            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
            return withInlineData(base64Encoded, mimeType, "user");
        } catch (Exception e) {
            throw new IllegalArgumentException("Error processing image file: " + e.getMessage(), e);
        }
    }


    /**
     * Adds an image from an {@link InputStream} to the request content.  The image is encoded as
     * a Base64 string and included as inline data within a {@link Part} object.  The MIME type
     * must be provided. Supported image formats are listed in the Gemini API documentation.
     *
     * @param imageStream The image input stream.
     * @param mimeType    The MIME type of the image (e.g., "image/jpeg").
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If an error occurs during stream processing.
     * @since 0.2.0
     */
    public B withImage(InputStream imageStream, String mimeType) {
        if (!mimeType.startsWith("image/")) {
            throw new IllegalArgumentException("Invalid image MIME type: " + mimeType);
        }
        try {
            byte[] fileContent = imageStream.readAllBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
            return withInlineData(base64Encoded, mimeType, "user");
        } catch (Exception e) {
            throw new IllegalArgumentException("Error processing image stream: " + e.getMessage(), e);
        }
    }


    /**
     * Adds an image from a URI to the request content. The URI is included as a {@link FileData}
     * object within a {@link Part}.  The MIME type is determined from the URI.  Supported image
     * formats are listed in the Gemini API documentation.
     *
     * @param imageUri The URI of the image.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the URI is invalid or if an error occurs during URI
     *                                  processing.
     * @since 0.2.0
     */
    public B withImage(URI imageUri) {
        try {
            String mimeType = Files.probeContentType(Paths.get(imageUri));
            if (!mimeType.startsWith("image/")) {
                throw new IllegalArgumentException("Invalid image URI: " + imageUri);
            }
            return withFileData(imageUri.toString(), mimeType, "user");
        } catch (Exception e) {
            throw new IllegalArgumentException("Error processing image URI: " + e.getMessage(), e);
        }
    }


    /**
     * Adds inline data to the request content. This method creates a {@link Part} object with the
     * specified data, MIME type, and role and adds it to the request content.  This is used for
     * including data directly within the request, such as Base64 encoded images or other media.
     *
     * @param data     The inline data as a Base64 encoded string.
     * @param mimeType The MIME type of the data.
     * @param role     The role of the content (e.g., "user", "model").
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withInlineData(String data, String mimeType, String role) {
        return withContent(Content.builder()
                .withRole(role)
                .withParts(List.of(Part.builder()
                        .withInlineData(Blob.builder()
                                .withData(data)
                                .withMimeType(mimeType)
                                .build())
                        .build()))
                .build());
    }


    /**
     * Adds a file data to the request content.  This method creates a {@link Part} object with
     * the specified file URI, MIME type, and role and adds it to the request content.  This is
     * used for referencing external files, such as images or documents stored in cloud storage.
     *
     * @param fileUri  The URI of the file.
     * @param mimeType The MIME type of the file.
     * @param role     The role of the content (e.g., "user", "model").
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withFileData(String fileUri, String mimeType, String role) {
        return withContent(Content.builder()
                .withRole(role)
                .withParts(List.of(Part.builder()
                        .withFileData(FileData.builder()
                                .withFileUri(fileUri)
                                .withMimeType(mimeType)
                                .build())
                        .build()))
                .build());
    }


    /**
     * Sets the system instruction for the request. System instructions provide guidance to the
     * model on how to behave and respond, as described in the Gemini API documentation:
     * [<a href="https://ai.google.dev/gemini-api/docs/system-instructions">...</a>](https://ai.google.dev/gemini-api/docs/system-instructions)
     *
     * @param systemInstruction The system instruction content.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withSystemInstruction(Content systemInstruction) {
        this.systemInstruction = systemInstruction;
        return self();
    }

}