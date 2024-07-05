package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * The user that sent this message.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

  /**
   * Optional. The user's display name.
   */
  @JsonProperty("displayName")
  private String displayName;

  /**
   * Optional. The user's email address.
   */
  @JsonProperty("email")
  private String email;

}