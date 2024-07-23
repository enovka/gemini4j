package com.enovka.gemini4j.infrastructure.http.factory;

import com.enovka.gemini4j.infrastructure.http.impl.DefaultHttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.Duration;

/**
 * {@inheritDoc}
 */
@Data
@Builder(setterPrefix = "with")
public class HttpClientBuilder {

    /**
     * The type of HTTP client to build.
     */
    @Builder.Default
    private HttpClientType httpClientType = HttpClientType.DEFAULT;

    /**
     * The connection timeout in milliseconds.
     */
    @Builder.Default
    private Integer connectionTimeout = 5000;

    /**
     * The response timeout in milliseconds.
     */
    @Builder.Default
    private Integer responseTimeout = 60000;

    /**
     * A custom HttpClient instance to use when `httpClientType` is set to
     * `CUSTOM`.
     */
    @Getter
    @Builder.Default
    private HttpClient customClient = new DefaultHttpClient();

    /**
     * The maximum number of requests allowed per time window.
     */
    @Builder.Default
    private Integer requestsPerWindow = 14;

    /**
     * The duration of the sliding time window for rate limiting.
     */
    @Builder.Default
    private Duration windowDuration = Duration.ofMinutes(1);

    /**
     * Creates a new {@link HttpClient} instance based on the builder
     * configuration.
     *
     * @return A new {@link HttpClient} instance.
     */
    public HttpClient build() {
        switch (httpClientType) {
            case DEFAULT:
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient(connectionTimeout, responseTimeout);
                // Configure rate limiter in the HTTP client
                defaultHttpClient.setRateLimiter(requestsPerWindow, windowDuration);
                return defaultHttpClient;
            case CUSTOM:
                if (customClient == null) {
                    throw new IllegalArgumentException(
                            "Custom HttpClient instance is required when using HttpClientType.CUSTOM.");
                }
                return customClient;
            default:
                throw new IllegalArgumentException(
                        "Unknown HttpClientType: " + httpClientType);
        }
    }
}