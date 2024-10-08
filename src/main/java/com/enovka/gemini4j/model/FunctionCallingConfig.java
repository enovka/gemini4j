package com.enovka.gemini4j.model;

import com.enovka.gemini4j.model.type.ModeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Configuration for specifying function calling behavior. This class allows
 * you to control how the Gemini model handles function calls during content
 * generation.
 *
 * <p>You can configure the function calling mode, which determines whether the
 * model should predict function calls, natural language responses, or a
 * combination of both. You can also specify a list of allowed function names
 * to restrict the model's function call predictions.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */

@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class FunctionCallingConfig {

    /**
     * Optional. Specifies the mode in which function calling should execute.
     * If unspecified, the default value will be set to AUTO.
     */
    @JsonProperty("mode")
    private ModeEnum mode;

    /**
     * Optional. A set of function names that, when provided, limits the
     * functions the model will call.
     * <p>
     * This should only be set when the Mode is ANY. Function names should match
     * [FunctionDeclaration.name]. With mode set to ANY, the model will predict a
     * function call from the set of function names provided.
     */
    @JsonProperty("allowedFunctionNames")
    private List<String> allowedFunctionNames;
}