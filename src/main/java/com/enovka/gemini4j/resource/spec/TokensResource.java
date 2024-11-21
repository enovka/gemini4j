package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.model.request.TokensRequest;
import com.enovka.gemini4j.model.response.TokensResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.AsyncResponse;
import com.enovka.gemini4j.resource.spec.base.Resource;

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
     * @return A {@link AsyncResponse} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @since 0.2.0
     */
    AsyncResponse<TokensResponse> executeAsync(TokensRequest request);
}