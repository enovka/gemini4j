package com.enovka.gemini4j.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the harm probability thresholds that determine when the Gemini API
 * should block content based on its safety assessment. These thresholds allow
 * developers to control the sensitivity of the content filtering system,
 * specifying the minimum harm probability level that triggers content blocking.
 * By choosing the appropriate `HarmBlockThresholdEnum` value, developers can
 * fine-tune the balance between safety and permissiveness in their
 * applications.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum HarmBlockThresholdEnum {

    /**
     * **Unspecified Harm Block Threshold:** The harm block threshold is
     * unspecified. This value should not be used. Always choose a specific
     * threshold from the other options (`BLOCK_LOW_AND_ABOVE`,
     * `BLOCK_MEDIUM_AND_ABOVE`, `BLOCK_ONLY_HIGH`, or `BLOCK_NONE`) to ensure
     * predictable behavior.
     */
    @JsonProperty("HARM_BLOCK_THRESHOLD_UNSPECIFIED")
    HARM_BLOCK_THRESHOLD_UNSPECIFIED,

    /**
     * **Block Low and Above:** Block content with a harm probability of `LOW`,
     * `MEDIUM`, or `HIGH`. This threshold enforces a stricter level of content
     * filtering, blocking content with even a low probability of harm.
     */
    @JsonProperty("BLOCK_LOW_AND_ABOVE")
    BLOCK_LOW_AND_ABOVE,

    /**
     * **Block Medium and Above:** Block content with a harm probability of
     * `MEDIUM` or `HIGH`. This threshold offers a moderate level of content
     * filtering, blocking content with a moderate to high probability of harm.
     */
    @JsonProperty("BLOCK_MEDIUM_AND_ABOVE")
    BLOCK_MEDIUM_AND_ABOVE,

    /**
     * **Block Only High:** Block only content with a `HIGH` harm probability.
     * This threshold provides a more permissive level of content filtering,
     * allowing content with low and medium harm probabilities.
     */
    @JsonProperty("BLOCK_ONLY_HIGH")
    BLOCK_ONLY_HIGH,

    /**
     * **Block None:** Do not block any content based on harm probability. This
     * threshold disables content blocking based on the safety assessment,
     * allowing all content to be returned regardless of its harm probability.
     */
    @JsonProperty("BLOCK_NONE")
    BLOCK_NONE
}