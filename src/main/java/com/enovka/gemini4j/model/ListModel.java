package com.enovka.gemini4j.model;

import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Response from ListModel containing a paginated list of Models. This class
 * represents the response from the `models.list` endpoint, which provides a
 * list of available Gemini models.
 *
 * <p>It includes a list of {@link Model} objects and a next page token for
 * retrieving subsequent pages of models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with", builderMethodName = "newBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class ListModel extends AbstractResponse {

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