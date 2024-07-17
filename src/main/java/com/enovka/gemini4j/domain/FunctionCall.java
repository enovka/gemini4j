package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A predicted FunctionCall returned from the model that contains a string
 * representing the FunctionDeclaration.name with the arguments and their
 * values.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class FunctionCall {

    /**
     * Required. The name of the function to call. It Must be a-z, A-Z, 0-9, or
     * contain underscores and dashes, with a maximum length of 63.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Optional. The function parameters and values in JSON object format.
     */
    @JsonProperty("args")
    private Object args;

}