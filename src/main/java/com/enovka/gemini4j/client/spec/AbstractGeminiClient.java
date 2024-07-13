package com.enovka.gemini4j.client.spec;

import com.enovka.gemini4j.common.BaseClass;
import com.enovka.gemini4j.http.factory.HttpClientBuilder;
import com.enovka.gemini4j.http.factory.HttpClientType;
import com.enovka.gemini4j.http.spec.HttpClient;
import com.enovka.gemini4j.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.json.spec.JsonService;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;

/**
 * Abstract base class for Gemini client implementations, providing shared
 * functionality.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public abstract class AbstractGeminiClient extends BaseClass
        implements GeminiClient {

    protected final String apiKey;
    /**
     * Sets the HTTP client to use for communication with the Gemini API.
     */
    @Setter
    protected HttpClient httpClient;
    protected String model;
    protected JsonService jsonService;

    /**
     * Constructs a new AbstractGeminiClient with the required API key.
     *
     * @param apiKey The API key for authentication with the Gemini API.
     */
    protected AbstractGeminiClient(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is required.");
        }
        this.apiKey = apiKey;
        this.httpClient = HttpClientBuilder.builder()
                .withHttpClientType(HttpClientType.DEFAULT)
                .build().build();
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public JsonService getJsonService() {
        if (jsonService == null) {

            jsonService = JsonServiceBuilder.builder().build().build();

        }
        return jsonService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getApiKey() {
        return apiKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModel() {
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> buildAuthHeaders() {
        return Collections.emptyMap();
    }
}