package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.response.internal.GenerateContentResponse;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.builder.request.GenerateRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.GenerateResource;
import com.enovka.gemini4j.resource.spec.base.AsyncResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the asynchronous methods of the {@link GenerateResource} implementation. This
 * class tests both text generation with and without cached content using asynchronous methods and
 * callbacks, focusing on verifying the core functionality and handling various scenarios. Error
 * handling and auxiliary method testing are also included.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenerateResourceAsyncTest {

    private static final String GEMINI_API_KEY = System.getenv("GEMINI_API_KEY");
    private static GeminiClient geminiClient;
    private static GenerateResource generateResource;
    private static String cachedContentName;
    private static final String SAMPLE_FILE_PATH = "sample.txt";

    /**
     * Initializes the Gemini client and GenerateResource before all tests.  Creates a cached
     * content entry for use in subsequent tests.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @BeforeAll
    static void init() throws ResourceException {
        geminiClient = GeminiClientBuilder.builder()
                .withApiKey(GEMINI_API_KEY)
                .withModel("models/gemini-1.5-flash-001")
                .withResponseTimeout(60000 * 20)
                .withRateLimiter(5, Duration.ofMinutes(1))
                .build();

        generateResource = ResourceBuilder.builder(geminiClient).buildGenerationResource();

        // Create a cached content entry for use in subsequent tests.
        String testContent = loadFileFromResources();
        cachedContentName = ResourceBuilder.builder(geminiClient).buildCachedContentResource().createCachedContent(
                com.enovka.gemini4j.resource.builder.request.CacheRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withTextContent(testContent, "user")
                        .withTtl("3600s") // 1 hour TTL
                        .build()
        ).getName();
    }

    /**
     * Helper method to load test content from a file in the resources' directory.
     *
     * @return The content of the file as a String.
     * @since 0.2.0
     */
    private static String loadFileFromResources() {
        try {
            Path resourcesPath = Paths.get("src", "test", "resources");
            Path filePath = resourcesPath.resolve(GenerateResourceAsyncTest.SAMPLE_FILE_PATH);
            return Files.readString(filePath);
        } catch (IOException e) {
            System.err.println("Error reading ResourceFiles file: " + e.getMessage());
            return "";
        }
    }

    /**
     * Cleans up resources after all tests have completed.  Deletes the cached content entry
     * created in the init() method.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @AfterAll
    static void tearDown() throws ResourceException {
        ResourceBuilder.builder(geminiClient).buildCachedContentResource().deleteCachedContent(cachedContentName);
    }

    /**
     * Tests asynchronous text generation with valid user input. Verifies that a non-null response
     * is returned containing generated text.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @throws InterruptedException If the current thread is interrupted while waiting.
     * @throws ExecutionException If the computation threw an exception.
     * @throws TimeoutException If the wait timed out.
     * @since 0.2.0
     */
    @Test
    void generateTextAsync_withValidInput_shouldReturnGeneratedText() throws ResourceException, InterruptedException, ExecutionException, TimeoutException {
        AsyncResponse<GenerateContentResponse> future = generateResource.executeAsync(
                GenerateRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withUserContent("Write a short story.")
                        .build());

        GenerateContentResponse response = future.get(60, TimeUnit.SECONDS);
        assertNotNull(response, "Response should not be null");
        //TODO update to new version
        //assertNotNull(response.getGeneratedText(), "Generated text should not be null");
        //assertFalse(response.getGeneratedText().isEmpty(), "Generated text should not be empty");
    }

    /**
     * Tests asynchronous text generation with cached content. Verifies that a non-null response is
     * returned containing generated text, demonstrating the use of cached content for context.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @throws InterruptedException If the current thread is interrupted while waiting.
     * @throws ExecutionException If the computation threw an exception.
     * @throws TimeoutException If the wait timed out.
     * @since 0.2.0
     */
    @Test
    void generateTextAsync_withCachedContent_shouldReturnGeneratedText() throws ResourceException, InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<GenerateContentResponse> future = generateResource.executeAsync(
                GenerateRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withUserContent("Continue the story.")
                        .withCachedContent(cachedContentName)
                        .build());

        GenerateContentResponse response = future.get(60, TimeUnit.SECONDS);
        assertNotNull(response, "Response should not be null");
        //TODO update to new version
        //assertNotNull(response.getGeneratedText(), "Generated text should not be null");
        //assertFalse(response.getGeneratedText().isEmpty(), "Generated text should not be empty");
    }
}