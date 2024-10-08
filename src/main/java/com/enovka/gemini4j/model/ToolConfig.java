package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The Tool configuration containing parameters for specifying  `Tool`  use in the request.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */

@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class ToolConfig {

    /**
     * Optional. Function calling config.
     */
    @JsonProperty("functionCallingConfig")
    private FunctionCallingConfig functionCallingConfig;

}