package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Structured representation of a function declaration as defined by the OpenAPI
 * 3.03 specification. Included in this declaration are the function name and
 * parameters. This FunctionDeclaration is a representation of a block of code
 * that can be used as a Tool by the model and executed by the client.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FunctionDeclaration {

    /**
     * Required. The name of the function. It Must be a-z, A-Z, 0-9, or contain
     * underscores and dashes, with a maximum length of 63.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Required. A brief description of the function.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Optional. Describes the parameters to this function. Reflects the Open
     * API 3.03 Parameter Object string Key: the name of the parameter.
     * Parameter names are case-sensitive. Schema Value: the Schema defining the
     * type used for the parameter.
     */
    @JsonProperty("parameters")
    private Schema parameters;

}