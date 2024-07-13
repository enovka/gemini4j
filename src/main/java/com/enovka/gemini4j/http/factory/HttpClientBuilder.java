package com.enovka.gemini4j.http.factory;

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

    @Builder.Default
    private Integer connectionTimeout = 5000;
    @Builder.Default
    private Integer responseTimeout = 60000;
    @Builder.Default
    private HttpClient customClient = new DefaultHttpClient();
}