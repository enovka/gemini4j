package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.impl.GenerationResourceImpl;
import com.enovka.gemini4j.resource.spec.GenerationResource;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link GenerationResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public class GenerationResourceImplTest {

    private GenerationResource generationResource;

    @BeforeEach
    public void setUp() {
        GeminiClient geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .withModel("gemini-1.5-flash-001")
                .build();

        generationResource = ResourceBuilder.builder(geminiClient)
                .buildGenerationResource();
    }

    /**
     * Tests a simple call to
     * {@link GenerationResourceImpl#generateContent(GenerateContentRequest)}
     * with only user input.
     *
     * @throws ResourceException If an error occurs during content generation.
     */
    @Test
    public void testSimpleGenerateContent()
            throws ResourceException {
        GenerateContentRequest request
                = generationResource.generateContentBuilder("Hello, Gemini!")
                .build();
        GeminiResult result = generationResource.generateContent(request);
        assertNotNull(result.getGeneratedText(),
                "Generated text should not be null.");
    }

    /**
     * Tests
     * {@link GenerationResourceImpl#generateContent(GenerateContentRequest)}
     * with user input and system instructions.
     *
     * @throws ResourceException If an error occurs during content generation.
     */
    @Test
    public void testGenerateContentWithSystemInstructions()
            throws ResourceException {
        GenerateContentRequest request
                = generationResource.generateContentBuilder("Tell me a joke.")
                .withSystemInstructions("You are a funny AI assistant.")
                .build();
        GeminiResult result = generationResource.generateContent(request);
        assertNotNull(result.getGeneratedText(),
                "Generated text should not be null.");
    }

    /**
     * Tests
     * {@link GenerationResourceImpl#generateContent(GenerateContentRequest)}
     * with user input, system instructions, and a temperature value.
     *
     * @throws ResourceException If an error occurs during content generation.
     */
    @Test
    public void testGenerateContentWithTemperature()
            throws ResourceException {
        Random random = new Random();
        double temperature = 0.1 + (2.0 - 0.1)
                * random.nextDouble(); // Random temperature between 0.1 and 2.0

        GenerateContentRequest request
                = generationResource.generateContentBuilder(
                        "Write a short poem.")
                .withSystemInstructions("You are a creative AI assistant.")
                .withGenerationConfig().withTemperature(temperature)
                .and()
                .build();
        GeminiResult result = generationResource.generateContent(request);
        assertNotNull(result.getGeneratedText(),
                "Generated text should not be null.");
    }

    /**
     * Tests multi-turn conversation using
     * {@link GenerationResourceImpl#generateContent(GenerateContentRequest)}
     * with context tracking enabled. It simulates a conversation by sending a
     * series of user messages and storing both user messages and model
     * responses. After the conversation, it compares the stored history with
     * the conversation history tracked by the {@link GenerationResourceImpl}
     * instance.
     *
     * @throws ResourceException If an error occurs during content generation.
     */
    @Test
    public void testMultiTurnConversation()
            throws ResourceException {
        generationResource.enableMultiTurnConversation(true);

        // Use a list to store the conversation history for comparison
        List<ConversationTurn> expectedConversation = new ArrayList<>();

        // Define the conversation turns
        expectedConversation.add(new ConversationTurn("user", "Hi there!"));
        expectedConversation.add(new ConversationTurn("model",
                null)); // Placeholder for the model's response
        expectedConversation.add(
                new ConversationTurn("user", "What's your name?"));
        expectedConversation.add(new ConversationTurn("model",
                null)); // Placeholder for the model's response
        expectedConversation.add(
                new ConversationTurn("user", "Tell me a fun fact."));
        expectedConversation.add(new ConversationTurn("model",
                null)); // Placeholder for the model's response
        expectedConversation.add(new ConversationTurn("user", "Goodbye!"));
        expectedConversation.add(new ConversationTurn("model",
                null)); // Placeholder for the model's response

        // Simulate the conversation
        for (int i = 0; i < expectedConversation.size(); i++) {
            ConversationTurn turn = expectedConversation.get(i);
            if (turn.getRole().equals("user")) {
                GenerateContentRequest request
                        = generationResource.generateContentBuilder(
                        turn.getMessage()).build();
                GeminiResult result = generationResource.generateContent(
                        request);
                assertNotNull(result.getGeneratedText(),
                        "Generated text should not be null.");
                // Store the model's response in the expected conversation history
                expectedConversation.get(i + 1)
                        .setMessage(result.getGeneratedText());
            }
        }

        // Get the conversation history tracked by the GenerationResourceImpl
        List<Content> actualConversation
                = generationResource.getConversationHistory();

        // Assertions
        assertEquals(expectedConversation.size(), actualConversation.size(),
                "Conversation history sizes should match.");

        for (int i = 0; i < expectedConversation.size(); i++) {
            ConversationTurn expectedTurn = expectedConversation.get(i);
            Content actualTurn = actualConversation.get(i);

            assertEquals(expectedTurn.getRole(), actualTurn.getRole(),
                    "Roles should match for turn " + (i + 1));
            assertEquals(expectedTurn.getMessage(),
                    actualTurn.getParts().get(0).getText(),
                    "Messages should match for turn " + (i + 1));
        }
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