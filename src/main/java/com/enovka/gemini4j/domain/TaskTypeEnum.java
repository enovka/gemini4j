package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Type of task for which the embedding will be used.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum TaskTypeEnum {

  @JsonProperty("TASK_TYPE_UNSPECIFIED")
  TASK_TYPE_UNSPECIFIED,

  @JsonProperty("RETRIEVAL_QUERY")
  RETRIEVAL_QUERY,

  @JsonProperty("RETRIEVAL_DOCUMENT")
  RETRIEVAL_DOCUMENT,

  @JsonProperty("SEMANTIC_SIMILARITY")
  SEMANTIC_SIMILARITY,

  @JsonProperty("CLASSIFICATION")
  CLASSIFICATION,

  @JsonProperty("CLUSTERING")
  CLUSTERING,

  @JsonProperty("QUESTION_ANSWERING")
  QUESTION_ANSWERING,

  @JsonProperty("FACT_VERIFICATION")
  FACT_VERIFICATION
}