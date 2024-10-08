package com.enovka.gemini4j.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the comparison operators that can be applied to key-value pairs when
 * filtering content based on metadata in the Gemini API. These operators allow
 * you to create precise filtering conditions for retrieving content that
 * matches specific metadata criteria.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum OperatorEnum {
    /**
     * **Unspecified Operator:**  The operator is unspecified. This is the
     * default value and should not be used. Always explicitly choose a valid
     * operator from the other options.
     */
    @JsonProperty("OPERATOR_UNSPECIFIED")
    OPERATOR_UNSPECIFIED,

    /**
     * **Less Than (`&lt;`):**  Compares a numeric metadata value to determine
     * if it is strictly less than the specified value. &lt;br&gt; **Supported
     * Data Types:** Numeric values only.
     */
    @JsonProperty("LESS")
    LESS,

    /**
     * **Less Than or Equal To (`&lt;=`):** Compares a numeric metadata value to
     * determine if it is less than or equal to the specified value. &lt;br&gt;
     * **Supported Data Types:** Numeric values only.
     */
    @JsonProperty("LESS_EQUAL")
    LESS_EQUAL,

    /**
     * **Equal To (`=`):** Compares a metadata value to determine if it is equal
     * to the specified value. &lt;br&gt; **Supported Data Types:**  Numeric and
     * string values.
     */
    @JsonProperty("EQUAL")
    EQUAL,

    /**
     * **Greater Than or Equal To (`&gt;=`):**  Compares a numeric metadata
     * value to determine if it is greater than or equal to the specified value.
     * &lt;br&gt; **Supported Data Types:** Numeric values only.
     */
    @JsonProperty("GREATER_EQUAL")
    GREATER_EQUAL,

    /**
     * **Greater Than (`&gt;`):** Compares a numeric metadata value to determine
     * if it is strictly greater than the specified value. &lt;br&gt;
     * **Supported Data Types:** Numeric values only.
     */
    @JsonProperty("GREATER")
    GREATER,

    /**
     * **Not Equal To (`!=`):** Compares a metadata value to determine if it is
     * not equal to the specified value. &lt;br&gt; **Supported Data Types:**
     * Numeric and string values.
     */
    @JsonProperty("NOT_EQUAL")
    NOT_EQUAL,

    /**
     * **Includes (Contains):**  Checks if a string metadata value with a
     * `StringList` type contains the specified string value. &lt;br&gt;
     * **Supported Data Types:**  String values with `StringList` type only.
     */
    @JsonProperty("INCLUDES")
    INCLUDES,

    /**
     * **Excludes (Does Not Contain):** Checks if a string metadata value with a
     * `StringList` type does not contain the specified string value. &lt;br&gt;
     * **Supported Data Types:** String values with `StringList` type only.
     */
    @JsonProperty("EXCLUDES")
    EXCLUDES
}