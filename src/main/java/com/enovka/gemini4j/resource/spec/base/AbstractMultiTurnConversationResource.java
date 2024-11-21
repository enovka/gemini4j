package com.enovka.gemini4j.resource.spec.base;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.tool.MultiTurnConversation;
import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.request.GenerateRequest;
import com.enovka.gemini4j.model.request.spec.Request;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for resource implementations that support multi-turn
 * conversations, providing shared functionality for context management.
 *
 * @param <I> The type of request object this resource handles.
 * @param <R> The type of response object this resource handles.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class AbstractMultiTurnConversationResource<I extends Request, R extends AbstractResponse> extends BaseAbstractResource<R, I> implements MultiTurnConversationAware {

    protected MultiTurnConversation multiTurnConversation = new MultiTurnConversation();

    /**
     * Constructs a new AbstractMultiTurnConversationResource with the
     * required GeminiClient.
     *
     * @param geminiClient The Gemini client for API communication.
     * @since 0.2.0
     */
    public AbstractMultiTurnConversationResource(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public void enableMultiTurnConversation(boolean value) {
        multiTurnConversation.enableMultiTurnConversation(value);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public boolean isMultiTurnConversationEnabled() {
        return multiTurnConversation.isMultiTurnConversationEnabled();
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public List<Content> getConversationHistory() {
        return multiTurnConversation.getConversationHistory();
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public void setConversationHistory(List<Content> conversationHistoryList) {
        multiTurnConversation.setConversationHistory(conversationHistoryList);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public void clearConversationHistory() {
        multiTurnConversation.clearConversationHistory();
    }

    /**
     * Prepares the request for a multi-turn conversation. This method
     * adds the current user input to the conversation history, ensuring
     * that the context is maintained across multiple turns.
     *
     * @param request The request object.
     * @return The updated request object with the conversation history.
     * @since 0.2.0
     */
    protected GenerateRequest prepareMultiTurnRequest(GenerateRequest request) {
        if (isMultiTurnConversationEnabled()) {
            List<Content> updatedContents = new ArrayList<>(request.getContents());
            multiTurnConversation.addContent(updatedContents.get(updatedContents.size() - 1));
            request.setContents(updatedContents);
        }
        return request;
    }
}