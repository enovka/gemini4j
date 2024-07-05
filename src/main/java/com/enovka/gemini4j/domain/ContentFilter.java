package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Content filtering metadata associated with processing a single request.
 * <p>
 * ContentFilter contains a reason and an optional supporting string. The reason
 * may be unspecified.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentFilter {

  /**
   * The reason content was blocked during request processing.
   */
  @JsonProperty("reason")
  private BlockedReason reason;

  /**
   * A string that describes the filtering behavior in more detail.
   */
  @JsonProperty("message")
  private String message;

}