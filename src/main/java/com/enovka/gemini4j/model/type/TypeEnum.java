package com.enovka.gemini4j.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the supported OpenAPI data types used for specifying parameters and
 * schemas in the Gemini API. This enum aligns with the OpenAPI 3.0.3
 * specification, providing a standardized way to represent the type of data
 * used in API requests and responses.
 * <p>
 * For a comprehensive understanding of each data type and its usage in OpenAPI,
 * refer to the official documentation: <a
 * href="https://spec.openapis.org/oas/v3.0.3#data-types">https://spec.openapis.org/oas/v3.0.3#data-types</a>
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum TypeEnum {

    /**
     * **Unspecified Data Type:** This value indicates that the data type is not
     * explicitly defined. It should not be used in API requests or definitions.
     * Always choose a specific data type from the other options to ensure
     * proper data validation and processing.
     */
    @JsonProperty("TYPE_UNSPECIFIED")
    TYPE_UNSPECIFIED,

    /**
     * **String Data Type:** Represents textual data, such as names,
     * descriptions, or any sequence of characters.
     */
    @JsonProperty("STRING")
    STRING,

    /**
     * **Number Data Type:**  Represents numerical data, including both integer
     * and floating-point numbers.
     */
    @JsonProperty("NUMBER")
    NUMBER,

    /**
     * **Integer Data Type:** Represents whole numbers without fractional
     * parts.
     */
    @JsonProperty("INTEGER")
    INTEGER,

    /**
     * **Boolean Data Type:** Represents truth values, either `true` or
     * `false`.
     */
    @JsonProperty("BOOLEAN")
    BOOLEAN,

    /**
     * **Array Data Type:** Represents an ordered collection of elements, where
     * each element can be of any data type, including other arrays.
     */
    @JsonProperty("ARRAY")
    ARRAY,

    /**
     * **Object Data Type:** Represents a complex data structure with named
     * properties, where each property has a specific data type.
     */
    @JsonProperty("OBJECT")
    OBJECT
}