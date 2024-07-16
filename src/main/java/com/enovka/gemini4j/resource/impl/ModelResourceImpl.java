package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.ListModel;
import com.enovka.gemini4j.domain.Model;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.spec.HttpResponse;
import com.enovka.gemini4j.json.exception.JsonException;
import com.enovka.gemini4j.json.spec.JsonService;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.ModelResource;

/**
 * Implementation of the {@link ModelResource} interface for interacting with
 * the Model resource of the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class ModelResourceImpl extends AbstractResource
        implements ModelResource {

    private static final String LIST_MODELS_ENDPOINT = "/models";
    private static final String GET_MODEL_ENDPOINT = "/models/%s"; // Corrected format string
    private final JsonService jsonService;

    /**
     * Constructs a new ModelResourceImpl with the required GeminiClient and
     * JsonService.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     * @param jsonService The {@link JsonService} instance to use for JSON
     * serialization/deserialization.
     */
    public ModelResourceImpl(GeminiClient geminiClient,
                             JsonService jsonService) {
        super(geminiClient);
        this.jsonService = jsonService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListModel listModels() throws HttpException, JsonException {
        logDebug("Listing Gemini models from endpoint: " + LIST_MODELS_ENDPOINT);
        HttpResponse response = get(LIST_MODELS_ENDPOINT,
                geminiClient.buildAuthHeaders());
        return jsonService.deserialize(response.getBody(), ListModel.class);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public Model getModel(String modelName)
            throws GeminiApiException, HttpException, JsonException {
        logDebug("Getting model details for: " + modelName);
        String endpoint = String.format(GET_MODEL_ENDPOINT, modelName);
        logDebug("Requesting model from endpoint: " + endpoint);
        HttpResponse response = get(endpoint, geminiClient.buildAuthHeaders());
        return jsonService.deserialize(response.getBody(), Model.class);
    }
}