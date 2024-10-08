package com.enovka.gemini4j.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Enumeration of possible outcomes of the code execution.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@Getter
public enum OutcomeEnum {

    /**
     * Unspecified status. This value should not be used.
     */
    @JsonProperty("OUTCOME_UNSPECIFIED")
    OUTCOME_UNSPECIFIED,

    /**
     * Code execution completed successfully.
     */
    @JsonProperty("OUTCOME_OK")
    OUTCOME_OK,

    /**
     * Code execution finished but with a failure.  `stderr`  should contain the reason.
     */
    @JsonProperty("OUTCOME_FAILED")
    OUTCOME_FAILED,

    /**
     * Code execution ran for too long, and was cancelled. There may or may not be a partial output present.
     */
    @JsonProperty("OUTCOME_DEADLINE_EXCEEDED")
    OUTCOME_DEADLINE_EXCEEDED

}