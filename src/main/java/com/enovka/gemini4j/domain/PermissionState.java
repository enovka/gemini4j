package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.PermissionStateEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the state of a permission within the Gemini API. This class
 * provides a structured way to indicate whether a permission is currently
 * active or inactive, allowing for granular control over access and
 * authorization.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class PermissionState {

    /**
     * The specific permission state, represented by the
     * {@link PermissionStateEnum}. For example, setting this to
     * {@link PermissionStateEnum#ACTIVE} indicates that the permission is
     * currently active and grants access.
     */
    @JsonProperty("permissionState")
    private PermissionStateEnum permissionState;
}