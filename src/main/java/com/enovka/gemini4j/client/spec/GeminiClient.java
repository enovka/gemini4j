package com.enovka.gemini4j.client.spec;

import com.enovka.gemini4j.http.spec.HttpClient;
import com.enovka.gemini4j.json.spec.JsonService;

import java.util.Map;

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
     * Returns the HTTP client used for communication with the Gemini API.
     *
     * @return The HTTP client instance.
     */
    HttpClient getHttpClient();

    /**
     * Returns the model name to be used for requests.
     *
     * @return The model name.
     */
    String getModel();

    /**
     * Sets the model name to be used for requests.
     *
     * @param model The model name.
     */
    void setModel(String model);

    /**
     * Builds the headers for API authentication.
     *
     * @return A map containing the authentication headers.
     */
    Map<String, String> buildAuthHeaders();

    /**
     * Returns the JSON service used for serialization and deserialization.
     *
     * @return The JSON service instance.
     */
    JsonService getJsonService();
}