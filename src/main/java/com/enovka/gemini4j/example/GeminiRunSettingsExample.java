package com.enovka.gemini4j.example;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.GenerationConfig;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.domain.type.HarmCategoryEnum;
import com.enovka.gemini4j.domain.type.ResponseMimeType;
import com.enovka.gemini4j.resource.builder.GenerationResourceBuilder;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.GenerationResource;

import java.util.Collections;

/**
 * Example class demonstrating the usage of the Gemini4j library with advanced
 * run settings, mirroring the options available in Google AI Studio.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.0
 */
public class GeminiRunSettingsExample {

    /**
     * Main method to execute the example.
     *
     * @param args Command line arguments (not used).
     * @throws ResourceException If an error occurs during the Gemini API call.
     */
    public static void main(String[] args) throws ResourceException {
        GeminiRunSettingsExample example = new GeminiRunSettingsExample();
        example.runGeminiWithSettings(
                "You are a helpful and informative AI assistant.",
                "What is the capital of France?",
                "gemini-1.5-flash-001",
                0.7,
                "###",
                0.8
        );
    }

    /**
     * Executes a Gemini generation request using the provided run settings.
     * This method showcases how to configure various parameters like system
     * instructions, user input, model selection, temperature, stop sequences,
     * top-p value, and safety settings, similar to the options available in
     * Google AI Studio.
     *
     * @param systemInstructions The system instructions to guide the model's
     * behavior. These instructions provide high-level guidance to the model,
     * influencing its personality and the type of responses it generates. For
     * example, "You are a helpful and informative AI assistant."
     * @param userInput The user's input or prompt that initiates the generation
     * process. This is the core input that the model will use to generate a
     * response.
     * @param model The name of the Gemini model to use. Each Gemini model has
     * different capabilities and characteristics, so selecting the appropriate
     * model is crucial for achieving the desired results.
     * @param temperature The temperature value to control the randomness of the
     * output. This value ranges from 0.0 to 2.0, with higher values leading to
     * more creative and unpredictable outputs, while lower values result in
     * more deterministic and focused responses.
     * @param stopSequence The stop sequence to halt the generation process.
     * This is a specific sequence of characters that, when encountered, will
     * signal the model to stop generating further text. This can be useful for
     * controlling the length or structure of the generated output.
     * @param topP The top-p value for nucleus sampling. This parameter
     * influences the diversity of the generated text by controlling the
     * probability distribution of tokens considered during generation. A higher
     * top-p value (closer to 1.0) includes a wider range of tokens, potentially
     * leading to more diverse and creative outputs.
     * @throws ResourceException If an error occurs during the generation
     * process. This exception encapsulates any errors that might occur while
     * interacting with the Gemini API, such as network issues, invalid
     * requests, or server errors.
     */
    public void runGeminiWithSettings(String systemInstructions,
                                      String userInput, String model,
                                      double temperature,
                                      String stopSequence, double topP)
            throws ResourceException {

        // Create a GeminiClient instance using the builder pattern.
        // The API key is retrieved from the environment variable "GEMINI_API_KEY".
        // The selected model is set for this client instance.
        GeminiClient geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .withModel(model)
                .build();

        // Build the GenerationResource using the ResourceBuilder, providing the
        // configured GeminiClient. This resource is responsible for handling
        // content generation requests.
        GenerationResource generationResource = ResourceBuilder.builder(
                geminiClient).buildGenerationResource();

        // Construct the GenerateContentRequest using the GenerationResourceBuilder.
        // This builder provides a fluent API for configuring all the parameters
        // of the generation request.
        GeminiResult result = generationResource.generateContent(
                GenerationResourceBuilder.builder(geminiClient)
                        // Set the user input for the generation request.
                        .withUserInput(userInput)
                        // Set the system instructions to guide the model's behavior.
                        .withSystemInstruction(systemInstructions)
                        // Configure the generation parameters using the GenerationConfig builder.
                        .withGenerationConfig(GenerationConfig.builder()
                                // Set the temperature value for controlling randomness.
                                .withTemperature(temperature)
                                // Set the top-p value for nucleus sampling.
                                .withTopP(topP)
                                // Set the response MIME type to plain text.
                                .withResponseMimeType(
                                        ResponseMimeType.TEXT_PLAIN.getMimeType())
                                // Add a stop sequence to halt generation.
                                .withStopSequences(
                                        Collections.singletonList(stopSequence))
                                .build())
                        // Configure safety settings for various harm categories.
                        // Each safety setting consists of a category and a threshold.
                        .withSafetySetting()
                        // Set the category to Harassment, which filters content that is abusive,
                        // threatening, or intended to harass or bully individuals or groups.
                        .withCategory(HarmCategoryEnum.HARM_CATEGORY_HARASSMENT)
                        // Set the threshold to BLOCK_MEDIUM_AND_ABOVE, which means that
                        // content with a medium or high probability of being harmful in this category
                        // will be blocked.
                        .withThreshold(
                                HarmBlockThresholdEnum.BLOCK_MEDIUM_AND_ABOVE)
                        // Chain the next safety setting using 'and()'.
                        .and()
                        .withSafetySetting()
                        // Set the category to Hate Speech, which blocks content that expresses
                        // hatred, prejudice, or discrimination based on protected characteristics
                        // like race, religion, or sexual orientation.
                        .withCategory(
                                HarmCategoryEnum.HARM_CATEGORY_HATE_SPEECH)
                        // Set the threshold to BLOCK_MEDIUM_AND_ABOVE for this category as well.
                        .withThreshold(
                                HarmBlockThresholdEnum.BLOCK_MEDIUM_AND_ABOVE)
                        // Chain the next safety setting using 'and()'.
                        .and()
                        // Configure safety setting for Sexually Explicit content.
                        .withSafetySetting()
                        // Set the category to Sexually Explicit, which filters content that is
                        // sexually suggestive, explicit, or inappropriate for general audiences.
                        .withCategory(
                                HarmCategoryEnum.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                        // Set the threshold to BLOCK_MEDIUM_AND_ABOVE, blocking content with a medium
                        // or high probability of being sexually explicit.
                        .withThreshold(
                                HarmBlockThresholdEnum.BLOCK_MEDIUM_AND_ABOVE)
                        // Chain the next safety setting using 'and()'.
                        .and()
                        // Configure safety setting for Dangerous Content.
                        .withSafetySetting()
                        // Set the category to Dangerous Content, which blocks content that promotes
                        // violence, illegal activities, self-harm, or other dangerous behavior.
                        .withCategory(
                                HarmCategoryEnum.HARM_CATEGORY_DANGEROUS_CONTENT)
                        // Set the threshold to BLOCK_MEDIUM_AND_ABOVE for dangerous content.
                        .withThreshold(
                                HarmBlockThresholdEnum.BLOCK_MEDIUM_AND_ABOVE)
                        // End the safety settings chain using 'and()'.
                        .and()
                        // Finally, build the GenerateContentRequest object.
                        .build()
        );
        // Print the generated text from the GeminiResult.
        System.out.println("Generated Text: " + result.getGeneratedText());
    }
}