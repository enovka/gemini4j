package com.enovka.gemini4j.model.request;

import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.GenerateConfig;
import com.enovka.gemini4j.model.Tool;
import com.enovka.gemini4j.model.ToolConfig;
import com.enovka.gemini4j.model.request.spec.AbstractContentRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Request to generate a completion from the model. This class now extends
 * {@link AbstractContentRequest} and inherits common request parameters,
 * simplifying its structure and promoting code reuse.
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
public class GenerateRequest extends AbstractContentRequest {


    /**
     * Optional. A list of Tools the model may use to generate the next
     * response.
     * <p>
     * A Tool is a piece of code that enables the system to interact with
     * external systems to perform an action, or set of actions, outside
     * knowledge and scope of the model. The only supported tool is currently
     * Function.
     */
    @Singular
    @JsonProperty("tools")
    private List<Tool> tools;

    /**
     * Optional. Tool configuration for any Tool specified in the request.
     */
    @JsonProperty("toolConfig")
    private ToolConfig toolConfig;

    /**
     * Optional. Developer set system instruction. Currently, text only.
     */
    @JsonProperty("systemInstruction")
    private Content systemInstruction;

    /**
     * Optional. Configuration options for model generation and outputs.
     */
    @JsonProperty("generationConfig")
    private GenerateConfig generateConfig;

    /**
     * Optional. The name of the cached content used as context to serve the
     * prediction. Note: only used in explicit caching, where users can have
     * control over caching (e.g., what content to cache) and enjoy guaranteed
     * cost savings. Format: `cachedContents/{cachedContent}`
     */
    @JsonProperty("cachedContent")
    private String cachedContent;
}