package com.enovka.gemini4j.resource.builder.spec;

import com.enovka.gemini4j.domain.Schema;
import com.enovka.gemini4j.domain.Type;

/**
 * Abstract class for builders handling {@link Schema} objects.
 *
 * @param <T> The type of object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public abstract class AbstractSchemaBuilder<T extends Schema>
        extends AbstractComplexBuilder<T, AbstractBuilder<?>> {

    protected Type type;
    protected String format;
    protected String description;
    protected Boolean nullable;

    /**
     * Constructor for the AbstractSchemaBuilder.
     *
     * @param parentBuilder The parent builder instance.
     */
    protected AbstractSchemaBuilder(AbstractBuilder<?> parentBuilder) {
        super(parentBuilder);
    }

    /**
     * Sets the format for the schema.
     *
     * @param format The format of the schema.
     * @return The builder instance for method chaining.
     */
    public AbstractSchemaBuilder<T> withFormat(String format) {
        this.format = format;
        return this;
    }

    /**
     * Sets the description for the schema.
     *
     * @param description The description of the schema.
     * @return The builder instance for method chaining.
     */
    public AbstractSchemaBuilder<T> withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the nullable flag for the schema.
     *
     * @param nullable Whether the schema can be null.
     * @return The builder instance for method chaining.
     */
    public AbstractSchemaBuilder<T> withNullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }
}