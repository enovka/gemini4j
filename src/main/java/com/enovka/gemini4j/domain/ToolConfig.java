package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * The Tool configuration containing parameters for specifying Tool use in the
 * request.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToolConfig {

  /**
   * Optional. Function calling config.
   */
  @JsonProperty("functionCallingConfig")
  private FunctionCallingConfig functionCallingConfig;

}