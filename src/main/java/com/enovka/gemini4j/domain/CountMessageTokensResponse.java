package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * A response from models.countMessageTokens.
 * <p>
 * It returns the model's tokenCount for the prompt.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountMessageTokensResponse {

  /**
   * The number of tokens that the model tokenizes the prompt into.
   * <p>
   * Always non-negative.
   */
  @JsonProperty("tokenCount")
  private Integer tokenCount;

}