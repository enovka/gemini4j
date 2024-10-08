package com.enovka.gemini4j.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Supported programming languages for the generated code.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@Getter
public enum LanguageEnum {

    /**
     * Unspecified language. This value should not be used.
     */
    @JsonProperty("LANGUAGE_UNSPECIFIED")
    LANGUAGE_UNSPECIFIED,

    /**
     * Python more or equals 3.10, with numpy and simpy available.
     */
    @JsonProperty("PYTHON")
    PYTHON

}