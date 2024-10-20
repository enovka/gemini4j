package com.enovka.gemini4j.model;

import com.enovka.gemini4j.model.type.HarmBlockThresholdEnum;
import com.enovka.gemini4j.model.type.HarmCategoryEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Safety setting, affecting the safety-blocking behavior.
 * <p>
 * Passing a safety setting for a category changes the allowed probability that
 * content is blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Accessors(chain = true)
@Builder(toBuilder = true, setterPrefix = "with")
public class SafetySetting {

    /**
     * Required. The category for this setting.
     */
    @JsonProperty("category")
    private HarmCategoryEnum category;

    /**
     * Required. Controls the probability threshold at which harm is blocked.
     */
    @JsonProperty("threshold")
    private HarmBlockThresholdEnum threshold;

}