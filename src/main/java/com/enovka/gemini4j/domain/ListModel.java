package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Response from ListModel containing a paginated list of Models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListModel {

  /**
   * The returned Models.
   */
  @JsonProperty("models")
  private List<Model> models;

  /**
   * A token, which can be sent as `pageToken` to retrieve the next page.
   * <p>
   * If this field is omitted, there are no more pages.
   */
  @JsonProperty("nextPageToken")
  private String nextPageToken;

}