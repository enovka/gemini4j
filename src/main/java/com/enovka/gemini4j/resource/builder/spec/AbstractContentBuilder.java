package com.enovka.gemini4j.resource.builder.spec;

import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.resource.builder.PartBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Specialized abstract class for builders handling {@link Content} objects,
 * providing methods for managing parts and roles.
 *
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractContentBuilder<T extends Content>
        extends AbstractComplexBuilder<T, AbstractBuilder<?>> {

    protected List<Part> parts = new ArrayList<>();
    protected String role;

    /**
     * Constructor for the AbstractContentBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractContentBuilder(AbstractBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Adds a part to the list of parts for the content.
     *
     * @param part The part to add.
     * @return The builder instance for method chaining.
     */
    public AbstractContentBuilder<T> withPart(Part part) {
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
    public AbstractContentBuilder<T> withRole(String role) {
        this.role = role;
        return this;
    }
}