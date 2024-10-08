package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseCacheContent {

    /**
     * Optional. Identifier. The resource name referring to the cached content.
     * Format: `cachedContents/{id}`
     *
     * @since 0.2.0
     */
    @JsonProperty("name")
    private String name;

    /**
     * Optional. Immutable. The user-generated meaningful display name of the cached content.
     * Maximum 128 Unicode characters.
     *
     * @since 0.2.0
     */
    @JsonProperty("displayName")
    private String displayName;

    /**
     * Required. Immutable. The name of the Model to use for cached content
     * Format: `models/{model}`
     *
     * @since 0.2.0
     */
    @JsonProperty("model")
    private String model;

    /**
     * The absolute expiration time for the cached content, represented as a timestamp string in RFC3339 UTC "Zulu"
     * format (e.g., "2024-10-27T12:00:00Z"). This field is now a direct part of the {@code CacheContent} object
     * for simplified JSON serialization. If this field is set, {@code ttl} must be null.
     *
     * @since 0.2.0
     */
    @JsonProperty("expireTime")
    private String expireTime;

    /**
     * Output only. Metadata about the usage of the cached content.  This field, added to address the
     * deserialization error, provides information about the token usage of the cached content.  It is
     * populated by the Gemini API and includes details such as the total token count.
     *
     * @since 0.2.0
     */
    @JsonProperty("usageMetadata")
    private UsageMetadata usageMetadata;
}
