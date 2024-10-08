package com.enovka.gemini4j.client.spec;

import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.model.ListModel;
import com.enovka.gemini4j.model.Model;
import com.enovka.gemini4j.resource.exception.ResourceException;

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
     * Sets the gemini api key to be used for requests.
     *
     * @param apiKey The new gemini api Key.
     * @since 0.1.7
     */
    void setApiKey(String apiKey);

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
    String getModelName();

    /**
     * Sets the model name to be used for requests.
     *
     * @param model The model name.
     * @since 0.0.2
     */
    void setModelName(String model);

    Model getModel(String name) throws ResourceException;

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

    ListModel getGeminiModels() throws ResourceException;

}