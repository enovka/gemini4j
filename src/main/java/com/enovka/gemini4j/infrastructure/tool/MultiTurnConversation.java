package com.enovka.gemini4j.infrastructure.tool;

import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;

import java.util.ArrayList;
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
     * disable it.
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
     * Returns the latest relevant context from the conversation history,
     * considering the model's maximum context window size.
     *
     * @param maxContextTokens The maximum number of tokens allowed in the
     * context window.
     * @return The latest relevant context as a list of Content objects.
     */
    public List<Content> getLatestContext(int maxContextTokens) {
        if (!contextTrackingEnabled || conversationHistory.isEmpty()) {
            return new ArrayList<>();
        }

        List<Content> latestContext = new ArrayList<>();
        int currentTokenCount = 0;

        // Iterate through the conversation history in reverse order
        for (int i = conversationHistory.size() - 1; i >= 0; i--) {
            Content content = conversationHistory.get(i);
            int contentTokenCount = 0;

            // Calculate the token count for the current Content object
            for (Part part : content.getParts()) {
                if (part.getText() != null) {
                    contentTokenCount += part.getText()
                            .length(); // Approximate token count
                }
            }

            // Add the Content object to the latest context if it fits within the token limit
            if (currentTokenCount + contentTokenCount <= maxContextTokens) {
                latestContext.add(0,
                        content); // Add to the beginning to maintain order
                currentTokenCount += contentTokenCount;
            } else {
                break; // Stop if adding more content would exceed the token limit
            }
        }

        return latestContext;
    }
}