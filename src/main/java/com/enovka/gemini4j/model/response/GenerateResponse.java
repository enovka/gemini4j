package com.enovka.gemini4j.model.response;

import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import com.enovka.gemini4j.model.Candidate;
import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.Part;
import com.enovka.gemini4j.model.UsageMetadata;
import com.enovka.gemini4j.model.response.internal.GenerateContentResponse;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a result from the Gemini API, providing both the raw
 * {@link GenerateContentResponse} and convenient shortcuts for accessing common data.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Getter
@Builder(setterPrefix = "with", toBuilder = true)
public class GenerateResponse extends BaseClass {

    private GenerateContentResponse generateContentResponse;

    /**
     * Returns the first generated text content from the response, if available. This method
     * extracts the text content from the first {@link Candidate} in the
     * {@link GenerateContentResponse}, handling cases where the response, candidates, content, or
     * parts are null or empty.  It correctly accesses the `text` field of the `Part` object.
     *
     * @return The first generated text content, or null if not available.
     */
    public String getGeneratedText() {
        logDebug("Getting generated text from GenerateResponse.");
        if (generateContentResponse != null
                && generateContentResponse.getError() == null
                && generateContentResponse.getCandidates() != null
                && !generateContentResponse.getCandidates().isEmpty()) {

            Candidate candidate = generateContentResponse.getCandidates().get(0);
            if (candidate.getContent() != null
                    && candidate.getContent().getParts() != null
                    && !candidate.getContent().getParts().isEmpty()) {

                Part part = candidate.getContent().getParts().get(0);
                return part.getText();

            }
        }
        return null;
    }

    /**
     * Returns the first generated {@link Content} from the response, if available.  This method
     * extracts the content from the first {@link Candidate} in the
     * {@link GenerateContentResponse}, handling cases where the response or candidates are null
     * or empty.
     *
     * @return The first generated {@link Content}, or null if not available.
     */
    public Content getGeneratedContent() {
        logDebug("Getting generated content from GenerateResponse.");
        if (generateContentResponse != null
                && generateContentResponse.getError() == null
                && generateContentResponse.getCandidates() != null
                && !generateContentResponse.getCandidates().isEmpty()) {

            Candidate candidate = generateContentResponse.getCandidates().get(0);
            return candidate.getContent();
        }
        return null;
    }

    /**
     * Returns the usage metadata from the response, if available. This method extracts
     * the {@link UsageMetadata} from the {@link GenerateContentResponse}, handling cases
     * where the response or its error field is null.
     *
     * @return The {@link UsageMetadata}, or null if not available.
     */
    public UsageMetadata getUsageMetadata() {
        if (generateContentResponse != null
                && generateContentResponse.getError() == null) {
            return generateContentResponse.getUsageMetadata();
        }
        return null;
    }
}