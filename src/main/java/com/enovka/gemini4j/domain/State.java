package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.StateEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * States for the lifecycle of a Chunk.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class State {

    /**
     * The default value. This value is used if the state is omitted.
     */
    @JsonProperty("STATE_UNSPECIFIED")
    private com.enovka.gemini4j.domain.type.StateEnum stateUnspecified;

    /**
     * Chunk is being processed (embedding and vector storage).
     */
    @JsonProperty("STATE_PENDING_PROCESSING")
    private com.enovka.gemini4j.domain.type.StateEnum statePendingProcessing;

    /**
     * Chunk is processed and available for querying.
     */
    @JsonProperty("STATE_ACTIVE")
    private com.enovka.gemini4j.domain.type.StateEnum stateActive;

    /**
     * Chunk failed processing.
     */
    @JsonProperty("STATE_FAILED")
    private StateEnum stateFailed;

}