package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Specifies the desired style for generating grounded answers from the Gemini
 * API. This enum allows you to control the level of detail, conciseness, and
 * formatting of the generated responses.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Getter
public enum AnswerStyleEnum {

    /**
     * Unspecified answer style. This is the default value and may result in
     * behavior that varies depending on the model. It is generally recommended
     * to explicitly choose a specific answer style for consistent results.
     */
    @JsonProperty("ANSWER_STYLE_UNSPECIFIED")
    ANSWER_STYLE_UNSPECIFIED,

    /**
     * **Abstractive Answer Style:** Generate a succinct and abstract answer
     * that summarizes the relevant information from the source material. This
     * style aims to provide a concise response without directly quoting from
     * the source.
     */
    @JsonProperty("ABSTRACTIVE")
    ABSTRACTIVE,

    /**
     * **Extractive Answer Style:** Generate a very brief and extractive answer
     * that directly quotes the most relevant segment from the source material.
     * This style prioritizes conciseness and accuracy by providing a verbatim
     * excerpt from the source.
     */
    @JsonProperty("EXTRACTIVE")
    EXTRACTIVE,

    /**
     * **Verbose Answer Style:** Generate a detailed and comprehensive answer
     * that includes extra context and information beyond the most relevant
     * segment. This style provides a more in-depth response that elaborates on
     * the answer and may use various formatting options like sentences,
     * paragraphs, or bullet points.
     */
    @JsonProperty("VERBOSE")
    VERBOSE
}