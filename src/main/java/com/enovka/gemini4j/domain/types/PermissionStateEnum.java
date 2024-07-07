package com.enovka.gemini4j.domain.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represents the possible states of a permission.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum PermissionStateEnum {

  /**
   * The state of the permission is unspecified.
   */
  @JsonProperty("PERMISSION_STATE_UNSPECIFIED")
  PERMISSION_STATE_UNSPECIFIED,

  /**
   * The permission is active.
   */
  @JsonProperty("ACTIVE")
  ACTIVE,

  /**
   * The permission is inactive.
   */
  @JsonProperty("INACTIVE")
  INACTIVE
}