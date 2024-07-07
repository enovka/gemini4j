package com.enovka.gemini4j.domain.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represents the categories of potential harm that content can be classified into.
 * <p>
 * These categories cover various kinds of harms that developers may wish to adjust the sensitivity for.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum HarmCategoryEnum {
  /**
   * The harm category is unspecified.
   */
  @JsonProperty("HARM_CATEGORY_UNSPECIFIED")
  HARM_CATEGORY_UNSPECIFIED,

  /**
   * Negative or harmful comments targeting a person's identity or protected attributes.
   */
  @JsonProperty("HARM_CATEGORY_DEROGATORY")
  HARM_CATEGORY_DEROGATORY,

  /**
   * Content that is rude, disrespectful, or uses profane language.
   */
  @JsonProperty("HARM_CATEGORY_TOXICITY")
  HARM_CATEGORY_TOXICITY,

  /**
   * Content depicting violence against an individual or group, or containing graphic descriptions of gore.
   */
  @JsonProperty("HARM_CATEGORY_VIOLENCE")
  HARM_CATEGORY_VIOLENCE,

  /**
   * Content containing sexually suggestive or explicit material.
   */
  @JsonProperty("HARM_CATEGORY_SEXUAL")
  HARM_CATEGORY_SEXUAL,

  /**
   * Content promoting potentially harmful or misleading medical advice.
   */
  @JsonProperty("HARM_CATEGORY_MEDICAL")
  HARM_CATEGORY_MEDICAL,

  /**
   * Content that encourages, facilitates, or promotes dangerous or illegal activities.
   */
  @JsonProperty("HARM_CATEGORY_DANGEROUS")
  HARM_CATEGORY_DANGEROUS,

  /**
   * Content that constitutes harassment or bullying.
   */
  @JsonProperty("HARM_CATEGORY_HARASSMENT")
  HARM_CATEGORY_HARASSMENT,

  /**
   * Content expressing hatred or prejudice against a specific group.
   */
  @JsonProperty("HARM_CATEGORY_HATE_SPEECH")
  HARM_CATEGORY_HATE_SPEECH,

  /**
   * Content that is explicitly sexual in nature.
   */
  @JsonProperty("HARM_CATEGORY_SEXUALLY_EXPLICIT")
  HARM_CATEGORY_SEXUALLY_EXPLICIT,

  /**
   * Content posing a significant risk of harm or danger.
   */
  @JsonProperty("HARM_CATEGORY_DANGEROUS_CONTENT")
  HARM_CATEGORY_DANGEROUS_CONTENT
}