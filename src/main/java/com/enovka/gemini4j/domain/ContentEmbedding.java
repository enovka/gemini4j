package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * A list of floats representing an embedding.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with")
public class ContentEmbedding {

    /**
     * The embedding values.
     */
    @JsonProperty("values")
    private List<Double> values;

}