package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * The permission's state.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
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
  private PermissionStateEnum active;

  /**
   * Permission is inactive.
   */
  @JsonProperty("INACTIVE")
  private PermissionStateEnum inactive;

}