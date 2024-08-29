package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;
import com.enovka.gemini4j.domain.type.TaskTypeEnum;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.EmbedResource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Builder for creating {@link EmbedContentRequest} instances. This builder
 * provides a fluent API for configuring all aspects of the request, including
 * the content to be embedded, task type, title, and output dimensionality.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Accessors(chain = true)
public class EmbedContentRequestBuilder {

    private final GeminiClient geminiClient;
    private final EmbedResource embedResource;
    private Content content;
    private TaskTypeEnum taskType;
    private String title;
    private Integer outputDimensionality;

    /**
     * Constructor for the EmbedContentRequestBuilder.
     *
     * @param embedResource The EmbedResource instance to use for API
     * communication.
     * @since 0.1.3
     */
    public EmbedContentRequestBuilder(EmbedResource embedResource) {
        this.embedResource = embedResource;
        this.geminiClient = embedResource.getGeminiClient();
    }

    /**
     * Creates a new instance of the EmbedContentRequestBuilder.
     *
     * @param embedResource The EmbedResource instance to use for API
     * communication.
     * @return A new EmbedContentRequestBuilder instance.
     * @since 0.1.3
     */
    public static EmbedContentRequestBuilder builder(
            EmbedResource embedResource) {
        return new EmbedContentRequestBuilder(embedResource);
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
            this.content = Content.builder()
                    .withParts(List.of(Part.builder().withText(text).build()))
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
     * Builds the {@link EmbedContentRequest} instance based on the configured
     * parameters.
     *
     * @return The built {@link EmbedContentRequest} instance.
     */
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
                .withOutputDimensionality(outputDimensionality == null ? 768
                        : outputDimensionality)
                .build();
    }

    /**
     * Executes the embed content request and returns the response.
     *
     * @return The {@link EmbedContentResponse} from the Gemini API.
     * @throws ResourceException If an error occurs during the API request.
     * @since 0.1.3
     */
    public EmbedContentResponse execute() throws ResourceException {
        return embedResource.execute(build());
    }
}