package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for resource implementations that support multi-turn
 * conversations, providing shared functionality for context management.
 *
 * @param <T> The type of response object this resource handles.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public abstract class AbstractMultiTurnConversationResource<T>
        extends AbstractResource<T> implements MultiTurnConversationAware {

    /**
     * Constructs a new AbstractMultiTurnConversationResource with the required
     * GeminiClient and JsonService.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     * @param jsonService The {@link JsonService} instance to use for JSON
     * serialization/deserialization.
     */
    public AbstractMultiTurnConversationResource(GeminiClient geminiClient,
                                                 JsonService jsonService) {
        super(geminiClient, jsonService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableMultiTurnConversation(boolean value) {
        multiTurnConversation.enableMultiTurnConversation(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMultiTurnConversationEnabled() {
        return multiTurnConversation.isMultiTurnConversationEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Content> getConversationHistory() {
        return multiTurnConversation.getConversationHistory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConversationHistory(List<Content> conversationHistoryList) {
        multiTurnConversation.setConversationHistory(conversationHistoryList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearConversationHistory() {
        multiTurnConversation.clearConversationHistory();
    }

    /**
     * Prepares the {@link GenerateContentRequest} for multi-turn conversation
     * by adding the latest context and the user input to the request's
     * contents.
     *
     * @param request The GenerateContentRequest to prepare.
     * @return The prepared GenerateContentRequest with context and user input.
     */
    protected GenerateContentRequest prepareMultiTurnRequest(
            GenerateContentRequest request) {
        if (isMultiTurnConversationEnabled()) {
            List<Content> latestContext
                    = multiTurnConversation.getLatestContext(
                    getModelTool().getModel(geminiClient.getModel())
                            .getInputTokenLimit());
            request.setContents(new ArrayList<>(request.getContents()));
            request.getContents().addAll(0, new ArrayList<>(latestContext));
            multiTurnConversation.addContent(request.getContents()
                    .get(request.getContents().size() - 1));
        }
        return request;
    }
}