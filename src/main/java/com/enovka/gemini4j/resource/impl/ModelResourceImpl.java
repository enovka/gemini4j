package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.ListModel;
import com.enovka.gemini4j.domain.Model;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.ModelResource;

/**
 * Implementation of the {@link ModelResource} interface for interacting with
 * the Model resource of the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class ModelResourceImpl extends AbstractResource<ModelResource>
        implements ModelResource {

    private static final String LIST_MODELS_ENDPOINT = "/models";
    private static final String GET_MODEL_ENDPOINT = "/%s";

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
        super(geminiClient, jsonService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListModel listModels() throws ResourceException {
        return executeGetRequest(LIST_MODELS_ENDPOINT, ListModel.class);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.2
     */
    @Override
    public Model getModel(String modelName) throws ResourceException {
        String endpoint = String.format(GET_MODEL_ENDPOINT, modelName);
        return executeGetRequest(endpoint, Model.class);
    }
}