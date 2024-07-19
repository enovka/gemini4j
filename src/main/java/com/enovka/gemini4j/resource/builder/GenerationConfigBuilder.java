package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.GenerationConfig;
import com.enovka.gemini4j.domain.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link GenerationConfig} instances.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class GenerationConfigBuilder {

    private final GenerateContentRequestBuilder generateContentRequestBuilder;
    public Schema responseSchema;
    private List<String> stopSequences;
    private String responseMimeType;
    private Integer candidateCount;
    private Integer maxOutputTokens;
    private Double temperature;
    private Double topP;
    private Integer topK;

    /**
     * Constructor for the GenerationConfigBuilder.
     *
     * @param generateContentRequestBuilder The parent
     * GenerateContentRequestBuilder instance.
     */
    public GenerationConfigBuilder(
            GenerateContentRequestBuilder generateContentRequestBuilder) {
        this.generateContentRequestBuilder = generateContentRequestBuilder;
    }

    /**
     * Adds a stop sequence to the list of stop sequences for the generation
     * config.
     *
     * @param stopSequence The stop sequence to add.
     * @return The builder instance for method chaining.
     */
    public GenerationConfigBuilder withStopSequence(String stopSequence) {
        if (this.stopSequences == null) {
            this.stopSequences = new ArrayList<>();
        }
        this.stopSequences.add(stopSequence);
        return this;
    }

    /**
     * Sets the response MIME type for the generation config.
     *
     * @param responseMimeType The response MIME type.
     * @return The builder instance for method chaining.
     */
    public GenerationConfigBuilder withResponseMimeType(
            String responseMimeType) {
        this.responseMimeType = responseMimeType;
        return this;
    }

    /**
     * Sets the response schema for the generation config.
     *
     * @param responseSchema The response schema.
     * @return The builder instance for method chaining.
     */
    public GenerationConfigBuilder withResponseSchema(Schema responseSchema) {
        this.responseSchema = responseSchema;
        return this;
    }

    /**
     * Creates a new {@link SchemaBuilder} for building the response schema.
     *
     * @return A new {@link SchemaBuilder} instance.
     */
    public SchemaBuilder withResponseSchema() {
        return new SchemaBuilder(this);
    }

    /**
     * Sets the candidate count for the generation config.
     *
     * @param candidateCount The candidate count.
     * @return The builder instance for method chaining.
     */
    public GenerationConfigBuilder withCandidateCount(Integer candidateCount) {
        this.candidateCount = candidateCount;
        return this;
    }

    /**
     * Sets the maximum output tokens for the generation config.
     *
     * @param maxOutputTokens The maximum output tokens.
     * @return The builder instance for method chaining.
     */
    public GenerationConfigBuilder withMaxOutputTokens(
            Integer maxOutputTokens) {
        this.maxOutputTokens = maxOutputTokens;
        return this;
    }

    /**
     * Sets the temperature for the generation config.
     *
     * @param temperature The temperature.
     * @return The builder instance for method chaining.
     */
    public GenerationConfigBuilder withTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    /**
     * Sets the topP value for the generation config.
     *
     * @param topP The topP value.
     * @return The builder instance for method chaining.
     */
    public GenerationConfigBuilder withTopP(Double topP) {
        this.topP = topP;
        return this;
    }

    /**
     * Sets the topK value for the generation config.
     *
     * @param topK The topK value.
     * @return The builder instance for method chaining.
     */
    public GenerationConfigBuilder withTopK(Integer topK) {
        this.topK = topK;
        return this;
    }

    /**
     * Builds a {@link GenerationConfig} instance based on the configured
     * parameters.
     *
     * @return The built {@link GenerationConfig} instance.
     */
    public GenerationConfig build() {
        return GenerationConfig.builder()
                .withStopSequences(stopSequences)
                .withResponseMimeType(responseMimeType)
                .withResponseSchema(responseSchema)
                .withCandidateCount(candidateCount)
                .withMaxOutputTokens(maxOutputTokens)
                .withTemperature(temperature)
                .withTopP(topP)
                .withTopK(topK)
                .build();
    }

    /**
     * Sets the built {@link GenerationConfig} instance as the generation config
     * in the parent builder.
     *
     * @return The parent {@link GenerateContentRequestBuilder} instance for
     * method chaining.
     */
    public GenerateContentRequestBuilder and() {
        generateContentRequestBuilder.generationConfig = build();
        return generateContentRequestBuilder;
    }
}