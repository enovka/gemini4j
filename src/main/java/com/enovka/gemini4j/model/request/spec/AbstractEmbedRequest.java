package com.enovka.gemini4j.model.request.spec;

import com.enovka.gemini4j.model.type.TaskTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@NoArgsConstructor
public class AbstractEmbedRequest extends AbstractSimpleRequest implements Request{

    /**
     * Optional. Optional task type for which the embeddings will be used. Can
     * only be set for models/embedding-001.
     */
    @JsonProperty("taskType")
    @Builder.Default
    private TaskTypeEnum taskType = TaskTypeEnum.TASK_TYPE_UNSPECIFIED;

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
    @Builder.Default
    private Integer outputDimensionality = 768;
}
