package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link Content} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class ContentBuilder {

    private final GenerateContentRequestBuilder generateContentRequestBuilder;
    private List<Part> parts;
    private String role;

    /**
     * Constructor for the ContentBuilder.
     *
     * @param generateContentRequestBuilder The parent
     * GenerateContentRequestBuilder instance.
     */
    public ContentBuilder(
            GenerateContentRequestBuilder generateContentRequestBuilder) {
        this.generateContentRequestBuilder = generateContentRequestBuilder;
    }

    /**
     * Adds a part to the list of parts for the content.
     *
     * @param part The part to add.
     * @return The builder instance for method chaining.
     */
    public ContentBuilder withPart(Part part) {
        if (this.parts == null) {
            this.parts = new ArrayList<>();
        }
        this.parts.add(part);
        return this;
    }

    /**
     * Creates a new {@link PartBuilder} for building a part.
     *
     * @return A new {@link PartBuilder} instance.
     */
    public PartBuilder withPart() {
        return new PartBuilder(this);
    }

    /**
     * Sets the role for the content.
     *
     * @param role The role of the content producer (e.g., "user", "model").
     * @return The builder instance for method chaining.
     */
    public ContentBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * Builds a {@link Content} instance based on the configured parameters.
     *
     * @return The built {@link Content} instance.
     */
    public Content build() {
        return Content.builder()
                .withParts(parts)
                .withRole(role)
                .build();
    }

    /**
     * Sets the built {@link Content} instance as the system instruction in the
     * parent builder.
     *
     * @return The parent {@link GenerateContentRequestBuilder} instance for
     * method chaining.
     */
    public GenerateContentRequestBuilder and() {
        generateContentRequestBuilder.systemInstruction = build();
        return generateContentRequestBuilder;
    }
}