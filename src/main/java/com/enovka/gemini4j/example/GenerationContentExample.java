package com.enovka.gemini4j.example;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;

/**
 * Example demonstrating the correct usage of the refactored builders in the
 * Gemini4j library.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class GenerationContentExample {

    public static void main(String[] args) {
        // Replace with your actual API key
        String apiKey = "API-KEY";

        // Create a GeminiClient and GenerationResource
        var generationResource = ResourceBuilder.builder(
                        GeminiClientBuilder.builder()
                                .withApiKey(apiKey)
                                .withModel("gemini-1.5-flash-001")
                                .build())
                .buildGenerationResource();

        try {
            // Example 1: Simple text generation with system instructions
            GeminiResult result1 = generationResource.generateContent(
                    generationResource.generateContentBuilder(
                                    "Write a short story about a cat who goes on an adventure.")
                            .withSystemInstructions(
                                    "Make it a humorous story with a happy ending.")
                            .withGenerationConfig()
                            .withTemperature(0.7)
                            .withMaxOutputTokens(200)
                            .and()
                            .build());
            System.out.println("Example 1 - Generated Text: "
                    + result1.getGeneratedText());

            // Example 2: Text generation with safety settings
            GeminiResult result2 = generationResource.generateContent(
                    generationResource.generateContentBuilder("Tell me a joke.")
                            .withSafetySetting()
                            .withCategory(
                                    HarmCategoryEnum.HARM_CATEGORY_DANGEROUS_CONTENT)
                            .withThreshold(
                                    HarmBlockThresholdEnum.BLOCK_MEDIUM_AND_ABOVE)
                            .and()
                            .build());
            System.out.println("Example 2 - Generated Text: "
                    + result2.getGeneratedText());

            // Example 3: Text generation using the generateTextBuilder
            GeminiResult result3 = generationResource.generateTextBuilder(
                            "Give me three ideas for a birthday party.")
                    .execute();
            System.out.println("Example 3 - Generated Text: "
                    + result3.getGeneratedText());

        } catch (GeminiApiException | JsonException | HttpException e) {
            e.printStackTrace();
        }
    }
}