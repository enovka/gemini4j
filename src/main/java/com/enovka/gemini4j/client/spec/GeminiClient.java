package com.enovka.gemini4j.client.spec;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.exception.GeminiClientException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Interface defining the contract for interacting with the Google Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface GeminiClient {

    /**
     * Returns the API key used for authentication.
     *
     * @return The API key.
     */
    String getApiKey();

    /**
     * Builds the headers for API authentication.
     *
     * @return A map containing the authentication headers.
     */
    Map<String, String> buildAuthHeaders();

    /**
     * Returns the HTTP client used for communication with the Gemini API.
     *
     * @return The HTTP client instance.
     * @since 0.0.2
     */
    HttpClient getHttpClient();

    /**
     * Returns the model name to be used for requests.
     *
     * @return The model name.
     * @since 0.0.2
     */
    String getModel();

    /**
     * Sets the model name to be used for requests.
     *
     * @param model The model name.
     * @since 0.0.2
     */
    void setModel(String model);

    /**
     * Returns the base URL for the Gemini API.
     *
     * @return The base URL.
     * @since 0.0.2
     */
    String getBaseUrl();

    /**
     * Sets the base URL for the Gemini API.
     *
     * @param baseUrl The base URL.
     * @since 0.0.2
     */
    void setBaseUrl(String baseUrl);

    /**
     * Returns the JSON service used for serialization and deserialization.
     *
     * @return The JSON service instance.
     * @since 0.0.2
     */
    JsonService getJsonService();

    /**
     * Sets the JSON service used for serialization and deserialization.
     *
     * @param jsonService The JSON service instance.
     * @since 0.0.2
     */
    void setJsonService(JsonService jsonService);

    /**
     * Executes a request to the Gemini API, handling potential exceptions and
     * providing centralized error logging.
     *
     * @param requestSupplier A supplier that provides the actual request
     * execution logic.
     * @param <R> The type of response object returned by the request.
     * @return The response object returned by the request.
     * @throws GeminiApiException If the Gemini API returns an error.
     * @throws GeminiClientException If an error occurs within the client, such
     * as a network error or JSON processing error.
     * @since 0.1.0
     */
    <R> R executeRequest(Supplier<R> requestSupplier) throws GeminiApiException,
            GeminiClientException;
}