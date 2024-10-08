package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CacheContentResponse extends BaseCacheContent {

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
