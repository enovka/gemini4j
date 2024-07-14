package com.enovka.gemini4j.domain.request;

import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.TaskType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Request containing the Content for the model to embed.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(setterPrefix = "with")
public class EmbedContentRequest {

    /**
     * Required. The model's resource name. This serves as an ID for the Model
     * to use.
     * <p>
     * This name should match a model name returned by the models.List method.
     * <p>
     * Format: models/{model}
     */
    @JsonProperty("model")
    private String model;

    /**
     * Required. The content to embed. Only the `parts.text` fields will be
     * counted.
     */
    @JsonProperty("content")
    private Content content;

    /**
     * Optional. Optional task type for which the embeddings will be used. Can
     * only be set for models/embedding-001.
     */
    @JsonProperty("taskType")
    private TaskType taskType;

    /**
     * Optional. An optional title for the text. Only applicable when TaskType
     * is RETRIEVAL_DOCUMENT.
     * <p>
     * Note: Specifying a title for RETRIEVAL_DOCUMENT provides better quality
     * embeddings for retrieval.
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Optional reduced dimension for the output embedding. If set,
     * excessive values in the output embedding are truncated from the end.
     * Supported by newer models since 2024, and the earlier model
     * (models/embedding-001) cannot specify this value.
     */
    @JsonProperty("outputDimensionality")
    private Integer outputDimensionality;

}