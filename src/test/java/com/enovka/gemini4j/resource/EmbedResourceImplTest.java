package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.impl.EmbedResourceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link EmbedResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public class EmbedResourceImplTest {

    private EmbedResourceImpl embedResource;
    private GeminiClient geminiClient;
    private JsonService jsonService;

    @BeforeEach
    public void setUp() {
        geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .withModel("models/text-embedding-004")
                .build();
        jsonService = JsonServiceBuilder.builder().build().build();
        embedResource = new EmbedResourceImpl(geminiClient, jsonService);
    }

    /**
     * Tests the {@link EmbedResourceImpl#embedContent(EmbedContentRequest)}
     * method.
     */
    @Test
    public void testEmbedContent() {
        EmbedContentRequest request = embedResource.embedContentBuilder(
                        "This is a test sentence.")
                .build();
        try {
            EmbedContentResponse response = embedResource.embedContent(request);
            assertNotNull(response.getEmbedding(),
                    "Embedding should not be null.");
        } catch (Exception e) {
            // Handle exceptions appropriately
            System.err.println("Error embedding content: " + e.getMessage());
        }
    }
}