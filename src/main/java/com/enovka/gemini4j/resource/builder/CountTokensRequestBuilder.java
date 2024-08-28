package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.request.CountTokensRequest;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Builder for creating {@link CountTokensRequest} instances in a fluent and
 * intuitive way. This builder provides methods to configure all aspects of the
 * request, including the content to be tokenized and an optional
 * {@link GenerateContentRequest} for more comprehensive token counting.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.3
 */
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Accessors(chain = true)
public class CountTokensRequestBuilder {

    private final GeminiClient geminiClient;
    private List<Content> contents;
    private GenerateContentRequest generateContentRequest;

    /**
     * Private constructor to enforce builder pattern.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    private CountTokensRequestBuilder(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    /**
     * Creates a new instance of the CountTokensRequestBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     * @return A new CountTokensRequestBuilder instance.
     */
    public static CountTokensRequestBuilder builder(
            GeminiClient geminiClient) {
        return new CountTokensRequestBuilder(geminiClient);
    }

    /**
     * Adds a content to be tokenized.
     *
     * @param content The content to be tokenized.
     * @return The builder instance for method chaining.
     */
    public CountTokensRequestBuilder withContent(Content content) {
        if (this.contents == null) {
            this.contents = new ArrayList<>();
        }
        this.contents.add(content);
        return this;
    }

    /**
     * Adds a text content to be tokenized.
     *
     * @param text The text content to be tokenized.
     * @return The builder instance for method chaining.
     */
    public CountTokensRequestBuilder withTextContent(String text) {
        if (this.contents == null) {
            this.contents = new ArrayList<>();
        }
        this.contents.add(Content.builder()
                .withParts(List.of(Part.builder().withText(text).build()))
                .build());
        return this;
    }

    /**
     * Sets the {@link GenerateContentRequest} for token counting. If provided,
     * the token count will include the tokens from the
     * {@link GenerateContentRequest}, including the prompt, system
     * instructions, and function declarations.
     *
     * @param generateContentRequest The GenerateContentRequest to use for token
     * counting.
     * @return The builder instance for method chaining.
     */
    public CountTokensRequestBuilder withGenerateContentRequest(
            GenerateContentRequest generateContentRequest) {
        this.generateContentRequest = generateContentRequest;
        return this;
    }

    /**
     * Configures the {@link GenerateContentRequest} for token counting using a
     * consumer.
     *
     * @param generateContentRequestConfigurer A consumer to configure the
     * GenerateContentRequest.
     * @return The builder instance for method chaining.
     */
    public CountTokensRequestBuilder withGenerateContentRequest(
            Consumer<GenerateContentRequestBuilder> generateContentRequestConfigurer) {
        GenerateContentRequestBuilder generateContentRequestBuilder
                = GenerateContentRequestBuilder.builder(geminiClient);
        generateContentRequestConfigurer.accept(generateContentRequestBuilder);
        this.generateContentRequest
                = generateContentRequestBuilder.buildWithoutUserInput();
        return this;
    }

    /**
     * Builds the {@link CountTokensRequest} instance based on the configured
     * parameters.
     *
     * @return The built {@link CountTokensRequest} instance.
     */
    public CountTokensRequest build() {
        return CountTokensRequest.builder()
                .withContents(contents)
                .withGenerateContentRequest(generateContentRequest)
                .withModel(geminiClient.getModel())
                .build();
    }
}