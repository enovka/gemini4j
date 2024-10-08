package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.request.TokensRequest;
import com.enovka.gemini4j.model.response.TokensResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.request.TokensRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;
import com.enovka.gemini4j.resource.spec.TokensResource;

import java.util.List;

/**
 * Implementation of the {@link TokensResource} interface for interacting
 * with the Count Tokens resource of the Gemini API. This class provides methods
 * for counting the number of tokens in text and other types of content using
 * various Gemini models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.3
 */
public class TokensResourceImpl
        extends AbstractResource<TokensResource>
        implements TokensResource {

    private static final String COUNT_TOKENS_ENDPOINT = "%s:countTokens";
    private static final List<SupportedModelMethod> supportedMethods = List.of(
            SupportedModelMethod.COUNT_TOKENS);

    /**
     * Constructs a new TokensResourceImpl with the required GeminiClient
     * and JsonService.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     *                     communication.
     *                     serialization/deserialization.
     */
    public TokensResourceImpl(GeminiClient geminiClient) {
        super(geminiClient);
    }

    @Override
    public List<SupportedModelMethod> getModelMethodList() {
        return supportedMethods;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GeminiClient getGeminiClient() {
        return geminiClient;
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.1.3
     */
    @Override
    public TokensResponse execute(TokensRequest request)
            throws ResourceException {
        String endpoint = String.format(COUNT_TOKENS_ENDPOINT,
                geminiClient.getModelName());
        return executePostRequest(endpoint, request,
                TokensResponse.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TokensRequestBuilder countTokensBuilder(String text) {
        return TokensRequestBuilder.builder()
                .withUserContent(text);
    }
}