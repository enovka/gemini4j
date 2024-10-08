package com.enovka.gemini4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * URI based data.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
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