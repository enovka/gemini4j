package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Lists the possible reasons why a piece of content was blocked by the Gemini
 * API's content filtering system. This enum provides more specific information
 * about the nature of the block, helping developers understand why the content
 * was flagged as inappropriate.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum BlockedReasonEnum {

    /**
     * **Unspecified Block Reason:**  The specific reason for blocking the
     * content is not provided or is unknown. This value is often used as a
     * default when a more specific reason cannot be determined.
     */
    @JsonProperty("BLOCKED_REASON_UNSPECIFIED")
    BLOCKED_REASON_UNSPECIFIED,

    /**
     * **Safety Violation:** The content was blocked due to safety concerns.
     * This indicates that the content likely violated the safety guidelines and
     * settings configured for the request. This could include content that is
     * considered harmful, offensive, hateful, or inappropriate based on the
     * chosen safety settings. Developers should review the content and modify
     * it to comply with the API's safety guidelines.
     */
    @JsonProperty("SAFETY")
    SAFETY,

    /**
     * **Other Reasons:** The content was blocked for reasons other than safety.
     * This could include various factors, such as:
     * <ul>
     *     <li>Technical issues with the request</li>
     *     <li>Invalid input formats or data types</li>
     *     <li>Violations of other API usage policies or restrictions</li>
     * </ul>
     * Developers should consult the API documentation and error messages for more specific information about the cause of the block.
     */
    @JsonProperty("OTHER")
    OTHER
}