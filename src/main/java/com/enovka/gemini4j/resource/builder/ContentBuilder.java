package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.resource.builder.spec.AbstractBuilder;
import com.enovka.gemini4j.resource.builder.spec.AbstractContentBuilder;

/**
 * Builder for creating {@link Content} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class ContentBuilder extends AbstractContentBuilder<Content> {

    /**
     * Private constructor to enforce a builder pattern.
     *
     * @param parentBuilder The parent {@link AbstractBuilder} instance.
     */
    ContentBuilder(AbstractBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Creates a new instance of the ContentBuilder.
     *
     * @param parentBuilder The parent {@link AbstractBuilder} instance.
     * @return A new ContentBuilder instance.
     */
    public static ContentBuilder builder(AbstractBuilder<?> parentBuilder) {
        return new ContentBuilder(parentBuilder);
    }

    /**
     * Sets the text for the content.
     *
     * @param text The text to set.
     * @return The builder instance for method chaining.
     */
    public ContentBuilder withText(String text) {
        if (text != null && !text.isEmpty()) {
            this.parts.add(PartBuilder.builder(this)
                    .withText(text)
                    .build());
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Content build() {
        return Content.builder()
                .withParts(parts)
                .withRole(role)
                .build();
    }
}