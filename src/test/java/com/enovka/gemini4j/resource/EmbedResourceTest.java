package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.Embedding;
import com.enovka.gemini4j.model.response.BatchEmbedResponse;
import com.enovka.gemini4j.model.response.EmbedResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.model.type.TaskTypeEnum;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.builder.request.BatchEmbedRequestBuilder;
import com.enovka.gemini4j.resource.builder.request.EmbedRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.EmbedResource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link EmbedResource} implementation. This class provides comprehensive
 * testing of both single and batch embedding functionalities, ensuring correct responses and
 * handling of various error scenarios. The tests utilize the appropriate builders for request
 * construction and focus on verifying the core business logic of the embedding generation
 * process.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmbedResourceTest {

    private static final String GEMINI_API_KEY = System.getenv("GEMINI_API_KEY");
    private static GeminiClient geminiClient;
    private static EmbedResource embedResource;

    /**
     * Initializes the Gemini client and EmbedResource before all tests.
     *
     * @since 0.2.0
     */
    @BeforeAll
    static void init() { // 'static' is crucial for per-class lifecycle
        geminiClient = GeminiClientBuilder.builder()
                .withApiKey(GEMINI_API_KEY)
                .withModel("models/text-embedding-004")
                .withResponseTimeout(60000 * 20)
                .withRateLimiter(5, Duration.ofMinutes(1))
                .build();

        embedResource = ResourceBuilder.builder(geminiClient).buildEmbedResource();
    }

    /**
     * Tests embedding generation with valid text using EmbedRequestBuilder. Verifies a successful
     * response with non-empty embedding values.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void embedContent_withValidText_shouldReturnValidEmbedding() throws ResourceException {
        EmbedResponse response = embedResource.execute(
                EmbedRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withText("This is a test sentence.")
                        .build()
        );
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getEmbedding(), "Embedding should not be null");
        assertFalse(response.getEmbedding().getValues().isEmpty(), "Embedding values should not be empty");
    }

    /**
     * Tests batch embedding generation with valid texts using BatchEmbedRequestBuilder. Verifies a
     * successful response with the correct number of embeddings.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void batchEmbedContents_withValidTexts_shouldReturnValidEmbeddings() throws ResourceException {
        List<String> texts = List.of("Sentence 1", "Sentence 2", "Sentence 3");
        BatchEmbedResponse response = embedResource.execute(
                BatchEmbedRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withTexts(texts)
                        .build()
        );
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getEmbeddings(), "Embeddings should not be null");
        assertEquals(texts.size(), response.getEmbeddings().size(), "Number of embeddings should match");
        for (Embedding embedding : response.getEmbeddings()) {
            assertFalse(embedding.getValues().isEmpty(), "Embedding values should not be empty");
        }
    }

    /**
     * Tests embedding generation with a specific task type using EmbedRequestBuilder. Verifies a
     * successful response with non-empty embedding values.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void embedContent_withSpecificTaskType_shouldReturnValidEmbedding() throws ResourceException {
        EmbedResponse response = embedResource.execute(
                EmbedRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withText("This is a test sentence.")
                        .withTaskType(TaskTypeEnum.SEMANTIC_SIMILARITY)
                        .build()
        );
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getEmbedding(), "Embedding should not be null");
        assertFalse(response.getEmbedding().getValues().isEmpty(), "Embedding values should not be empty");
    }

    /**
     * Tests batch embedding generation with a specific task type using BatchEmbedRequestBuilder.
     * Verifies a successful response with the correct number of embeddings.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void batchEmbedContents_withSpecificTaskType_shouldReturnValidEmbeddings() throws ResourceException {
        List<String> texts = List.of("Sentence 1", "Sentence 2", "Sentence 3");
        BatchEmbedResponse response = embedResource.execute(
                BatchEmbedRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withTexts(texts)
                        .withTaskType(TaskTypeEnum.SEMANTIC_SIMILARITY)
                        .build()
        );
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getEmbeddings(), "Embeddings should not be null");
        assertEquals(texts.size(), response.getEmbeddings().size(), "Number of embeddings should match");
        for (Embedding embedding : response.getEmbeddings()) {
            assertFalse(embedding.getValues().isEmpty(), "Embedding values should not be empty");
        }
    }


    /**
     * Tests the getModelMethodList() method. Verifies that the returned list of supported
     * model methods is not null and contains only the expected method.
     *
     * @since 0.2.0
     */
    @Test
    void getModelMethodList_shouldReturnCorrectMethod() {
        List<SupportedModelMethod> supportedMethods = embedResource.getModelMethodList();
        assertNotNull(supportedMethods, "Supported methods list should not be null");
        assertEquals(List.of(SupportedModelMethod.EMBED_CONTENT), supportedMethods, "Supported methods list should contain only EMBED_CONTENT");
    }

    /**
     * Tests the getGeminiClient() method. Verifies that the returned GeminiClient is not null and
     * is the same instance used to create the resource.
     *
     * @since 0.2.0
     */
    @Test
    void getGeminiClient_shouldReturnCorrectInstance() {
        GeminiClient client = embedResource.getGeminiClient();
        assertNotNull(client, "GeminiClient should not be null");
        assertSame(geminiClient, client, "GeminiClient instances should be the same");
    }

    /**
     * Tests error handling when the API key is invalid. Verifies that a ResourceException is
     * thrown, indicating that the API key is invalid.
     *
     * @since 0.2.0
     */
    @Test
    void embedContent_withInvalidApiKey_shouldThrowResourceException() {
        GeminiClient clientWithInvalidKey = GeminiClientBuilder.builder()
                .withApiKey("INVALID_API_KEY")
                .withModel("models/text-embedding-004")
                .build();
        EmbedResource resourceWithInvalidKey = ResourceBuilder.builder(clientWithInvalidKey).buildEmbedResource();

        assertThrows(ResourceException.class, () -> resourceWithInvalidKey.execute(
                EmbedRequestBuilder.builder()
                        .withModel(clientWithInvalidKey.getModelName())
                        .withText("test")
                        .build()
        ));
    }

    /**
     * Tests error handling when the model name is invalid. Verifies that a ResourceException is
     * thrown, indicating that the model name is invalid.
     *
     * @since 0.2.0
     */
    @Test
    void embedContent_withInvalidModelName_shouldThrowResourceException() {
        GeminiClient clientWithInvalidModel = GeminiClientBuilder.builder()
                .withApiKey(GEMINI_API_KEY)
                .withModel("invalid-model-name")
                .build();
        EmbedResource resourceWithInvalidModel = ResourceBuilder.builder(clientWithInvalidModel).buildEmbedResource();

        assertThrows(ResourceException.class, () -> resourceWithInvalidModel.execute(
                EmbedRequestBuilder.builder()
                        .withModel(clientWithInvalidModel.getModelName())
                        .withText("test")
                        .build()
        ));
    }
}