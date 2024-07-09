package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Extracted data that represents the Chunk content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChunkData {

    /**
     * The Chunk content as a string. The maximum number of tokens per chunk is
     * 2043.
     */
    @JsonProperty("stringValue")
    private String stringValue;

}