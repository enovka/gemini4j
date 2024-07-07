package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.HarmCategoryEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The category of a rating.
 * <p>
 * These categories cover various kinds of harms that developers may wish to
 * adjust.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HarmCategory {

  /**
   * The Category is unspecified.
   */
  @JsonProperty("HARM_CATEGORY_UNSPECIFIED")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum
          harmCategoryUnspecified;

  /**
   * Negative or harmful comments focusing on identity and/or protected attribute.
   */
  @JsonProperty("HARM_CATEGORY_DEROGATORY")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum
          harmCategoryDerogatory;

  /**
   * Content that is rude, disrespectful, or profane.
   */
  @JsonProperty("HARM_CATEGORY_TOXICITY")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum harmCategoryToxicity;

  /**
   * Describes scenarios depicting violence against an individual or group, or
   * general descriptions of gore.
   */
  @JsonProperty("HARM_CATEGORY_VIOLENCE")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum harmCategoryViolence;

  /**
   * Contains references to sexual acts or other lewd content.
   */
  @JsonProperty("HARM_CATEGORY_SEXUAL")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum harmCategorySexual;

  /**
   * Promotes unchecked medical advice.
   */
  @JsonProperty("HARM_CATEGORY_MEDICAL")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum harmCategoryMedical;

  /**
   * Dangerous content that promotes, facilitates, or encourages harmful acts.
   */
  @JsonProperty("HARM_CATEGORY_DANGEROUS")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum
          harmCategoryDangerous;

  /**
   * Harassment content.
   */
  @JsonProperty("HARM_CATEGORY_HARASSMENT")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum
          harmCategoryHarassment;

  /**
   * Hate speech and content.
   */
  @JsonProperty("HARM_CATEGORY_HATE_SPEECH")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum
          harmCategoryHateSpeech;

  /**
   * Sexually explicit content.
   */
  @JsonProperty("HARM_CATEGORY_SEXUALLY_EXPLICIT")
  private HarmCategoryEnum harmCategorySexuallyExplicit;

  /**
   * Dangerous content.
   */
  @JsonProperty("HARM_CATEGORY_DANGEROUS_CONTENT")
  private com.enovka.gemini4j.domain.types.HarmCategoryEnum
          harmCategoryDangerousContent;

}