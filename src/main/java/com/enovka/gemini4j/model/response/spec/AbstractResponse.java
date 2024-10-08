package com.enovka.gemini4j.model.response.spec;

import com.enovka.gemini4j.model.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Abstract base class for all Gemini API responses. This class provides a
 * common structure for handling errors returned by the Gemini API.  All Gemini
 * API responses should inherit from this class.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Getter
@Setter
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with")
@AllArgsConstructor
public abstract class AbstractResponse {

    /**
     * Gemini API object error.  This field will contain details about any errors
     * encountered during the API request.  A successful request will have this
     * field set to null. Always check this field first to ensure the API call
     * was successful.
     */
    @JsonProperty("error")
    protected ErrorResponse error;

    public AbstractResponse() {
    }
}