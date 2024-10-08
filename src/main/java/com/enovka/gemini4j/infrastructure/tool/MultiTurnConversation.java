package com.enovka.gemini4j.infrastructure.tool;

import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.Part;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Manages multi-turn conversations by storing conversation history and
 * providing methods for context tracking and retrieval.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public class MultiTurnConversation extends BaseClass {

    private boolean contextTrackingEnabled;
    private List<Content> conversationHistory;

    /**
     * Constructs a new MultiTurnConversation with context tracking disabled by
     * default and an empty conversation history.
     */
    public MultiTurnConversation() {
        this.contextTrackingEnabled = false;
        this.conversationHistory = new ArrayList<>();
    }

    /**
     * Enables or disables multi-turn conversation tracking.
     *
     * @param value {@code true} to enable context tracking, {@code false} to
     *              disable it.
     */
    public void enableMultiTurnConversation(boolean value) {
        this.contextTrackingEnabled = value;
    }

    /**
     * Checks if multi-turn conversation tracking is enabled.
     *
     * @return {@code true} if context tracking is enabled, {@code false}
     * otherwise.
     */
    public boolean isMultiTurnConversationEnabled() {
        return contextTrackingEnabled;
    }

    /**
     * Adds a new user input or model response to the conversation history if
     * context tracking is enabled.
     *
     * @param content The Content object to add to the history.
     */
    public void addContent(Content content) {
        if (contextTrackingEnabled) {
            this.conversationHistory.add(content);
        }
    }

    /**
     * Returns the entire conversation history as a list of Content objects.
     *
     * @return The conversation history.
     */
    public List<Content> getConversationHistory() {
        return new ArrayList<>(conversationHistory);
    }

    /**
     * Sets the conversation history to the provided list.
     *
     * @param conversationHistoryList The new conversation history.
     */
    public void setConversationHistory(List<Content> conversationHistoryList) {
        this.conversationHistory = conversationHistoryList != null
                ? new ArrayList<>(conversationHistoryList) : new ArrayList<>();
    }

    /**
     * Clears the conversation history.
     */
    public void clearConversationHistory() {
        this.conversationHistory.clear();
    }

    /**
     * Returns the latest relevant context from the conversation history, considering the model's
     * maximum context window size. This method now accurately calculates token counts for different
     * Part data types and efficiently manages context retrieval.
     *
     * @param maxContextTokens The maximum number of tokens allowed in the context window.
     * @return The latest relevant context as a list of Content objects.
     */
    public List<Content> getLatestContext(int maxContextTokens) {
        if (!contextTrackingEnabled || conversationHistory.isEmpty()) {
            return new ArrayList<>();
        }

        LinkedList<Content> latestContext = new LinkedList<>();
        int currentTokenCount = 0;

        for (int i = conversationHistory.size() - 1; i >= 0; i--) {
            Content content = conversationHistory.get(i);
            int contentTokenCount = calculateContentTokenCount(content);

            if (currentTokenCount + contentTokenCount <= maxContextTokens) {
                latestContext.addFirst(content);
                currentTokenCount += contentTokenCount;
            } else {
                break;
            }
        }

        return latestContext;
    }

    /**
     * Calculates the token count for a given Content object. This method now correctly handles
     * different Part data types and provides a more accurate token count estimation.
     *
     * @param content The Content object to calculate the token count for.
     * @return The estimated token count for the Content object.
     */
    private int calculateContentTokenCount(Content content) {
        int contentTokenCount = 0;
        for (Part part : content.getParts()) {
            if (part.getText() != null) {
                contentTokenCount += part.getText().length(); // Approximate token count for text
            } else if (part.getInlineData() != null) {
                contentTokenCount += part.getInlineData().getData().length() / 4; // Estimate for base64 encoded data
            }
        }
        return contentTokenCount;
    }

}