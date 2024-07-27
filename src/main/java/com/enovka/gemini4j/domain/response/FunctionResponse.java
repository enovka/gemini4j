package com.enovka.gemini4j.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * The result output from a FunctionCall that contains a string representing the
 * FunctionDeclaration.name and a structured JSON object containing any output
 * from the function is used as context to the model. This should contain the
 * result of aFunctionCall made based on model prediction.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
public class FunctionResponse extends AbstractGeminiResponse {

    /**
     * Required. The name of the function to call. It Must be a-z, A-Z, 0-9, or
     * contain underscores and dashes, with a maximum length of 63.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Required. The function response in JSON object format.
     */
    @JsonProperty("response")
    private Object response;

}