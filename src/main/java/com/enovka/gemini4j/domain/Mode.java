package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.ModeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Defines the execution behavior for function calling by defining the execution
 * mode.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mode {

    /**
     * Unspecified function calling mode. This value should not be used.
     */
    @JsonProperty("MODE_UNSPECIFIED")
    private com.enovka.gemini4j.domain.types.ModeEnum modeUnspecified;

    /**
     * Default model behavior, model decides to predict either a function call
     * or a natural language response.
     */
    @JsonProperty("AUTO")
    private ModeEnum auto;

    /**
     * The Model is constrained to always predicting a function call only. If
     * "allowedFunctionNames" are set, the predicted function call will be
     * limited to any one of "allowedFunctionNames", else the predicted function
     * call will be any one of the provided "functionDeclarations".
     */
    @JsonProperty("ANY")
    private com.enovka.gemini4j.domain.types.ModeEnum any;

    /**
     * The Model will not predict any function call. Model behavior is the same
     * as when not passing any function declarations.
     */
    @JsonProperty("NONE")
    private com.enovka.gemini4j.domain.types.ModeEnum none;

}