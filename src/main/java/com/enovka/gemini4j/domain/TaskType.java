package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.type.TaskTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the type of task for which the embedding will be used. This is
 * used to provide context to the embedding model, potentially improving its
 * performance.
 *
 * @author Everson Novka <enovka@gmail.com>
 */
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class TaskType {

    /**
     * The specific task type from the {@link TaskTypeEnum}. This field is
     * required when embedding content. For example, setting this to
     * {@link TaskTypeEnum#RETRIEVAL_QUERY} indicates that the embedded text is
     * a search query.
     */
    @JsonProperty("taskType")
    private TaskTypeEnum taskType;
}