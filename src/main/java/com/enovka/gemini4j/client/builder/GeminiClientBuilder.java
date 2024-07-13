package com.enovka.gemini4j.client.builder;

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
 * @author Everson Novka &lt;enovka@gmail.com&gt;
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
    @Builder.Default
    private GeminiClient client = GeminiClientBuilder.builder().build()
            .getClient();

}