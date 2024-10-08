package com.enovka.gemini4j.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the possible reasons why the Gemini API model stopped generating
 * tokens for a particular candidate response. This enum provides insights into
 * the model's behavior and can be used to understand the completion status of a
 * generation request.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum FinishReasonEnum {
    /**
     * **Unspecified Finish Reason:** The reason for stopping token generation
     * is unspecified. This is typically used as a default value when no other
     * reason applies.
     */
    @JsonProperty("FINISH_REASON_UNSPECIFIED")
    FINISH_REASON_UNSPECIFIED,

    /**
     * **Natural Stop or Stop Sequence:** The model reached a natural stopping
     * point in its generation process, such as the end of a sentence or a
     * logical conclusion to the content. This can also occur when the model
     * encounters a stop sequence provided in the generation request.
     */
    @JsonProperty("STOP")
    STOP,

    /**
     * **Unsupported Language:** The candidate content was flagged for using an
     * unsupported language.
     */
    @JsonProperty("LANGUAGE")
    LANGUAGE,

    /**
     * **Maximum Token Limit:** The model stopped generating tokens because it
     * reached the maximum token limit specified in the generation request.
     */
    @JsonProperty("MAX_TOKENS")
    MAX_TOKENS,

    /**
     * **Safety Violation:** The generated content was flagged by the API's
     * safety system and blocked from being returned. This indicates that the
     * content potentially violated the safety guidelines configured for the
     * request.
     */
    @JsonProperty("SAFETY")
    SAFETY,

    /**
     * **Potential Recitation:** The generated content was flagged as being
     * potentially recited from the model's training data. This indicates that
     * the content might be too similar to existing copyrighted material and was
     * blocked to prevent potential copyright infringement.
     */
    @JsonProperty("RECITATION")
    RECITATION,

    /**
     * **Unknown Reason:** The model stopped generating tokens for a reason that
     * is not explicitly categorized by the other enum values.
     */
    @JsonProperty("OTHER")
    OTHER;

    /**
     * Creates an instance of {@link FinishReasonEnum} from a string value.
     *
     * @param value The string value to convert to an enum value.
     * @return The enum value corresponding to the string value.
     */
    public static FinishReasonEnum fromString(String value) {
        return valueOf(value.toUpperCase());
    }
}