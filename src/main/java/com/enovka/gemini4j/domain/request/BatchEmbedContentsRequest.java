package com.enovka.gemini4j.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Request for generating multiple embeddings from the model in a synchronous
 * call. This request allows you to embed multiple pieces of content in a single
 * API call.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class BatchEmbedContentsRequest {

    /**
     * Required. The model's resource name. This serves as an ID for the Model
     * to use. This name should match a model name returned by the
     * {@code models.list} method. Format: {@code models/{model}}.
     */
    @JsonProperty("model")
    private String model;

    /**
     * Required. Embed requests for the batch. The model in each of these
     * requests must match the model specified in
     * {@code BatchEmbedContentsRequest.model}.
     */
    @JsonProperty("requests")
    @Singular
    private List<EmbedContentRequest> requests;
}