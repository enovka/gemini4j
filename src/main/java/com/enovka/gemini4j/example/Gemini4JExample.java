package com.enovka.gemini4j.example;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.common.PropertiesLoader;
import com.enovka.gemini4j.domain.*;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.json.exception.JsonException;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.spec.GenerationResource;
import com.enovka.gemini4j.resource.spec.ModelResource;

import java.util.List;
import java.util.Properties;

/**
 * Example class demonstrating the usage of the Gemini4J library for interacting
 * with the Google Gemini API.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 0.0.2
 */
public class Gemini4JExample {

    static Properties properties;

    public static void main(String[] args) {
        properties = PropertiesLoader.loadProperties();
        // Create a GeminiClient instance
        GeminiClient client = createGeminiClient();

        // Create instances of ModelResource and GenerationResource
        ModelResource modelResource = createModelResource(client);
        GenerationResource generationResource = createGenerationResource(
                client);

        // List available models
        printAvailableModels(modelResource);

        // Set the desired model for text generation
        client.setModel("gemini-1.5-flash-001");

        // Example 1: Basic Text Generation
        generateAndPrintText("Hello, Gemini! How are you today?",
                generationResource);

        // Example 2: Text Generation with System Instructions
        generateAndPrintText("What is the capital of France?",
                "You are a helpful AI assistant. Answer the question in a concise way.",
                generationResource);

        // Example 3: Text Generation with Safety Settings
        generateAndPrintTextWithSafetySettings(generationResource);

        // Example 4: Text Generation with Temperature
        generateAndPrintTextWithTemperature(generationResource);

        // Example 5: Text Generation with Candidate Count
        generateAndPrintTextWithCandidateCount(generationResource);

        // Example 6: Text Generation with Max Output Tokens
        generateAndPrintTextWithMaxOutputTokens(generationResource);

        // Example 7: Text Generation with TopP
        generateAndPrintTextWithTopP(generationResource);

        // Example 8: Text Generation with TopK
        generateAndPrintTextWithTopK(generationResource);

        // Example 9: Text Generation with Stop Sequences
        generateAndPrintTextWithStopSequences(generationResource);

        // Example 10: Text Generation with Custom Configuration
        generateAndPrintTextWithCustomConfig(generationResource);
    }

    /**
     * Creates a new {@link GeminiClient} instance with the provided API key.
     *
     * @return A new {@link GeminiClient} instance.
     */
    private static GeminiClient createGeminiClient() {
        System.out.println("Creating GeminiClient instance...");
        String apiKey = getApiKey();
        if (apiKey == null) {
            throw new RuntimeException(
                    "API key not found. Define the GEMINI_API_KEY environment variable.");
        }
        return GeminiClientBuilder.builder()
                .withApiKey(apiKey)
                .build().build();
    }

    private static String getApiKey() {
        return (String) properties.get("com.google.gemini.key");
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
     * Generates text using the provided user input and system instructions and
     * prints the response.
     *
     * @param userInput The user's input text.
     * @param systemInstructions The system instructions to guide the model's
     * response.
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
     * @param userInput The user's input text.
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
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintTextWithCustomConfig(
            GenerationResource generationResource) {
        System.out.println("Generating content with custom configuration:");
        try {
            GenerateContentResponse response = generationResource
                    .generateText(
                            "Write a short story about a cat who loves to travel.",
                            List.of(
                                    SafetySetting.builder()
                                            .withCategory(
                                                    HarmCategory.fromValue(
                                                            HarmCategoryEnum.HARM_CATEGORY_HARASSMENT.name()))
                                            .build()
                            ),
                            0.7,
                            3,
                            100,
                            0.9,
                            50,
                            List.of("The End"));
            printGenerateContentResponse(response);
        } catch (GeminiApiException | JsonException | HttpException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text with safety settings and prints the response.
     *
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintTextWithSafetySettings(
            GenerationResource generationResource) {
        System.out.println("Generating text with safety settings:");
        try {
            GenerateContentResponse response = generationResource
                    .generateText(
                            "Write a story about a robot who falls in love with a human.",
                            List.of(
                                    SafetySetting.builder()
                                            .withCategory(
                                                    HarmCategory.fromValue(
                                                            HarmCategoryEnum.HARM_CATEGORY_HARASSMENT.name()))
                                            .build()
                            ));
            printGenerateContentResponse(response);
        } catch (GeminiApiException | JsonException | HttpException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text with temperature and prints the response.
     *
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintTextWithTemperature(
            GenerationResource generationResource) {
        System.out.println("Generating text with temperature:");
        try {
            GenerateContentResponse response = generationResource
                    .generateText("Write a poem about the beauty of nature.",
                            null, // No safety settings
                            0.9); // Temperature
            printGenerateContentResponse(response);
        } catch (GeminiApiException | JsonException | HttpException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text with candidate count and prints the response.
     *
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintTextWithCandidateCount(
            GenerationResource generationResource) {
        System.out.println("Generating text with candidate count:");
        try {
            GenerateContentResponse response = generationResource
                    .generateText(
                            "Write a list of five interesting facts about the moon.",
                            null, // No safety settings
                            0.5, // Temperature
                            5); // Candidate count
            printGenerateContentResponse(response);
        } catch (GeminiApiException | JsonException | HttpException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text with max output tokens and prints the response.
     *
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintTextWithMaxOutputTokens(
            GenerationResource generationResource) {
        System.out.println("Generating text with max output tokens:");
        try {
            GenerateContentResponse response = generationResource
                    .generateText(
                            "Write a short story about a dog who loves to play fetch.",
                            null, // No safety settings
                            0.5, // Temperature
                            1, // Candidate count
                            10); // Max output tokens
            printGenerateContentResponse(response);
        } catch (GeminiApiException | JsonException | HttpException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text with topP and prints the response.
     *
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintTextWithTopP(
            GenerationResource generationResource) {
        System.out.println("Generating text with topP:");
        try {
            GenerateContentResponse response = generationResource
                    .generateText("Write a funny joke about a cat.",
                            null, // No safety settings
                            0.5, // Temperature
                            1, // Candidate count
                            100, // Max output tokens
                            0.9); // TopP
            printGenerateContentResponse(response);
        } catch (GeminiApiException | JsonException | HttpException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text with topK and prints the response.
     *
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintTextWithTopK(
            GenerationResource generationResource) {
        System.out.println("Generating text with topK:");
        try {
            GenerateContentResponse response = generationResource
                    .generateText("Write a haiku about a sunset.",
                            null, // No safety settings
                            0.5, // Temperature
                            1, // Candidate count
                            50, // Max output tokens
                            0.9, // TopP
                            40); // TopK
            printGenerateContentResponse(response);
        } catch (GeminiApiException | JsonException | HttpException e) {
            System.err.println(
                    "Error generating text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates text with stop sequences and prints the response.
     *
     * @param generationResource The {@link GenerationResource} instance.
     */
    private static void generateAndPrintTextWithStopSequences(
            GenerationResource generationResource) {
        System.out.println("Generating text with stop sequences:");
        try {
            GenerateContentResponse response = generationResource
                    .generateText(
                            "Write a short story about a magical creature.",
                            null, // No safety settings
                            0.5, // Temperature
                            1, // Candidate count
                            100, // Max output tokens
                            0.9, // TopP
                            40, // TopK
                            List.of("happily ever after",
                                    "the end")); // Stop sequences
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
                System.out.println(
                        "      - BaseModelId: " + model.getBaseModelId());
                System.out.println("      - Version: " + model.getVersion());
                System.out.println(
                        "      - DisplayName: " + model.getDisplayName());
                System.out.println(
                        "      - Description: " + model.getDescription());
                System.out.println("      - InputTokenLimit: "
                        + model.getInputTokenLimit());
                System.out.println("      - OutputTokenLimit: "
                        + model.getOutputTokenLimit());
                System.out.println("      - SupportedGenerationMethods: "
                        + model.getSupportedGenerationMethods());
                System.out.println(
                        "      - Temperature: " + model.getTemperature());
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
                System.out.println(
                        "      - FinishReason: " + candidate.getFinishReason()
                                .getNameValue());
                System.out.println("      - SafetyRatings:");
                if (candidate.getSafetyRatings() != null) {
                    for (SafetyRating rating : candidate.getSafetyRatings()) {
                        System.out.println(
                                "        - Category: " + rating.getCategory()
                                        .getNameValue());
                        System.out.println("          - Probability: "
                                + rating.getProbability().getValueName());
                        System.out.println(
                                "          - Blocked: " + rating.getBlocked());
                    }
                } else {
                    System.out.println(
                            "        - No safety ratings available.");
                }
                System.out.println(
                        "      - TokenCount: " + candidate.getTokenCount());
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
                System.out.println(
                        "      - InlineData: " + part.getInlineData());
                System.out.println(
                        "      - FunctionCall: " + part.getFunctionCall());
                System.out.println("      - FunctionResponse: "
                        + part.getFunctionResponse());
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
            System.out.println(
                    "    - BlockReason: " + promptFeedback.getBlockReason()
                            .getValue());
            System.out.println("    - SafetyRatings:");
            if (promptFeedback.getSafetyRatings() != null) {
                for (SafetyRating rating : promptFeedback.getSafetyRatings()) {
                    System.out.println(
                            "      - Category: " + rating.getCategory()
                                    .getNameValue());
                    System.out.println(
                            "        - Probability: " + rating.getProbability()
                                    .getValueName());
                    System.out.println(
                            "        - Blocked: " + rating.getBlocked());
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
            System.out.println("    - TotalTokenCount: "
                    + usageMetadata.getTotalTokenCount());
            System.out.println("    - PromptTokenCount: "
                    + usageMetadata.getPromptTokenCount());
            System.out.println("    - CachedContentTokenCount: "
                    + usageMetadata.getCachedContentTokenCount());
            System.out.println("    - CandidatesTokenCount: "
                    + usageMetadata.getCandidatesTokenCount());
        } else {
            System.out.println("    - No usage metadata available.");
        }
    }
}