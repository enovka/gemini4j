package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A datatype containing media that is part of a multi-part {@link Content} message.
 * <p>
 * A `Part` consists of data which has an associated datatype. A `Part` can only contain one of the
 * accepted types in `Part.data`.
 * <p>
 * A `Part` must have a fixed IANA MIME type identifying the type and subtype of the media if the
 * `inlineData` field is filled with raw bytes.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = Part.Text.class, name = "text"),
        @JsonSubTypes.Type(value = Part.InlineData.class, name = "inlineData"),
        @JsonSubTypes.Type(value = Part.FunctionCall.class, name = "functionCall"),
        @JsonSubTypes.Type(value = Part.FunctionResponse.class, name = "functionResponse"),
        @JsonSubTypes.Type(value = FileData.class, name = "fileData"),
        @JsonSubTypes.Type(value = Part.ExecutableCode.class, name = "executableCode"),
        @JsonSubTypes.Type(value = Part.CodeExecutionResult.class, name = "codeExecutionResult")
})
public class Part {

    @JsonProperty("text")
    private String text;

    @JsonProperty("inlineData")
    private Blob inlineData;

    @JsonProperty("functionCall")
    private FunctionCall functionCall;

    @JsonProperty("functionResponse")
    private com.enovka.gemini4j.model.response.FunctionResponse functionResponse;

    @JsonProperty("fileData")
    private FileData fileData;

    @JsonProperty("executableCode")
    private ExecutableCode executableCode;

    @JsonProperty("codeExecutionResult")
    private CodeExecutionResult codeExecutionResult;


    // Inner classes for subtypes (without @JsonProperty)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Text {
        private String text;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InlineData {
        private Blob inlineData;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FunctionCall {
        private FunctionCall functionCall;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FunctionResponse {
        private com.enovka.gemini4j.model.response.FunctionResponse functionResponse;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExecutableCode {
        private ExecutableCode executableCode;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CodeExecutionResult {
        private CodeExecutionResult codeExecutionResult;
    }
}