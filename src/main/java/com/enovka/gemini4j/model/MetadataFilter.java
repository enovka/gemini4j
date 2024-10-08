package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * User-provided filter to limit retrieval based on Chunk or Document level
 * metadata values. This class represents a filter that can be applied to
 * limit the retrieval of chunks or documents based on their metadata values.
 *
 * <p>Example (genre = drama OR genre = action):
 * <p>
 * key = "document.custom_metadata.genre" conditions = [{stringValue = "drama",
 * operation = EQUAL}, {stringValue = "action", operation = EQUAL}]
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetadataFilter {

    /**
     * Required. The key of the metadata to filter on.
     */
    @JsonProperty("key")
    private String key;

    /**
     * Required. The Conditions for the given key that will trigger this filter.
     * Multiple Conditions are joined by logical ORs.
     */
    @JsonProperty("conditions")
    private List<Condition> conditions;
}