package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.domain.GenerationConfig;
import com.enovka.gemini4j.domain.SafetySetting;
import com.enovka.gemini4j.domain.Tool;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.json.exception.JsonException;

import java.util.List;

/**
 * Interface defining the contract for interacting with the Generation resource
 * of the Gemini API.
 *
 * @author Everson Novka <enovka@gmail.com>
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
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates content using the specified user input, system instructions,
     * and temperature.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates content using the specified user input, system instructions,
     * temperature, and outputJson flag.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates content using the specified user input, system instructions,
     * temperature, outputJson flag, and topP value.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @param topP The maximum cumulative probability of tokens to consider when
     * sampling.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson,
                                            double topP)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates content using the specified user input, system instructions,
     * temperature, outputJson flag, topP value, and safety settings.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @param topP The maximum cumulative probability of tokens to consider when
     * sampling.
     * @param safetySettings The safety settings to apply.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson,
                                            double topP,
                                            List<SafetySetting> safetySettings)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates content using the specified user input, system instructions,
     * temperature, outputJson flag, topP value, safety settings, and tools.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @param topP The maximum cumulative probability of tokens to consider when
     * sampling.
     * @param safetySettings The safety settings to apply.
     * @param tools The tools to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson,
                                            double topP,
                                            List<SafetySetting> safetySettings,
                                            List<Tool> tools)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates content using the specified user input, system instructions,
     * temperature, outputJson flag, topP value, safety settings, tools, and
     * generation config.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
     * @param temperature The temperature to use for generation.
     * @param outputJson Whether the output should be in JSON format.
     * @param topP The maximum cumulative probability of tokens to consider when
     * sampling.
     * @param safetySettings The safety settings to apply.
     * @param tools The tools to use for generation.
     * @param generationConfig The generation configuration.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateContent(String userInput,
                                            String systemInstructions,
                                            double temperature,
                                            boolean outputJson,
                                            double topP,
                                            List<SafetySetting> safetySettings,
                                            List<Tool> tools,
                                            GenerationConfig generationConfig)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates text from the model given an input text prompt.
     *
     * @param textPrompt The text prompt to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateText(String textPrompt)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates text from the model given an input text prompt and safety
     * settings.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, and temperature.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, and candidate count.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, candidate count, and max output tokens.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, candidate count, max output tokens, and topP
     * value.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider when
     * sampling.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, candidate count, max output tokens, topP value,
     * and topK value.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider when
     * sampling.
     * @param topK The maximum number of tokens to consider when sampling.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP,
                                         int topK)
            throws GeminiApiException, HttpException, JsonException;

    /**
     * Generates text from the model given an input text prompt, safety
     * settings, temperature, candidate count, max output tokens, topP value,
     * topK value, and stop sequences.
     *
     * @param textPrompt The text prompt to use for generation.
     * @param safetySettings The safety settings to apply.
     * @param temperature The temperature to use for generation.
     * @param candidateCount The number of candidate responses to return.
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @param topP The maximum cumulative probability of tokens to consider when
     * sampling.
     * @param topK The maximum number of tokens to consider when sampling.
     * @param stopSequences The stop sequences to use for generation.
     * @return A {@link GenerateContentResponse} containing the generated
     * content.
     * @throws GeminiApiException If an error occurs during content generation.
     */
    GenerateContentResponse generateText(String textPrompt,
                                         List<SafetySetting> safetySettings,
                                         double temperature,
                                         int candidateCount,
                                         int maxOutputTokens,
                                         double topP,
                                         int topK,
                                         List<String> stopSequences)
            throws GeminiApiException, HttpException, JsonException;

}