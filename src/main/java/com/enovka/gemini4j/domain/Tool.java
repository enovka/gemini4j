package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Tool details that the model may use to generate response.
 * <p>
 * A Tool is a piece of code that enables the system to interact with external
 * systems to perform an action, or set of actions, outside knowledge and scope
 * of the model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Tool {

    /**
     * Optional. A list of FunctionDeclarations available to the model that can
     * be used for function calling.
     * <p>
     * The model or system does not execute the function. Instead, the defined
     * function may be returned as a [FunctionCall][content.part.function_call]
     * with arguments to the client side for execution. The model may decide to
     * call a subset of these functions by populating
     * [FunctionCall][content.part.function_call] in the response. The next
     * conversation turn may contain a
     * [FunctionResponse][content.part.function_response] with the
     * [content.role] "function" generation context for the next model turn.
     */
    @JsonProperty("functionDeclarations")
    private List<FunctionDeclaration> functionDeclarations;

}