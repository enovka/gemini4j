package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.ListModel;
import com.enovka.gemini4j.model.Model;
import com.enovka.gemini4j.model.request.spec.Request;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.ModelResource;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;
import com.enovka.gemini4j.resource.spec.base.AsyncResponse;
import com.enovka.gemini4j.resource.spec.base.BaseAbstractResource;
import org.apache.hc.core5.http.ContentType;

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
public class ModelResourceImpl extends BaseAbstractResource<Model, Request>
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
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    protected String getEndpointForRequest(Request request) {
        return "";
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public ListModel listModels() throws ResourceException {
        return executeRequest("GET", LIST_MODELS_ENDPOINT, null, ContentType.APPLICATION_JSON, ListModel.class);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public AsyncResponse<ListModel> listModelsAsync() {
        return executeRequestAsync("GET", LIST_MODELS_ENDPOINT, null, ContentType.APPLICATION_JSON, ListModel.class);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public Model getModel(String modelName) throws ResourceException {
        String endpoint = String.format(GET_MODEL_ENDPOINT, modelName);
        return executeRequest("GET", endpoint, null, ContentType.APPLICATION_JSON, Model.class);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public AsyncResponse<Model> getModelAsync(String modelName) {
        String endpoint = String.format(GET_MODEL_ENDPOINT, modelName);
        return executeRequestAsync("GET", endpoint, null, ContentType.APPLICATION_JSON, Model.class);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public List<SupportedModelMethod> getSupportedMethods() {
        return SUPPORTED_METHODS;
    }
}