package com.enovka.gemini4j.model.request;

import com.enovka.gemini4j.model.CacheContent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents a request for creating or updating cached content in the Gemini API.
 * This class encapsulates the {@link CacheContent} object to be sent as part of the request.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@Data
@Builder(setterPrefix = "with")
@EqualsAndHashCode(callSuper = false)
public class CacheRequest {

    /**
     * The cached content to be created or updated.
     */
    @JsonProperty("cachedContent")
    private CacheContent cacheContent;
}