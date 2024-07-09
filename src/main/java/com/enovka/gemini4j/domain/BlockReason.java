package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.BlockReasonEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Specifies what was the reason why input was blocked.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockReason {

    /**
     * Default value. This value is unused.
     */
    @JsonProperty("BLOCK_REASON_UNSPECIFIED")
    private BlockReasonEnum blockReasonUnspecified;

    /**
     * Input was blocked due to safety reasons. You can inspect safetyRatings to
     * understand which safety category blocked it.
     */
    @JsonProperty("SAFETY")
    private com.enovka.gemini4j.domain.types.BlockReasonEnum safety;

    /**
     * Input was blocked due to other reasons.
     */
    @JsonProperty("OTHER")
    private com.enovka.gemini4j.domain.types.BlockReasonEnum other;

}