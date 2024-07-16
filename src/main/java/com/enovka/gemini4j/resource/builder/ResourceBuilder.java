package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.json.spec.JsonService;
import com.enovka.gemini4j.resource.impl.GenerationResourceImpl;
import com.enovka.gemini4j.resource.impl.ModelResourceImpl;
import com.enovka.gemini4j.resource.spec.GenerationResource;
import com.enovka.gemini4j.resource.spec.ModelResource;
import lombok.Builder;
import lombok.Data;

/**
 * Builder for creating resource instances for interacting with the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
@Data
@Builder(setterPrefix = "with")
public class ResourceBuilder {

    private final GeminiClient geminiClient; // Now final
    @Builder.Default
    private final JsonService jsonService = JsonServiceBuilder.builder().build()
            .build(); // Now final

    /**
     * Creates a new {@link GenerationResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link GenerationResource} instance.
     * @throws IllegalArgumentException If the GeminiClient is not set.
     */
    public GenerationResource buildGenerationResource() {
        if (geminiClient == null) {
            throw new IllegalArgumentException(
                    "GeminiClient is required to build a GenerationResource.");
        }
        return new GenerationResourceImpl(geminiClient, jsonService);
    }

    /**
     * Creates a new {@link ModelResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link ModelResource} instance.
     * @throws IllegalArgumentException If the GeminiClient is not set.
     */
    public ModelResource buildModelResource() {
        if (geminiClient == null) {
            throw new IllegalArgumentException(
                    "GeminiClient is required to build a ModelResource.");
        }
        return new ModelResourceImpl(geminiClient, jsonService);
    }
}