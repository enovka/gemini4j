package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represents the possible states of a permission within the Gemini API,
 * indicating whether the permission is currently active and grants access or is
 * inactive and denies access. This enum allows for granular control over
 * permissions, enabling dynamic authorization management based on the
 * permission's state.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum PermissionStateEnum {

    /**
     * **Unspecified Permission State:** The state of the permission is
     * unspecified. This value should not be used. Always explicitly choose
     * either `ACTIVE` or `INACTIVE` to clearly define the permission's state.
     */
    @JsonProperty("PERMISSION_STATE_UNSPECIFIED")
    PERMISSION_STATE_UNSPECIFIED,

    /**
     * **Active Permission:** The permission is active and grants the associated
     * access rights or privileges. This state indicates that the permission is
     * currently valid and enforceable.
     */
    @JsonProperty("ACTIVE")
    ACTIVE,

    /**
     * **Inactive Permission:** The permission is inactive and does not grant
     * the associated access rights or privileges. This state indicates that the
     * permission is currently not valid or enforceable, effectively denying
     * access.
     */
    @JsonProperty("INACTIVE")
    INACTIVE
}