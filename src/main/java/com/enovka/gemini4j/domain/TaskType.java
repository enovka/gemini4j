package com.enovka.gemini4j.domain;

import com.enovka.gemini4j.domain.types.TaskTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Type of task for which the embedding will be used.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskType {

  /**
   * Unset value, which will default to one of the other enum values.
   */
  @JsonProperty("TASK_TYPE_UNSPECIFIED")
  private com.enovka.gemini4j.domain.types.TaskTypeEnum taskTypeUnspecified;

  /**
   * Specifies the given text is a query in a search/retrieval setting.
   */
  @JsonProperty("RETRIEVAL_QUERY")
  private com.enovka.gemini4j.domain.types.TaskTypeEnum retrievalQuery;

  /**
   * Specifies the given text is a document from the corpus being searched.
   */
  @JsonProperty("RETRIEVAL_DOCUMENT")
  private com.enovka.gemini4j.domain.types.TaskTypeEnum retrievalDocument;

  /**
   * Specifies the given text will be used for STS.
   */
  @JsonProperty("SEMANTIC_SIMILARITY")
  private com.enovka.gemini4j.domain.types.TaskTypeEnum semanticSimilarity;

  /**
   * Specifies that the given text will be classified.
   */
  @JsonProperty("CLASSIFICATION")
  private com.enovka.gemini4j.domain.types.TaskTypeEnum classification;

  /**
   * Specifies that the embeddings will be used for clustering.
   */
  @JsonProperty("CLUSTERING")
  private com.enovka.gemini4j.domain.types.TaskTypeEnum clustering;

  /**
   * Specifies that the given text will be used for question answering.
   */
  @JsonProperty("QUESTION_ANSWERING")
  private TaskTypeEnum questionAnswering;

  /**
   * Specifies that the given text will be used for fact verification.
   */
  @JsonProperty("FACT_VERIFICATION")
  private com.enovka.gemini4j.domain.types.TaskTypeEnum factVerification;

}