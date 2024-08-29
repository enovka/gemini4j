package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.CountTokensRequest;
import com.enovka.gemini4j.domain.response.CountTokensResponse;
import com.enovka.gemini4j.resource.builder.CountTokensRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;

/**
 * Interface defining the contract for interacting with the Count Tokens
 * resource of the Gemini API. This resource provides methods for counting the
 * number of tokens in text and other types of content using various Gemini
 * models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.3
 */
public interface CountTokensResource {

    /**
     * Returns the associated {@link GeminiClient}.
     *
     * @return The {@link GeminiClient} instance.
     */
    GeminiClient getGeminiClient();

    /**
     * Executes a token counting request using the specified Gemini model.
     *
     * @param request The {@link CountTokensRequest} containing the content to
     * be tokenized and other parameters.
     * @return A {@link CountTokensResponse} containing the token count.
     * @throws ResourceException If an error occurs during the token counting
     * process.
     * @since 0.1.3
     */
    CountTokensResponse execute(CountTokensRequest request)
            throws ResourceException;

    /**
     * Creates a new {@link CountTokensRequestBuilder} instance to build a
     * {@link CountTokensRequest} for counting tokens in the given text using
     * the specified Gemini model.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * String text = "This is a test sentence.";
     * CountTokensRequestBuilder builder = countTokensResource.countTokensBuilder(text);
     * CountTokensRequest request = builder.build();
     * CountTokensResponse response = countTokensResource.execute(request);
     * int tokenCount = response.getTotalTokens();
     * System.out.println("Token count: " + tokenCount);
     * }</pre>
     *
     * @param text The text to be tokenized.
     * @return A new {@link CountTokensRequestBuilder} instance.
     */
    CountTokensRequestBuilder countTokensBuilder(String text);
}