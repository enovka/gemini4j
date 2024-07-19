package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Represents a cached content.
 * <p>
 * Content that has been preprocessed and can be used in later requests to
 * GenerativeService.
 * <p>
 * Cached content can be only used with the model it was created for.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CachedContent {

    /**
     * Input only. Immutable. The content to cache.
     */
    @JsonProperty("contents")
    private List<Content> contents;

    /**
     * Input only. Immutable. A list of Tools the model may use to generate the
     * next response.
     */
    @JsonProperty("tools")
    private List<Tool> tools;

    /**
     * Output only. Creation time of the cache entry.
     * <p>
     * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond resolution and
     * up to nine fractional digits. Examples: "2014-10-02T15:01:23Z" and
     * "2014-10-02T15:01:23.045123456Z".
     */
    @JsonProperty("createTime")
    private String createTime;

    /**
     * Output only. When the cache entry was last updated in UTC time.
     * <p>
     * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond resolution and
     * up to nine fractional digits. Examples: "2014-10-02T15:01:23Z" and
     * "2014-10-02T15:01:23.045123456Z".
     */
    @JsonProperty("updateTime")
    private String updateTime;

    /**
     * Output only. Metadata on the usage of the cached content.
     */
    @JsonProperty("usageMetadata")
    private UsageMetadata usageMetadata;

    /**
     * Timestamp in UTC of when this resource is considered expired. This is
     * always provided on output, regardless of what was sent on input.
     * <p>
     * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond resolution and
     * up to nine fractional digits. Examples: "2014-10-02T15:01:23Z" and
     * "2014-10-02T15:01:23.045123456Z".
     */
    @JsonProperty("expireTime")
    private String expireTime;

    /**
     * Input only. New TTL for this resource, input only.
     * <p>
     * A duration in seconds with up to nine fractional digits, ending with 's'.
     * Example: "3.5s".
     */
    @JsonProperty("ttl")
    private String ttl;

    /**
     * Optional. Identifier. The resource name referring to the cached content.
     * Format: `cachedContents/{id}`
     */
    @JsonProperty("name")
    private String name;

    /**
     * Optional. Immutable. The user-generated meaningful display name of the
     * cached content. Maximum 128 Unicode characters.
     */
    @JsonProperty("displayName")
    private String displayName;

    /**
     * Required. Immutable. The name of the Model to use for cached content
     * Format: `models/{model}`
     */
    @JsonProperty("model")
    private String model;

    /**
     * Optional. Input only. Immutable. Developer set system instruction.
     * Currently text only.
     */
    @JsonProperty("systemInstruction")
    private Content systemInstruction;

    /**
     * Optional. Input only. Immutable. Tool config. This config is shared for
     * all tools.
     */
    @JsonProperty("toolConfig")
    private ToolConfig toolConfig;

}