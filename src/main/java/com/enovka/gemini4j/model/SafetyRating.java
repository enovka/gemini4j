package com.enovka.gemini4j.model;

import com.enovka.gemini4j.model.type.HarmCategoryEnum;
import com.enovka.gemini4j.model.type.HarmProbabilityEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Safety rating for a piece of content.
 * <p>
 * The safety rating contains the category of harm and the harm probability
 * level in that category for a piece of content. Content is classified for
 * safety across a number of harm categories, and the probability of the harm
 * classification is included here.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SafetyRating {

    /**
     * Required. The category for this rating.
     */
    @JsonProperty("category")
    private HarmCategoryEnum category;

    /**
     * Required. The probability of harm for this content.
     */
    @JsonProperty("probability")
    private HarmProbabilityEnum probability;

    /**
     * Was this content blocked because of this rating?
     */
    @JsonProperty("blocked")
    private Boolean blocked;

}