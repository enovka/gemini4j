package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.response.internal.GenerateContentResponse;
import com.enovka.gemini4j.model.response.GenerateResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.request.GenerateRequestBuilder;
import com.enovka.gemini4j.resource.builder.request.GenerateTextRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.base.AbstractMultiTurnConversationResource;
import com.enovka.gemini4j.resource.spec.base.AbstractResource;
import com.enovka.gemini4j.resource.spec.GenerateResource;

import java.util.List;

/**
 * Implementation of the {@link GenerateResource} interface for interacting
 * with the Generation resource of the Gemini API. This class provides methods
 * for generating text, chat messages, and other content using the Gemini Pro,
 * Gemini Flash, and other compatible models.  It leverages the functionality
 * provided by the {@link AbstractResource} base class for common HTTP operations
 * and error handling.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class GenerateResourceImpl extends AbstractMultiTurnConversationResource<GenerateContentResponse>
        implements GenerateResource {

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

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public GenerateResponse execute(GenerateRequest request) throws ResourceException {
        try {
            request = prepareMultiTurnRequest(request);
            String endpoint = String.format(GENERATE_CONTENT_ENDPOINT, geminiClient.getModelName());
            GenerateContentResponse response = executeRequest("POST", endpoint, request, GenerateContentResponse.class);
            multiTurnConversation.addContent(response.getCandidates().get(0).getContent());
            return GenerateResponse.builder().withGenerateContentResponse(response).build();
        } catch (ResourceException e) {
            throw new ResourceException("Error generating content: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public GenerateRequestBuilder generateContentBuilder(String userInput) {
        return GenerateRequestBuilder.builder().withUserContent(userInput);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public GenerateTextRequestBuilder generateTextBuilder(String userInput) {
        return GenerateTextRequestBuilder.builder(this).withUserInput(userInput);
    }

    @Override
    public List<SupportedModelMethod> getModelMethodList() {
        return SUPPORTED_METHODS;
    }
}