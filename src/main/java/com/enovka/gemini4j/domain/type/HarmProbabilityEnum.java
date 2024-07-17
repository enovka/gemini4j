package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represents the probability levels that content is classified with for each
 * harm category assessed by the Gemini API's safety system. These probability
 * levels indicate the likelihood that a piece of content falls into a
 * particular harm category, providing a nuanced assessment of the potential
 * safety concerns associated with the content.
 * <p>
 * It's important to note that these probability levels only reflect the
 * likelihood of harm and do not represent the severity of the potential harm.
 * For example, a high probability of a relatively minor harm category might be
 * less concerning than a low probability of a severe harm category.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Getter
public enum HarmProbabilityEnum {

    /**
     * **Unspecified Harm Probability:** The harm probability is not explicitly
     * specified or is unknown. This value is usually used as a default or
     * placeholder when a specific probability level cannot be determined.
     */
    @JsonProperty("HARM_PROBABILITY_UNSPECIFIED")
    HARM_PROBABILITY_UNSPECIFIED,

    /**
     * **Negligible Probability:**  The content has a negligible chance of being
     * harmful. This indicates that the API's safety system has assessed the
     * content and found a very low likelihood of any harm.
     */
    @JsonProperty("NEGLIGIBLE")
    NEGLIGIBLE,

    /**
     * **Low Probability:** The content has a low probability of being harmful.
     * The API's assessment indicates a relatively small chance that the content
     * falls into the associated harm category.
     */
    @JsonProperty("LOW")
    LOW,

    /**
     * **Medium Probability:** The content has a medium probability of being
     * harmful. The API's assessment suggests a moderate chance that the content
     * is considered harmful according to the corresponding category.
     */
    @JsonProperty("MEDIUM")
    MEDIUM,

    /**
     * **High Probability:** The content has a high probability of being
     * harmful. The API's safety system has determined a significant likelihood
     * that the content violates the guidelines for the associated harm
     * category.
     */
    @JsonProperty("HIGH")
    HIGH
}