package com.enovka.gemini4j.model.request;

import com.enovka.gemini4j.model.CacheContent;
import com.enovka.gemini4j.model.request.spec.AbstractContentRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Represents a request for creating or updating cached content in the Gemini
 * API. This class now extends {@link AbstractContentRequest} and inherits
 * common request parameters, simplifying its structure and promoting code
 * reuse. It retains the {@code cacheContent} field for specifying the content
 * to be cached.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class CacheRequest extends AbstractContentRequest {

    /**
     * The cached content to be created or updated.
     */
    @JsonProperty("cachedContent")
    private CacheContent cacheContent;
}