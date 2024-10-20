package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.ListModel;
import com.enovka.gemini4j.model.Model;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.ModelResource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for the {@link ModelResource} implementation.  It verifies the resource's ability
 * to interact with the Gemini API's model endpoints, including listing available models and
 * retrieving a specific model by name.  It also tests error handling and ensures the correct
 * exceptions are thrown for invalid requests.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelResourceTest {

    private static final String GEMINI_API_KEY = System.getenv("GEMINI_API_KEY");
    private static GeminiClient geminiClient;
    private static ModelResource modelResource;

    /**
     * Initializes the Gemini client and the ModelResource before all tests.  This method retrieves
     * the API key from environment variables and sets up the necessary resources for interacting
     * with the Gemini API.
     *
     * @since 0.2.0
     */
    @BeforeAll
    void init() {
        geminiClient = GeminiClientBuilder.builder()
                .withApiKey(GEMINI_API_KEY)
                .withModel("models/gemini-1.5-flash-001")
                .withResponseTimeout(60000 * 20)
                .withRateLimiter(5, Duration.ofMinutes(1))
                .build();

        modelResource = ResourceBuilder.builder(geminiClient).buildModelResource();
    }

    /**
     * Tests the {@link ModelResource#listModels()} method.  Verifies that the returned ListModel is
     * not null, contains models, and that the first model has a valid name.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void listModels_shouldReturnValidListModel() throws ResourceException {
        ListModel listModel = modelResource.listModels();

        assertNotNull(listModel, "ListModel should not be null");
        assertFalse(listModel.getModels().isEmpty(), "Model list should not be empty");

        Model firstModel = listModel.getModels().get(0);
        assertNotNull(firstModel.getName(), "First model name should not be null");
    }

    /**
     * Tests the {@link ModelResource#getModel(String)} method with a valid model name.  Verifies that
     * the returned Model is not null and has the expected name.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void getModel_withValidModelName_shouldReturnValidModel() throws ResourceException {
        ListModel listModel = modelResource.listModels();
        Model expectedModel = listModel.getModels().get(0);
        String modelName = expectedModel.getName();

        Model actualModel = modelResource.getModel(modelName);

        assertNotNull(actualModel, "Retrieved model should not be null");
        assertEquals(expectedModel.getName(), actualModel.getName(), "Model names should match");
    }

    /**
     * Tests the {@link ModelResource#getModel(String)} method with an invalid model name.  Verifies
     * that a ResourceException is thrown when trying to retrieve a model that doesn't exist.
     *
     * @since 0.2.0
     */
    @Test
    void getModel_withInvalidModelName_shouldThrowResourceException() {
        String invalidModelName = "models/invalid-model-name";

        assertThrows(ResourceException.class, () -> modelResource.getModel(invalidModelName));
    }

    /**
     * Tests the {@link ModelResource#getSupportedMethods()} method. Verifies that the returned list
     * of supported model methods is not null and matches the expected list.
     *
     * @since 0.2.0
     */
    @Test
    void getSupportedMethods_shouldReturnSupportedMethods() {
        List<SupportedModelMethod> supportedMethods = modelResource.getSupportedMethods();

        assertNotNull(supportedMethods, "Supported methods list should not be null");
        assertIterableEquals(List.of(), supportedMethods, "Supported methods list should be empty"); // Using assertIterableEquals from JUnit 5.11.2
    }


    /**
     * Tests the {@link ModelResource#getGeminiClient()} method. Verifies that the returned GeminiClient
     * is not null and is the same instance used to create the resource.
     *
     * @since 0.2.0
     */
    @Test
    void getGeminiClient_shouldReturnCorrectInstance() {
        GeminiClient client = modelResource.getGeminiClient();

        assertNotNull(client, "GeminiClient should not be null");
        assertSame(geminiClient, client, "GeminiClient instances should be the same");
    }
}