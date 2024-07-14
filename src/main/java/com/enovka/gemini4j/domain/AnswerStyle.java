package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.AnswerStyleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Style for grounded answers.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(setterPrefix = "with")
public class AnswerStyle {

    /**
     * Unspecified answer style.
     */
    @JsonProperty("ANSWER_STYLE_UNSPECIFIED")
    private com.enovka.gemini4j.domain.type.AnswerStyleEnum
            answerStyleUnspecified;

    /**
     * Succinct but abstract style.
     */
    @JsonProperty("ABSTRACTIVE")
    private com.enovka.gemini4j.domain.type.AnswerStyleEnum abstractive;

    /**
     * Very brief and extractive style.
     */
    @JsonProperty("EXTRACTIVE")
    private com.enovka.gemini4j.domain.type.AnswerStyleEnum extractive;

    /**
     * Verbose style including extra details. The response may be formatted as a
     * sentence, paragraph, multiple paragraphs, or bullet points, etc.
     */
    @JsonProperty("VERBOSE")
    private AnswerStyleEnum verbose;

}