package com.enovka.gemini4j.http.factory;

import com.enovka.gemini4j.http.impl.CustomHttpClient;
import com.enovka.gemini4j.http.impl.DefaultHttpClient;
import com.enovka.gemini4j.http.spec.HttpClient;
import lombok.Builder;
import lombok.Data;

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

    private Integer connectionTimeout;
    private Integer responseTimeout;
    private HttpClient customClient;

    /**
     * Creates a new {@link HttpClient} instance based on the builder
     * configuration.
     *
     * @return A new {@link HttpClient} instance.
     */
    public HttpClient build() {
        HttpClient httpClient;

        switch (httpClientType) {
        case DEFAULT:
            httpClient = new DefaultHttpClient();
            break;
        case CUSTOM:
            if (customClient == null) {
                throw new IllegalArgumentException(
                        "Custom HttpClient instance is required when using HttpClientType.CUSTOM.");
            }
            httpClient = new CustomHttpClient(customClient);
            break;
        default:
            throw new IllegalArgumentException(
                    "Unknown HttpClientType: " + httpClientType);
        }

        // Set timeouts only if explicitly provided in the builder
        if (connectionTimeout != null) {
            httpClient.setConnectionTimeout(connectionTimeout);
        }
        if (responseTimeout != null) {
            httpClient.setResponseTimeout(responseTimeout);
        }

        return httpClient;
    }
}