package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Metadata on the usage of the cached content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
public class UsageMetadata {

    /**
     * Total number of tokens that the cached content consumes.
     */
    @JsonProperty("totalTokenCount")
    private Integer totalTokenCount;
    @JsonProperty("promptTokenCount")
    private Integer promptTokenCount;
    @JsonProperty("cachedContentTokenCount")
    private Integer cachedContentTokenCount;
    @JsonProperty("candidatesTokenCount")
    private Integer candidatesTokenCount;

}