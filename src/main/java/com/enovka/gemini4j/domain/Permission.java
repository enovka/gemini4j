package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Represents a permission.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permission {

  /**
   * The resource name of the permission.
   * <p>
   * Format: projects/{project}/permissions/{permission}
   */
  @JsonProperty("name")
  private String name;

  /**
   * The permission's display name.
   */
  @JsonProperty("displayName")
  private String displayName;

  /**
   * The permission's description.
   */
  @JsonProperty("description")
  private String description;

  /**
   * The permission's role.
   */
  @JsonProperty("role")
  private String role;

  /**
   * The permission's state.
   */
  @JsonProperty("state")
  private PermissionState state;

  /**
   * The permission's creation timestamp.
   */
  @JsonProperty("createTime")
  private String createTime;

  /**
   * The permission's last update timestamp.
   */
  @JsonProperty("updateTime")
  private String updateTime;

}