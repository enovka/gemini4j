package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.domain.GenerationConfig;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.resource.builder.spec.AbstractRequestBuilder;
import com.enovka.gemini4j.resource.spec.GenerationResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Builder for creating {@link GenerateContentRequest} instances specifically
 * for generating text in a fluent and intuitive way.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class GenerateTextRequestBuilder extends
        AbstractRequestBuilder<GenerateContentRequest> {

    private final GenerationResource generationResource;
    private String userInput;
    private Double temperature;
    private Integer candidateCount;
    private Integer maxOutputTokens;
    private Double topP;
    private Integer topK;
    private List<String> stopSequences;

    /**
     * Constructor for the GenerateTextRequestBuilder.
     *
     * @param generationResource The GenerationResource instance.
     */
    public GenerateTextRequestBuilder(GenerationResource generationResource) {
        super(generationResource.getGeminiClient());
        this.generationResource = generationResource;
    }

    /**
     * Sets the required user input (text prompt) for the text generation
     * request.
     *
     * @param userInput The user's input text prompt.
     * @return The builder instance for method chaining.
     */
    public GenerateTextRequestBuilder withUserInput(String userInput) {
        if (userInput == null || userInput.isEmpty()) {
            throw new IllegalArgumentException("User input is required.");
        }
        this.userInput = userInput;
        return this;
    }

    /**
     * Sets the optional temperature for the text generation request.
     *
     * @param temperature The temperature to use for generation.
     * @return The builder instance for method chaining.
     */
    public GenerateTextRequestBuilder withTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    /**
     * Sets the optional candidate count for the text generation request.
     *
     * @param candidateCount The number of candidate responses to return.
     * @return The builder instance for method chaining.
     */
    public GenerateTextRequestBuilder withCandidateCount(
            Integer candidateCount) {
        this.candidateCount = candidateCount;
        return this;
    }

    /**
     * Sets the optional maximum output tokens for the text generation request.
     *
     * @param maxOutputTokens The maximum number of tokens to include in a
     * candidate.
     * @return The builder instance for method chaining.
     */
    public GenerateTextRequestBuilder withMaxOutputTokens(
            Integer maxOutputTokens) {
        this.maxOutputTokens = maxOutputTokens;
        return this;
    }

    /**
     * Sets the optional topP value for the text generation request.
     *
     * @param topP The maximum cumulative probability of tokens to consider when
     * sampling.
     * @return The builder instance for method chaining.
     */
    public GenerateTextRequestBuilder withTopP(Double topP) {
        this.topP = topP;
        return this;
    }

    /**
     * Sets the optional topK value for the text generation request.
     *
     * @param topK The maximum number of tokens to consider when sampling.
     * @return The builder instance for method chaining.
     */
    public GenerateTextRequestBuilder withTopK(Integer topK) {
        this.topK = topK;
        return this;
    }

    /**
     * Adds a stop sequence to the list of stop sequences for the text
     * generation request.
     *
     * @param stopSequence The stop sequence to add.
     * @return The builder instance for method chaining.
     */
    public GenerateTextRequestBuilder withStopSequence(String stopSequence) {
        if (this.stopSequences == null) {
            this.stopSequences = new ArrayList<>();
        }
        this.stopSequences.add(stopSequence);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenerateContentRequest build() {
        if (userInput == null) {
            throw new IllegalArgumentException("User input is required.");
        }

        GenerationConfig generationConfig = GenerationConfig.builder()
                .withTemperature(temperature)
                .withCandidateCount(candidateCount)
                .withMaxOutputTokens(maxOutputTokens)
                .withTopP(topP)
                .withTopK(topK)
                .withStopSequences(stopSequences)
                .build();

        ContentBuilder contentBuilder = ContentBuilder.builder(this);
        contentBuilder.withText(userInput);
        contentBuilder.withRole("user");

        return GenerateContentRequest.builder()
                .withModel(generationResource.getGeminiClient().getModel())
                .withContents(Collections.singletonList(contentBuilder.build()))
                .withSafetySettings(safetySettings)
                .withGenerationConfig(generationConfig)
                .build();
    }

    /**
     * Executes the generateText request synchronously and returns the
     * response.
     *
     * @return The {@link GeminiResult} from the Gemini API.
     * @throws GeminiApiException If an error occurs during the API request.
     * @throws JsonException If an error occurs during JSON processing.
     */
    public GeminiResult execute()
            throws GeminiApiException, JsonException, HttpException {
        return generationResource.generateContent(build());
    }
}