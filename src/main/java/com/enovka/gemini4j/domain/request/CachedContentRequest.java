package com.enovka.gemini4j.domain.request;

import com.enovka.gemini4j.domain.CachedContent;
import com.enovka.gemini4j.domain.ErrorResponse;
import com.enovka.gemini4j.domain.response.AbstractGeminiResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Represents a request for creating or updating cached content in the Gemini
 * API. This class extends {@link CachedContent} and inherits from
 * {@link AbstractGeminiResponse} to ensure proper authentication and error
 * handling.
 * <p>
 * Cached content is preprocessed content that can be used in later requests to
 * the GenerativeService, offering potential cost and speed savings. It can only
 * be used with the model it was created for.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "with")
public class CachedContentRequest extends CachedContent {

    /**
     * The error response from the Gemini API, if any. This field is ignored
     * during serialization to prevent potential conflicts with the
     * {@code error} field in the {@link AbstractGeminiResponse} class.
     */
    @JsonIgnore
    private transient ErrorResponse error;

    /**
     * Default constructor for the CachedContentRequest.
     */
    public CachedContentRequest() {
    }
}