// com.enovka.gemini4j.resource.spec.TokensResource
package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.model.request.TokensRequest;
import com.enovka.gemini4j.model.response.TokensResponse;
import com.enovka.gemini4j.resource.builder.request.TokensRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.Resource;

import java.util.concurrent.CompletableFuture;

/**
 * Interface defining the contract for interacting with the Count Tokens resource of the Gemini
 * API. This resource provides methods for counting the number of tokens in text and other types
 * of content using various Gemini models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.3
 */
public interface TokensResource extends Resource {

    /**
     * Executes a token counting request using the specified Gemini model.
     *
     * @param request The {@link TokensRequest} containing the content to be tokenized and other
     *                parameters.
     * @return A {@link TokensResponse} containing the token count.
     * @throws ResourceException If an error occurs during the token counting process.
     * @since 0.1.3
     */
    TokensResponse execute(TokensRequest request) throws ResourceException;

    /**
     * Executes a token counting request asynchronously using the specified Gemini model.
     *
     * @param request  The {@link TokensRequest} containing the content to be tokenized and other parameters.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    CompletableFuture<TokensResponse> executeAsync(TokensRequest request, AsyncCallback<TokensResponse> callback) throws ResourceException;

    /**
     * Creates a new {@link TokensRequestBuilder} instance to build a {@link TokensRequest} for
     * counting tokens in the given text using the specified Gemini model.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * String text = "This is a test sentence.";
     * TokensRequestBuilder builder = countTokensResource.countTokensBuilder(text);
     * TokensRequest request = builder.build();
     * TokensResponse response = countTokensResource.execute(request);
     * int tokenCount = response.getTotalTokens();
     * System.out.println("Token count: " + tokenCount);
     * }</pre>
     *
     * @param text The text to be tokenized.
     * @return A new {@link TokensRequestBuilder} instance.
     */
    TokensRequestBuilder countTokensBuilder(String text);
}