package com.enovka.gemini4j.model;

import com.enovka.gemini4j.model.request.spec.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents cached content for use in later Gemini API requests.
 * This class implements
 * the {@link Request} interface for consistent request handling.
 * It holds the content to be
 * cached, the associated model, system instructions, tools, configurations, and expiration
 * settings.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CacheContent extends BaseCacheContent implements Request {

    /**
     * Optional. Input only. Immutable. The content to be cached.
     *
     * @since 0.2.0
     */
    @JsonProperty("contents")
    private List<Content> contents;

    /**
     * Optional. Input only. Immutable. A list of tools the model may use.
     *
     * @since 0.2.0
     */
    @JsonProperty("tools")
    private List<Tool> tools;


    /**
     * Optional. Input only. Immutable. Developer-set system instruction. Currently, text only.
     *
     * @since 0.2.0
     */
    @JsonProperty("systemInstruction")
    private Content systemInstruction;

    /**
     * Optional. Input only. Immutable. Tool config. This config is shared for all tools.
     *
     * @since 0.2.0
     */
    @JsonProperty("toolConfig")
    private ToolConfig toolConfig;

    /**
     * The Time-To-Live (TTL) for the cached content, represented as a duration string (e.g., "3600s" for 1 hour).
     * This field is now directly part of the {@code CacheContent} object, simplifying the JSON structure.  If
     * this field is set, {@code expireTime} must be null. A default TTL of 1 hour is used if neither TTL nor
     * expireTime is explicitly configured.
     *
     * @since 0.2.0
     */
    @JsonProperty("ttl")
    @Builder.Default
    private String ttl = "3600s";

    /**
     * Output only. The update timestamp of the cached content. This field is returned by the Gemini API
     * and indicates the last time the cached content was modified.
     *
     * @since 0.2.0
     */
    @JsonProperty("updateTime")
    private String updateTime;

    /**
     * Output only. The creation timestamp of the cached content. This field is returned by the
     * Gemini API and represents the time the cached content was created.
     *
     * @since 0.2.0
     */
    @JsonProperty("createTime")
    private String createTime;
}


