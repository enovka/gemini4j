package com.enovka.gemini4j.client.builder;

import com.enovka.gemini4j.client.imp.GeminiClientImp;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientBuilder;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;

/**
 * Builder for creating {@link GeminiClient} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class GeminiClientBuilder {

    private String apiKey;
    private String model = "models/gemini-pro-vision-001";
    private HttpClient httpClient = HttpClientBuilder.builder().build()
            .getCustomClient();
    private String baseUrl = "https://generativelanguage.googleapis.com/v1beta";
    private JsonService jsonService = JsonServiceBuilder.builder().build()
            .build();

    /**
     * Private constructor to enforce builder pattern.
     */
    private GeminiClientBuilder() {
    }

    /**
     * Creates a new instance of the GeminiClientBuilder.
     *
     * @return A new GeminiClientBuilder instance.
     */
    public static GeminiClientBuilder builder() {
        return new GeminiClientBuilder();
    }

    /**
     * Sets the API key for authentication with the Gemini API.
     *
     * @param apiKey The API key.
     * @return The builder instance for method chaining.
     */
    public GeminiClientBuilder withApiKey(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is required.");
        }
        this.apiKey = apiKey;
        return this;
    }

    /**
     * Sets the default model to use for requests.
     *
     * @param model The model name.
     * @return The builder instance for method chaining.
     */
    public GeminiClientBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    /**
     * Sets the HTTP client to use for communication with the Gemini API.
     *
     * @param httpClient The HTTP client instance.
     * @return The builder instance for method chaining.
     */
    public GeminiClientBuilder withHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /**
     * Sets the base URL for the Gemini API.
     *
     * @param baseUrl The base URL.
     * @return The builder instance for method chaining.
     */
    public GeminiClientBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * Sets the JSON service to use for serialization and deserialization.
     *
     * @param jsonService The JSON service instance.
     * @return The builder instance for method chaining.
     */
    public GeminiClientBuilder withJsonService(JsonService jsonService) {
        this.jsonService = jsonService;
        return this;
    }

    /**
     * Builds a new {@link GeminiClient} instance based on the configured
     * parameters.
     *
     * @return A new {@link GeminiClient} instance.
     */
    public GeminiClient build() {
        return new GeminiClientImp(apiKey, model, httpClient, baseUrl,
                jsonService);
    }
}