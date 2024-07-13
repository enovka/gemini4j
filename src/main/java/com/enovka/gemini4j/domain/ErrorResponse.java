package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an error response from the Gemini API.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    /**
     * The error code.
     */
    @JsonProperty("code")
    private Integer code;

    /**
     * The error message.
     */
    @JsonProperty("message")
    private String message;

    /**
     * The error status.
     */
    @JsonProperty("status")
    private String status;
}