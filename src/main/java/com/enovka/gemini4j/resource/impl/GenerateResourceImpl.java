package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.request.spec.Request;
import com.enovka.gemini4j.model.response.internal.GenerateContentResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.GenerateResource;
import com.enovka.gemini4j.resource.spec.base.AbstractMultiTurnConversationResource;
import com.enovka.gemini4j.resource.spec.base.AsyncResponse;
import org.apache.hc.core5.http.ContentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link GenerateResource} interface for interacting with the Generation
 * resource of the Gemini API. This class provides methods for generating text, chat messages, and
 * other content using the Gemini Pro, Gemini Flash, and other compatible models. It leverages the
 * functionality provided by the {@link AbstractMultiTurnConversationResource} base class for
 * common HTTP operations and error handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class GenerateResourceImpl extends AbstractMultiTurnConversationResource<GenerateRequest, GenerateContentResponse> implements GenerateResource {

    private static final String GENERATE_CONTENT_ENDPOINT = "%s:generateContent";
    private static final List<SupportedModelMethod> SUPPORTED_METHODS = List.of(SupportedModelMethod.GENERATE_CONTENT);

    /**
     * Constructs a new GenerateResourceImpl with the required GeminiClient.
     *
     * @param geminiClient The Gemini client for API communication.
     * @since 0.2.0
     */
    public GenerateResourceImpl(GeminiClient geminiClient) {
        super(geminiClient);
    }

    @Override
    protected String getEndpointForRequest(Request request) {
        return GENERATE_CONTENT_ENDPOINT;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public List<SupportedModelMethod> getSupportedMethods() {
        return SUPPORTED_METHODS;
    }

    private Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>(geminiClient.buildAuthHeaders());
        headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        return headers;
    }

    @Override
    public GenerateContentResponse execute(GenerateRequest request) throws ResourceException {
        return this.post(request, GenerateContentResponse.class);
    }

    @Override
    public AsyncResponse<GenerateContentResponse> executeAsync(GenerateRequest request) {
        return this.postAsync(request, GenerateContentResponse.class);
    }
}