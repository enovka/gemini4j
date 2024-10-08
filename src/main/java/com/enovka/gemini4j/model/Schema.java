package com.enovka.gemini4j.model;

import com.enovka.gemini4j.model.type.TypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * The `Schema` object allows the definition of input and output data types.
 * These types can be objects, but also primitives and arrays.
 * Represents a select subset of an [OpenAPI 3.0 schema object](https://spec.openapis.org/oas/v3.0.3#schema).
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Schema {

    /**
     * Required. Data type.
     */
    @JsonProperty("type")
    private TypeEnum type;

    /**
     * Optional. The format of the data. This is used only for primitive datatypes. Supported formats: for NUMBER type: float, double for INTEGER type: int32, int64 for STRING type: enum
     */
    @JsonProperty("format")
    private String format;

    /**
     * Optional. A brief description of the parameter. This could contain examples of use. Parameter description may be formatted as Markdown.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Optional. Indicates if the value may be null.
     */
    @JsonProperty("nullable")
    private Boolean nullable;

    /**
     * Optional.
     * Possible values of the element of Type.
     * STRING with enum format.
     * For example, we can define an Enum Direction as: {type:STRING,
     * format:enum, enum:["EAST", NORTH", "SOUTH", "WEST"]}
     */
    @JsonProperty("enum")
    private List<String> enumValues;

    /**
     * Optional. Maximum number of the elements for Type.ARRAY.
     */
    @JsonProperty("maxItems")
    private String maxItems;

    /**
     * Optional. Minimum number of the elements for Type.ARRAY.
     */
    @JsonProperty("minItems")
    private String minItems;

    /**
     * Optional. Properties of Type.OBJECT.
     */
    @JsonProperty("properties")
    private Map<String, Schema> properties;

    /**
     * Optional. Required properties of Type.OBJECT.
     */
    @JsonProperty("required")
    private List<String> required;

    /**
     * Optional. Schema of the elements of Type.ARRAY.
     */
    @JsonProperty("items")
    private Schema items;

}