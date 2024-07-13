package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.response.FunctionResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A datatype containing media that is part of a multipart Content message.
 * <p>
 * A Part consists of `data,` which has an associated datatype. A Part can only
 * contain one of the accepted types in Part.data.
 * <p>
 * A Part must have a fixed IANA MIME type identifying the type and subtype of
 * the media if the `inlineData` field is filled with raw bytes.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Part {

    /**
     * Inline text.
     */
    @JsonProperty("text")
    private String text;

    /**
     * Inline media bytes.
     */
    @JsonProperty("inlineData")
    private Blob inlineData;

    /**
     * A predicted FunctionCall returned from the model that contains a string
     * representing the FunctionDeclaration.name with the arguments and their
     * values.
     */
    @JsonProperty("functionCall")
    private FunctionCall functionCall;

    /**
     * The result output of a FunctionCall that contains a string representing
     * the FunctionDeclaration.name and a structured JSON object containing any
     * output from the function is used as context to the model.
     */
    @JsonProperty("functionResponse")
    private FunctionResponse functionResponse;

    /**
     * URI based data.
     */
    @JsonProperty("fileData")
    private FileData fileData;

}