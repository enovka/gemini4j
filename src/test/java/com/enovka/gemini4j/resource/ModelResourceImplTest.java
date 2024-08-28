package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.ListModel;
import com.enovka.gemini4j.domain.Model;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.ModelResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link com.enovka.gemini4j.resource.impl.ModelResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public class ModelResourceImplTest {

    private ModelResource modelResource;

    @BeforeEach
    public void setUp() {
        GeminiClient geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .build();
        modelResource = ResourceBuilder.builder(geminiClient)
                .buildModelResource();
    }

    /**
     * Tests listing available Gemini models.
     *
     * @throws ResourceException If an error occurs while fetching the models.
     */
    @Test
    public void testListModels() throws ResourceException {
        ListModel models = modelResource.listModels();
        assertNotNull(models, "ListModel should not be null.");
        assertFalse(models.getModels().isEmpty(),
                "Model list should not be empty.");
    }

    /**
     * Tests retrieving a specific Gemini model by its name.
     *
     * @throws ResourceException If an error occurs while fetching the models or
     * the specific model.
     */
    @Test
    public void testGetModel() throws ResourceException {
        ListModel listModel = modelResource.listModels();
        assertFalse(listModel.getModels().isEmpty(),
                "Model list should not be empty.");

        // Get a random model from the list
        Random random = new Random();
        List<Model> models = listModel.getModels();
        Model randomModel = models.get(random.nextInt(models.size()));

        // Fetch the model using its name
        Model fetchedModel = modelResource.getModel(randomModel.getName());

        // Assertions
        assertNotNull(fetchedModel, "Fetched model should not be null.");
        assertEquals(randomModel.getName(), fetchedModel.getName(),
                "Model names should match.");
    }
}