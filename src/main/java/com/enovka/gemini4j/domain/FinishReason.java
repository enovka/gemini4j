package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.FinishReasonEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Represents the reason why the Gemini API model stopped generating tokens for
 * a particular candidate response. This class provides a structured way to
 * access the finish reason information.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with", toBuilder = true)
public class FinishReason {

    /**
     * The specific finish reason from the {@link FinishReasonEnum}. For
     * example, a value of {@link FinishReasonEnum#MAX_TOKENS} indicates that
     * the model reached the maximum token limit.
     */
    @JsonProperty("finishReason")
    private FinishReasonEnum finishReason;

    @JsonCreator
    public FinishReason(@JsonProperty("finishReason") String finishReason) {
        this.finishReason = FinishReasonEnum.fromString(finishReason);
    }

    public FinishReason(FinishReasonEnum finishReason) {
        this.finishReason = finishReason;
    }
}