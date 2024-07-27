package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.Permission;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Response from ListPermissions containing a paginated list of permissions.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
public class ListPermissionsResponse extends AbstractGeminiResponse {

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