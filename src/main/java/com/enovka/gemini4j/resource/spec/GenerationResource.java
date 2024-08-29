package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.resource.builder.GenerateContentRequestBuilder;
import com.enovka.gemini4j.resource.builder.GenerateTextRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;

/**
 * Interface defining the contract for interacting with the Generation resource
 * of the Gemini API. This resource provides methods for generating text, chat
 * messages, and other content using the Gemini Pro, Gemini Flash, and other
 * compatible models.
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
     * Executes a content generation request.
     *
     * @param request The {@link GenerateContentRequest} containing the
     * generation parameters.
     * @return A {@link GeminiResult} containing the generated content and
     * shortcuts.
     * @throws ResourceException If an error occurs during content generation.
     * @since 0.1.3
     */
    GeminiResult execute(GenerateContentRequest request)
            throws ResourceException;

    /**
     * Creates a new {@link GenerateTextRequestBuilder} instance to build a
     * {@link GenerateContentRequest} for generating text using the Gemini Pro,
     * Gemini Flash, and other compatible models.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * String userInput = "Write a short story about a magic backpack.";
     * GenerateTextRequestBuilder builder = generationResource.generateTextBuilder(userInput);
     * builder.withTemperature(0.7);
     * builder.withMaxOutputTokens(200);
     * GenerateContentRequest request = builder.build();
     * GeminiResult result = generationResource.execute(request);
     * String generatedText = result.getGeneratedText();
     * System.out.println(generatedText);
     * }</pre>
     *
     * @param userInput The required user input for the generation request.
     * @return A new {@link GenerateTextRequestBuilder} instance.
     * @since 0.0.2
     */
    GenerateTextRequestBuilder generateTextBuilder(String userInput);

    /**
     * Creates a new {@link GenerateContentRequestBuilder} instance to build a
     * {@link GenerateContentRequest} for generating content using the Gemini
     * Pro, Gemini Flash, and other compatible models.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * String userInput = "Tell me a joke.";
     * GenerateContentRequestBuilder builder = generationResource.generateContentBuilder(userInput);
     * builder.withSystemInstruction("You are a funny AI assistant.");
     * builder.withSafetySetting(safety -> safety
     *     .withHarassment(HarmBlockThresholdEnum.BLOCK_NONE)
     *     .withHateSpeech(HarmBlockThresholdEnum.BLOCK_NONE));
     * GenerateContentRequest request = builder.build();
     * GeminiResult result = generationResource.execute(request);
     * String generatedText = result.getGeneratedText();
     * System.out.println(generatedText);
     * }</pre>
     *
     * @param userInput The required user input for the generation request.
     * @return A new {@link GenerateContentRequestBuilder} instance.
     * @since 0.0.2
     */
    GenerateContentRequestBuilder generateContentBuilder(String userInput);
}