package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the execution modes for function calling in the Gemini API, dictating
 * how the model handles function predictions within a conversation. This enum
 * allows developers to control the model's behavior regarding function calls,
 * determining whether the model should: - Autonomously decide between function
 * calls and natural language responses. - Always predict a function call
 * (either from a restricted set or any available function). - Never predict
 * function calls. By selecting the appropriate `ModeEnum` value, developers can
 * fine-tune the interaction between the model and external functions, enabling
 * sophisticated workflows and integrations.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum ModeEnum {

    /**
     * **Unspecified Function Calling Mode:** The function calling mode is
     * unspecified. This value should not be used. Always explicitly choose a
     * valid function calling mode from the other options (`AUTO`, `ANY`, or
     * `NONE`) to ensure predictable behavior.
     */
    @JsonProperty("MODE_UNSPECIFIED")
    MODE_UNSPECIFIED,

    /**
     * **Automatic Mode (`AUTO`):**  The model autonomously decides whether to
     * predict a function call or a natural language response based on the
     * context of the conversation. This is the default behavior, providing a
     * balance between leveraging external functions and generating human-like
     * text.
     */
    @JsonProperty("AUTO")
    AUTO,

    /**
     * **Function Call Only Mode (`ANY`):** The model is constrained to always
     * predict a function call.
     * <br> - If `allowedFunctionNames` are provided in the request, the model
     * will choose a function from that specific list.
     * <br> - Otherwise, the model can predict a call to any of the available
     * functions declared in the request. This mode is suitable when you want to
     * ensure that the model always interacts with external systems through
     * function calls.
     */
    @JsonProperty("ANY")
    ANY,

    /**
     * **No Function Call Mode (`NONE`):** The model will not predict any
     * function calls, behaving as if no function declarations were provided.
     * This mode effectively disables function calling for the request, ensuring
     * that the model only generates natural language responses.
     */
    @JsonProperty("NONE")
    NONE
}