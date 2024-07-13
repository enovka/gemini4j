package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Specifies the desired style for generating grounded answers.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum AnswerStyleEnum {

    /**
     * The answer style is unspecified. This is the default value.
     */
    @JsonProperty("ANSWER_STYLE_UNSPECIFIED")
    ANSWER_STYLE_UNSPECIFIED,

    /**
     * Generate a succinct and abstract answer.
     */
    @JsonProperty("ABSTRACTIVE")
    ABSTRACTIVE,

    /**
     * Generate a very brief and extractive answer, directly quoting from the
     * source material.
     */
    @JsonProperty("EXTRACTIVE")
    EXTRACTIVE,

    /**
     * Generate a verbose answer, including extra details and context. The
     * response may be formatted as a sentence, paragraph, multiple paragraphs,
     * or bullet points.
     */
    @JsonProperty("VERBOSE")
    VERBOSE
}