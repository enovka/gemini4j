package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.json.exception.JsonException;
import com.enovka.gemini4j.domain.GenerationConfig;
import com.enovka.gemini4j.domain.SafetySetting;
import com.enovka.gemini4j.domain.Tool;

import java.util.List;

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
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param generationConfig The generation configuration.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param tools The tools to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param cachedContent The cached content to use as context.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            GenerationConfig generationConfig,
                                            String systemInstructions,
                                            List<Tool> tools,
                                            List<SafetySetting> safetySettings,
                                            String cachedContent)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param generationConfig The generation configuration.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param tools The tools to use for generation.
     * @param safetySettings The safety settings to apply.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            GenerationConfig generationConfig,
                                            String systemInstructions,
                                            List<Tool> tools,
                                            List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param generationConfig The generation configuration.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param tools The tools to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            GenerationConfig generationConfig,
                                            String systemInstructions,
                                            List<Tool> tools)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param generationConfig The generation configuration.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            GenerationConfig generationConfig,
                                            String systemInstructions)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param generationConfig The generation configuration.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            GenerationConfig generationConfig)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param tools The tools to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param cachedContent The cached content to use as context.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            List<Tool> tools,
                                            List<SafetySetting> safetySettings,
                                            String cachedContent)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param tools The tools to use for generation.
     * @param safetySettings The safety settings to apply.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            List<Tool> tools,
                                            List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param tools The tools to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            List<Tool> tools)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param tools The tools to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param cachedContent The cached content to use as context.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            List<Tool> tools,
                                            List<SafetySetting> safetySettings,
                                            String cachedContent)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param tools The tools to use for generation.
     * @param safetySettings The safety settings to apply.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            List<Tool> tools,
                                            List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param safetySettings The safety settings to apply.
     * @param cachedContent The cached content to use as context.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            List<SafetySetting> safetySettings,
                                            String cachedContent)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param safetySettings The safety settings to apply.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates content with custom configuration.
     *
     * @param userInput The user's input text.
     * @param cachedContent The cached content to use as context.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateContent(String userInput,
                                            String cachedContent)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param stopSequences The stop sequences to use for generation.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @param topK The maximum number of tokens to consider when sampling.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         List<String> stopSequences,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP,
                                         int topK)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param stopSequences The stop sequences to use for generation.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         List<String> stopSequences,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param stopSequences The stop sequences to use for generation.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         List<String> stopSequences,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param stopSequences The stop sequences to use for generation.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         List<String> stopSequences,
                                         double temperature,
                                         int candidateCount)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param stopSequences The stop sequences to use for generation.
     * @param temperature The temperature to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         List<String> stopSequences,
                                         double temperature)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param stopSequences The stop sequences to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         List<String> stopSequences)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @param topK The maximum number of tokens to consider when sampling.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP,
                                         int topK)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider
     * when sampling.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param stopSequences The stop sequences to use for generation.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<String> stopSequences,
                                         double temperature,
                                         int candidateCount)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings)
            throws GeminiApiException, JsonException, HttpException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param stopSequences The stop sequences to use for generation.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     * @since 0.0.2
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<String> stopSequences,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens)
            throws GeminiApiException, JsonException, HttpException;


}