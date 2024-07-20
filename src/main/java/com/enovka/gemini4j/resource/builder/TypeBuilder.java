package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.Type;
import com.enovka.gemini4j.domain.type.TypeEnum;
import com.enovka.gemini4j.resource.builder.spec.AbstractComplexBuilder;
import com.enovka.gemini4j.resource.builder.spec.AbstractSchemaBuilder;

/**
 * Builder for creating {@link Type} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class TypeBuilder extends
        AbstractComplexBuilder<Type, AbstractSchemaBuilder<?>> {

    private TypeEnum type;

    /**
     * Constructor for the TypeBuilder.
     *
     * @param parentBuilder The parent {@link AbstractSchemaBuilder} instance.
     */
    public TypeBuilder(AbstractSchemaBuilder<?> parentBuilder) {
        super(parentBuilder);
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
     * {@inheritDoc}
     */
    @Override
    public Type build() {
        return Type.builder()
                .withType(type)
                .build();
    }
}