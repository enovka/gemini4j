package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Content filtering metadata associated with processing a single request.
 * <p>
 * ContentFilter contains a reason and an optional supporting string. The reason
 * may be unspecified.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContentFilter {

    /**
     * The reason content was blocked during request processing.
     */
    @JsonProperty("reason")
    private BlockedReason reason;

    /**
     * A string that describes the filtering behavior in more detail.
     */
    @JsonProperty("message")
    private String message;

}