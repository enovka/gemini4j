package com.enovka.gemini4j.client.impl;

import com.enovka.gemini4j.client.spec.AbstractGeminiClient;
import com.enovka.gemini4j.client.spec.GeminiClient;

/**
 * Default implementation of the {@link GeminiClient} interface.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class DefaultGeminiClient extends AbstractGeminiClient {

    /**
     * Constructs a new DefaultGeminiClient with the required API key.
     *
     * @param apiKey The API key for authentication with the Gemini API.
     */
    public DefaultGeminiClient(String apiKey) {
        super(apiKey);
    }
}