package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.HarmBlockThresholdEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the threshold for blocking content based on the probability of
 * harm, as assessed by the Gemini API's safety system. This class allows you to
 * define the level of sensitivity for blocking content, determining at which
 * probability level the content should be blocked. By configuring the
 * `HarmBlockThreshold`, developers can control the strictness of the content
 * filtering applied to the generated responses.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class HarmBlockThreshold {

    /**
     * The specific harm block threshold, represented by the
     * {@link HarmBlockThresholdEnum}. For example, setting this to
     * {@link HarmBlockThresholdEnum#BLOCK_MEDIUM_AND_ABOVE} will block content
     * with medium or high probability of harm.
     */
    @JsonProperty("harmBlockThreshold")
    private HarmBlockThresholdEnum harmBlockThreshold;
}