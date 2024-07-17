package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * The Schema object allows the definition of input and output data types. These
 * types can be objects, but also primitives and arrays. Represents a select
 * subset of an OpenAPI 3.0 schema object.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Schema {

    /**
     * Required. Data type.
     */
    @JsonProperty("type")
    private Type type;

    /**
     * Optional. The format of the data. This is used only for primitive
     * datatypes. Supported formats: for NUMBER type: float, double for INTEGER
     * type: int32, int64
     */
    @JsonProperty("format")
    private String format;

    /**
     * Optional. A brief description of the parameter. This could contain
     * examples of use. Parameter description may be formatted as Markdown.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Optional. Indicates if the value may be null.
     */
    @JsonProperty("nullable")
    private Boolean nullable;

    /**
     * Optional. Possible values of the element of Type.STRING with enum format.
     * For example, we can define an Enum Direction as: {type:STRING,
     * format:enum, enum:["EAST", NORTH", "SOUTH", "WEST"]}
     */
    @JsonProperty("enum")
    private List<String> enumValues;

    /**
     * Optional. Properties of Type.OBJECT.
     * <p>
     * An object containing a list of "key": value pairs. Example: { "name":
     * "wrench", "mass": "1.3kg", "count": "3" }.
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