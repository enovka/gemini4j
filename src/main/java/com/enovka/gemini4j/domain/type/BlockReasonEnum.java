package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Specifies the reason why a user input or prompt was blocked by the Gemini
 * API. This enum provides insights into the API's decision to block a request
 * and can help developers understand how to modify their inputs to comply with
 * the API's guidelines.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum BlockReasonEnum {

    /**
     * **Unspecified Block Reason:** The specific reason for blocking the input
     * is not provided. This value may be used when the API cannot determine a
     * more specific reason or as a default value when no other reason applies.
     */
    @JsonProperty("BLOCK_REASON_UNSPECIFIED")
    BLOCK_REASON_UNSPECIFIED,

    /**
     * **Safety Concerns:** The input was blocked due to safety concerns. This
     * indicates that the content of the input potentially violated the safety
     * guidelines configured for the request, such as guidelines related to hate
     * speech, violence, or harmful content. To resolve this issue, developers
     * should review their input and modify it to comply with the API's safety
     * guidelines.
     */
    @JsonProperty("SAFETY")
    SAFETY,

    /**
     * **Other Reasons:** The input was blocked for reasons other than safety.
     * This could include technical issues, invalid input formats, or violations
     * of other API usage policies. Developers should consult the API
     * documentation and error messages for more specific information about the
     * reason for blocking.
     */
    @JsonProperty("OTHER")
    OTHER
}