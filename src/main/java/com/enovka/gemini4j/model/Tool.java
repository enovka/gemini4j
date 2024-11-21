package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Tool details that the model may use to generate response.
 * <p>
 * A  `Tool`  is a piece of code that enables the system to interact with external systems to perform an action, or set of actions, outside of knowledge and scope of the model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */

@Data
@Accessors(chain = true)
@Builder(toBuilder = true, setterPrefix = "with")
public class Tool {

    /**
     * Optional. A list of  `FunctionDeclarations`  available to the model that can be used for function calling.
     * <p>
     * The model or system does not execute the function. Instead the defined function may be returned as a  `[FunctionCall](https://ai.google.dev/api/caching#Part.FIELDS.function_call)`  with arguments to the client side for execution. The model may decide to call a subset of these functions by populating  `[FunctionCall](https://ai.google.dev/api/caching#Part.FIELDS.function_call)`  in the response. The next conversation turn may contain a  `[FunctionResponse](https://ai.google.dev/api/caching#Part.FIELDS.function_response)`  with the  `[Content.role](https://ai.google.dev/api/caching#Content.FIELDS.role)`  "function" generation context for the next model turn.
     */
    @JsonProperty("functionDeclarations")
    private List<FunctionDeclaration> functionDeclarations;

    /**
     * Optional. Enables the model to execute code as part of generation.
     */
    @JsonProperty("codeExecution")
    private CodeExecution codeExecution;

}