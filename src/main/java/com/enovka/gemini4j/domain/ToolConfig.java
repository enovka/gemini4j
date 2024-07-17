package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The Tool configuration containing parameters for specifying Tool use in the
 * request.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Builder(setterPrefix = "with")
@AllArgsConstructor
public class ToolConfig {

    /**
     * Optional. Function calling config.
     */
    @JsonProperty("functionCallingConfig")
    private FunctionCallingConfig functionCallingConfig;

}