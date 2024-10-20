package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.response.GenerateResponse;
import com.enovka.gemini4j.model.response.internal.GenerateContentResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.AsyncResponse;
import com.enovka.gemini4j.resource.spec.base.MultiTurnConversationAware;

/**
 * Interface defining the contract for interacting with the Generation resource of the Gemini API.
 * This resource provides methods for generating text, chat messages, and other content using the
 * Gemini Pro, Gemini Flash, and other compatible models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface GenerateResource extends MultiTurnConversationAware {

    /**
     * Executes a content generation request.
     *
     * @param request The {@link GenerateRequest} containing the generation parameters.
     * @return A {@link GenerateResponse} containing the generated content and shortcuts.
     * @throws ResourceException If an error occurs during content generation.
     * @since 0.1.3
     */
    GenerateContentResponse execute(GenerateRequest request) throws ResourceException;

    /**
     * Executes a content generation request asynchronously.
     *
     * @param request  The {@link GenerateRequest} containing the generation parameters.
     * @return A {@link AsyncResponse} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @since 0.2.0
     */
    AsyncResponse<GenerateContentResponse> executeAsync(GenerateRequest request);
}