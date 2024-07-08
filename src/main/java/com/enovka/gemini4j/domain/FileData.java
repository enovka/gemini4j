package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * URI based data.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileData {

  /**
   * Optional. The IANA standard MIME type of the source data.
   */
  @JsonProperty("mimeType")
  private String mimeType;

  /**
   * Required. URI.
   */
  @JsonProperty("fileUri")
  private String fileUri;

}