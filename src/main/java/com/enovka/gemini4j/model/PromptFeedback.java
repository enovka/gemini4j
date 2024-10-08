package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * A set of the feedback metadata the prompt specified in
 * GenerateRequest.content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
public class PromptFeedback {

    /**
     * Optional. If set, the prompt was blocked and no candidates are returned.
     * Rephrase your prompt.
     */
    @JsonProperty("blockReason")
    private BlockReason blockReason;

    /**
     * Ratings for safety of the prompt. There is at most one rating per
     * category.
     */
    @JsonProperty("safetyRatings")
    private List<SafetyRating> safetyRatings;

}