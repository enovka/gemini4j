// com.enovka.gemini4j.resource.impl.TokensResourceImpl
package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.model.request.TokensRequest;
import com.enovka.gemini4j.model.response.TokensResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.request.TokensRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.TokensResource;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;
import org.apache.hc.core5.http.ContentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
     * Executes a token counting request asynchronously using the specified Gemini model.
     *
     * @param request  The {@link TokensRequest} containing the content to be tokenized and other parameters.
     * @param callback The callback to handle the asynchronous response.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which can be
     *         used to cancel the request.
     * @throws ResourceException If an error occurs during request setup.
     * @since 0.2.0
     */
    public CompletableFuture<TokensResponse> executeAsync(TokensRequest request, AsyncCallback<TokensResponse> callback) throws ResourceException {
        String endpoint = String.format(COUNT_TOKENS_ENDPOINT, geminiClient.getModelName());
        CompletableFuture<TokensResponse> future = new CompletableFuture<>();

        try {
            httpClient.postAsync(buildEndpointUrl(endpoint), jsonService.serialize(request), buildHeaders(), ContentType.APPLICATION_JSON, new AsyncCallback<>() {
                @Override
                public void onSuccess(HttpResponse httpResponse) {
                    try {
                        future.complete(deserializeResponse(httpResponse, TokensResponse.class));
                    } catch (ResourceException e) {
                        future.completeExceptionally(e);
                    }
                }

                @Override
                public void onError(Throwable exception) {
                    future.completeExceptionally(new ResourceException("Error counting tokens", exception));
                }

                @Override
                public void onCanceled() {
                    future.cancel(true);
                }
            });
        } catch (JsonException e) {
            throw new ResourceException(e);
        }

        return future;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public TokensRequestBuilder countTokensBuilder(String text) {
        return TokensRequestBuilder.builder().withUserContent(text);
    }

    private Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>(geminiClient.buildAuthHeaders());
        headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        return headers;
    }
}