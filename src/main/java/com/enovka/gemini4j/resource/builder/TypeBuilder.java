package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Type;
import com.enovka.gemini4j.domain.type.TypeEnum;

/**
 * Builder for creating {@link Type} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class TypeBuilder {

    private final SchemaBuilder schemaBuilder;
    private TypeEnum type;

    /**
     * Constructor for the TypeBuilder.
     *
     * @param schemaBuilder The parent SchemaBuilder instance.
     */
    public TypeBuilder(SchemaBuilder schemaBuilder) {
        this.schemaBuilder = schemaBuilder;
    }

    /**
     * Sets the type enum for the type.
     *
     * @param type The type enum.
     * @return The builder instance for method chaining.
     */
    public TypeBuilder withType(TypeEnum type) {
        if (type == null) {
            throw new IllegalArgumentException("Type enum is required.");
        }
        this.type = type;
        return this;
    }

    /**
     * Builds a {@link Type} instance based on the configured parameters.
     *
     * @return The built {@link Type} instance.
     */
    public Type build() {
        return Type.builder()
                .withType(type)
                .build();
    }

    /**
     * Sets the built {@link Type} instance as the type in the parent builder.
     *
     * @return The parent {@link SchemaBuilder} instance for method chaining.
     */
    public SchemaBuilder and() {
        schemaBuilder.withType(build());
        return schemaBuilder;
    }
}