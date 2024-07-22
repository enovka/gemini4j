package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.resource.builder.GenerateContentRequestBuilder;
import com.enovka.gemini4j.resource.builder.GenerateTextRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;

/**
 * Interface defining the contract for interacting with the Generation resource
 * of the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface GenerationResource extends MultiTurnConversationAware {

    /**
     * Returns the associated {@link GeminiClient}.
     *
     * @return The {@link GeminiClient} instance.
     * @since 0.0.2
     */
    GeminiClient getGeminiClient();

    /**
     * Generates content using the provided request.
     *
     * @param request The {@link GenerateContentRequest} containing the
     * generation parameters.
     * @return A {@link GeminiResult} containing the generated content and
     * shortcuts.
     * @throws ResourceException If an error occurs during content generation.
     * @since 0.1.0
     */
    GeminiResult generateContent(GenerateContentRequest request)
            throws ResourceException;

    /**
     * Creates a new {@link GenerateTextRequestBuilder} instance to build a
     * {@link GenerateContentRequest} for generating text.
     *
     * @return A new {@link GenerateTextRequestBuilder} instance.
     * @since 0.0.2
     */
    GenerateTextRequestBuilder generateTextBuilder(String userInput);

    /**
     * Creates a new {@link GenerateContentRequestBuilder} instance to build a
     * {@link GenerateContentRequest} for generating content.
     *
     * @param userInput The required user input for the generation request.
     * @return A new {@link GenerateContentRequestBuilder} instance.
     * @since 0.0.2
     */
    GenerateContentRequestBuilder generateContentBuilder(String userInput);
}