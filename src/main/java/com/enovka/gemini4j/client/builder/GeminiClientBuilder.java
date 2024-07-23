package com.enovka.gemini4j.client.builder;

import com.enovka.gemini4j.client.imp.GeminiClientImp;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientBuilder;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientType;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;

import java.time.Duration;

/**
 * Builder for creating {@link GeminiClient} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class GeminiClientBuilder {

    private String apiKey;
    private String model = "models/gemini-pro-vision-001";
    private String baseUrl = "https://generativelanguage.googleapis.com/v1beta";
    private JsonService jsonService = JsonServiceBuilder.builder().build()
            .build();
    private Integer connectionTimeout = 5000; // Default value
    private Integer responseTimeout = 60000; // Default value
    private Integer requestsPerWindow = Integer.MAX_VALUE; // Default value
    private Duration windowDuration = Duration.ofMinutes(1); // Default value

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
     * Sets the connection timeout in milliseconds.
     *
     * @param connectionTimeout The connection timeout in milliseconds.
     * @return The builder instance for method chaining.
     */
    public GeminiClientBuilder withConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    /**
     * Sets the response timeout in milliseconds.
     *
     * @param responseTimeout The response timeout in milliseconds.
     * @return The builder instance for method chaining.
     */
    public GeminiClientBuilder withResponseTimeout(int responseTimeout) {
        this.responseTimeout = responseTimeout;
        return this;
    }

    /**
     * Sets the rate limiter parameters for the HTTP client.
     *
     * @param requestsPerWindow The maximum number of requests allowed per time
     * window.
     * @param windowDuration The duration of the sliding time window for rate
     * limiting.
     * @return The builder instance for method chaining.
     */
    public GeminiClientBuilder withRateLimiter(int requestsPerWindow,
                                               Duration windowDuration) {
        this.requestsPerWindow = requestsPerWindow;
        this.windowDuration = windowDuration;
        return this;
    }

    /**
     * Builds a new {@link GeminiClient} instance based on the configured
     * parameters.
     *
     * @return A new {@link GeminiClient} instance.
     */
    public GeminiClient build() {
        // Create HttpClient with all configurations
        HttpClient httpClient = HttpClientBuilder.builder()
                .withHttpClientType(HttpClientType.DEFAULT)
                .withConnectionTimeout(connectionTimeout)
                .withResponseTimeout(responseTimeout)
                .withRequestsPerWindow(requestsPerWindow)
                .withWindowDuration(windowDuration)
                .build().build();

        return new GeminiClientImp(apiKey, model, httpClient, baseUrl,
                jsonService);
    }
}