package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * URI based data.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
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