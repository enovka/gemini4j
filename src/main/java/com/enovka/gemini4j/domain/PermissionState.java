package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.PermissionStateEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The permission's state.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionState {

  /**
   * Permission state is unspecified.
   */
  @JsonProperty("PERMISSION_STATE_UNSPECIFIED")
  private PermissionStateEnum permissionStateUnspecified;

  /**
   * Permission is active.
   */
  @JsonProperty("ACTIVE")
  private com.enovka.gemini4j.domain.types.PermissionStateEnum active;

  /**
   * Permission is inactive.
   */
  @JsonProperty("INACTIVE")
  private com.enovka.gemini4j.domain.types.PermissionStateEnum inactive;

}