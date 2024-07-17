package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.TypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an OpenAPI data type used for defining parameters and schemas in
 * the Gemini API. This class aligns with the OpenAPI 3.0.3 specification for
 * data types, providing a standardized way to specify the type of data used in
 * requests and responses. You can refer to the full OpenAPI data type
 * documentation here: <a
 * href="https://spec.openapis.org/oas/v3.0.3#data-types">https://spec.openapis.org/oas/v3.0.3#data-types</a>
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    /**
     * The specific OpenAPI data type, as defined by the {@link TypeEnum}. For
     * example, setting this to {@link TypeEnum#STRING} indicates a string data
     * type.
     */
    @JsonProperty("type")
    private TypeEnum type;
}