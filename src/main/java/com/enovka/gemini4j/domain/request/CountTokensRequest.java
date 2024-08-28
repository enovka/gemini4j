package com.enovka.gemini4j.domain.request;

import com.enovka.gemini4j.domain.Content;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Request for counting the number of tokens in text and other types of content
 * using a specified Gemini model. This request allows you to count tokens in
 * either raw content or a {@link GenerateContentRequest}, providing flexibility
 * for different token counting scenarios.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.3
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class CountTokensRequest {

    /**
     * Required. The model's resource name. This serves as an ID for the Model
     * to use. This name should match a model name returned by the
     * {@code models.list} method. Format: {@code models/{model}}.
     */
    @JsonProperty("model")
    private String model;

    /**
     * Optional. The input given to the model as a prompt. This field is ignored
     * when {@code generateContentRequest} is set.
     */
    @JsonProperty("contents")
    private List<Content> contents;

    /**
     * Optional. The overall input given to the Model. This includes the prompt
     * as well as other model steering information like system instructions,
     * and/or function declarations for function calling.
     * <p>
     * {@code contents} and {@code generateContentRequest} are mutually
     * exclusive. You can either send {@code model} + {@code contents} or a
     * {@code generateContentRequest}, but never both.
     */
    @JsonProperty("generateContentRequest")
    private GenerateContentRequest generateContentRequest;
}