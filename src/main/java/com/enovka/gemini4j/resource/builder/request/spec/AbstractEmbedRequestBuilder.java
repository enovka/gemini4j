package com.enovka.gemini4j.resource.builder.request.spec;

import com.enovka.gemini4j.model.type.TaskTypeEnum;
import com.enovka.gemini4j.resource.builder.request.BatchEmbedRequestBuilder;
import com.enovka.gemini4j.resource.builder.request.EmbedRequestBuilder;

/**
 * Abstract base class for embedding-related request builders, extending {@link AbstractGeminiRequestBuilder}.
 * This class encapsulates the common parameters for embedding requests, such as task type, title,
 * and output dimensionality, promoting code reuse and reducing redundancy between
 * {@link EmbedRequestBuilder} and {@link BatchEmbedRequestBuilder}.
 *
 * @param <B> The concrete builder type.
 * @param <T> The type of request object this builder creates.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class AbstractEmbedRequestBuilder<B extends AbstractEmbedRequestBuilder<B, T>, T> extends AbstractGeminiRequestBuilder<B, T> {

    protected TaskTypeEnum taskType;
    protected String title;
    protected Integer outputDimensionality;


    /**
     * Sets the task type for which the embeddings will be used.  Providing the task type gives
     * context to the embedding model and can potentially improve the quality and relevance of the
     * resulting embeddings. Consult the Gemini API documentation for valid task types and their
     * intended uses.
     *
     * @param taskType The task type for the embedding request.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided task type is null.
     * @see TaskTypeEnum
     * @since 0.2.0
     */
    public B withTaskType(TaskTypeEnum taskType) {
        if (taskType == null) {
            throw new IllegalArgumentException("Task type cannot be null.");
        }
        this.taskType = taskType;
        return self();
    }

    /**
     * Sets the optional title for the content being embedded. This parameter is particularly
     * useful for retrieval tasks, providing additional context for the embedding model and
     * potentially enhancing the relevance of the generated embeddings.  It is commonly used
     * with the {@link TaskTypeEnum#RETRIEVAL_DOCUMENT} task type.
     *
     * @param title The title of the content being embedded.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withTitle(String title) {
        this.title = title;
        return self();
    }

    /**
     * Sets the desired output dimensionality for the embedding. This allows you to control the
     * size of the embedding vectors. Note that different models may have limitations on the supported
     * output dimensionalities. Check the Gemini API documentation for the valid range for your chosen
     * model.  Setting an invalid dimensionality may lead to errors during request processing.
     *
     * @param outputDimensionality The desired dimensionality for the embedding vectors.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public B withOutputDimensionality(Integer outputDimensionality) {
        this.outputDimensionality = outputDimensionality;
        return self();
    }
}
