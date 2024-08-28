package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.CountTokensRequest;
import com.enovka.gemini4j.domain.response.CountTokensResponse;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.builder.CountTokensRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.CountTokensResource;

/**
 * Implementation of the {@link CountTokensResource} interface for interacting
 * with the Count Tokens resource of the Gemini API. This class provides methods
 * for counting the number of tokens in text and other types of content using
 * various Gemini models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.3
 */
public class CountTokensResourceImpl
        extends AbstractResource<CountTokensResource>
        implements CountTokensResource {

    private static final String COUNT_TOKENS_ENDPOINT = "%s:countTokens";

    /**
     * Constructs a new CountTokensResourceImpl with the required GeminiClient
     * and JsonService.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     * @param jsonService The {@link JsonService} instance to use for JSON
     * serialization/deserialization.
     */
    public CountTokensResourceImpl(GeminiClient geminiClient,
                                   JsonService jsonService) {
        super(geminiClient, jsonService);
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
     */
    @Override
    public CountTokensResponse countTokens(CountTokensRequest request)
            throws ResourceException {
        String endpoint = String.format(COUNT_TOKENS_ENDPOINT,
                geminiClient.getModel());
        return executePostRequest(endpoint, request,
                CountTokensResponse.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountTokensRequestBuilder countTokensBuilder(String text) {
        return CountTokensRequestBuilder.builder(geminiClient)
                .withTextContent(text);
    }
}