package com.enovka.gemini4j.infrastructure.json.builder;

import com.enovka.gemini4j.infrastructure.json.impl.JacksonJsonService;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import lombok.Builder;
import lombok.Data;

/**
 * Builder for creating {@link JsonService} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
@Data
@Builder(setterPrefix = "with")
public class JsonServiceBuilder {

    @Builder.Default
    private JsonServiceType jsonServiceType = JsonServiceType.JACKSON;

    private JsonService customJsonService;

    /**
     * Creates a new {@link JsonService} instance based on the builder
     * configuration.
     *
     * @return A new {@link JsonService} instance.
     * @since 0.0.2
     */
    public JsonService build() {
        switch (jsonServiceType) {
            case JACKSON:
                return new JacksonJsonService();
            case CUSTOM:
                if (customJsonService == null) {
                    throw new IllegalArgumentException(
                            "Custom JsonService instance is required when using JsonServiceType.CUSTOM.");
                }
                return customJsonService;
            default:
                throw new IllegalArgumentException(
                        "Unknown JsonServiceType: " + jsonServiceType);
        }
    }
}