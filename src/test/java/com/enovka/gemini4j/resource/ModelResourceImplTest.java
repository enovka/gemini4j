package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.ListModel;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.impl.ModelResourceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for {@link ModelResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public class ModelResourceImplTest {

    private ModelResourceImpl modelResource;
    private GeminiClient geminiClient;
    private JsonService jsonService;

    @BeforeEach
    public void setUp() {
        geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .build();
        jsonService = JsonServiceBuilder.builder().build().build();
        modelResource = new ModelResourceImpl(geminiClient, jsonService);
    }

    /**
     * Tests the {@link ModelResourceImpl#listModels()} method.
     */
    @Test
    public void testListModels() {
        try {
            ListModel models = modelResource.listModels();
            assertFalse(models.getModels().isEmpty(),
                    "Model list should not be empty.");
        } catch (Exception e) {
            // Handle exceptions appropriately
            System.err.println("Error listing models: " + e.getMessage());
        }
    }
}