package com.enovka.gemini4j.resource.spec.base;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.request.GenerateRequest;

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
     *                     communication.
     *                     serialization/deserialization.
     */
    public AbstractMultiTurnConversationResource(GeminiClient geminiClient) {
        super(geminiClient);
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
     * Prepares the {@link GenerateRequest} for multi-turn conversation
     * by adding the latest context and the user input to the request's
     * contents.
     *
     * @param request The GenerateRequest to prepare.
     * @return The prepared GenerateRequest with context and user input.
     */
    protected GenerateRequest prepareMultiTurnRequest(
            GenerateRequest request) {
        if (isMultiTurnConversationEnabled()) {
            request.setContents(new ArrayList<>(request.getContents()));
            multiTurnConversation.addContent(request.getContents()
                    .get(request.getContents().size() - 1));
        }
        return request;
    }
}