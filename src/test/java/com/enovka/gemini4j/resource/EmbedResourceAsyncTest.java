package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.Embedding;
import com.enovka.gemini4j.model.request.BatchEmbedRequest;
import com.enovka.gemini4j.model.request.EmbedRequest;
import com.enovka.gemini4j.model.response.BatchEmbedResponse;
import com.enovka.gemini4j.model.response.EmbedResponse;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.builder.request.BatchEmbedRequestBuilder;
import com.enovka.gemini4j.resource.builder.request.EmbedRequestBuilder;
import com.enovka.gemini4j.resource.spec.EmbedResource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the asynchronous methods of the {@link EmbedResource} implementation. This class
 * provides comprehensive testing of both single and batch embedding functionalities using
 * asynchronous methods and callbacks, ensuring correct responses and handling of various error
 * scenarios. The tests utilize the appropriate builders for request construction and focus on
 * verifying the core business logic of the embedding generation process.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmbedResourceAsyncTest {

    private static final String GEMINI_API_KEY = System.getenv("GEMINI_API_KEY");
    private static GeminiClient geminiClient;

    /**
     * Initializes the Gemini client and EmbedResource before all tests.
     *
     * @since 0.2.0
     */
    @BeforeAll
    static void init() {
        geminiClient = GeminiClientBuilder.builder()
                .withApiKey(GEMINI_API_KEY)
                .withModel("models/text-embedding-004")
                .withResponseTimeout(60000 * 20)
                .withRateLimiter(5, Duration.ofMinutes(1))
                .build();
    }

    /**
     * Cleans up resources after all tests have completed. Closes the Gemini client.
     *
     * @throws Exception If an error occurs during client closure.
     * @since 0.2.0
     */
    @AfterAll
    static void tearDown() throws Exception {
        geminiClient.close();
    }

    /**
     * Tests asynchronous embedding generation with valid text using EmbedRequestBuilder. Verifies a
     * successful response with non-empty embedding values.
     *
     * @throws Exception If an error occurs during the test.
     * @since 0.2.0
     */
    @Test
    void embedContentAsync_withValidText_shouldReturnValidEmbedding() throws Exception {
        EmbedRequest request = EmbedRequestBuilder.builder()
                .withModel(geminiClient.getModelName())
                .withText("This is a test sentence.")
                .build();
        EmbedResource embedResource = ResourceBuilder.builder(geminiClient).buildEmbedResource();
        EmbedResponse response = embedResource.execute(request);
        // Assertions
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getEmbedding(), "Embedding should not be null");
        assertFalse(response.getEmbedding().getValues().isEmpty(), "Embedding values should not be empty");
    }

    /**
     * Tests asynchronous batch embedding generation with valid texts using BatchEmbedRequestBuilder.
     * Verifies a successful response with the correct number of embeddings.
     *
     * @throws Exception If an error occurs during the test.
     * @since 0.2.0
     */
    @Test
    void batchEmbedContentsAsync_withValidTexts_shouldReturnValidEmbeddings() throws Exception {
        List<String> texts = List.of("Sentence 1", "Sentence 2", "Sentence 3");
        BatchEmbedRequest request = BatchEmbedRequestBuilder.builder()
                .withModel(geminiClient.getModelName())
                .withTexts(texts)
                .build();

        EmbedResource embedResource = ResourceBuilder.builder(geminiClient).buildEmbedResource();

        BatchEmbedResponse response = embedResource.execute(request);

        // Assertions
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getEmbeddings(), "Embeddings should not be null");
        assertEquals(texts.size(), response.getEmbeddings().size(), "Number of embeddings should match");
        for (Embedding embedding : response.getEmbeddings()) {
            assertFalse(embedding.getValues().isEmpty(), "Embedding values should not be empty");
        }
    }
}