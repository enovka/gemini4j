package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Configuration options for model generation and outputs. Not all parameters
 * may be configurable for every model.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerationConfig {

  /**
   * Optional. The set of character sequences (up to 5) that will stop output
   * generation. If specified, the API will stop at the first appearance of a
   * stop sequence. The stop sequence will not be included as part of the
   * response.
   */
  @JsonProperty("stopSequences")
  private List<String> stopSequences;

  /**
   * Optional. Output response mimetype of the generated candidate text.
   * Supported mimetype: `text/plain`: (default) Text output.
   * `application/json`: JSON response to the candidates.
   */
  @JsonProperty("responseMimeType")
  private String responseMimeType;

  /**
   * Optional. Output response schema of the generated candidate text when
   * a response mime type can have schema. Schema can be objects, primitives or
   * arrays and is a subset of OpenAPI schema.
   * <p>
   * If set, a compatible responseMimeType must also be set. Compatible
   * mimetypes: `application/json`: Schema for JSON response.
   */
  @JsonProperty("responseSchema")
  private Schema responseSchema;

  /**
   * Optional. Number of generated responses to return.
   * <p>
   * Currently, this value can only be set to 1. If unset, this will default to
   * 1.
   */
  @JsonProperty("candidateCount")
  private Integer candidateCount;

  /**
   * Optional. The maximum number of tokens to include in a candidate.
   * <p>
   * Note: The default value varies by model, see the Model.output_token_limit
   * attribute of the Model returned from the getModel function.
   */
  @JsonProperty("maxOutputTokens")
  private Integer maxOutputTokens;

  /**
   * Optional. Controls the randomness of the output.
   * <p>
   * Note: The default value varies by model, see the Model.temperature
   * attribute of the Model returned from the getModel function.
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
   * maximum number of tokens to consider, while Nucleus sampling limits the number
   * of tokens based on the cumulative probability.
   * <p>
   * Note: The default value varies by model, see the Model.top_p attribute of
   * the Model returned from the getModel function.
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
   * the Model returned from the getModel function. Empty topK field in the Model
   * indicates the model doesn't apply top-k sampling and doesn't allow setting
   * topK on requests.
   */
  @JsonProperty("topK")
  private Integer topK;

}