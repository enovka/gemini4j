package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.domain.ListModel;

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
     * @throws GeminiApiException If an error occurs while fetching the models.
     */
    ListModel listModels() throws GeminiApiException;
}