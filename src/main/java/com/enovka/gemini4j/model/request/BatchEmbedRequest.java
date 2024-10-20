package com.enovka.gemini4j.model.request;

import com.enovka.gemini4j.model.request.spec.AbstractContentRequest;
import com.enovka.gemini4j.model.request.spec.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Request for generating multiple embeddings from the model in a synchronous
 * call. This request allows you to embed multiple pieces of content in a
 * single API call, reducing overhead and improving efficiency.  This class
 * now extends {@link AbstractContentRequest} and inherits common request
 * parameters, simplifying its structure and promoting code reuse.  It retains
 * the {@code requests} field for holding individual embedding requests.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@Data
@EqualsAndHashCode()
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
public class BatchEmbedRequest implements Request {

    /**
     * Required. Embed requests for the batch. The model in each of these
     * requests must match the model specified in
     * {@code BatchEmbedRequest.model}.
     */
    @Singular
    @JsonProperty("requests")
    private List<EmbedRequest> requests;

}