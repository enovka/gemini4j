package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A citation to a source for a portion of a specific response.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CitationSource {

    /**
     * Optional. Start of segment of the response that is attributed to this
     * source.
     * <p>
     * Index indicates the start of the segment, measured in bytes.
     */
    @JsonProperty("startIndex")
    private Integer startIndex;

    /**
     * Optional. End of the attributed segment, exclusive.
     */
    @JsonProperty("endIndex")
    private Integer endIndex;

    /**
     * Optional. URI that is attributed as a source for a portion of the text.
     */
    @JsonProperty("uri")
    private String uri;

    /**
     * Optional. License for the GitHub project that is attributed as a source
     * for segment.
     * <p>
     * License info is required for code citations.
     */
    @JsonProperty("license")
    private String license;

}