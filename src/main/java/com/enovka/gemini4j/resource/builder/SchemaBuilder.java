package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Schema;
import com.enovka.gemini4j.domain.Type;
import com.enovka.gemini4j.resource.builder.spec.AbstractBuilder;
import com.enovka.gemini4j.resource.builder.spec.AbstractSchemaBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for constructing {@link Schema} objects. This builder can be used
 * independently or as part of other builders like
 * {@link FunctionDeclarationBuilder} and {@link GenerationConfigBuilder}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class SchemaBuilder extends AbstractSchemaBuilder<Schema> {

    private List<String> enumValues;
    private Schema items;

    /**
     * Constructor for the SchemaBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    public SchemaBuilder(AbstractBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the type for the schema.
     *
     * @param type The type of the schema.
     * @return The builder instance for method chaining.
     */
    public SchemaBuilder withType(Type type) {
        this.type = type;
        return this;
    }

    /**
     * Creates a new {@link TypeBuilder} for building the schema type.
     *
     * @return A new {@link TypeBuilder} instance.
     */
    public TypeBuilder withType() {
        return new TypeBuilder(this);
    }

    /**
     * Adds an enum value to the list of enum values for the schema.
     *
     * @param enumValue The enum value to add.
     * @return The builder instance for method chaining.
     */
    public SchemaBuilder withEnumValue(String enumValue) {
        if (this.enumValues == null) {
            this.enumValues = new ArrayList<>();
        }
        this.enumValues.add(enumValue);
        return this;
    }

    /**
     * Sets the schema for the items in an array schema.
     *
     * @param items The schema for the array items.
     * @return The builder instance for method chaining.
     */
    public SchemaBuilder withItems(Schema items) {
        this.items = items;
        return this;
    }

    /**
     * Creates a new {@link SchemaBuilder} for building the schema for array
     * items.
     *
     * @return A new {@link SchemaBuilder} instance.
     */
    public SchemaBuilder withItems() {
        return new SchemaBuilder(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema build() {
        return new Schema(type, format, description, nullable, enumValues,
                items);
    }
}