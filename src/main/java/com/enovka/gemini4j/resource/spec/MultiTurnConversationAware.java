package com.enovka.gemini4j.resource.spec;

import com.enovka.gemini4j.domain.Content;

import java.util.List;

/**
 * Interface defining the contract for resources that support multi-turn
 * conversations.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public interface MultiTurnConversationAware {

    /**
     * Enables or disables multi-turn conversation tracking for this resource.
     *
     * @param value {@code true} to enable context tracking, {@code false} to
     * disable it.
     */
    void enableMultiTurnConversation(boolean value);

    /**
     * Checks if multi-turn conversation tracking is enabled for this resource.
     *
     * @return {@code true} if context tracking is enabled, {@code false}
     * otherwise.
     */
    boolean isMultiTurnConversationEnabled();

    /**
     * Returns the entire conversation history for this resource.
     *
     * @return The conversation history as a list of Content objects.
     */
    List<Content> getConversationHistory();

    /**
     * Sets the conversation history for this resource.
     *
     * @param conversationHistoryList The new conversation history.
     */
    void setConversationHistory(List<Content> conversationHistoryList);

    /**
     * Clears the conversation history for this resource.
     */
    void clearConversationHistory();
}