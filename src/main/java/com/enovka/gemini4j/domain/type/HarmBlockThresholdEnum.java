package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the harm probability thresholds for content blocking.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum HarmBlockThresholdEnum {

    /**
     * The harm block threshold is unspecified.
     */
    @JsonProperty("HARM_BLOCK_THRESHOLD_UNSPECIFIED")
    HARM_BLOCK_THRESHOLD_UNSPECIFIED,

    /**
     * Block content with a harm probability of LOW or higher.
     */
    @JsonProperty("BLOCK_LOW_AND_ABOVE")
    BLOCK_LOW_AND_ABOVE,

    /**
     * Block content with a harm probability of MEDIUM or higher.
     */
    @JsonProperty("BLOCK_MEDIUM_AND_ABOVE")
    BLOCK_MEDIUM_AND_ABOVE,

    /**
     * Block only content with a HIGH harm probability.
     */
    @JsonProperty("BLOCK_ONLY_HIGH")
    BLOCK_ONLY_HIGH,

    /**
     * Do not block any content based on harm probability.
     */
    @JsonProperty("BLOCK_NONE")
    BLOCK_NONE
}