package com.enovka.gemini4j.infrastructure.http.factory;

import com.enovka.gemini4j.infrastructure.http.impl.DefaultHttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * Builder for creating {@link HttpClient} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
@Data
@Builder(setterPrefix = "with")
public class HttpClientBuilder {

    @Builder.Default
    private HttpClientType httpClientType = HttpClientType.DEFAULT;

    @Builder.Default
    private Integer connectionTimeout = 5000;
    @Builder.Default
    private Integer responseTimeout = 60000;
    /**
     * -- GETTER -- Returns a custom instance.
     *
     * @return A custom {@link HttpClient} instance.
     */
    @Getter
    @Builder.Default
    private HttpClient customClient = new DefaultHttpClient();

    /**
     * Creates a new {@link HttpClient} instance based on the builder
     * configuration.
     *
     * @return A new {@link HttpClient} instance.
     * @since 0.0.2
     */
    public HttpClient build() {
        switch (httpClientType) {
        case DEFAULT:
            return new DefaultHttpClient(connectionTimeout, responseTimeout);
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