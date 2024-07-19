package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Schema;
import com.enovka.gemini4j.domain.Type;

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
public class SchemaBuilder {

    private Object parentBuilder;
    // Can be FunctionDeclarationBuilder, GenerationConfigBuilder, or SchemaBuilder
    private Type type;
    private String format;
    private String description;
    private Boolean nullable;
    private List<String> enumValues;
    private Schema items;

    /**
     * Constructor for the SchemaBuilder used by
     * {@link FunctionDeclarationBuilder}.
     *
     * @param functionDeclarationBuilder The parent FunctionDeclarationBuilder
     * instance.
     */
    public SchemaBuilder(
            FunctionDeclarationBuilder functionDeclarationBuilder) {
        this.parentBuilder = functionDeclarationBuilder;
    }

    /**
     * Constructor for the SchemaBuilder used by
     * {@link GenerationConfigBuilder}.
     *
     * @param generationConfigBuilder The parent GenerationConfigBuilder
     * instance.
     */
    public SchemaBuilder(GenerationConfigBuilder generationConfigBuilder) {
        this.parentBuilder = generationConfigBuilder;
    }

    /**
     * Constructor for the SchemaBuilder used by another SchemaBuilder (for
     * nested items).
     *
     * @param schemaBuilder The parent SchemaBuilder instance.
     */
    public SchemaBuilder(SchemaBuilder schemaBuilder) {
        this.parentBuilder = schemaBuilder;
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
     * Sets the format for the schema.
     *
     * @param format The format of the schema.
     * @return The builder instance for method chaining.
     */
    public SchemaBuilder withFormat(String format) {
        this.format = format;
        return this;
    }

    /**
     * Sets the description for the schema.
     *
     * @param description The description of the schema.
     * @return The builder instance for method chaining.
     */
    public SchemaBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the nullable flag for the schema.
     *
     * @param nullable Whether the schema can be null.
     * @return The builder instance for method chaining.
     */
    public SchemaBuilder withNullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
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
     * Builds a {@link Schema} instance based on the configured parameters.
     *
     * @return The built {@link Schema} instance.
     */
    public Schema build() {
        return new Schema(type, format, description, nullable, enumValues,
                items);
    }

    /**
     * Returns to the parent builder, setting the built {@link Schema} instance
     * as appropriate.
     *
     * @return The parent builder instance for method chaining.
     */
    public Object and() {
        if (parentBuilder instanceof FunctionDeclarationBuilder) {
            ((FunctionDeclarationBuilder) parentBuilder).parameters = build();
            return parentBuilder;
        } else if (parentBuilder instanceof GenerationConfigBuilder) {
            ((GenerationConfigBuilder) parentBuilder).responseSchema = build();
            return parentBuilder;
        } else if (parentBuilder instanceof SchemaBuilder) {
            ((SchemaBuilder) parentBuilder).items = build();
            return parentBuilder;
        } else {
            throw new IllegalStateException("Invalid parent builder type.");
        }
    }
}