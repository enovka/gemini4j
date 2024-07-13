package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.TypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Type contains the list of OpenAPI data types as defined by
 * <a href="https://spec.openapis.org/oas/v3.0.3#data-types">...</a>
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    /**
     * Not specified, should not be used.
     */
    @JsonProperty("TYPE_UNSPECIFIED")
    private com.enovka.gemini4j.domain.type.TypeEnum typeUnspecified;

    /**
     * String type.
     */
    @JsonProperty("STRING")
    private TypeEnum string;

    /**
     * Number type.
     */
    @JsonProperty("NUMBER")
    private com.enovka.gemini4j.domain.type.TypeEnum number;

    /**
     * Integer type.
     */
    @JsonProperty("INTEGER")
    private com.enovka.gemini4j.domain.type.TypeEnum integer;

    /**
     * Boolean type.
     */
    @JsonProperty("BOOLEAN")
    private com.enovka.gemini4j.domain.type.TypeEnum booleanValue;

    /**
     * Array type.
     */
    @JsonProperty("ARRAY")
    private com.enovka.gemini4j.domain.type.TypeEnum array;

    /**
     * Object type.
     */
    @JsonProperty("OBJECT")
    private com.enovka.gemini4j.domain.type.TypeEnum object;

}