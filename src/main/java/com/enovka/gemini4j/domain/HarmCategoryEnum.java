package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * The category of a rating.
 * <p>
 * These categories cover various kinds of harms that developers may wish to
 * adjust.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum HarmCategoryEnum {

  @JsonProperty("HARM_CATEGORY_UNSPECIFIED")
  HARM_CATEGORY_UNSPECIFIED,

  @JsonProperty("HARM_CATEGORY_DEROGATORY")
  HARM_CATEGORY_DEROGATORY,

  @JsonProperty("HARM_CATEGORY_TOXICITY")
  HARM_CATEGORY_TOXICITY,

  @JsonProperty("HARM_CATEGORY_VIOLENCE")
  HARM_CATEGORY_VIOLENCE,

  @JsonProperty("HARM_CATEGORY_SEXUAL")
  HARM_CATEGORY_SEXUAL,

  @JsonProperty("HARM_CATEGORY_MEDICAL")
  HARM_CATEGORY_MEDICAL,

  @JsonProperty("HARM_CATEGORY_DANGEROUS")
  HARM_CATEGORY_DANGEROUS,

  @JsonProperty("HARM_CATEGORY_HARASSMENT")
  HARM_CATEGORY_HARASSMENT,

  @JsonProperty("HARM_CATEGORY_HATE_SPEECH")
  HARM_CATEGORY_HATE_SPEECH,

  @JsonProperty("HARM_CATEGORY_SEXUALLY_EXPLICIT")
  HARM_CATEGORY_SEXUALLY_EXPLICIT,

  @JsonProperty("HARM_CATEGORY_DANGEROUS_CONTENT")
  HARM_CATEGORY_DANGEROUS_CONTENT
}