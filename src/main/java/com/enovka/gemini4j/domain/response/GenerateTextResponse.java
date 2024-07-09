package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.Candidate;
import com.enovka.gemini4j.domain.PromptFeedback;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Response from the model supporting multiple candidates.
 * <p>
 * Note on safety ratings and content filtering. They are reported for both
 * prompt in GenerateTextResponse.prompt_feedback and for each candidate in
 * finishReason and in safetyRatings. The API contract is that:
 * <p>
 * - Either all requested candidates are returned or no candidates at all - no
 * candidates are returned only if there was something wrong with the prompt
 * (see promptFeedback) - feedback on each candidate is reported on finishReason
 * and safetyRatings.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateTextResponse {

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

}