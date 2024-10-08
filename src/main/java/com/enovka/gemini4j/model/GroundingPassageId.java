package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Identifier for a part within a GroundingPassage. This class represents the
 * identifier for a specific part within a {@link GroundingPassage}.
 *
 * <p>It includes the passage ID and the index of the part within the passage
 * content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class GroundingPassageId {

    /**
     * Output only. ID of the passage matching the GenerateAnswerRequest's
     * GroundingPassage.id.
     */
    @JsonProperty("passageId")
    private String passageId;

    /**
     * Output only. Index of the part within the GenerateAnswerRequest's
     * GroundingPassage.content.
     */
    @JsonProperty("partIndex")
    private Integer partIndex;
}