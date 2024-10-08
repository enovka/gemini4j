package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * A repeated list of passages. This class represents a collection of
 * {@link GroundingPassage} objects, which are passages provided inline with
 * a request to the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class GroundingPassages {

    /**
     * List of passages.
     */
    @JsonProperty("passages")
    @Singular
    private List<GroundingPassage> passages;
}