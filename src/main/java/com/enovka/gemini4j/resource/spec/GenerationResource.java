package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.resource.builder.GenerateContentRequestBuilder;
import com.enovka.gemini4j.resource.builder.GenerateTextRequestBuilder;

/**
 * Interface defining the contract for interacting with the Generation resource
 * of the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface GenerationResource {

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
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GeminiResult generateContent(GenerateContentRequest request)
            throws GeminiApiException, JsonException;

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