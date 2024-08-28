package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.*;
import com.enovka.gemini4j.domain.request.CachedContentRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Builder for creating {@link CachedContent} instances. This builder provides a
 * fluent API for configuring all aspects of the request, including the content
 * to be cached, model, system instruction, tool config and expiration.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@Accessors(chain = true)
public class CachedContentRequestBuilder {

    private final GeminiClient geminiClient;
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
        this.geminiClient = geminiClient;
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
                .withParts(List.of(Part.builder().withText(text).build()))
                .build());
        return this;
    }

    /**
     * Adds a tool with function declarations to the request.
     *
     * @param name The name of the tool.
     * @param description A brief description of the tool.
     * @param functionDeclarationConfigurer A consumer to configure the function
     * declarations for the tool.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withTool(String name,
                                                String description,
                                                Consumer<GenerateContentRequestBuilder.FunctionDeclarationBuilder> functionDeclarationConfigurer) {
        GenerateContentRequestBuilder.FunctionDeclarationBuilder
                functionDeclarationBuilder
                = new GenerateContentRequestBuilder.FunctionDeclarationBuilder();
        functionDeclarationConfigurer.accept(functionDeclarationBuilder);
        this.tools = List.of(Tool.builder()
                .withFunctionDeclarations(functionDeclarationBuilder.build())
                .build());
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
                .withParts(List.of(Part.builder().withText(systemInstruction)
                        .build()))
                .build();
        return this;
    }

    /**
     * Configures the tool configuration for the request.
     *
     * @param toolConfigConfigurer A consumer to configure the tool
     * configuration.
     * @return The builder instance for method chaining.
     */
    public CachedContentRequestBuilder withToolConfig(
            Consumer<GenerateContentRequestBuilder.ToolConfigBuilder> toolConfigConfigurer) {
        GenerateContentRequestBuilder.ToolConfigBuilder toolConfigBuilder
                = new GenerateContentRequestBuilder.ToolConfigBuilder();
        toolConfigConfigurer.accept(toolConfigBuilder);
        this.toolConfig = toolConfigBuilder.build();
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
    public CachedContentRequest build() {
        if (model == null) {
            throw new IllegalArgumentException(
                    "Model is required.");
        }
        if (contents == null) {
            this.contents = new ArrayList<>();
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
}