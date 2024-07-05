package com.enovka.gemini4j.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Type of task for which the embedding will be used.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskType {

  /**
   * Unset value, which will default to one of the other enum values.
   */
  @JsonProperty("TASK_TYPE_UNSPECIFIED")
  private TaskTypeEnum taskTypeUnspecified;

  /**
   * Specifies the given text is a query in a search/retrieval setting.
   */
  @JsonProperty("RETRIEVAL_QUERY")
  private TaskTypeEnum retrievalQuery;

  /**
   * Specifies the given text is a document from the corpus being searched.
   */
  @JsonProperty("RETRIEVAL_DOCUMENT")
  private TaskTypeEnum retrievalDocument;

  /**
   * Specifies the given text will be used for STS.
   */
  @JsonProperty("SEMANTIC_SIMILARITY")
  private TaskTypeEnum semanticSimilarity;

  /**
   * Specifies that the given text will be classified.
   */
  @JsonProperty("CLASSIFICATION")
  private TaskTypeEnum classification;

  /**
   * Specifies that the embeddings will be used for clustering.
   */
  @JsonProperty("CLUSTERING")
  private TaskTypeEnum clustering;

  /**
   * Specifies that the given text will be used for question answering.
   */
  @JsonProperty("QUESTION_ANSWERING")
  private TaskTypeEnum questionAnswering;

  /**
   * Specifies that the given text will be used for fact verification.
   */
  @JsonProperty("FACT_VERIFICATION")
  private TaskTypeEnum factVerification;

}