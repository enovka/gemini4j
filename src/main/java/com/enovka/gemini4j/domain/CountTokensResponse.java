package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Response from models.countTokens.
 * <p>
 * It returns the model's tokenCount for the prompt.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountTokensResponse {

  /**
   * The number of tokens that the model tokenizes the prompt into.
   * <p>
   * Always non-negative. When cachedContent is set, this is still the total
   * effective prompt size. I.e., this includes the number of tokens in the
   * cached content.
   */
  @JsonProperty("totalTokens")
  private Integer totalTokens;

}