package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * The permission's state.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum PermissionStateEnum {

  @JsonProperty("PERMISSION_STATE_UNSPECIFIED")
  PERMISSION_STATE_UNSPECIFIED,

  @JsonProperty("ACTIVE")
  ACTIVE,

  @JsonProperty("INACTIVE")
  INACTIVE
}