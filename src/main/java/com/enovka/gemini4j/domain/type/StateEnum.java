package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the possible processing states of a Chunk in the Gemini API,
 * representing the different stages of its lifecycle. These states provide
 * insights into the Chunk's current status, indicating whether it is pending
 * processing, actively available, or has encountered an error during
 * processing.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum StateEnum {

    /**
     * **Unspecified Chunk State:** The state of the Chunk is unspecified or
     * unknown. This value should not be used explicitly.
     */
    @JsonProperty("STATE_UNSPECIFIED")
    STATE_UNSPECIFIED,

    /**
     * **Pending Processing:** The Chunk is currently being processed by the
     * Gemini API. This includes generating its embedding and storing its vector
     * representation. During this state, the Chunk is not yet available for
     * querying.
     */
    @JsonProperty("STATE_PENDING_PROCESSING")
    STATE_PENDING_PROCESSING,

    /**
     * **Active Chunk:** The Chunk has been successfully processed and is now
     * available for querying. Its embedding and vector representation have been
     * generated and stored, making it ready for use in similarity searches and
     * other operations.
     */
    @JsonProperty("STATE_ACTIVE")
    STATE_ACTIVE,

    /**
     * **Failed Processing:**  An error occurred during the Chunk's processing,
     * and it is not available for querying. This could be due to various
     * reasons, such as invalid input data or internal errors. Check the API
     * logs for more specific error messages and details.
     */
    @JsonProperty("STATE_FAILED")
    STATE_FAILED
}