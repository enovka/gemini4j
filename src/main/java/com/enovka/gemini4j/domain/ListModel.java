package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.response.AbstractGeminiResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Response from ListModel containing a paginated list of Models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ListModel extends AbstractGeminiResponse {

    /**
     * The returned Models.
     */
    @JsonProperty("models")
    private List<Model> models;

    /**
     * A token, which can be sent as `pageToken` to retrieve the next page.
     * <p>
     * If this field is omitted, there are no more pages.
     */
    @JsonProperty("nextPageToken")
    private String nextPageToken;

}