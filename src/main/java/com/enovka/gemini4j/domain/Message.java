package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A message within a conversation.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    /**
     * The content of the message.
     */
    @JsonProperty("content")
    private Content content;

    /**
     * Optional. The producer of the content. Must be either 'user' or 'model'.
     * Useful to set for multi-turn conversations, otherwise can be left blank
     * or unset.
     */
    @JsonProperty("role")
    private String role;

    /**
     * Optional. The user that sent this message.
     */
    @JsonProperty("user")
    private User user;

}