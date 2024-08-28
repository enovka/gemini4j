package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.ResourceFiles;
import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.CachedContent;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.domain.type.ModeEnum;
import com.enovka.gemini4j.domain.type.TypeEnum;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.CachedContentResource;
import com.enovka.gemini4j.resource.spec.CountTokensResource;
import com.enovka.gemini4j.resource.spec.GenerationResource;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for
 * {@link com.enovka.gemini4j.resource.impl.GenerationResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public class GenerationResourceImplTest {

    private static final String MODEL_NAME = "/models/gemini-1.5-flash-001";
    private static final String SAMPLE_TEXT_FILE = "sample.txt";
    private static final String USER_INPUT = "Hello, Gemini!";
    private static final String SYSTEM_INSTRUCTION
            = "You are a funny AI assistant.";
    private static final String JOKE_PROMPT = "Tell me a joke.";
    private static final String POEM_PROMPT = "Write a short poem.";
    private static final String TRANSLATION_PROMPT
            = "Translate this sentence to Spanish: 'The cat sat on the mat.'";
    private static final String TRANSLATOR_INSTRUCTION
            = "You are a helpful and accurate translator.";
    private static final String WEATHER_PROMPT
            = "What is the weather like in London?";
    private static final String ROBBERY_PROMPT
            = "Write a story about a bank robbery.";
    private static final String CACHED_CONTENT_QUESTION
            = "When was the book 'Psyche' originally published?";
    private static final String EXPECTED_CACHED_CONTENT_ANSWER = "1905";
    private static final String CAT_POEM_PROMPT
            = "Write a short poem about a cat.";
    private static final String TELL_ME_A_JOKE_PROMPT = "Tell me a joke.";
    private static final String SIMPLE_TEXT = "This is a test sentence.";
    private static final String PARIS_WEATHER_PROMPT
            = "What is the weather like in Paris?";

    private GenerationResource generationResource;
    private CachedContentResource cachedContentResource;
    private CountTokensResource countTokensResource;
    private String cacheName;

    @BeforeEach
    public void setUp() {
        GeminiClient geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .withModel(MODEL_NAME)
                .build();

        generationResource = ResourceBuilder.builder(geminiClient)
                .buildGenerationResource();

        cachedContentResource = ResourceBuilder.builder(geminiClient)
                .buildCachedContentResource();

        countTokensResource = ResourceBuilder.builder(geminiClient)
                .buildCountTokensResource();

        cacheName = "cachedContents/test-cache-" + System.currentTimeMillis();
    }

    /**
     * Tests simple content generation with user input.
     *
     * @throws ResourceException If an error occurs during content generation.
     */
    @Test
    public void testSimpleGenerateContent() throws ResourceException {
        GeminiResult result = generateContent(USER_INPUT);
        assertNotNull(result.getGeneratedText(),
                "Generated text should not be null.");
    }

    /**
     * Tests content generation with user input and system instructions.
     *
     * @throws ResourceException If an error occurs during content generation.
     */
    @Test
    public void testGenerateContentWithSystemInstructions()
            throws ResourceException {
        GeminiResult result = generateContentWithSystemInstructions(JOKE_PROMPT,
                SYSTEM_INSTRUCTION);
        assertNotNull(result.getGeneratedText(),
                "Generated text should not be null.");
    }

    /**
     * Tests content generation with temperature control.
     *
     * @throws ResourceException If an error occurs during content generation.
     */
    @Test
    public void testGenerateContentWithTemperature() throws ResourceException {
        Random random = new Random();
        double temperature = 0.1 + (2.0 - 0.1)
                * random.nextDouble(); // Random temperature between 0.1 and 2.0

        GeminiResult result = generateContentWithTemperature(POEM_PROMPT,
                temperature);
        assertNotNull(result.getGeneratedText(),
                "Generated text should not be null.");
    }

    /**
     * Tests multi-turn conversation with context tracking.
     *
     * @throws ResourceException If an error occurs during content generation.
     */
    @Test
    public void testMultiTurnConversation() throws ResourceException {
        generationResource.enableMultiTurnConversation(true);

        // Use a list to store the conversation history for comparison
        List<ConversationTurn> expectedConversation = new ArrayList<>();

        // Define the conversation turns
        expectedConversation.add(new ConversationTurn("user", "Hi there!"));
        expectedConversation.add(new ConversationTurn("model", null));
        expectedConversation.add(
                new ConversationTurn("user", "What's your name?"));
        expectedConversation.add(new ConversationTurn("model", null));
        expectedConversation.add(
                new ConversationTurn("user", "Tell me a fun fact."));
        expectedConversation.add(new ConversationTurn("model", null));
        expectedConversation.add(new ConversationTurn("user", "Goodbye!"));
        expectedConversation.add(new ConversationTurn("model", null));

        // Simulate the conversation
        for (int i = 0; i < expectedConversation.size(); i++) {
            ConversationTurn turn = expectedConversation.get(i);
            if (turn.getRole().equals("user")) {
                GeminiResult result = generateContent(turn.getMessage());
                assertNotNull(result.getGeneratedText(),
                        "Generated text should not be null.");
                // Store the model's response in the expected conversation history
                expectedConversation.get(i + 1)
                        .setMessage(result.getGeneratedText());
            }
        }

        // Assertions
        assertEquals(expectedConversation.size(),
                generationResource.getConversationHistory().size(),
                "Conversation history sizes should match.");

        // Get the conversation history tracked by the GenerationResourceImpl
        for (int i = 0; i < expectedConversation.size(); i += 2) {
            ConversationTurn expectedTurn = expectedConversation.get(i);
            String actualTurn = generationResource.getConversationHistory()
                    .get(i).getParts().get(0).getText();

            assertEquals(expectedTurn.getMessage(), actualTurn,
                    "Messages should match for turn " + (i + 1));
        }
    }

    /**
     * Tests content generation with and without cached content.
     *
     * @throws ResourceException If an error occurs during the test.
     * @since 0.1.3
     */
    @Test
    public void testGenerateContentWithCachedContent()
            throws ResourceException {
        // Test without cached content
        GeminiResult resultWithoutCache = generateContentWithSafetySettings(
                CACHED_CONTENT_QUESTION);
        assertFalse(resultWithoutCache.getGeneratedText()
                        .contains(EXPECTED_CACHED_CONTENT_ANSWER),
                "Generated text should not contain the expected answer without cached content.");

        // Create cached content
        cacheName = createCachedContent(MODEL_NAME, SAMPLE_TEXT_FILE,
                "300s").getName();

        // Test with cached content
        GeminiResult resultWithCache = generateContentWithCachedContent(
                CACHED_CONTENT_QUESTION, cacheName);
        assertTrue(resultWithCache.getGeneratedText()
                        .contains(EXPECTED_CACHED_CONTENT_ANSWER),
                "Generated text should contain the expected answer with cached content.");

        // Clean up cached content
        cachedContentResource.deleteCachedContent(cacheName);
    }

    /*    *//**
     * Tests content generation with function calling.
     *
     * @throws ResourceException If an error occurs during the test.
     * @since 0.1.3
     *//*
    @Test
    public void testGenerateContentWithFunctionCalling()
            throws ResourceException {
        GeminiResult result = generateContentWithFunctionCalling(
                WEATHER_PROMPT);
        assertNotNull(result.getGeneratedText(),
                "Generated text should not be null.");
        assertTrue(result.getGeneratedText().contains("getCurrentWeather"),
                "Generated text should contain the function call.");
    }*/

    /**
     * Tests simple text generation.
     *
     * @throws ResourceException If an error occurs during text generation.
     */
    @Test
    public void testSimpleGenerateText() throws ResourceException {
        GeminiResult result = generateText(CAT_POEM_PROMPT);
        assertNotNull(result.getGeneratedText(),
                "Generated text should not be null.");
    }

    /**
     * Tests text generation with temperature and max output tokens.
     *
     * @throws ResourceException If an error occurs during text generation.
     */
    @Test
    public void testGenerateTextWithTemperatureAndMaxOutputTokens()
            throws ResourceException {
        GeminiResult result = generateTextWithParameters(TELL_ME_A_JOKE_PROMPT,
                0.8, 100);
        assertNotNull(result.getGeneratedText(),
                "Generated text should not be null.");
    }

    /**
     * Tests token counting for simple text.
     *
     * @throws ResourceException If an error occurs during token counting.
     */
    @Test
    public void testCountTokensForSimpleText() throws ResourceException {
        int tokenCount = countTokens(SIMPLE_TEXT);
        assertTrue(tokenCount > 0, "Token count should be greater than 0.");
    }

    /**
     * Generates content with the given user input.
     *
     * @param userInput The user input for content generation.
     * @return The {@link GeminiResult} containing the generated content.
     * @throws ResourceException If an error occurs during content generation.
     */
    private GeminiResult generateContent(String userInput)
            throws ResourceException {
        GenerateContentRequest request
                = generationResource.generateContentBuilder(userInput)
                .build();
        return generationResource.generateContent(request);
    }

    /**
     * Generates content with the given user input and system instructions.
     *
     * @param userInput The user input for content generation.
     * @param systemInstruction The system instructions for the model.
     * @return The {@link GeminiResult} containing the generated content.
     * @throws ResourceException If an error occurs during content generation.
     */
    private GeminiResult generateContentWithSystemInstructions(String userInput,
                                                               String systemInstruction)
            throws ResourceException {
        GenerateContentRequest request
                = generationResource.generateContentBuilder(userInput)
                .withSystemInstruction(systemInstruction)
                .build();
        return generationResource.generateContent(request);
    }

    /**
     * Generates content with the given user input, system instructions, and
     * temperature.
     *
     * @param userInput The user input for content generation.
     * @param temperature The temperature for controlling randomness.
     * @return The {@link GeminiResult} containing the generated content.
     * @throws ResourceException If an error occurs during content generation.
     */
    private GeminiResult generateContentWithTemperature(String userInput,
                                                        double temperature)
            throws ResourceException {
        GenerateContentRequest request
                = generationResource.generateContentBuilder(userInput)
                .withSystemInstruction("You are a creative AI assistant.")
                .withGenerationConfig(
                        config -> config.withTemperature(temperature))
                .build();
        return generationResource.generateContent(request);
    }

    /**
     * Generates content with the given user input and cached content.
     *
     * @param userInput The user input for content generation.
     * @param cacheName The name of the cached content.
     * @return The {@link GeminiResult} containing the generated content.
     * @throws ResourceException If an error occurs during content generation.
     */
    private GeminiResult generateContentWithCachedContent(String userInput,
                                                          String cacheName)
            throws ResourceException {
        GenerateContentRequest request
                = generationResource.generateContentBuilder(userInput)
                .withCachedContent(cacheName)
                .withSafetySetting(safety -> safety
                        .withHarassment(HarmBlockThresholdEnum.BLOCK_NONE)
                        .withHateSpeech(HarmBlockThresholdEnum.BLOCK_NONE)
                        .withSexuallyExplicit(HarmBlockThresholdEnum.BLOCK_NONE)
                        .withDangerousContent(
                                HarmBlockThresholdEnum.BLOCK_NONE))
                .build();
        return generationResource.generateContent(request);
    }

    /**
     * Generates content with function calling.
     *
     * @param userInput The user input for content generation.
     * @return The {@link GeminiResult} containing the generated content.
     * @throws ResourceException If an error occurs during content generation.
     */
    private GeminiResult generateContentWithFunctionCalling(String userInput)
            throws ResourceException {
        GenerateContentRequest request
                = generationResource.generateContentBuilder(userInput)
                .withTool("getWeather",
                        "Gets the current weather for a given location",
                        function -> function
                                .withFunctionDeclaration("getCurrentWeather",
                                        "Gets the current weather conditions.",
                                        params -> params
                                                .withParameter("location",
                                                        TypeEnum.STRING,
                                                        "The location to get the weather for.")
                                                .withRequiredParameter(
                                                        "location")))
                .withToolConfig(config -> config
                        .withFunctionCallingConfig(calling -> calling
                                .withMode(ModeEnum.AUTO)))
                .build();
        return generationResource.generateContent(request);
    }

    /**
     * Generates content with safety settings.
     *
     * @param userInput The user input for content generation.
     * @return The {@link GeminiResult} containing the generated content.
     * @throws ResourceException If an error occurs during content generation.
     */
    private GeminiResult generateContentWithSafetySettings(String userInput)
            throws ResourceException {
        GenerateContentRequest request
                = generationResource.generateContentBuilder(userInput)
                .withSafetySetting(safety -> safety
                        .withCivicIntegrity(HarmBlockThresholdEnum.BLOCK_NONE)
                        .withHarassment(HarmBlockThresholdEnum.BLOCK_NONE)
                        .withSexuallyExplicit(HarmBlockThresholdEnum.BLOCK_NONE)
                        .withDangerousContent(HarmBlockThresholdEnum.BLOCK_NONE)
                        .withHateSpeech(HarmBlockThresholdEnum.BLOCK_NONE))
                .build();
        return generationResource.generateContent(request);
    }

    /**
     * Generates text with the given prompt.
     *
     * @param prompt The text prompt for generation.
     * @return The {@link GeminiResult} containing the generated text.
     * @throws ResourceException If an error occurs during text generation.
     */
    private GeminiResult generateText(String prompt) throws ResourceException {
        return generationResource.generateTextBuilder(prompt)
                .execute();
    }

    /**
     * Generates text with the given prompt, temperature, and maximum output
     * tokens.
     *
     * @param prompt The text prompt for generation.
     * @param temperature The temperature for controlling randomness.
     * @param maxOutputTokens The maximum number of tokens to generate.
     * @return The {@link GeminiResult} containing the generated text.
     * @throws ResourceException If an error occurs during text generation.
     */
    private GeminiResult generateTextWithParameters(String prompt,
                                                    double temperature,
                                                    int maxOutputTokens)
            throws ResourceException {
        return generationResource.generateTextBuilder(prompt)
                .withTemperature(temperature)
                .withMaxOutputTokens(maxOutputTokens)
                .execute();
    }

    /**
     * Counts the number of tokens in the given text.
     *
     * @param text The text to count tokens in.
     * @return The number of tokens in the text.
     * @throws ResourceException If an error occurs during token counting.
     */
    private int countTokens(String text) throws ResourceException {
        return countTokensResource.countTokens(
                countTokensResource.countTokensBuilder(text)
                        .build()).getTotalTokens();
    }

    /**
     * Creates cached content for testing.
     *
     * @param modelName The name of the model to use for the cached content.
     * @param fileName The name of the file containing the text content.
     * @param ttl The time-to-live for the cached content.
     * @return The created {@link CachedContent} instance.
     * @throws ResourceException If an error occurs during the cached content
     * creation process.
     */
    private CachedContent createCachedContent(String modelName, String fileName,
                                              String ttl)
            throws ResourceException {
        return cachedContentResource.createCachedContent(
                cachedContentResource.createCachedContentBuilder(
                                "models/" + modelName.replaceAll("^(/)?models(/)?", ""))
                        .withContent(Content.builder()
                                .withRole("user")
                                .withParts(
                                        Collections.singletonList(Part.builder()
                                                .withText(
                                                        ResourceFiles.loadFileFromResources(
                                                                fileName))
                                                .build()))
                                .build())
                        .withTtl(ttl)
                        .build()
        );
    }

    /**
     * Inner class to represent a single turn in a conversation.
     */
    @Getter
    private static class ConversationTurn {
        private final String role;
        @Setter
        private String message;

        public ConversationTurn(String role, String message) {
            this.role = role;
            this.message = message;
        }
    }
}