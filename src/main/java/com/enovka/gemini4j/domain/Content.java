package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * The base structured datatype containing multipart content of a message.
 * <p>
 * A Content includes a `role` field designating the producer of the Content and
 * a `parts` field containing multipart data that contains the content of the
 * message turn.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content {

    /**
     * Ordered Parts that constitute a single message. Parts may have different
     * MIME types.
     */
    @JsonProperty("parts")
    private List<Part> parts;

    /**
     * Optional. The producer of the content. Must be either 'user' or 'model'.
     * Useful to set for multi-turn conversations, otherwise can be left blank
     * or unset.
     */
    @JsonProperty("role")
    private String role;

}