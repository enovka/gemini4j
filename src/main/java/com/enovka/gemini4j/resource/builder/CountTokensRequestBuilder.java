package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.request.CountTokensRequest;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.CountTokensResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.CountTokensResource;
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
    private final CountTokensResource countTokensResource;
    private List<Content> contents;
    private GenerateContentRequest generateContentRequest;

    /**
     * Constructor for the CountTokensRequestBuilder.
     *
     * @param countTokensResource The CountTokensResource instance to use for
     * API communication.
     * @since 0.1.3
     */
    public CountTokensRequestBuilder(CountTokensResource countTokensResource) {
        this.countTokensResource = countTokensResource;
        this.geminiClient = countTokensResource.getGeminiClient();
    }

    /**
     * Creates a new instance of the CountTokensRequestBuilder.
     *
     * @param countTokensResource The CountTokensResource instance to use for
     * API communication.
     * @return A new CountTokensRequestBuilder instance.
     * @since 0.1.3
     */
    public static CountTokensRequestBuilder builder(
            CountTokensResource countTokensResource) {
        return new CountTokensRequestBuilder(countTokensResource);
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

    /**
     * Executes the count tokens request and returns the response.
     *
     * @return The {@link CountTokensResponse} from the Gemini API.
     * @throws ResourceException If an error occurs during the API request.
     * @since 0.1.3
     */
    public CountTokensResponse execute() throws ResourceException {
        return countTokensResource.execute(build());
    }
}