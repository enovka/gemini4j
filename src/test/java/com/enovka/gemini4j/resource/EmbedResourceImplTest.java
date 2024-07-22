package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.impl.EmbedResourceImpl;
import com.enovka.gemini4j.resource.spec.EmbedResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link EmbedResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public class EmbedResourceImplTest {

    private EmbedResource embedResource;

    @BeforeEach
    public void setUp() {
        GeminiClient geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .withModel("text-embedding-004")
                .build();

        embedResource = ResourceBuilder.builder(geminiClient)
                .buildEmbedResource();
    }

    /**
     * Tests the {@link EmbedResourceImpl#embedContent(EmbedContentRequest)}
     * method with different text inputs.
     *
     * @throws ResourceException If an error occurs during the embedding
     * generation process.
     */
    @Test
    public void testEmbedContent()
            throws ResourceException {
        String[] testTexts = {
                "This is a test sentence.",
                "Another sentence for embedding.",
                "Short text.",
                "Long text with more words to embed."
        };

        for (String text : testTexts) {
            EmbedContentResponse response = embedResource.embedContent(
                    embedResource.embedContentBuilder(text)
                            .build());
            assertNotNull(response.getEmbedding(),
                    "Embedding should not be null for text: " + text);
            assertFalse(response.getEmbedding().getValues().isEmpty(),
                    "Embedding values should not be empty for text: " + text);
        }
    }
}