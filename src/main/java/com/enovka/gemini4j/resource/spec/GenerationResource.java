package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.json.exception.JsonException;

/**
 * Interface defining the contract for interacting with the Generation resource
 * of the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface GenerationResource {

    /**
     * Generates content using the provided request.
     *
     * @param request The {@link GenerateContentRequest} containing the
     * generation parameters.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateContent(GenerateContentRequest request)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content using the specified user input.
     *
     * @param userInput The user's input text.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateContent(String userInput)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates content using the specified user input and system
     * instructions.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions)
            throws GeminiApiException, JsonException, HttpException;
}