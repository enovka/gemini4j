package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.Candidate;
import com.enovka.gemini4j.domain.PromptFeedback;
import com.enovka.gemini4j.domain.UsageMetadata;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * response from the model supporting multiple candidates.
 * <p>
 * Note on safety ratings and content filtering. they are reported for both
 * prompt in GenerateContentResponse.prompt_feedback and for each candidate in
 * finishReason and in safetyRatings. the API contract is that:
 * <p>
 * - either all requested candidates are returned or no candidates at all - no
 * candidates are returned only if there was something wrong with the prompt
 * (see promptFeedback) - feedback on each candidate is reported on finishReason
 * and safetyRatings.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
public class GenerateContentResponse extends AbstractGeminiResponse {

    /**
     * Candidate responses from the model.
     */
    @JsonProperty("candidates")
    private List<Candidate> candidates;

    /**
     * Returns the prompt's feedback related to the content filters.
     */
    @JsonProperty("promptFeedback")
    private PromptFeedback promptFeedback;

    /**
     * Output only. Metadata on the generation requests' token usage.
     */
    @JsonProperty("usageMetadata")
    private UsageMetadata usageMetadata;

    public GenerateContentResponse() {
    }
}