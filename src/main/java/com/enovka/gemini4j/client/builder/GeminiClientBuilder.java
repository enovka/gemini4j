package com.enovka.gemini4j.client.builder;

import com.enovka.gemini4j.client.imp.GeminiClientImp;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.http.factory.HttpClientBuilder;
import com.enovka.gemini4j.http.spec.HttpClient;
import com.enovka.gemini4j.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.json.spec.JsonService;
import lombok.Builder;
import lombok.Data;

/**
 * Builder for creating {@link GeminiClient} instances.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 0.0.1
 */
@Data
@Builder(setterPrefix = "with")
public class GeminiClientBuilder {

    private String apiKey;
    @Builder.Default
    private String model = "models/gemini-pro-vision-001";
    @Builder.Default
    private HttpClient httpClient = HttpClientBuilder.builder().build()
            .getCustomClient();
    @Builder.Default
    private String baseUrl = "https://generativelanguage.googleapis.com/v1beta";
    @Builder.Default
    private JsonService jsonService = JsonServiceBuilder.builder().build()
            .build();

    /**
     * Creates a new {@link GeminiClient} instance with default settings.
     *
     * @return A new {@link GeminiClient} instance with default settings.
     */
    public GeminiClient build() {
        return new GeminiClientImp(apiKey, model, httpClient, baseUrl, jsonService);
    }

    /**
     * Creates a new {@link GeminiClient} instance with custom settings.
     *
     * @param apiKey       The API key for authentication.
     * @param model        The model to use for requests.
     * @param httpClient   The HTTP client to use for communication.
     * @param baseUrl      The base URL for the Gemini API.
     * @param jsonService The JSON service to use for serialization and
     *                    deserialization.
     * @return A new {@link GeminiClient} instance with custom settings.
     * @since 0.0.2
     */
    public GeminiClient buildCustom(String apiKey, String model,
                                    HttpClient httpClient, String baseUrl,
                                    JsonService jsonService) {
        return new GeminiClientImp(apiKey, model, httpClient, baseUrl, jsonService);
    }
}