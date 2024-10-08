package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.ListModel;
import com.enovka.gemini4j.model.Model;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.ModelResource;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;

import java.util.List;

/**
 * Implementation of the {@link ModelResource} interface for interacting with
 * the Model resource of the Gemini API. This class provides methods for listing
 * and retrieving Gemini models. It leverages the functionality provided by the
 * {@link AbstractResource} base class for common HTTP operations and error handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class ModelResourceImpl extends AbstractResource<ModelResource>
        implements ModelResource {

    private static final String LIST_MODELS_ENDPOINT = "models";
    private static final String GET_MODEL_ENDPOINT = "%s";
    private static final List<SupportedModelMethod> SUPPORTED_METHODS = List.of();

    /**
     * Constructs a new ModelResourceImpl with the required GeminiClient.
     *
     * @param geminiClient The Gemini client for API communication.
     * @since 0.2.0
     */
    public ModelResourceImpl(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * Retrieves a list of available Gemini models.
     *
     * @return A {@link ListModel} containing the available models.
     * @throws ResourceException If an error occurs while fetching the models.
     * @since 0.2.0
     */
    @Override
    public ListModel listModels() throws ResourceException {
        return executeRequest("GET", LIST_MODELS_ENDPOINT, null, ListModel.class);
    }

    /**
     * Retrieves a specific Gemini model by its name.
     *
     * @param modelName The name of the model to retrieve.
     * @return A {@link Model} object representing the specified model.
     * @throws ResourceException If an error occurs while fetching the model or
     *                          if the model is not found.
     * @since 0.2.0
     */
    @Override
    public Model getModel(String modelName) throws ResourceException {
        String endpoint = String.format(GET_MODEL_ENDPOINT, modelName);
        return executeRequest("GET", endpoint, null, Model.class);
    }

    @Override
    public List<SupportedModelMethod> getModelMethodList() {
        return SUPPORTED_METHODS;
    }
}