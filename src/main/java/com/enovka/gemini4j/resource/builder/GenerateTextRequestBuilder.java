package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.GenerationConfig;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.GenerationResource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Accessors(chain = true)
public class GenerateTextRequestBuilder {

    private final GenerationResource generationResource;
    private final GeminiClient geminiClient;
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
        this.generationResource = generationResource;
        this.geminiClient = generationResource.getGeminiClient();
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
     * Builds the {@link GenerateContentRequest} instance based on the
     * configured parameters.
     *
     * @return The built {@link GenerateContentRequest} instance.
     */
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

        Content content = Content.builder()
                .withParts(Collections.singletonList(
                        Part.builder().withText(userInput).build()))
                .withRole("user")
                .build();

        return GenerateContentRequest.builder()
                .withModel(geminiClient.getModel())
                .withContents(Collections.singletonList(content))
                .withGenerationConfig(generationConfig)
                .build();
    }

    /**
     * Executes the generateText request synchronously and returns the
     * response.
     *
     * @return The {@link GeminiResult} from the Gemini API.
     * @throws ResourceException If an error occurs during the API request. As a
     * network error or JSON processing error.
     */
    public GeminiResult execute()
            throws ResourceException {
        return generationResource.generateContent(build());
    }
}