package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * States for the lifecycle of a Chunk.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class State {

  /**
   * The default value. This value is used if the state is omitted.
   */
  @JsonProperty("STATE_UNSPECIFIED")
  private StateEnum stateUnspecified;

  /**
   * Chunk is being processed (embedding and vector storage).
   */
  @JsonProperty("STATE_PENDING_PROCESSING")
  private StateEnum statePendingProcessing;

  /**
   * Chunk is processed and available for querying.
   */
  @JsonProperty("STATE_ACTIVE")
  private StateEnum stateActive;

  /**
   * Chunk failed processing.
   */
  @JsonProperty("STATE_FAILED")
  private StateEnum stateFailed;

}