package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Configuration options for model generation and outputs. Not all parameters
 * may be configurable for every model.
 *
 * <p>This class provides a way to customize the behavior of the Gemini model
 * during content generation. You can configure parameters such as stop
 * sequences, response MIME type, response schema, candidate count, maximum
 * output tokens, temperature, top-p, top-k, presence penalty, frequency penalty,
 * response log probabilities, and log probabilities.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */

@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class GenerateConfig {

    /**
     * Optional. The set of character sequences (up to 5) that will stop output
     * generation. If specified, the API will stop at the first appearance of a
     * stop sequence. The stop sequence will not be included as part of the
     * response.
     */
    @JsonProperty("stopSequences")
    private List<String> stopSequences;

    /**
     * Optional. Output response MIME type of the generated candidate text.
     * Supported MIME type: `text/plain`: (default) Text output.
     * `application/json`: JSON response to the candidates.
     */
    @JsonProperty("responseMimeType")
    private String responseMimeType;

    /**
     * Optional. Output response schema of the generated candidate text when a
     * response MIME type can have a schema. Schema can be objects, primitives, or
     * arrays and is a subset of the OpenAPI schema.
     * <p>
     * If set, a compatible responseMimeType must also be set. Compatible
     * MIME types: `application/json`: Schema for JSON response.
     */
    @JsonProperty("responseSchema")
    private Schema responseSchema;

    /**
     * Optional. Number of generated responses to return.
     * <p>
     * Currently, this value can only be set to 1. If unset, this will default
     * to 1.
     */
    @JsonProperty("candidateCount")
    private Integer candidateCount;

    /**
     * Optional. The maximum number of tokens to include in a candidate.
     * <p>
     * Note: The default value varies by model, see the Model.output_token_limit
     * attribute of the Model returned from the getModelName function.
     */
    @JsonProperty("maxOutputTokens")
    private Integer maxOutputTokens;

    /**
     * Optional. Controls the randomness of the output.
     * <p>
     * Note: The default value varies by model, see the Model.temperature
     * attribute of the Model returned from the getModelName function.
     * <p>
     * Values can range from [0.0, 2.0].
     */
    @JsonProperty("temperature")
    private Double temperature;

    /**
     * Optional. The maximum cumulative probability of tokens to consider when
     * sampling.
     * <p>
     * The model uses combined Top-k and nucleus sampling.
     * <p>
     * Tokens are sorted based on their assigned probabilities so that only the
     * most likely tokens are considered. Top-k sampling directly limits the
     * maximum number of tokens to consider, while Nucleus sampling limits the
     * number of tokens based on the cumulative probability.
     * <p>
     * Note: The default value varies by model, see the Model.top_p attribute of
     * the Model returned from the getModelName function.
     */
    @JsonProperty("topP")
    private Double topP;

    /**
     * Optional. The maximum number of tokens to consider when sampling.
     * <p>
     * Models use nucleus sampling or combined Top-k and nucleus sampling. Top-k
     * sampling considers the set of topK most probable tokens. Models running
     * with nucleus sampling don't allow topK setting.
     * <p>
     * Note: The default value varies by model, see the Model.top_k attribute of
     * the Model returned from the getModelName function. Empty topK field in the
     * Model indicates the model doesn't apply top-k sampling and doesn't allow
     * setting topK on requests.
     */
    @JsonProperty("topK")
    private Integer topK;

    /**
     * Optional. Presence penalty applied to the next token's logprobs if the token has already been seen in the response.
     * <p>
     * This penalty is binary on/off and not dependent on the number of times the token is used (after the first). Use
     * {@link #frequencyPenalty} for a penalty that increases with each use.
     * <p>
     * A positive penalty will discourage the use of tokens that have already been used in the response, increasing the
     * vocabulary.
     * <p>
     * A negative penalty will encourage the use of tokens that have already been used in the response, decreasing the
     * vocabulary.
     *
     * @since 0.2.0
     */
    @JsonProperty("presencePenalty")
    private Double presencePenalty;

    /**
     * Optional. Frequency penalty applied to the next token's logprobs, multiplied by the number of times each token has
     * been seen in the response so far.
     * <p>
     * A positive penalty will discourage the use of tokens that have already been used, proportional to the number of
     * times the token has been used: The more a token is used, the more difficult it is for the model to use that token
     * again, increasing the vocabulary of responses.
     * <p>
     * Caution: A *negative* penalty will encourage the model to reuse tokens proportional to the number of times the
     * token has been used. Small negative values will reduce the vocabulary of a response. Larger negative values will
     * cause the model to start repeating a common token until it hits the {@link #maxOutputTokens} limit: "...the the
     * the the the...".
     *
     * @since 0.2.0
     */
    @JsonProperty("frequencyPenalty")
    private Double frequencyPenalty;

    /**
     * Optional. If true, export the logprobs results in response.
     *
     * @since 0.2.0
     */
    @JsonProperty("responseLogprobs")
    private Boolean responseLogprobs;

    /**
     * Optional. Only valid if {@link #responseLogprobs} is true. This sets the number of top logprobs to return at each
     * decoding step in the `Candidate.logprobs_result`.
     *
     * @since 0.2.0
     */
    @JsonProperty("logprobs")
    private Integer logprobs;
}