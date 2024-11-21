package com.enovka.gemini4j.model.response.internal;

import com.enovka.gemini4j.model.Candidate;
import com.enovka.gemini4j.model.PromptFeedback;
import com.enovka.gemini4j.model.UsageMetadata;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents a response from the `models.generateContent` and `models.streamGenerateContent` endpoints of the Gemini API.
 * This response contains a list of candidate responses generated by the model,
 * feedback on the prompt, and usage metadata about the request.
 *
 * <p>This class provides a structured representation of the model's output,
 * allowing developers to access the generated content, evaluate its safety, and
 * track resource usage.  It handles both single and multi-turn conversations.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @see <a href="https://ai.google.dev/gemini-api/docs/reference/rest/v1beta/models/generateContent#GenerateContentResponse">Gemini API GenerateContentResponse Documentation</a>
 * @since 0.0.2
 */
@Getter
@Setter
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class GenerateContentResponse extends AbstractResponse {

    /**
     * Candidate responses generated by the model.  This list contains one or
     * more possible completions for the given prompt. Each candidate includes
     * the generated content, finish reason, safety ratings, and other
     * relevant metadata.
     *
     * @see Candidate for the structure of a candidate response.
     */
    @JsonProperty("candidates")
    private List<Candidate> candidates;

    /**
     * Feedback on the prompt related to content filters.  This field provides
     * information about the safety and appropriateness of the prompt itself,
     * including any potential block reasons or safety ratings.
     *
     * @see PromptFeedback for the structure of the prompt feedback.
     */
    @JsonProperty("promptFeedback")
    private PromptFeedback promptFeedback;

    /**
     * Metadata on the generation request's token usage. This field contains
     * information about the number of tokens used for the prompt and generated
     * responses, allowing developers to monitor and manage token consumption.
     *
     * @see UsageMetadata for the structure of the usage metadata.
     */
    @JsonProperty("usageMetadata")
    private UsageMetadata usageMetadata;
}