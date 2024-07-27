package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.*;
import com.enovka.gemini4j.domain.request.CachedContentRequest;
import com.enovka.gemini4j.resource.builder.spec.AbstractBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating {@link CachedContent} instances. This builder provides a
 * fluent API for configuring all aspects of the request, including the content
 * to be cached, model, system instruction, tool config and expiration.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class CachedContentRequestBuilder extends
        AbstractBuilder<CachedContentRequest> {

    private List<Content> contents;
    private List<Tool> tools;
    private String model;
    private Content systemInstruction;
    private ToolConfig toolConfig;
    private String expireTime;
    private String ttl;
    private String name;
    private String displayName;

    /**
     * Private constructor to enforce a builder pattern.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     */
    private CachedContentRequestBuilder(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * Creates a new instance of the CachedContentRequestBuilder.
     *
     * @param geminiClient The GeminiClient instance to use for API
     * communication.
     * @return A new CachedContentRequestBuilder instance.
     */
    public static CachedContentRequestBuilder builder(
            GeminiClient geminiClient) {
        return new CachedContentRequestBuilder(geminiClient);
    }

    /**
     * Adds a content to be cached.
     *
     * @param content The content to cache.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withContent(Content content) {
        if (this.contents == null) {
            this.contents = new ArrayList<>();
        }
        this.contents.add(content);
        return this;
    }

    /**
     * Adds a text content to be cached.
     *
     * @param text The text content to cache.
     * @param role Role to use (user, model)
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withTextContent(String text,
                                                       String role) {
        if (this.contents == null) {
            this.contents = new ArrayList<>();
        }
        this.contents.add(Content.builder()
                .withRole(role)
                .withParts(List.of(PartBuilder.builder(this)
                        .withText(text)
                        .build()))
                .build());
        return this;
    }

    /**
     * Adds a tool to be cached.
     *
     * @param tool The tool to cache.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withTool(Tool tool) {
        if (this.tools == null) {
            this.tools = new ArrayList<>();
        }
        this.tools.add(tool);
        return this;
    }

    /**
     * Adds a function declaration tool to be cached.
     *
     * @param functionDeclaration The function declaration to cache.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withFunctionDeclarationTool(
            FunctionDeclaration functionDeclaration) {
        if (this.tools == null) {
            this.tools = new ArrayList<>();
        }
        this.tools.add(Tool.builder()
                .withFunctionDeclarations(List.of(functionDeclaration))
                .build());
        return this;
    }

    /**
     * Sets the model to be used for cached content.
     *
     * @param model The model to use.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    /**
     * Sets the system instruction to be cached.
     *
     * @param systemInstruction The system instruction to cache.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withSystemInstruction(
            Content systemInstruction) {
        this.systemInstruction = systemInstruction;
        return this;
    }

    /**
     * Sets the system instruction to be cached as text.
     *
     * @param systemInstruction The system instruction text to cache.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withSystemInstruction(
            String systemInstruction) {
        this.systemInstruction = Content.builder()
                .withParts(List.of(PartBuilder.builder(this)
                        .withText(systemInstruction)
                        .build()))
                .build();
        return this;
    }

    /**
     * Sets the tool config to be cached.
     *
     * @param toolConfig The tool config to cache.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withToolConfig(ToolConfig toolConfig) {
        this.toolConfig = toolConfig;
        return this;
    }

    /**
     * Sets the expiration time for the cached content as a String.
     *
     * @param expireTime The expiration time in string format.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withExpireTime(String expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    /**
     * Sets the expiration time for the cached content.
     *
     * @param expireTime The expiration time as an Instant.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withExpireTime(Instant expireTime) {
        this.expireTime = expireTime.toString();
        return this;
    }

    /**
     * Sets the time-to-live (TTL) for the cached content.
     *
     * @param ttl The time-to-live.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withTtl(String ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * Sets the name of the cached content.
     *
     * @param name The name of the cached content.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the display name of the cached content.
     *
     * @param displayName The display name of the cached content.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CachedContentRequest build() {
        if (model == null) {
            throw new IllegalArgumentException(
                    "Model is required.");
        }
        return CachedContentRequest.builder()
                .withContents(contents)
                .withTools(tools)
                .withModel(model)
                .withSystemInstruction(systemInstruction)
                .withToolConfig(toolConfig)
                .withExpireTime(expireTime)
                .withTtl(ttl)
                .withName(name)
                .withDisplayName(displayName)
                .build();
    }

    /**
     * Builder for creating {@link Part} instances.
     *
     * @author Everson Novka &lt;enovka@gmail.com&gt;
     * @since 0.0.2
     */
    public static class PartBuilder extends AbstractBuilder<Part> {

        private String text;
        private FunctionCall functionCall;

        /**
         * Private constructor to enforce a builder pattern.
         *
         * @param parentBuilder The parent {@link AbstractBuilder} instance.
         */
        public PartBuilder(AbstractBuilder<?> parentBuilder) {
            super(parentBuilder);
        }

        /**
         * Creates a new instance of the PartBuilder.
         *
         * @param parentBuilder The parent {@link AbstractBuilder} instance.
         * @return A new PartBuilder instance.
         */
        public static PartBuilder builder(AbstractBuilder<?> parentBuilder) {
            return new PartBuilder(parentBuilder);
        }

        /**
         * Sets the inline text for the part.
         *
         * @param text The inline text.
         * @return The builder instance for method chaining.
         */
        public PartBuilder withText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Sets the function call for the part.
         *
         * @param functionCall The function call.
         * @return The builder instance for method chaining.
         */
        public PartBuilder withFunctionCall(FunctionCall functionCall) {
            this.functionCall = functionCall;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Part build() {
            return Part.builder()
                    .withText(text)
                    .withFunctionCall(functionCall)
                    .build();
        }
    }
}