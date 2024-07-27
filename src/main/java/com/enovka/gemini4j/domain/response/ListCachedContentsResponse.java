package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.CachedContent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class ListCachedContentsResponse extends AbstractGeminiResponse {

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

    public ListCachedContentsResponse() {
    }
}