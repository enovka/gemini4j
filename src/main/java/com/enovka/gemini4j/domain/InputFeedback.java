package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Feedback related to the input data used to answer the question, as opposed to
 * model-generated response to the question.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InputFeedback {

    /**
     * Ratings for safety of the input. There is at most one rating per
     * category.
     */
    @JsonProperty("safetyRatings")
    private List<SafetyRating> safetyRatings;

    /**
     * Optional. If set, the input was blocked and no candidates are returned.
     * Rephrase your input.
     */
    @JsonProperty("blockReason")
    private BlockReason blockReason;

}