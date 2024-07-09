package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Attribution for a source that contributed to an answer.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroundingAttribution {

    /**
     * Output only. Identifier for the source contributing to this attribution.
     */
    @JsonProperty("sourceId")
    private AttributionSourceId sourceId;

    /**
     * Grounding source content that makes up this attribution.
     */
    @JsonProperty("content")
    private Content content;

}