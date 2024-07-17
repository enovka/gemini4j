package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.ModeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the execution mode for function calling in the Gemini API. This
 * class provides a structured way to control how the model handles function
 * calls, allowing you to specify whether the model should predict function
 * calls, natural language responses, or a combination of both.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Mode {

    /**
     * The specific execution mode for function calling, as defined by the
     * {@link ModeEnum}. For example, setting this to {@link ModeEnum#AUTO}
     * enables the default model behavior where the model decides whether to
     * predict a function call or a natural language response.
     */
    @JsonProperty("mode")
    private ModeEnum mode;
}