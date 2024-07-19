package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Blob;
import com.enovka.gemini4j.domain.FileData;
import com.enovka.gemini4j.domain.FunctionCall;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.response.FunctionResponse;

/**
 * Builder for creating {@link Part} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class PartBuilder {

    private final ContentBuilder contentBuilder;
    public Blob inlineData;
    public FunctionCall functionCall;
    public FunctionResponse functionResponse;
    public FileData fileData;
    private String text;

    /**
     * Constructor for the PartBuilder.
     *
     * @param contentBuilder The parent ContentBuilder instance.
     */
    public PartBuilder(ContentBuilder contentBuilder) {
        this.contentBuilder = contentBuilder;
    }

    /**
     * Sets the inline text for the part.
     *
     * @param text The inline text.
     * @return The builder instance for method chaining.
     */
    public PartBuilder withText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Sets the inline data for the part.
     *
     * @param inlineData The inline data.
     * @return The builder instance for method chaining.
     */
    public PartBuilder withInlineData(Blob inlineData) {
        this.inlineData = inlineData;
        return this;
    }

    /**
     * Creates a new {@link BlobBuilder} for building the inline data.
     *
     * @return A new {@link BlobBuilder} instance.
     */
    public BlobBuilder withInlineData() {
        return new BlobBuilder(this);
    }

    /**
     * Sets the function call for the part.
     *
     * @param functionCall The function call.
     * @return The builder instance for method chaining.
     */
    public PartBuilder withFunctionCall(FunctionCall functionCall) {
        this.functionCall = functionCall;
        return this;
    }

    /**
     * Creates a new {@link FunctionCallBuilder} for building the function
     * call.
     *
     * @return A new {@link FunctionCallBuilder} instance.
     */
    public FunctionCallBuilder withFunctionCall() {
        return new FunctionCallBuilder(this);
    }

    /**
     * Sets the function response for the part.
     *
     * @param functionResponse The function response.
     * @return The builder instance for method chaining.
     */
    public PartBuilder withFunctionResponse(
            FunctionResponse functionResponse) {
        this.functionResponse = functionResponse;
        return this;
    }

    /**
     * Creates a new {@link FunctionResponseBuilder} for building the function
     * response.
     *
     * @return A new {@link FunctionResponseBuilder} instance.
     */
    public FunctionResponseBuilder withFunctionResponse() {
        return new FunctionResponseBuilder(this);
    }

    /**
     * Sets the file data for the part.
     *
     * @param fileData The file data.
     * @return The builder instance for method chaining.
     */
    public PartBuilder withFileData(FileData fileData) {
        this.fileData = fileData;
        return this;
    }

    /**
     * Creates a new {@link FileDataBuilder} for building the file data.
     *
     * @return A new {@link FileDataBuilder} instance.
     */
    public FileDataBuilder withFileData() {
        return new FileDataBuilder(this);
    }

    /**
     * Builds a {@link Part} instance based on the configured parameters.
     *
     * @return The built {@link Part} instance.
     */
    public Part build() {
        return Part.builder()
                .withText(text)
                .withInlineData(inlineData)
                .withFunctionCall(functionCall)
                .withFunctionResponse(functionResponse)
                .withFileData(fileData)
                .build();
    }

    /**
     * Adds the built {@link Part} instance to the list of parts in the parent
     * builder.
     *
     * @return The parent {@link ContentBuilder} instance for method chaining.
     */
    public ContentBuilder and() {
        contentBuilder.withPart(build());
        return contentBuilder;
    }
}