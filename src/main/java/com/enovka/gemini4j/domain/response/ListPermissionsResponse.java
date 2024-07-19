package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.Permission;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Response from ListPermissions containing a paginated list of permissions.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class ListPermissionsResponse {

    /**
     * Returned permissions.
     */
    @JsonProperty("permissions")
    private List<Permission> permissions;

    /**
     * A token, which can be sent as `pageToken` to retrieve the next page.
     * <p>
     * If this field is omitted, there are no more pages.
     */
    @JsonProperty("nextPageToken")
    private String nextPageToken;

}