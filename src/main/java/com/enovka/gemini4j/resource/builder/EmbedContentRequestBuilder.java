package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.type.TaskTypeEnum;
import com.enovka.gemini4j.resource.builder.spec.AbstractBuilder;

/**
 * Builder for creating {@link EmbedContentRequest} instances. This builder
 * provides a fluent API for configuring all aspects of the request, including
 * the content to be embedded, task type, title, and output dimensionality.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class EmbedContentRequestBuilder extends
        AbstractBuilder<EmbedContentRequest> {

    private Content content;
    private TaskTypeEnum taskType;
    private String title;
    private Integer outputDimensionality;

    /**
     * Private constructor to enforce builder pattern.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    private EmbedContentRequestBuilder(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * Creates a new instance of the EmbedContentRequestBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     * @return A new EmbedContentRequestBuilder instance.
     */
    public static EmbedContentRequestBuilder builder(
            GeminiClient geminiClient) {
        return new EmbedContentRequestBuilder(geminiClient);
    }

    /**
     * Sets the content to be embedded.
     *
     * @param content The content to embed.
     * @return The builder instance for method chaining.
     */
    public EmbedContentRequestBuilder withContent(Content content) {
        this.content = content;
        return this;
    }

    /**
     * Sets the text to be embedded. This method creates a new {@link Content}
     * object with the given text and sets it as the content to be embedded.
     *
     * @param text The text to embed.
     * @return The builder instance for method chaining.
     */
    public EmbedContentRequestBuilder withText(String text) {
        if (text != null && !text.isEmpty()) {
            this.content = ContentBuilder.builder(this)
                    .withText(text)
                    .build();
        }
        return this;
    }

    /**
     * Sets the task type for which the embedding will be used.
     *
     * @param taskType The task type enum.
     * @return The builder instance for method chaining.
     */
    public EmbedContentRequestBuilder withTaskType(TaskTypeEnum taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     * Sets the optional title for the content. This is only applicable when the
     * task type is {@link TaskTypeEnum#RETRIEVAL_DOCUMENT}.
     *
     * @param title The title of the content.
     * @return The builder instance for method chaining.
     */
    public EmbedContentRequestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the optional output dimensionality for the embedding. If set,
     * excessive values in the output embedding are truncated from the end.
     *
     * @param outputDimensionality The desired output dimensionality.
     * @return The builder instance for method chaining.
     */
    public EmbedContentRequestBuilder withOutputDimensionality(
            Integer outputDimensionality) {
        this.outputDimensionality = outputDimensionality;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmbedContentRequest build() {
        if (content == null) {
            throw new IllegalArgumentException(
                    "Content with text is required.");
        }
        return EmbedContentRequest.builder()
                .withModel(geminiClient.getModel())
                .withContent(content)
                .withTaskType(taskType)
                .withTitle(title)
                .withOutputDimensionality(outputDimensionality)
                .build();
    }
}