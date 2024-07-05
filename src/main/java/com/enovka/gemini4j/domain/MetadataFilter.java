package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * User provided filter to limit retrieval based on Chunk or Document level
 * metadata values.
 * <p>
 * Example (genre = drama OR genre = action):
 * <p>
 * key = "document.custom_metadata.genre"
 * conditions = [{stringValue = "drama", operation = EQUAL}, {stringValue =
 * "action", operation = EQUAL}]
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
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