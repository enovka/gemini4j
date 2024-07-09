package com.enovka.gemini4j.domain.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the states for the lifecycle of a Chunk.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum StateEnum {

    /**
     * The state of the Chunk is unspecified. This is the default value.
     */
    @JsonProperty("STATE_UNSPECIFIED")
    STATE_UNSPECIFIED,

    /**
     * The Chunk is currently being processed, including embedding and vector
     * storage.
     */
    @JsonProperty("STATE_PENDING_PROCESSING")
    STATE_PENDING_PROCESSING,

    /**
     * The Chunk has been successfully processed and is available for querying.
     */
    @JsonProperty("STATE_ACTIVE")
    STATE_ACTIVE,

    /**
     * The Chunk processing failed.
     */
    @JsonProperty("STATE_FAILED")
    STATE_FAILED
}