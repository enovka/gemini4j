package com.enovka.gemini4j.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Request to get a text embedding from the model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(setterPrefix = "with")
public class EmbedTextRequest {

    /**
     * Required. The model name to use with the format `model=models/{model}`.
     */
    @JsonProperty("model")
    private String model;

    /**
     * Optional. The free-form input text that the model will turn into an
     * embedding.
     */
    @JsonProperty("text")
    private String text;

}