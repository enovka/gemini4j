package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Configuration for specifying function calling behavior.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FunctionCallingConfig {

  /**
   * Optional. Specifies the mode in which function calling should execute. If
   * unspecified, the default value will be set to AUTO.
   */
  @JsonProperty("mode")
  private Mode mode;

  /**
   * Optional. A set of function names that, when provided, limits the functions
   * the model will call.
   * <p>
   * This should only be set when the Mode is ANY. Function names should match
   * [FunctionDeclaration.name]. With mode set to ANY, model will predict a
   * function call from the set of function names provided.
   */
  @JsonProperty("allowedFunctionNames")
  private List<String> allowedFunctionNames;

}