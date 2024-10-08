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
import java.util.Collections;
import java.util.List;

/**
 * Abstract builder for requests involving content, inheriting from {@link AbstractGeminiRequestBuilder}.
 * This class provides common functionality for adding and managing different types of content
 * within a Gemini API request, such as text, images, and other media.  It serves as the base
 * for builders dealing with content generation, embedding, and related operations.
 *
 * @param <B> The type of the concrete builder class, enabling method chaining.
 * @param <T> The type of request object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class AbstractContentRequestBuilder<B extends AbstractContentRequestBuilder<B, T>, T>
        extends AbstractGeminiRequestBuilder<B, T> {

    protected List<Content> contents = new ArrayList<>();
    protected Content systemInstruction;

    /**
     * Adds a {@link Content} object to the request.  Content objects represent a structured
     * message within a conversation, encapsulating various parts like text, images, or function
     * calls.  For details on the Content structure, refer to the Gemini API documentation.
     *
     * @param content The Content object to add to the request.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided Content object is null.
     * @see Content
     * @since 0.2.0
     */
    protected B withContent(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null.");
        }
        this.contents.add(content);
        return self();
    }

    /**
     * Adds a text part to the request content.  This method creates a {@link Content} object
     * containing a single {@link Part} with the specified text and role, and adds it to the
     * request.  The role indicates the sender of the text ("user" or "model").
     *
     * @param text The text content to add.
     * @param role The role of the sender ("user" or "model").
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided text or role is null or empty.
     * @see Content
     * @see Part
     * @since 0.2.0
     */
    public B withTextContent(String text, String role) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty.");
        }
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }
        return withContent(Content.builder()
                .withRole(role)
                .withParts(List.of(Part.builder().withText(text).build()))
                .build());
    }

    /**
     * Adds a List of {@link Content} object to the request.  Content objects represent a structured
     * message within a conversation, encapsulating various parts like text, images, or function
     * calls.  For details on the Content structure, refer to the Gemini API documentation.
     *
     * @param contents The Content list object to add to the request.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided Content object is null or empty.
     * @see Content
     * @since 0.2.0
     */
    protected B withContents(List<Content> contents) {
        if (contents == null || contents.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }
        this.contents.addAll(contents);
        return self();
    }

    /**
     * Adds user text content to the request. This is a convenience method for adding text input
     * with the role "user".
     *
     * @param text The user input text.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided text is null or empty.
     * @since 0.2.0
     */
    public B withUserContent(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("User content cannot be null or empty.");
        }
        return withTextContent(text, "user");
    }

    /**
     * Adds an image to the request content from a {@link File}.  The image is Base64 encoded
     * and included as inline data. The MIME type is automatically determined from the file
     * extension.  Ensure the file is a valid image and that the file extension correctly reflects
     * its MIME type. For supported image formats, consult the Gemini API documentation.
     *
     * @param imageFile The image file to add.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided file is null, not an image, or if an error
     *                                  occurs during file processing.
     * @since 0.2.0
     */
    protected B withImage(File imageFile) {
        if (imageFile == null) {
            throw new IllegalArgumentException("Image file cannot be null.");
        }

        try {
            String mimeType = Files.probeContentType(imageFile.toPath());
            if (mimeType == null || !mimeType.startsWith("image/")) {
                throw new IllegalArgumentException(
                        "Invalid image file: " + imageFile.getName());
            }

            byte[] fileContent = Files.readAllBytes(imageFile.toPath());
            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);

            return withInlineData(base64Encoded, mimeType, "user");

        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Error processing image file: " + e.getMessage(), e);
        }
    }

    /**
     * Adds an image to the request content from an {@link InputStream}. The image is Base64
     * encoded and included as inline data. You must provide the correct MIME type for the image.
     * For supported image formats and MIME types, refer to the Gemini API documentation.
     *
     * @param imageStream The image input stream.
     * @param mimeType    The MIME type of the image (e.g., "image/jpeg").
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided input stream or MIME type is null, or if an
     *                                  error occurs during stream processing.
     * @since 0.2.0
     */
    protected B withImage(InputStream imageStream, String mimeType) {
        if (imageStream == null) {
            throw new IllegalArgumentException("Image input stream cannot be null.");
        }
        if (mimeType == null || mimeType.isEmpty()) {
            throw new IllegalArgumentException("MIME type cannot be null or empty.");
        }

        if (!mimeType.startsWith("image/")) {
            throw new IllegalArgumentException("Invalid MIME type: " + mimeType);
        }

        try {
            byte[] fileContent = imageStream.readAllBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);

            return withInlineData(base64Encoded, mimeType, "user");

        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Error processing image stream: " + e.getMessage(), e);
        }
    }

    /**
     * Adds an image to the request content from a {@link URI}.  The URI is included as file
     * data. The MIME type is automatically determined from the URI. Ensure the URI is valid
     * and points to a supported image format, as detailed in the Gemini API documentation.
     *
     * @param imageUri The URI of the image.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided URI is null or invalid, or if an error
     *                                  occurs during URI processing.
     * @since 0.2.0
     */
    protected B withImage(URI imageUri) {
        if (imageUri == null) {
            throw new IllegalArgumentException("Image URI cannot be null.");
        }

        try {
            String mimeType = Files.probeContentType(Paths.get(imageUri));

            if (mimeType == null || !mimeType.startsWith("image/")) {
                throw new IllegalArgumentException("Invalid image URI: " + imageUri);
            }

            return withFileData(imageUri.toString(), mimeType, "user");

        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Error processing image URI: " + e.getMessage(), e);
        }
    }

    /**
     * Adds inline data, such as a Base64 encoded string, to the request content. This data
     * is included directly in the request body. You must provide the correct MIME type for
     * the data.  For supported MIME types, consult the Gemini API documentation.
     *
     * @param data     The inline data, typically a Base64 encoded string.
     * @param mimeType The MIME type of the data.
     * @param role     The role of the sender ("user" or "model").
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided data, MIME type, or role is null or empty.
     * @since 0.2.0
     */
    protected B withInlineData(String data, String mimeType, String role) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Inline data cannot be null or empty.");
        }
        if (mimeType == null || mimeType.isEmpty()) {
            throw new IllegalArgumentException("MIME type cannot be null or empty.");
        }
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }


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
     * Adds file data to the request content, referencing an external file using its URI.  The MIME
     * type should accurately reflect the file's content type.  For supported file formats and MIME
     * types, refer to the Gemini API documentation.
     *
     * @param fileUri  The URI of the external file.
     * @param mimeType The MIME type of the file.
     * @param role     The role of the sender ("user" or "model").
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided file URI, MIME type, or role is null or empty.
     * @since 0.2.0
     */
    protected B withFileData(String fileUri, String mimeType, String role) {
        if (fileUri == null || fileUri.isEmpty()) {
            throw new IllegalArgumentException("File URI cannot be null or empty.");
        }
        if (mimeType == null || mimeType.isEmpty()) {
            throw new IllegalArgumentException("MIME type cannot be null or empty.");
        }
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }

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
     * Sets the system instruction for the request.  System instructions provide high-level
     * guidance to the model on how it should behave and generate responses. For details and
     * best practices, refer to the Gemini API documentation on system instructions.
     *
     * @param systemInstruction The system instruction content.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided system instruction is null.
     * @since 0.2.0
     */
    protected B withSystemInstruction(Content systemInstruction) {
        if (systemInstruction == null) {
            throw new IllegalArgumentException("System instruction cannot be null.");
        }
        this.systemInstruction = systemInstruction;
        return self();
    }

    /**
     * Sets the system instruction for the request.  System instructions provide high-level
     * guidance to the model on how it should behave and generate responses. For details and
     * best practices, refer to the Gemini API documentation on system instructions.
     *
     * @param systemInstruction The system instruction content.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided system instruction is null.
     * @since 0.2.0
     */
    public B withSystemInstruction(String systemInstruction) {
        if (systemInstruction == null) {
            throw new IllegalArgumentException("System instruction cannot be null.");
        }
        this.systemInstruction = Content.builder()
                .withParts(Collections.singletonList(Part.builder().withText(systemInstruction).build()))
                .build();
        return self();
    }
}