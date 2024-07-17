package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.CachedContent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Response with CachedContents list.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with")
public class ListCachedContentsResponse {

    /**
     * List of cached contents.
     */
    @JsonProperty("cachedContents")
    private List<CachedContent> cachedContents;

    /**
     * A token, which can be sent as `pageToken` to retrieve the next page. If
     * this field is omitted, there are no subsequent pages.
     */
    @JsonProperty("nextPageToken")
    private String nextPageToken;

}