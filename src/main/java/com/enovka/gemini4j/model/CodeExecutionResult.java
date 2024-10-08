package com.enovka.gemini4j.model;

import com.enovka.gemini4j.model.type.OutcomeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Result of executing the  `ExecutableCode`.
 * <p>
 * Only generated when using the  `CodeExecution`, and always follows a  `part`  containing the  `ExecutableCode`.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
public class CodeExecutionResult {

    /**
     * Required. Outcome of the code execution.
     */
    @JsonProperty("outcome")
    private OutcomeEnum outcome;

    /**
     * Optional. Contains stdout when code execution is successful, stderr or other description otherwise.
     */
    @JsonProperty("output")
    private String output;

}