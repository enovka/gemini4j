package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Passage included inline with a grounding configuration. This class
 * represents a passage that is provided inline with a grounding
 * configuration in the Gemini API.
 *
 * <p>It includes the identifier of the passage and its content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class GroundingPassage {

    /**
     * Identifier for the passage for attributing this passage in grounded
     * answers.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Content of the passage.
     */
    @JsonProperty("content")
    private Content content;
}