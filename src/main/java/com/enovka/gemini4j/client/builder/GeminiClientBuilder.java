package com.enovka.gemini4j.client.builder;

import com.enovka.gemini4j.client.impl.DefaultGeminiClient;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.http.spec.HttpClient;
import com.enovka.gemini4j.json.spec.JsonService;
import lombok.Builder;
import lombok.Data;

/**
 * Builder for creating {@link GeminiClient} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
@Data
@Builder(setterPrefix = "with")
public class GeminiClientBuilder {

    private String apiKey;
    private String model;
    private HttpClient httpClient;
    private String baseUrl;
    private JsonService jsonService;

    /**
     * Creates a new {@link GeminiClient} instance based on the builder
     * configuration.
     *
     * @return A new {@link GeminiClient} instance.
     */
    public GeminiClient build() {
        DefaultGeminiClient client = new DefaultGeminiClient(apiKey);
        if (model != null) {
            client.setModel(model);
        }
        if (httpClient != null) {
            client.setHttpClient(httpClient);
        }
        return client;
    }
}