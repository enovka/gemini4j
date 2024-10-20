package com.enovka.gemini4j.resource.builder.request;

import com.enovka.gemini4j.model.CacheContent;
import com.enovka.gemini4j.model.Content;
import com.enovka.gemini4j.model.Tool;
import com.enovka.gemini4j.model.ToolConfig;
import com.enovka.gemini4j.model.request.CacheRequest;
import com.enovka.gemini4j.resource.builder.request.spec.AbstractContentRequestBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link CacheRequest} instances.  This builder provides a fluent API
 * for configuring requests related to cached content in the Gemini API, including setting the
 * content to cache, model, system instructions, tool configuration, and expiration parameters.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public class CacheRequestBuilder extends AbstractContentRequestBuilder<CacheRequestBuilder, CacheRequest> {

    List<Tool> tools = new ArrayList<>();
    private String name;
    private String displayName;
    private String ttl;
    private String expireTime;
    private String cachedContent;
    private ToolConfig toolConfig;

    /**
     * Private constructor to enforce a builder pattern.  Instances of this builder should be
     * created using the {@link #builder()} method.
     *
     * @since 0.2.0
     */
    private CacheRequestBuilder() {
        super();
    }

    /**
     * Creates a new instance of the {@link CacheRequestBuilder}.
     *
     * @return A new {@link CacheRequestBuilder} instance.
     * @since 0.2.0
     */
    public static CacheRequestBuilder builder() {
        return new CacheRequestBuilder();
    }

    /**
     * Sets the name (identifier) for the cached content. The name should follow a specific format
     * as outlined in the Gemini API documentation.  This identifier is used to refer to the cached
     * content in later requests.
     *
     * @param name The name of the cached content, following the format "cachedContents/{id}".
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException If the provided name is null or empty.
     * @since 0.2.0
     */
    protected CacheRequestBuilder withName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
        return this;
    }

    /**
     * Sets the human-readable display name for the cached content. This is an optional field
     * that can be used to provide a more descriptive label for the cached content.
     *
     * @param displayName The human-readable display name for the cached content.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public CacheRequestBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Sets the absolute expiration time for the cached content.  This method now directly sets
     * the 'expireTime' field in the {@code CacheContent} object. The provided value should be a timestamp string
     * in RFC3339 UTC "Zulu" format (e.g., "2024-12-31T23:59:59Z").  Setting this field will clear any
     * previously set TTL value.
     *
     * @param expireTime The timestamp string representing the absolute expiration time.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public CacheRequestBuilder withExpireTime(String expireTime) {
        if (expireTime == null || expireTime.isEmpty()) {
            throw new IllegalArgumentException("ExpireTime cannot be null or empty.");
        }
        this.expireTime = expireTime;
        this.ttl = null;
        return this;
    }

    /**
     * Sets the Time-To-Live (TTL) for the cached content.  This method now directly sets the 'ttl'
     * field in the {@code CacheContent} object.  The provided TTL value should be a duration string in
     * seconds (e.g., "3600s" for one hour). Setting this field will clear any previously set expireTime value.
     *
     * @param ttl The duration string representing the TTL.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public CacheRequestBuilder withTtl(String ttl) {
        if (ttl == null || ttl.isEmpty()) {
            throw new IllegalArgumentException("TTL cannot be null or empty.");
        }
        this.ttl = ttl;
        this.expireTime = null; // Ensure only one expiration type is set
        return this;

    }

    /**
     * Sets the identifier of existing cached content to be used in the request.  This is used
     * to reference previously cached content. The identifier should follow the format
     * 'cachedContents/{cachedContent}'.  If both 'cachedContent' and content parts are provided,
     * 'cachedContent' takes precedence.
     *
     * @param cachedContent The identifier of the existing cached content.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    public CacheRequestBuilder withCachedContent(String cachedContent) {
        this.cachedContent = cachedContent;
        return this;
    }

    /**
     * Adds a tool to the request, enabling the model to interact with external systems or perform
     * specific actions. For details on tools and their usage, refer to the Gemini API
     * documentation.
     *
     * @param tool The tool to add to the request.
     * @return The builder instance for method chaining.
     * @throws IllegalArgumentException if the tool is null.
     * @since 0.2.0
     */
    protected CacheRequestBuilder withTool(Tool tool) {
        if (tool == null) {
            throw new IllegalArgumentException("Tool cannot be null.");
        }
        this.tools.add(tool);
        return this;
    }

    /**
     * Sets the configuration for tools to be used in the request.  This object controls how
     * tools are used during content generation.  For details on tool configurations, refer to
     * the Gemini API documentation.
     *
     * @param toolConfig The configuration object for the tools.
     * @return The builder instance for method chaining.
     * @since 0.2.0
     */
    protected CacheRequestBuilder withToolConfig(ToolConfig toolConfig) {
        this.toolConfig = toolConfig;
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @SuppressWarnings("unchecked")
    @Override
    public CacheRequest build() {
        if (model == null) {
            throw new IllegalStateException("Model is required for CacheRequest.");
        }

        CacheContent.CacheContentBuilder cachedContentBuilder = CacheContent.builder()
                .withModel(model)
                .withTools(tools)
                .withSystemInstruction(systemInstruction)
                .withToolConfig(toolConfig)
                .withName(name)
                .withTtl(ttl)
                .withExpireTime(expireTime)
                .withDisplayName(displayName);

        if (cachedContent != null) {
            cachedContentBuilder.withName(cachedContent);
        } else if (!contents.isEmpty()) {
            cachedContentBuilder.withContents(contents);
        } else {
            throw new IllegalStateException("Either cachedContent or contents must be provided.");
        }

        if (this.ttl != null && this.expireTime != null) {
            throw new IllegalStateException("Set ttl or ExpireTime, only one is permitted");
        }

        return CacheRequest.builder()
                .withCacheContent(cachedContentBuilder.build())
                .build();
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public CacheRequestBuilder withContents(List<Content> contents) {
        return super.withContents(contents);
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    protected CacheRequestBuilder self() {
        return this;
    }
}