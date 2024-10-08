package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.request.TokensRequest;
import com.enovka.gemini4j.model.response.TokensResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.request.TokensRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.TokensResource;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;

import java.util.List;

/**
 * Implementation of the {@link TokensResource} interface for interacting with the Count
 * Tokens resource of the Gemini API. This resource provides methods for counting the
 * number of tokens in text and other types of content using various Gemini models.  This
 * implementation uses the {@code executeRequest} method from {@link AbstractResource} for
 * making API calls and correctly handles the updated {@link TokensRequest} and
 * {@link TokensResponse} objects.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.3
 */
public class TokensResourceImpl extends AbstractResource<TokensResource> implements TokensResource {

    private static final String COUNT_TOKENS_ENDPOINT = "%s:countTokens";
    private static final List<SupportedModelMethod> SUPPORTED_METHODS = List.of(SupportedModelMethod.COUNT_TOKENS);

    /**
     * Constructs a new TokensResourceImpl with the required GeminiClient.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API communication.
     * @since 0.2.0
     */
    public TokensResourceImpl(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public List<SupportedModelMethod> getModelMethodList() {
        return SUPPORTED_METHODS;
    }


    /**
     * {@inheritDoc}
     * @since 0.1.3
     */
    @Override
    public TokensResponse execute(TokensRequest request) throws ResourceException {
        String endpoint = String.format(COUNT_TOKENS_ENDPOINT, geminiClient.getModelName());
        return executeRequest("POST", endpoint, request, TokensResponse.class); // Use executeRequest
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TokensRequestBuilder countTokensBuilder(String text) {
        return TokensRequestBuilder.builder().withUserContent(text);
    }
}