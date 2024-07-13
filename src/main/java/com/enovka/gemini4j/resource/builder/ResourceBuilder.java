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
 * Builder for creating resource instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
@Data
@Builder(setterPrefix = "with")
public class ResourceBuilder {

    private GeminiClient geminiClient;
    private JsonService jsonService;

    /**
     * Creates a new {@link GenerationResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link GenerationResource} instance.
     */
    public GenerationResource buildGenerationResource() {
        if (geminiClient == null) {
            throw new IllegalArgumentException(
                    "GeminiClient is required to build a GenerationResource.");
        }
        // Use a default JsonService if not provided
        if (jsonService == null) {
            jsonService = JsonServiceBuilder.builder().build().build();
        }
        return new GenerationResourceImpl(geminiClient, jsonService);
    }

    /**
     * Creates a new {@link ModelResource} instance based on the builder
     * configuration.
     *
     * @return A new {@link ModelResource} instance.
     */
    public ModelResource buildModelResource() {
        if (geminiClient == null) {
            throw new IllegalArgumentException(
                    "GeminiClient is required to build a ModelResource.");
        }
        // Use a default JsonService if not provided
        if (jsonService == null) {
            jsonService = JsonServiceBuilder.builder().build().build();
        }
        return new ModelResourceImpl(geminiClient, jsonService);
    }
}