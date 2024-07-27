package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.Candidate;
import com.enovka.gemini4j.domain.InputFeedback;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Response from the model for a grounded answer.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
public class GenerateAnswerResponse extends AbstractGeminiResponse {

    /**
     * Candidate answer from the model.
     * <p>
     * Note: The model always attempts to provide a grounded answer, even when
     * the answer is unlikely to be answerable from the given passages. In that
     * case, a low-quality or ungrounded answer may be provided, along with a
     * low answerableProbability.
     */
    @JsonProperty("answer")
    private Candidate answer;

    /**
     * Output only. The model's estimate of the probability that its answer is
     * correct and grounded in the input passages.
     * <p>
     * A low answerableProbability indicates that the answer might not be
     * grounded in the sources.
     * <p>
     * When answerableProbability is low, some clients may wish to:
     * <p>
     * - Display a message to the effect of "We couldn’t answer that question"
     * to the user. - Fall back to a general-purpose LLM that answers the
     * question from world knowledge. The threshold and nature of such fallbacks
     * will depend on individual clients’ use cases. 0.5 is a good starting
     * threshold.
     */
    @JsonProperty("answerableProbability")
    private Double answerableProbability;

    /**
     * Output only. Feedback related to the input data used to answer the
     * question, as opposed to model-generated response to the question.
     * <p>
     * "Input data" can be one or more of the following:
     * <p>
     * - Question specified by the last entry in GenerateAnswerRequest.content -
     * Conversation history specified by the other entries in
     * GenerateAnswerRequest.content - Grounding sources
     * (GenerateAnswerRequest.semantic_retriever or
     * GenerateAnswerRequest.inline_passages)
     */
    @JsonProperty("inputFeedback")
    private InputFeedback inputFeedback;

}