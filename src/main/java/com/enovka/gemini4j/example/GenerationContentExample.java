package com.enovka.gemini4j.example;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.resource.builder.GenerateContentRequestBuilder;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.spec.GenerationResource;

/**
 * Example demonstrating the usage of the refactored builders in the Gemini4j
 * library.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class GenerationContentExample {

    public static void main(String[] args) {
        // Replace with your actual API key
        String apiKey = "API-KEY";

        // Create a GeminiClient
        GeminiClient client = GeminiClientBuilder.builder()
                .withApiKey(apiKey)
                .withModel(
                        "gemini-1.5-flash-001") // Set the desired Gemini model
                .build();

        // Create a GenerationResource
        GenerationResource generationResource = ResourceBuilder.builder(client)
                .buildGenerationResource();

        // Example 1: Simple text generation with system instructions
        GenerateContentRequest request1 = GenerateContentRequestBuilder.builder(
                        generationResource.getGeminiClient())
                .withUserInput(
                        "Write a short story about a cat who goes on an adventure.") // User input prompt
                .withSystemInstructions(
                        "Make it a humorous story with a happy ending.") // System-level instructions for the model
                .withGenerationConfig()
                .withTemperature(
                        0.7) // Controls the randomness of the output (higher = more random)
                .withMaxOutputTokens(
                        200) // Limits the maximum number of tokens in the generated response
                .and()
                .build();

        // Example 2: Text generation with safety settings
        GenerateContentRequest request2 = GenerateContentRequestBuilder.builder(
                        generationResource.getGeminiClient())
                .withUserInput("Tell me a joke.") // User input prompt
                .withSafetySetting()
                .withCategory(HarmCategoryEnum.HARM_CATEGORY_DANGEROUS_CONTENT)
                .withThreshold(HarmBlockThresholdEnum.BLOCK_MEDIUM_AND_ABOVE)
                .and()
                .build();

        // Example 3: Text generation with multiple candidates
        GenerateContentRequest request3 = GenerateContentRequestBuilder.builder(
                        generationResource.getGeminiClient())
                .withUserInput(
                        "Give me three ideas for a birthday party.") // User input prompt
                .withGenerationConfig()
                .withCandidateCount(
                        1) // Request 1 different response candidates
                .and()
                .build();

        // Execute the requests and print the generated text
        try {
            GeminiResult result1 = generationResource.generateContent(request1);
            System.out.println("Example 1 - Generated Text: "
                    + result1.getGeneratedText());

            GeminiResult result2 = generationResource.generateContent(request2);
            System.out.println("Example 2 - Generated Text: "
                    + result2.getGeneratedText());

            GeminiResult result3 = generationResource.generateContent(request3);
            System.out.println("Example 3 - Generated Text: "
                    + result3.getGeneratedText());

        } catch (GeminiApiException | JsonException | HttpException e) {
            e.printStackTrace();
        }
    }
}