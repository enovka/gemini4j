package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the execution modes for function calling, dictating how the model
 * handles function predictions.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum ModeEnum {

    /**
     * The function calling mode is unspecified. This value should not be used.
     */
    @JsonProperty("MODE_UNSPECIFIED")
    MODE_UNSPECIFIED,

    /**
     * The model decides autonomously whether to predict a function call or a
     * natural language response. This is the default behavior.
     */
    @JsonProperty("AUTO")
    AUTO,

    /**
     * The model is constrained to always predict a function call. If
     * 'allowedFunctionNames' are provided, the prediction will be limited to
     * those specific functions. Otherwise, the prediction will be any one of
     * the provided function declarations.
     */
    @JsonProperty("ANY")
    ANY,

    /**
     * The model will not predict any function calls, behaving as if no function
     * declarations were provided.
     */
    @JsonProperty("NONE")
    NONE
}