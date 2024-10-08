package com.enovka.gemini4j.model;

import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Information about a Generative Language Model. This class represents a
 * Gemini language model and provides information about its capabilities,
 * limitations, and supported features.
 *
 * <p>It includes details such as the model's name, base model ID, version,
 * display name, description, input and output token limits, supported
 * generation methods, temperature, maximum temperature, top-p, and top-k.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with", builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Model extends AbstractResponse {

    /**
     * Required. The resource name of the Model. Refer to Model variants for
     * all allowed values.
     * <p>
     * Format: `models/{model}` with a `{model}` naming convention of:
     * <p>
     * `"{baseModelId}-{version}"`
     * <p>
     * Examples:
     * <p>
     * - `models/chat-bison-001`
     */
    @JsonProperty("name")
    private String name;

    /**
     * Required. The name of the base model, pass this to the generation
     * request.
     * <p>
     * Examples:
     * <p>
     * - `chat-bison`
     */
    @JsonProperty("baseModelId")
    private String baseModelId;

    /**
     * Required. The version number of the model.
     * <p>
     * This represents the major version (1.0 or 1.5)
     */
    @JsonProperty("version")
    private String version;

    /**
     * The human-readable name of the model. E.g. "Chat Bison".
     * <p>
     * The name can be up to 128 characters long and can consist of any UTF-8
     * characters.
     */
    @JsonProperty("displayName")
    private String displayName;

    /**
     * A short description of the model.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Maximum number of input tokens allowed for this model.
     */
    @JsonProperty("inputTokenLimit")
    private Integer inputTokenLimit;

    /**
     * Maximum number of output tokens available for this model.
     */
    @JsonProperty("outputTokenLimit")
    private Integer outputTokenLimit;

    /**
     * The model's supported generation methods.
     * <p>
     * The method names are defined as Pascal case strings, such as
     * `generateMessage` which correspond to API methods.
     */
    @JsonProperty("supportedGenerationMethods")
    private List<String> supportedGenerationMethods;

    /**
     * Controls the randomness of the output.
     * <p>
     * Values can range over [0.0, maxTemperature], inclusive. A higher value
     * will produce responses that are more varied, while a value closer to 0.0
     * will typically result in less surprising responses from the model. This
     * value specifies the default to be used by the backend while making the
     * call to the model.
     */
    @JsonProperty("temperature")
    private Double temperature;

    /**
     * Max value supported by the model.
     * <p>
     * Values can range over [0.1, 2.0]
     */
    @JsonProperty("maxTemperature")
    private Double maxTemperature;

    /**
     * For Nucleus sampling.
     * <p>
     * Nucleus sampling considers the smallest set of tokens whose probability
     * sum is at least topP. This value specifies the default to be used by the
     * backend while making the call to the model.
     */
    @JsonProperty("topP")
    private Double topP;

    /**
     * For Top-k sampling.
     * <p>
     * Top-k sampling considers the set of topK most probable tokens. This
     * value specifies the default to be used by the backend while making the
     * call to the model. If empty, indicates the model doesn't use top-k
     * sampling, and topK isn't allowed as a generation parameter.
     */
    @JsonProperty("topK")
    private Integer topK;
}