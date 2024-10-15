// com.enovka.gemini4j.resource.impl.ModelResourceImpl
package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.model.ListModel;
import com.enovka.gemini4j.model.Model;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.ModelResource;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;
import org.apache.hc.core5.http.ContentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public ListModel listModels() throws ResourceException {
        return executeRequest("GET", LIST_MODELS_ENDPOINT, null, ListModel.class);
    }

    /**
     * Retrieves a list of available Gemini models asynchronously.
     *
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<ListModel> listModelsAsync(AsyncCallback<ListModel> callback) throws ResourceException {
        CompletableFuture<ListModel> future = new CompletableFuture<>();

        httpClient.getAsync(buildEndpointUrl(LIST_MODELS_ENDPOINT), buildHeaders(), new AsyncCallback<>() {
            @Override
            public void onSuccess(HttpResponse httpResponse) {
                try {
                    future.complete(deserializeResponse(httpResponse, ListModel.class));
                } catch (ResourceException e) {
                    future.completeExceptionally(e);
                }
            }

            @Override
            public void onError(Throwable exception) {
                future.completeExceptionally(new ResourceException("Error listing models", exception));
            }

            @Override
            public void onCanceled() {
                future.cancel(true);
            }
        });

        return future;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public Model getModel(String modelName) throws ResourceException {
        String endpoint = String.format(GET_MODEL_ENDPOINT, modelName);
        return executeRequest("GET", endpoint, null, Model.class);
    }

    /**
     * Retrieves a specific Gemini model by its name asynchronously.
     *
     * @param modelName The name of the model to retrieve.
     * @param callback  The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<Model> getModelAsync(String modelName, AsyncCallback<Model> callback) throws ResourceException {
        String endpoint = String.format(GET_MODEL_ENDPOINT, modelName);
        CompletableFuture<Model> future = new CompletableFuture<>();

        httpClient.getAsync(buildEndpointUrl(endpoint), buildHeaders(), new AsyncCallback<>() {
            @Override
            public void onSuccess(HttpResponse httpResponse) {
                try {
                    future.complete(deserializeResponse(httpResponse, Model.class));
                } catch (ResourceException e) {
                    future.completeExceptionally(e);
                }
            }

            @Override
            public void onError(Throwable exception) {
                future.completeExceptionally(new ResourceException("Error getting model", exception));
            }

            @Override
            public void onCanceled() {
                future.cancel(true);
            }
        });

        return future;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public List<SupportedModelMethod> getModelMethodList() {
        return SUPPORTED_METHODS;
    }

    private Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>(geminiClient.buildAuthHeaders());
        headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        return headers;
    }
}