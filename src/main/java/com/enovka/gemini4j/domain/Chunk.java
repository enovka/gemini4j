package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * A Chunk is a subpart of a Document that is treated as an independent unit for
 * the purposes of vector representation and storage. A Corpus can have a
 * maximum of 1 million Chunks.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Chunk {

    /**
     * Immutable. Identifier. The Chunk resource name. The ID (name excluding
     * the "corpora/documents/chunks/" prefix) can contain up to 40 characters
     * that are lowercase alphanumeric or dashes (-). The ID cannot start or end
     * with a dash. If the name is empty on creation, a random 12-character
     * unique ID will be generated. Example:
     * `corpora/{corpus_id}/documents/{document_id}/chunks/123a456b789c`
     */
    @JsonProperty("name")
    private String name;

    /**
     * Required. The content for the Chunk, such as the text string. The maximum
     * number of tokens per chunk is 2043.
     */
    @JsonProperty("data")
    private ChunkData data;

    /**
     * Optional. User provided custom metadata stored as key-value pairs. The
     * maximum number of CustomMetadata per chunk is 20.
     */
    @JsonProperty("customMetadata")
    private List<CustomMetadata> customMetadata;

    /**
     * Output only. The Timestamp of when the Chunk was created.
     * <p>
     * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond resolution and
     * up to nine fractional digits. Examples: "2014-10-02T15:01:23Z" and
     * "2014-10-02T15:01:23.045123456Z".
     */
    @JsonProperty("createTime")
    private String createTime;

    /**
     * Output only. The Timestamp of when the Chunk was last updated.
     * <p>
     * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond resolution and
     * up to nine fractional digits. Examples: "2014-10-02T15:01:23Z" and
     * "2014-10-02T15:01:23.045123456Z".
     */
    @JsonProperty("updateTime")
    private String updateTime;

    /**
     * Output only. Current state of the Chunk.
     */
    @JsonProperty("state")
    private State state;

}