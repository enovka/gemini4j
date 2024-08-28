package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.ModeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Configuration for specifying function calling behavior.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
public class FunctionCallingConfig {

    /**
     * Optional. Specifies the mode in which function calling should execute. If
     * unspecified, the default value will be set to AUTO.
     * <p>
     * This field is now of type {@link Mode}, ensuring that it is serialized as
     * an object JSON.
     */
    @JsonProperty("mode")
    private ModeEnum mode;

    /**
     * Optional. A set of function names that, when provided, limits the
     * functions the model will call.
     * <p>
     * This should only be set when the Mode is ANY. Function names should match
     * [FunctionDeclaration.name]. With mode set to ANY, model will predict a
     * function call from the set of function names provided.
     */
    @JsonProperty("allowedFunctionNames")
    private List<String> allowedFunctionNames;

    /**
     * Constructor for the FunctionCallingConfig.
     *
     * @param mode The function calling mode.
     * @param allowedFunctionNames The list of allowed function names.
     */
    public FunctionCallingConfig(ModeEnum mode,
                                 List<String> allowedFunctionNames) {
        this.mode = mode;
        this.allowedFunctionNames = allowedFunctionNames;
    }
}