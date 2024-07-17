package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.StateEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the current processing state of a Chunk in the Gemini API. This
 * class provides a structured way to track the Chunk's lifecycle, indicating
 * whether it is pending processing, actively available, or has failed
 * processing.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class State {

    /**
     * The current processing state of the Chunk, represented by the
     * {@link StateEnum}. For example, a value of {@link StateEnum#STATE_ACTIVE}
     * indicates that the Chunk has been successfully processed and is ready for
     * use.
     */
    @JsonProperty("state")
    private StateEnum state;
}