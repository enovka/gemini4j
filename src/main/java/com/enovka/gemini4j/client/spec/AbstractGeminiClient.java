package com.enovka.gemini4j.client.spec;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.exception.GeminiClientException;
import com.enovka.gemini4j.infrastructure.exception.GeminiInfrastructureException;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientBuilder;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientType;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceType;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Abstract base class for Gemini client implementations, providing shared
 * functionality.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
@Getter
public abstract class AbstractGeminiClient extends BaseClass
        implements GeminiClient {

    protected final String apiKey;
    /**
     * The HTTP client to use for communication with the Gemini API.
     */
    @Setter
    protected HttpClient httpClient;
    @Setter
    protected String model;
    @Setter
    protected String baseUrl;
    @Setter
    protected JsonService jsonService;

    /**
     * Constructs a new AbstractGeminiClient with the required API key, model,
     * HTTP client, base URL, and JSON service.
     *
     * @param apiKey The API key for authentication with the Gemini API.
     * @param model The default model to use for requests.
     * @param httpClient The HTTP client to use for communication.
     * @param baseUrl The base URL for the Gemini API.
     * @param jsonService The JSON service to use for serialization and
     * deserialization.
     */
    protected AbstractGeminiClient(String apiKey, String model,
                                   HttpClient httpClient, String baseUrl,
                                   JsonService jsonService) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is required.");
        }
        this.apiKey = apiKey;
        this.model = model;
        this.httpClient = httpClient != null ? httpClient
                : HttpClientBuilder.builder()
                        .withHttpClientType(HttpClientType.DEFAULT)
                        .build().getCustomClient();
        this.baseUrl = baseUrl != null ? baseUrl
                : "https://generativelanguage.googleapis.com/v1beta";
        this.jsonService = jsonService != null ? jsonService
                : JsonServiceBuilder.builder().withJsonServiceType(
                        JsonServiceType.JACKSON).build().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> buildAuthHeaders() {
        return Collections.emptyMap();
    }

    /**
     * Executes a request to the Gemini API, handling potential
     * {@link GeminiInfrastructureException} and wrapping it in a
     * {@link GeminiClientException}.
     *
     * @param requestSupplier A supplier that provides the actual request
     * execution logic.
     * @param <R> The type of response object returned by the request.
     * @return The response object returned by the request.
     * @throws GeminiApiException If the Gemini API returns an error.
     * @throws GeminiClientException If an error occurs within the client, such
     * as a network error or JSON processing error.
     */
    @Override
    public <R> R executeRequest(Supplier<R> requestSupplier)
            throws GeminiApiException, GeminiClientException {
        return requestSupplier.get();
    }
}