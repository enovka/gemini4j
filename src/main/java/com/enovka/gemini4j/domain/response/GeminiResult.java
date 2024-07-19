package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.Candidate;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import lombok.Builder;

/**
 * Represents a result from the Gemini API, providing both the raw
 * {@link GenerateContentResponse} and convenient shortcuts for accessing common
 * data.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */

@Builder(setterPrefix = "with", toBuilder = true)
public class GeminiResult extends BaseClass {

    private GenerateContentResponse generateContentResponse;

    /**
     * Returns the first generated text content from the response, if
     * available.
     *
     * @return The first generated text content, or null if not available.
     */
    public String getGeneratedText() {
        logDebug("Getting generated text from GeminiResult.");
        if (generateContentResponse != null
                && generateContentResponse.getError() == null
                && generateContentResponse.getCandidates() != null
                && !generateContentResponse.getCandidates().isEmpty()) {

            Candidate candidate = generateContentResponse.getCandidates()
                    .get(0);
            if (candidate.getContent() != null
                    && candidate.getContent().getParts() != null
                    && !candidate.getContent().getParts().isEmpty()) {

                Part part = candidate.getContent().getParts().get(0);
                if (part.getText() != null) {
                    return part.getText();
                }
            }
        }
        return null;
    }

    /**
     * Returns the first generated content from the response, if available.
     *
     * @return The first generated content, or null if not available.
     */
    public Content getGeneratedContent() {
        logDebug("Getting generated content from GeminiResult.");
        if (generateContentResponse != null
                && generateContentResponse.getError() == null
                && generateContentResponse.getCandidates() != null
                && !generateContentResponse.getCandidates().isEmpty()) {

            Candidate candidate = generateContentResponse.getCandidates()
                    .get(0);
            return candidate.getContent();
        }
        return null;
    }
}