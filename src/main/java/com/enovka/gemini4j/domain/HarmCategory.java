package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.HarmCategoryEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a category of potential harm that the Gemini API can assess in
 * generated content. These categories cover various types of content that
 * developers may wish to moderate or block based on their application's safety
 * requirements.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class HarmCategory {

    /**
     * The specific harm category from the {@link HarmCategoryEnum}. For
     * example, setting this to
     * {@link HarmCategoryEnum#HARM_CATEGORY_HATE_SPEECH} indicates the category
     * of hate speech.
     */
    @JsonProperty("harmCategory")
    private HarmCategoryEnum harmCategory;
}