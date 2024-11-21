package com.enovka.gemini4j.model.response;

import com.enovka.gemini4j.model.CacheResponse;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Response with CachedContents list.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class ListCacheResponse extends AbstractResponse {

    /**
     * List of cached contents.
     */
    @JsonProperty("cachedContents")
    private List<CacheResponse> cachedContents;

    /**
     * A token, which can be sent as `pageToken` to retrieve the next page. If
     * this field is omitted, there are no subsequent pages.
     */
    @JsonProperty("nextPageToken")
    private String nextPageToken;
}