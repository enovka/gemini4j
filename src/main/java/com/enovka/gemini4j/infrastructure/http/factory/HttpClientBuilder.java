package com.enovka.gemini4j.infrastructure.http.factory;

import com.enovka.gemini4j.infrastructure.http.impl.DefaultHttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;

/**
 * Builder for creating {@link HttpClient} instances.
 * This builder allows customization of various
 * parameters like {@link HttpClientType}, connection timeouts, response timeouts, and the number
 * of maximum connections for the underlying connection pool.
 * It provides a fluent API for
 * configuring these parameters and building different types of HTTP clients.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
@Data
@Builder(setterPrefix = "with")
public class HttpClientBuilder {

    /**
     * The type of HTTP client to build. Defaults to {@link HttpClientType#DEFAULT}.
     *
     * @since 0.0.1
     */
    @Builder.Default
    private HttpClientType httpClientType = HttpClientType.DEFAULT;

    /**
     * The connection timeout in milliseconds. Defaults to 60.000 ms.
     *
     * @since 0.0.1
     */
    @Builder.Default
    private Integer connectionTimeout = 60000;

    /**
     * The response timeout in milliseconds. Defaults to 10 minutes.
     *
     * @since 0.0.1
     */
    @Builder.Default
    private Integer responseTimeout = 600000;


    /**
     * A custom HttpClient instance to use when {@code httpClientType} is set to
     * {@link HttpClientType#CUSTOM}.
     *
     * @since 0.0.1
     */
    private HttpClient customClient;


    /**
     * The maximum number of requests (concurrent connections) allowed per time window.
     * This is used for rate limiting and also configures the connection pool size.
     * Default to 1500.
     * @since 0.0.1
     */
    @Builder.Default
    private Integer requestsPerWindow = 1500;


    /**
     * The duration of the sliding time window for rate limiting. Defaults to 1 minute.
     *
     * @since 0.0.1
     */
    @Builder.Default
    private Duration windowDuration = Duration.ofMinutes(1);

    /**
     * Creates a new {@link HttpClient} instance based on the builder configuration.
     *
     * @return A new {@link HttpClient} instance.
     * @throws IllegalArgumentException If an unknown {@link HttpClientType} is provided.
     * @since 0.0.2
     */
    public HttpClient build() {
        switch (httpClientType) {
            case DEFAULT:
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient(connectionTimeout, responseTimeout, requestsPerWindow);
                defaultHttpClient.setRateLimiter(requestsPerWindow, windowDuration);
                return defaultHttpClient;
            case CUSTOM:
                if (customClient == null) {
                    throw new IllegalArgumentException("Custom HttpClient instance is required when using HttpClientType.CUSTOM.");
                }
                return customClient;
            default:
                throw new IllegalArgumentException("Unknown HttpClientType: " + httpClientType);
        }
    }


    /**
     * Returns the custom client instance. This method is primarily used for testing purposes.
     *
     * @return The custom {@link HttpClient} instance, or a new {@link DefaultHttpClient} if no
     * custom client is set and the client type is {@link HttpClientType#DEFAULT}.
     * @since 0.0.2
     */
    public HttpClient getCustomClient() {
        if (httpClientType == HttpClientType.CUSTOM) {
            return customClient;
        } else {
            return new DefaultHttpClient();
        }
    }
}