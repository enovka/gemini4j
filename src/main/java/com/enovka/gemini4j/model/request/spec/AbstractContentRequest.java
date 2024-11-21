package com.enovka.gemini4j.model.request.spec;

import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.SafetySetting;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Abstract base class for Gemini API requests involving content. This class
 * implements the {@link Request} interface and provides common fields for
 * requests involving content, such as 'model', 'contents', and
 * 'safetySettings'.  It promotes code reuse and consistency across content-based
 * request types.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
public abstract class AbstractContentRequest extends AbstractSimpleRequest implements Request {

    /**
     * Required. The content of the request. This field typically contains a list
     * of {@link Content} objects, representing the input data for the request.
     * Each {@link Content} object encapsulates the text, images, or other media
     * elements that form the request's input.  The structure and format of the
     * content should adhere to the Gemini API specifications for the specific
     * request type.
     */
    @Singular
    @JsonProperty("contents")
    private List<Content> contents;

    /**
     * Optional. A list of safety settings for the request. This field allows
     * configuring the safety parameters for the request, such as blocking
     * certain categories of content or setting thresholds for harm probabilities.
     * The safety settings provided here will override the default settings for
     * the specified categories.  If no safety settings are provided, the API
     * will use the default safety settings.  Refer to the Gemini API
     * documentation for details on available safety settings and their impact on
     * the request processing.
     */
    @Singular
    @JsonProperty("safetySettings")
    private List<SafetySetting> safetySettings;
}