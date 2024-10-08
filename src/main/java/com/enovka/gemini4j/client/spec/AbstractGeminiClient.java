package com.enovka.gemini4j.client.spec;

import com.enovka.gemini4j.infrastructure.Constants;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientBuilder;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientType;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceType;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import com.enovka.gemini4j.model.ListModel;
import com.enovka.gemini4j.model.Model;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.impl.ModelResourceImpl;
import lombok.Getter;
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
@Getter
public abstract class AbstractGeminiClient extends BaseClass
        implements GeminiClient {

    protected String apiKey;
    /**
     * The HTTP client to use for communication with the Gemini API.
     */
    @Setter
    protected HttpClient httpClient;
    @Setter
    protected String modelName;
    @Setter
    protected String baseUrl;
    @Setter
    protected JsonService jsonService;

    @Getter
    protected ListModel listModel;

    /**
     * Constructs a new AbstractGeminiClient with the required API key, model,
     * HTTP client, base URL, and JSON service.
     *
     * @param apiKey      The API key for authentication with the Gemini API.
     * @param modelName   The default model to use for requests.
     * @param httpClient  The HTTP client to use for communication.
     * @param baseUrl     The base URL for the Gemini API.
     * @param jsonService The JSON service to use for serialization and
     *                    deserialization.
     */
    protected AbstractGeminiClient(String apiKey, String modelName,
                                   HttpClient httpClient, String baseUrl,
                                   JsonService jsonService) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is required.");
        }
        this.apiKey = apiKey;
        this.modelName = modelName;
        this.httpClient = httpClient != null ? httpClient
                : HttpClientBuilder.builder()
                .withHttpClientType(HttpClientType.DEFAULT)
                .build().getCustomClient();
        this.baseUrl = baseUrl != null ? baseUrl
                : Constants.BASE_URL;
        this.jsonService = jsonService != null ? jsonService
                : JsonServiceBuilder.builder().withJsonServiceType(
                JsonServiceType.JACKSON).build().build();
    }

    @Override
    public ListModel getGeminiModels() throws ResourceException {
        if (this.listModel == null)
            this.listModel = new ModelResourceImpl(this).listModels();
        return this.listModel;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> buildAuthHeaders() {
        return Collections.emptyMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Model getModel(String name) throws ResourceException {
        getGeminiModels();
        return findModelByName(name);
    }

    /**
     * Finds a {@link Model} in the list by its name.
     *
     * @param modelName The name of the model to search for (e.g., "models/gemini-pro").
     * @return The {@link Model} with the matching name, or null if not found.
     */
    private Model findModelByName(String modelName) throws ResourceException {
        if (modelName == null || getGeminiModels().getModels() == null) {
            throw new ResourceException("Model not found");
        }
        for (Model model : getGeminiModels().getModels()) {
            if (modelName.equals(model.getName())) {
                return model;
            }
        }
        throw new ResourceException("Model not found");
    }
}