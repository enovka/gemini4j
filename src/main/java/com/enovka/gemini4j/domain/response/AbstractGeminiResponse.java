package com.enovka.gemini4j.domain.response;

import com.enovka.gemini4j.domain.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AbstractGeminiResponse {

    /**
     * Gemini api object error
     */
    @JsonProperty("error")
    private ErrorResponse error;

}
