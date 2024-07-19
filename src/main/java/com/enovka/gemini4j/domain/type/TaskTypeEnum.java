package com.enovka.gemini4j.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Defines the type of task for which a text embedding will be used in the
 * Gemini API. This enum provides context to the embedding model, potentially
 * improving its performance and relevance for specific tasks.
 * <p>
 * By specifying the intended task type when generating embeddings, developers
 * can guide the model to create embeddings that are better suited for the
 * downstream application, whether it's search, classification, clustering, or
 * question answering.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 */
@Getter
public enum TaskTypeEnum {

    /**
     * **Unspecified Task Type:** The task type is unspecified. This value
     * should not be used. Always explicitly choose a relevant task type from
     * the other enum values to provide the embedding model with the necessary
     * context for optimal performance.
     */
    @JsonProperty("TASK_TYPE_UNSPECIFIED")
    TASK_TYPE_UNSPECIFIED,

    /**
     * **Retrieval Query:** The embedding will be used for a retrieval query,
     * where the given text represents a search query. This indicates that the
     * embedding should capture the semantic meaning of the query to effectively
     * retrieve relevant documents.
     * <br> **Example:**  "What are the benefits of using solar energy?"
     */
    @JsonProperty("RETRIEVAL_QUERY")
    RETRIEVAL_QUERY,

    /**
     * **Retrieval Document:** The embedding will be used for a retrieval
     * document, representing a document within the corpus being searched. This
     * indicates that the embedding should capture the semantic content of the
     * document to enable accurate matching with search queries.
     * <br> **Example:** "Solar energy is a clean and renewable source of
     * energy..."
     */
    @JsonProperty("RETRIEVAL_DOCUMENT")
    RETRIEVAL_DOCUMENT,

    /**
     * **Semantic Similarity (STS):** The embedding will be used for Semantic
     * Textual Similarity (STS) tasks. These tasks involve measuring the
     * semantic similarity between two pieces of text. Embeddings generated for
     * STS should capture the semantic relationships between words and concepts
     * to accurately reflect the degree of similarity.
     * <br> **Example:** Comparing the similarity between "climate change" and
     * "global warming."
     */
    @JsonProperty("SEMANTIC_SIMILARITY")
    SEMANTIC_SIMILARITY,

    /**
     * **Classification:** The embedding will be used for classification tasks,
     * where the given text will be categorized into one or more predefined
     * classes or categories. Embeddings for classification should capture the
     * distinguishing features of the text that are relevant to the target
     * categories.
     * <br> **Example:** Classifying news articles into topics like "politics,"
     * "sports," or "entertainment."
     */
    @JsonProperty("CLASSIFICATION")
    CLASSIFICATION,

    /**
     * **Clustering:** The embedding will be used for clustering tasks, where
     * similar texts are grouped together based on their semantic content.
     * Embeddings for clustering should capture the underlying semantic
     * structure of the text to enable effective grouping of related documents.
     * <br> **Example:** Grouping customer reviews based on sentiment
     * (positive, negative, neutral).
     */
    @JsonProperty("CLUSTERING")
    CLUSTERING,

    /**
     * **Question Answering:** The embedding will be used for question answering
     * tasks, where the goal is to find an answer to a given question within a
     * corpus of text. Embeddings for question answering should capture the
     * semantic relationships between the question and potential answer
     * passages.
     * <br> **Example:** "What is the capital of France?"
     */
    @JsonProperty("QUESTION_ANSWERING")
    QUESTION_ANSWERING,

    /**
     * **Fact Verification:** The embedding will be used for fact verification
     * tasks, aiming to determine the truthfulness of a given claim or
     * statement. Embeddings for fact verification should capture the semantic
     * representation of the claim and its potential evidence sources.
     * <br> **Example:**  Verifying the claim "The Earth is flat."
     */
    @JsonProperty("FACT_VERIFICATION")
    FACT_VERIFICATION
}