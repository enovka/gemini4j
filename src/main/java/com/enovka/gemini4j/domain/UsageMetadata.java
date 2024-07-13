package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class UsageMetadata {

    /**
     * Total number of tokens that the cached content consumes.
     */
    @JsonProperty("totalTokenCount")
    private Integer totalTokenCount;
    private Integer promptTokenCount;
    private Integer cachedContentTokenCount;
    private Integer candidatesTokenCount;

}