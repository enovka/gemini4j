package com.enovka.gemini4j.example;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.*;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.json.exception.JsonException;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.spec.GenerationResource;
import com.enovka.gemini4j.resource.spec.ModelResource;

import java.util.List;

/**
 * Example class demonstrating the usage of the Gemini4J library for interacting
 * with the Google Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class Gemini4JExample {

    private static final String API_KEY = "AIzaSyBk4YjBizGHvRY2EPWHvGVyvVIMT2giW4w";

    public static void main(String[] args) {
        // Create a GeminiClient instance
        GeminiClient client = createGeminiClient();

        // Create instances of ModelResource and GenerationResource
        ModelResource modelResource = createModelResource(client);
        GenerationResource generationResource = createGenerationResource(client);

        // List available models
        printAvailableModels(modelResource);

        // Set the desired model for text generation
        client.setModel("models/gemini-1.5-flash-001");

        // Generate text based on user input
        generateAndPrintText("Hello, Gemini! How are you today?", generationResource);

        // Generate text with system instructions
        generateAndPrintText("What is the capital of France?",
                "You are a helpful AI assistant. Answer the question in a concise way.",
                generationResource);

        // Generate content with custom configuration
        generateAndPrintTextWithCustomConfig(client, generationResource);

    }

    /**
     * Creates a new {@link GeminiClient} instance with the provided API key.
     *
     * @return A new {@link GeminiClient} instance.
     */
    private static GeminiClient createGeminiClient() {
        System.out.println("Creating GeminiClient instance...");
        return GeminiClientBuilder.builder()
                .withApiKey(API_KEY)
                .build().build();
    }

    /**
     * Creates a new {@link ModelResource} instance using the provided
     * {@link GeminiClient}.
     *
     * @param client The {@link GeminiClient} instance.
     * @return A new {@link ModelResource} instance.
     */
    private static ModelResource createModelResource(GeminiClient client) {
        System.out.println("Creating ModelResource instance...");
        return ResourceBuilder.builder()
                .withGeminiClient(client)
                .build()
                .buildModelResource();
    }

    /**
     * Creates a new {@link GenerationResource} instance using the provided
     * {@link GeminiClient}.
     *
     * @param client The {@link GeminiClient} instance.
     * @return A new {@link GenerationResource} instance.
     */
    private static GenerationResource createGenerationResource(
            GeminiClient client) {
        System.out.println("Creating GenerationResource instance...");
        return ResourceBuilder.builder()
                .withGeminiClient(client)
                .build()
                .buildGenerationResource();
    }

    /**
     * Prints the list of available models.
     *
     * @param modelResource The {@link ModelResource} instance.
     */
    private static void printAvailableModels(ModelResource modelResource) {
        System.out.println("Listing available models:");
        try {
            ListModel models = modelResource.listModels();
            printModels(models);
        } catch (GeminiApiException | HttpException | JsonException e) {
            System.err.println(
                    "Error listing models: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text using the provided user input and system instructions
     * and prints the response.
     *
     * @param userInput           The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     *                           response.
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintText(String userInput,
                                            String systemInstructions,
                                            GenerationResource generationResource) {
        System.out.println(
                "Generating text with user input and system instructions:");
        try {
            GenerateContentResponse response = generationResource
                    .generateContent(userInput, systemInstructions);
            printGenerateContentResponse(response);
        } catch (GeminiApiException | JsonException | HttpException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text using the provided user input and prints the response.
     *
     * @param userInput           The user's input text.
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintText(String userInput,
                                            GenerationResource generationResource) {
        System.out.println("Generating text with user input:");
        try {
            GenerateContentResponse response = generationResource
                    .generateContent(userInput);
            printGenerateContentResponse(response);
        } catch (GeminiApiException | HttpException | JsonException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text with custom configuration and prints the response.
     *
     * @param client              The {@link GeminiClient} instance.
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintTextWithCustomConfig(
            GeminiClient client, GenerationResource generationResource) {
        System.out.println("Generating content with custom configuration:");
        try {
            GenerateContentRequest request = GenerateContentRequest.builder()
                    .withModel(client.getModel())
                    .withContents(List.of(
                            Content.builder()
                                    .withRole("user")
                                    .withParts(List.of(
                                            Part.builder()
                                                    .withText(
                                                            "Write a short story about a cat who loves to travel.")
                                                    .build()
                                    ))
                                    .build()
                    ))
                    .withGenerationConfig(
                            GenerationConfig.builder()
                                    .withTemperature(0.7)
                                    .withTopP(0.9)
                                    .withTopK(50)
                                    .build())
                    .build();
            GenerateContentResponse response = generationResource
                    .generateContent(request);
            printGenerateContentResponse(response);
        } catch (GeminiApiException | JsonException | HttpException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Prints the list of available models.
     *
     * @param models The {@link ListModel} containing the models.
     */
    private static void printModels(ListModel models) {
        System.out.println("  Models:");
        if (models != null && models.getModels() != null) {
            for (Model model : models.getModels()) {
                System.out.println("    - Name: " + model.getName());
                System.out.println("      - BaseModelId: " + model.getBaseModelId());
                System.out.println("      - Version: " + model.getVersion());
                System.out.println("      - DisplayName: " + model.getDisplayName());
                System.out.println("      - Description: " + model.getDescription());
                System.out.println("      - InputTokenLimit: " + model.getInputTokenLimit());
                System.out.println("      - OutputTokenLimit: " + model.getOutputTokenLimit());
                System.out.println("      - SupportedGenerationMethods: " + model.getSupportedGenerationMethods());
                System.out.println("      - Temperature: " + model.getTemperature());
                System.out.println("      - TopP: " + model.getTopP());
                System.out.println("      - TopK: " + model.getTopK());
            }
        } else {
            System.out.println("    - No models found.");
        }
    }

    /**
     * Prints the response from the GenerateContent API call.
     *
     * @param response The {@link GenerateContentResponse} object.
     */
    private static void printGenerateContentResponse(
            GenerateContentResponse response) {
        System.out.println("  Candidates:");
        if (response != null && response.getCandidates() != null) {
            for (Candidate candidate : response.getCandidates()) {
                System.out.println("    - Content:");
                printContent(candidate.getContent());
                System.out.println("      - FinishReason: " + candidate.getFinishReason().getValue());
                System.out.println("      - SafetyRatings:");
                if (candidate.getSafetyRatings() != null) {
                    for (SafetyRating rating : candidate.getSafetyRatings()) {
                        System.out.println("        - Category: " + rating.getCategory().getValue());
                        System.out.println("          - Probability: " + rating.getProbability().getValue());
                        System.out.println("          - Blocked: " + rating.getBlocked());
                    }
                } else {
                    System.out.println("        - No safety ratings available.");
                }
                System.out.println("      - TokenCount: " + candidate.getTokenCount());
            }
        } else {
            System.out.println("    - No candidates found.");
        }
        System.out.println("  PromptFeedback:");
        printPromptFeedback(response.getPromptFeedback());
        System.out.println("  UsageMetadata:");
        printUsageMetadata(response.getUsageMetadata());
    }

    /**
     * Prints the content of a message.
     *
     * @param content The {@link Content} object.
     */
    private static void printContent(Content content) {
        System.out.println("    - Parts:");
        if (content != null && content.getParts() != null) {
            for (Part part : content.getParts()) {
                System.out.println("      - Role: " + content.getRole());
                System.out.println("      - Text: " + part.getText());
                System.out.println("      - InlineData: " + part.getInlineData());
                System.out.println("      - FunctionCall: " + part.getFunctionCall());
                System.out.println("      - FunctionResponse: " + part.getFunctionResponse());
                System.out.println("      - FileData: " + part.getFileData());
            }
        } else {
            System.out.println("      - No parts found.");
        }
        System.out.println("    - Role: " + content.getRole());
    }

    /**
     * Prints the prompt feedback.
     *
     * @param promptFeedback The {@link PromptFeedback} object.
     */
    private static void printPromptFeedback(PromptFeedback promptFeedback) {
        if (promptFeedback != null) {
            System.out.println("    - BlockReason: " + promptFeedback.getBlockReason().getValue());
            System.out.println("    - SafetyRatings:");
            if (promptFeedback.getSafetyRatings() != null) {
                for (SafetyRating rating : promptFeedback.getSafetyRatings()) {
                    System.out.println("      - Category: " + rating.getCategory().getValue());
                    System.out.println("        - Probability: " + rating.getProbability().getValue());
                    System.out.println("        - Blocked: " + rating.getBlocked());
                }
            } else {
                System.out.println("      - No safety ratings available.");
            }
        } else {
            System.out.println("    - No prompt feedback available.");
        }
    }

    /**
     * Prints the usage metadata.
     *
     * @param usageMetadata The {@link UsageMetadata} object.
     */
    private static void printUsageMetadata(UsageMetadata usageMetadata) {
        if (usageMetadata != null) {
            System.out.println("    - TotalTokenCount: " + usageMetadata.getTotalTokenCount());
            System.out.println("    - PromptTokenCount: " + usageMetadata.getPromptTokenCount());
            System.out.println("    - CachedContentTokenCount: " + usageMetadata.getCachedContentTokenCount());
            System.out.println("    - CandidatesTokenCount: " + usageMetadata.getCandidatesTokenCount());
        } else {
            System.out.println("    - No usage metadata available.");
        }
    }
}