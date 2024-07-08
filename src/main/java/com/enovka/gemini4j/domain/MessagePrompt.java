package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * The structured textual input given to the model as a prompt.
 * <p>
 * Given a prompt, the model will return what it predicts is the next message in
 * the discussion.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessagePrompt {

  /**
   * Required. The structured textual input given to the model as a prompt.
   * <p>
   * Given a prompt, the model will return what it predicts is the next message
   * in the discussion.
   */
  @JsonProperty("messages")
  private List<Message> messages;

}