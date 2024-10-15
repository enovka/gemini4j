package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.response.GenerateResponse;
import com.enovka.gemini4j.resource.builder.request.GenerateRequestBuilder;
import com.enovka.gemini4j.resource.builder.request.GenerateTextRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.MultiTurnConversationAware;

import java.util.concurrent.CompletableFuture;

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
    GenerateResponse execute(GenerateRequest request) throws ResourceException;

    /**
     * Executes a content generation request asynchronously.
     *
     * @param request  The {@link GenerateRequest} containing the generation parameters.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    CompletableFuture<GenerateResponse> executeAsync(GenerateRequest request, AsyncCallback<GenerateResponse> callback) throws ResourceException;

    /**
     * Creates a new {@link GenerateTextRequestBuilder} instance to build a
     * {@link GenerateRequest} for generating text using the Gemini Pro, Gemini Flash, and other
     * compatible models.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * String userInput = "Write a short story about a magic backpack.";
     * GenerateTextRequestBuilder builder = generationResource.generateTextBuilder(userInput);
     * builder.withTemperature(0.7);
     * builder.withMaxOutputTokens(200);
     * GenerateRequest request = builder.build();
     * GenerateResponse result = generationResource.execute(request);
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
     * Creates a new {@link GenerateRequestBuilder} instance to build a
     * {@link GenerateRequest} for generating content using the Gemini Pro, Gemini Flash, and
     * other compatible models.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * String userInput = "Tell me a joke.";
     * GenerateRequestBuilder builder = generationResource.generateContentBuilder(userInput);
     * builder.withSystemInstruction("You are a funny AI assistant.");
     * builder.withSafetySetting(safety -> safety
     *     .withHarassment(HarmBlockThresholdEnum.BLOCK_NONE)
     *     .withHateSpeech(HarmBlockThresholdEnum.BLOCK_NONE));
     * GenerateRequest request = builder.build();
     * GenerateResponse result = generationResource.execute(request);
     * String generatedText = result.getGeneratedText();
     * System.out.println(generatedText);
     * }</pre>
     *
     * @param userInput The required user input for the generation request.
     * @return A new {@link GenerateRequestBuilder} instance.
     * @since 0.0.2
     */
    GenerateRequestBuilder generateContentBuilder(String userInput);
}