package com.enovka.gemini4j.resource.spec.base;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.type.SupportedModelMethod;

import java.util.List;

public interface Resource {
    List<SupportedModelMethod> getSupportedMethods();

    /**
     * Returns the associated {@link GeminiClient}.
     *
     * @return The {@link GeminiClient} instance.
     */
    GeminiClient getGeminiClient();
}
