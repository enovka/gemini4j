package com.enovka.gemini4j.model.request.spec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Abstract base class for simple Gemini API requests. This class implements
 * the {@link Request} interface and provides a common field for the 'model'
 * used in simpler request types. It promotes code reuse and consistency.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractSimpleRequest implements Request {

    /**
     * Required. The model's resource name. This field identifies the Gemini
     * model to be used for the request. The format should be 'models/{model}',
     * where '{model}' is the specific model identifier (e.g.,
     * "models/gemini-1.5-pro"). Ensure that the model specified here is
     * compatible with the request type and the intended operation.
     */
    @JsonProperty("model")
    private String model;
}