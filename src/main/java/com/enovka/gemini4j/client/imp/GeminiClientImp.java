package com.enovka.gemini4j.client.imp;

import com.enovka.gemini4j.client.spec.AbstractGeminiClient;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;

/**
 * Implementation of the {@link GeminiClient} interface.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class GeminiClientImp extends AbstractGeminiClient {

    /**
     * Constructs a new AbstractGeminiClient with the required API key, model,
     * HTTP client, base URL, and JSON service.
     *
     * @param apiKey The API key for authentication with the Gemini API.
     * @param model The default model to use for requests.
     * @param httpClient The HTTP client to use for communication.
     * @param baseUrl The base URL for the Gemini API.
     * @param jsonService The JSON service to use for serialization and
     * deserialization.
     */
    public GeminiClientImp(String apiKey, String model,
                           HttpClient httpClient,
                           String baseUrl,
                           JsonService jsonService) {
        super(apiKey, model, httpClient, baseUrl, jsonService);
    }
}