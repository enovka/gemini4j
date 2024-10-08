package com.enovka.gemini4j.model.request;

import com.enovka.gemini4j.model.request.spec.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
public class BatchEmbedRequest implements Request {
    /**
     * Required. Embed requests for the batch. The model in each of these
     * requests must match the model specified in
     * {@code BatchEmbedRequest.model}.
     */
    @JsonProperty("requests")
    @Singular
    private List<EmbedRequest> requests;
}