package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Raw media bytes.
 * <p>
 * Text should not be sent as raw bytes, use the 'text' field.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Blob {

    /**
     * The IANA standard MIME type of the source data. Examples: - image/png -
     * image/jpeg If an unsupported MIME type is provided, an error will be
     * returned. For a complete list of supported types, see Supported file
     * formats.
     */
    @JsonProperty("mimeType")
    private String mimeType;

    /**
     * Raw bytes for media formats.
     * <p>
     * A base64-encoded string.
     */
    @JsonProperty("data")
    private String data;

}