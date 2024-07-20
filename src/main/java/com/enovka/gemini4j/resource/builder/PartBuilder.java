package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.resource.builder.spec.AbstractContentBuilder;
import com.enovka.gemini4j.resource.builder.spec.AbstractPartBuilder;

/**
 * Builder for creating {@link Part} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class PartBuilder extends AbstractPartBuilder<Part> {

    private String text;

    /**
     * Private constructor to enforce builder pattern.
     *
     * @param parentBuilder The parent {@link AbstractContentBuilder} instance.
     */
    public PartBuilder(AbstractContentBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Creates a new instance of the PartBuilder.
     *
     * @param parentBuilder The parent {@link AbstractContentBuilder} instance.
     * @return A new PartBuilder instance.
     */
    public static PartBuilder builder(AbstractContentBuilder<?> parentBuilder) {
        return new PartBuilder(parentBuilder);
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
     * {@inheritDoc}
     */
    @Override
    public Part build() {
        return Part.builder()
                .withText(text)
                .withInlineData(inlineData)
                .withFunctionCall(functionCall)
                .withFunctionResponse(functionResponse)
                .withFileData(fileData)
                .build();
    }
}