// com.enovka.gemini4j.resource.spec.ModelResource
package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.model.ListModel;
import com.enovka.gemini4j.model.Model;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.Resource;

import java.util.concurrent.CompletableFuture;

/**
 * Interface defining the contract for interacting with the Model resource of the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface ModelResource extends Resource {

    /**
     * Retrieves a list of available Gemini models.
     *
     * @return A {@link ListModel} containing the available models.
     * @throws ResourceException If an error occurs while fetching the models.
     * @since 0.1.0
     */
    ListModel listModels() throws ResourceException;

    /**
     * Retrieves a list of available Gemini models asynchronously.
     *
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    CompletableFuture<ListModel> listModelsAsync(AsyncCallback<ListModel> callback) throws ResourceException;

    /**
     * Retrieves a specific Gemini model by its name.
     *
     * @param modelName The name of the model to retrieve.
     * @return A {@link Model} object representing the specified model.
     * @throws ResourceException If an error occurs while fetching the model or if the model is
     *                           not found.
     * @since 0.0.2
     */
    Model getModel(String modelName) throws ResourceException;

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
    CompletableFuture<Model> getModelAsync(String modelName, AsyncCallback<Model> callback) throws ResourceException;
}