package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the type of task for which an embedding will be used.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum TaskTypeEnum {

    /**
     * The task type is unspecified. This value will default to one of the other
     * enum values.
     */
    @JsonProperty("TASK_TYPE_UNSPECIFIED")
    TASK_TYPE_UNSPECIFIED,

    /**
     * The embedding will be used for a retrieval query. The given text
     * represents a search query.
     */
    @JsonProperty("RETRIEVAL_QUERY")
    RETRIEVAL_QUERY,

    /**
     * The embedding will be used for a retrieval document. The given text
     * represents a document within the search corpus.
     */
    @JsonProperty("RETRIEVAL_DOCUMENT")
    RETRIEVAL_DOCUMENT,

    /**
     * The embedding will be used for semantic similarity tasks (STS).
     */
    @JsonProperty("SEMANTIC_SIMILARITY")
    SEMANTIC_SIMILARITY,

    /**
     * The embedding will be used for classification tasks. The given text will
     * be classified into one or more categories.
     */
    @JsonProperty("CLASSIFICATION")
    CLASSIFICATION,

    /**
     * The embedding will be used for clustering tasks.
     */
    @JsonProperty("CLUSTERING")
    CLUSTERING,

    /**
     * The embedding will be used for question answering tasks.
     */
    @JsonProperty("QUESTION_ANSWERING")
    QUESTION_ANSWERING,

    /**
     * The embedding will be used for fact verification tasks.
     */
    @JsonProperty("FACT_VERIFICATION")
    FACT_VERIFICATION
}