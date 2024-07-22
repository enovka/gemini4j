package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.domain.ListModel;
import com.enovka.gemini4j.domain.Model;
import com.enovka.gemini4j.resource.exception.ResourceException;

/**
 * Interface defining the contract for interacting with the Model resource of
 * the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface ModelResource {

    /**
     * Retrieves a list of available Gemini models.
     *
     * @return A {@link ListModel} containing the available models.
     * @throws ResourceException If an error occurs while fetching the models.
     * @since 0.1.0
     */
    ListModel listModels() throws ResourceException;

    /**
     * Retrieves a specific Gemini model by its name.
     *
     * @param modelName The name of the model to retrieve.
     * @return A {@link Model} object representing the specified model.
     * @throws ResourceException If an error occurs while fetching the model or
     * if the model is not found.
     * @since 0.0.2
     */
    Model getModel(String modelName) throws ResourceException;
}