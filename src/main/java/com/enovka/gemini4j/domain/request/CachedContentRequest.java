package com.enovka.gemini4j.domain.request;

import com.enovka.gemini4j.domain.CachedContent;
import com.enovka.gemini4j.domain.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Represents a cached content.
 * <p>
 * Content that has been preprocessed and can be used in later requests to
 * GenerativeService.
 * <p>
 * Cached content can be only used with the model it was created for.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "with")
public class CachedContentRequest extends CachedContent {

    @JsonIgnore
    private ErrorResponse error;

    public CachedContentRequest() {
    }
}
