package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.BatchEmbedContentsRequest;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.BatchEmbedContentsResponse;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;
import com.enovka.gemini4j.domain.type.TaskTypeEnum;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.EmbedResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link com.enovka.gemini4j.resource.impl.EmbedResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public class EmbedResourceImplTest {

    private static final String EMBEDDING_MODEL = "models/text-embedding-004";
    private static final String TEST_SENTENCE = "This is a test sentence.";
    private static final String TEST_QUESTION
            = "What are the benefits of using solar energy?";
    private static final String TEST_DOCUMENT
            = "Solar energy is a clean and renewable source of energy...";
    private static final String TEST_TITLE = "Benefits of Solar Energy";

    private EmbedResource embedResource;

    /**
     * Generates a single text embedding for the given text.
     *
     * @param embedResource The {@link EmbedResource} instance.
     * @param text The text to generate an embedding for.
     * @return The {@link EmbedContentResponse} containing the generated
     * embedding.
     * @throws ResourceException If an error occurs during embedding
     * generation.
     */
    private static EmbedContentResponse generateSingleEmbedding(
            EmbedResource embedResource, String text)
            throws ResourceException {
        EmbedContentRequest request = embedResource.embedContentBuilder(text)
                .build();
        return embedResource.execute(request);
    }

    /**
     * Generates multiple text embeddings for the given list of texts in a batch
     * request.
     *
     * @param embedResource The {@link EmbedResource} instance.
     * @param texts The list of texts to generate embeddings for.
     * @return The {@link BatchEmbedContentsResponse} containing the generated
     * embeddings.
     * @throws ResourceException If an error occurs during batch embedding
     * generation.
     */
    private static BatchEmbedContentsResponse generateBatchEmbeddings(
            EmbedResource embedResource, List<String> texts)
            throws ResourceException {
        BatchEmbedContentsRequest request
                = embedResource.batchEmbedContentsBuilder(texts)
                .build();
        return embedResource.execute(request);
    }

    /**
     * Generates a text embedding with a specific task type.
     *
     * @param embedResource The {@link EmbedResource} instance.
     * @param text The text to generate an embedding for.
     * @param taskType The task type for which the embedding will be used.
     * @return The {@link EmbedContentResponse} containing the generated
     * embedding.
     * @throws ResourceException If an error occurs during embedding
     * generation.
     */
    private static EmbedContentResponse generateEmbeddingWithTaskType(
            EmbedResource embedResource, String text,
            TaskTypeEnum taskType) throws ResourceException {
        EmbedContentRequest request = embedResource.embedContentBuilder(text)
                .withTaskType(taskType)
                .build();
        return embedResource.execute(request);
    }

    /**
     * Generates a text embedding with a title, specifically for retrieval
     * tasks.
     *
     * @param embedResource The {@link EmbedResource} instance.
     * @param text The text to generate an embedding for.
     * @param title The title of the text.
     * @param taskType The task type for which the embedding will be used
     * (should be RETRIEVAL_DOCUMENT).
     * @return The {@link EmbedContentResponse} containing the generated
     * embedding.
     * @throws ResourceException If an error occurs during embedding
     * generation.
     */
    private static EmbedContentResponse generateEmbeddingWithTitle(
            EmbedResource embedResource, String text, String title,
            TaskTypeEnum taskType) throws ResourceException {
        EmbedContentRequest request = embedResource.embedContentBuilder(text)
                .withTaskType(taskType)
                .withTitle(title)
                .build();
        return embedResource.execute(request);
    }

    @BeforeEach
    public void setUp() {
        GeminiClient geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .withModel(EMBEDDING_MODEL)
                .build();

        embedResource = ResourceBuilder.builder(geminiClient)
                .buildEmbedResource();
    }

    /**
     * Tests the generation of a single embedding.
     *
     * @throws ResourceException If an error occurs during the embedding
     * generation process.
     */
    @Test
    public void testEmbedContent() throws ResourceException {
        EmbedContentResponse response = generateSingleEmbedding(embedResource,
                TEST_SENTENCE);
        assertNotNull(response.getEmbedding(),
                "Embedding should not be null for text: " + TEST_SENTENCE);
        assertFalse(response.getEmbedding().getValues().isEmpty(),
                "Embedding values should not be empty for text: "
                        + TEST_SENTENCE);
    }

    /**
     * Tests the generation of multiple embeddings in a batch request.
     *
     * @throws ResourceException If an error occurs during the batch embedding
     * generation process.
     */
    @Test
    public void testBatchEmbedContent() throws ResourceException {
        List<String> testTexts = List.of(
                "This is a test sentence.",
                "Another sentence for embedding.",
                "Short text.",
                "Long text with more words to embed."
        );

        BatchEmbedContentsResponse response = generateBatchEmbeddings(
                embedResource, testTexts);

        assertNotNull(response.getEmbeddings(),
                "Embeddings should not be null.");
        assertEquals(testTexts.size(), response.getEmbeddings().size(),
                "Number of embeddings should match the number of input texts.");

        for (int i = 0; i < testTexts.size(); i++) {
            assertFalse(response.getEmbeddings().get(i).getValues().isEmpty(),
                    "Embedding values should not be empty for text: "
                            + testTexts.get(i));
        }
    }

    /**
     * Tests the generation of an embedding with a specific task type.
     *
     * @throws ResourceException If an error occurs during the embedding
     * generation process.
     */
    @Test
    public void testGenerateEmbeddingWithTaskType() throws ResourceException {
        EmbedContentResponse response = generateEmbeddingWithTaskType(
                embedResource, TEST_QUESTION,
                TaskTypeEnum.RETRIEVAL_QUERY);
        assertNotNull(response.getEmbedding(),
                "Embedding should not be null for text: " + TEST_QUESTION);
        assertFalse(response.getEmbedding().getValues().isEmpty(),
                "Embedding values should not be empty for text: "
                        + TEST_QUESTION);
    }

    /**
     * Tests the generation of an embedding with a title for retrieval tasks.
     *
     * @throws ResourceException If an error occurs during the embedding
     * generation process.
     */
    @Test
    public void testGenerateEmbeddingWithTitle() throws ResourceException {
        EmbedContentResponse response = generateEmbeddingWithTitle(
                embedResource, TEST_DOCUMENT, TEST_TITLE,
                TaskTypeEnum.RETRIEVAL_DOCUMENT);
        assertNotNull(response.getEmbedding(),
                "Embedding should not be null for text: " + TEST_DOCUMENT);
        assertFalse(response.getEmbedding().getValues().isEmpty(),
                "Embedding values should not be empty for text: "
                        + TEST_DOCUMENT);
    }
}