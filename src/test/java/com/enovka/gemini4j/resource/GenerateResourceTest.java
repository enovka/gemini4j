package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.response.internal.GenerateContentResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.builder.request.GenerateRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.GenerateResource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link GenerateResource} implementation. This class tests both text generation
 * with and without cached content, focusing on verifying the core functionality and handling
 * various scenarios.  Error handling and auxiliary method testing are also included.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenerateResourceTest {

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
    static void init() throws ResourceException  {
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
            Path filePath = resourcesPath.resolve(GenerateResourceTest.SAMPLE_FILE_PATH);
            return Files.readString(filePath);
        } catch (IOException e) {
            System.err.println(
                    "Error reading ResourceFiles file: " + e.getMessage());
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
     * Tests text generation with valid user input. Verifies that a non-null response is returned
     * containing generated text.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void generateText_withValidInput_shouldReturnGeneratedText() throws ResourceException {
        GenerateContentResponse response = generateResource.execute(
                GenerateRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withUserContent("Write a short story.")
                        .build()
        );
        assertNotNull(response, "Response should not be null");
        //TODO update to new version
        //assertNotNull(response.getGeneratedText(), "Generated text should not be null");
        //assertFalse(response.getGeneratedText().isEmpty(), "Generated text should not be empty");
    }

    /**
     * Tests text generation with cached content. Verifies that a non-null response is returned
     * containing generated text, demonstrating the use of cached content for context.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void generateText_withCachedContent_shouldReturnGeneratedText() throws ResourceException {
        GenerateContentResponse response = generateResource.execute(
                GenerateRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withUserContent("Continue the story.")
                        .withCachedContent(cachedContentName)
                        .build()
        );
        assertNotNull(response, "Response should not be null");
        //TODO update to new version
        //assertNotNull(response.getGeneratedText(), "Generated text should not be null");
        //assertFalse(response.getGeneratedText().isEmpty(), "Generated text should not be empty");
    }


    /**
     * Tests text generation with invalid API key. Verifies that a ResourceException is thrown,
     * indicating an invalid API key.
     *
     * @since 0.2.0
     */
    @Test
    void generateText_withInvalidApiKey_shouldThrowResourceException() {
        GeminiClient clientWithInvalidKey = GeminiClientBuilder.builder()
                .withApiKey("INVALID_API_KEY")
                .withModel("models/gemini-1.5-pro-001")
                .build();
        GenerateResource resourceWithInvalidKey = ResourceBuilder.builder(clientWithInvalidKey).buildGenerationResource();

        assertThrows(ResourceException.class, () -> resourceWithInvalidKey.execute(
                GenerateRequestBuilder.builder()
                        .withModel(clientWithInvalidKey.getModelName())
                        .withUserContent("test")
                        .build()
        ));
    }

    /**
     * Tests text generation with invalid model name. Verifies that a ResourceException is thrown,
     * indicating an invalid model name.
     *
     * @since 0.2.0
     */
    @Test
    void generateText_withInvalidModelName_shouldThrowResourceException() {
        GeminiClient clientWithInvalidModel = GeminiClientBuilder.builder()
                .withApiKey(GEMINI_API_KEY)
                .withModel("invalid-model-name")
                .build();
        GenerateResource resourceWithInvalidModel = ResourceBuilder.builder(clientWithInvalidModel).buildGenerationResource();

        assertThrows(ResourceException.class, () -> resourceWithInvalidModel.execute(
                GenerateRequestBuilder.builder()
                        .withModel(clientWithInvalidModel.getModelName())
                        .withUserContent("test")
                        .build()
        ));
    }

    /**
     * Tests the getSupportedMethods() method. Verifies that the returned list of supported
     * model methods is not null and contains only the expected method.
     *
     * @since 0.2.0
     */
    @Test
    void getModelMethodList_shouldReturnCorrectMethod() {
        List<SupportedModelMethod> supportedMethods = generateResource.getSupportedMethods();
        assertNotNull(supportedMethods, "Supported methods list should not be null");
        assertEquals(List.of(SupportedModelMethod.GENERATE_CONTENT), supportedMethods, "Supported methods list should contain only GENERATE_CONTENT");
    }

    /**
     * Tests the getGeminiClient() method. Verifies that the returned GeminiClient is not null and
     * is the same instance used to create the resource.
     *
     * @since 0.2.0
     */
    @Test
    void getGeminiClient_shouldReturnCorrectInstance() {
        GeminiClient client = generateResource.getGeminiClient();
        assertNotNull(client, "GeminiClient should not be null");
        assertSame(geminiClient, client, "GeminiClient instances should be the same");
    }

    /**
     * Tests text generation with JSON mode enabled. Verifies that the response is not null and
     * contains generated JSON data. This test demonstrates the use of JSON mode for structured
     * responses.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void generateText_withJsonModeEnabled_shouldReturnJsonData() throws ResourceException {
        String jsonStringSchema = "{\n" +
                "                  \"type\": \"OBJECT\",\n" +
                "                  \"properties\": {\n" +
                "                    \"role\": {\n" +
                "                      \"type\": \"STRING\",\n" +
                "                      \"description\": \"The role of the message sender (e.g., 'user', 'model').\"\n" +
                "                    },\n" +
                "                    \"message\": {\n" +
                "                      \"type\": \"STRING\",\n" +
                "                      \"description\": \"The content of the message.\"\n" +
                "                    }\n" +
                "                  },\n" +
                "                  \"required\": [\n" +
                "                    \"role\",\n" +
                "                    \"message\"\n" +
                "                  ]\n" +
                "                }";

        GenerateContentResponse response = generateResource.execute(
                GenerateRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withUserContent("Generate a JSON response.")
                        .withJsonMode(jsonModeConfigBuilder -> {
                            jsonModeConfigBuilder.withEnable(true);
                            jsonModeConfigBuilder.withSchema(jsonStringSchema);
                        })
                        .build()
        );

        assertNotNull(response, "Response should not be null");
        //TODO update to new version
        //assertNotNull(response.getGeneratedText(), "Generated text should not be null");
        //TODO Add assertions to verify the JSON structure based on jsonStringSchema.  This would
    }

    /**
     * Tests text generation with a provided schema. Verifies that the generated response matches
     * the provided schema. This test demonstrates the use of schema validation for ensuring
     * that the generated response conforms to the expected structure.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void generateText_withProvidedSchema_shouldMatchSchema() throws ResourceException {
        String jsonStringSchema = "{\n" +
                "                  \"type\": \"OBJECT\",\n" +
                "                  \"properties\": {\n" +
                "                    \"role\": {\n" +
                "                      \"type\": \"STRING\",\n" +
                "                      \"description\": \"The role of the message sender (e.g., 'user', 'model').\"\n" +
                "                    },\n" +
                "                    \"message\": {\n" +
                "                      \"type\": \"STRING\",\n" +
                "                      \"description\": \"The content of the message.\"\n" +
                "                    }\n" +
                "                  },\n" +
                "                  \"required\": [\n" +
                "                    \"role\",\n" +
                "                    \"message\"\n" +
                "                  ]\n" +
                "                }";

        GenerateContentResponse response = generateResource.execute(
                GenerateRequestBuilder.builder()
                        .withModel(geminiClient.getModelName())
                        .withUserContent("Generate a JSON response that matches the schema.")
                        .withJsonMode(jsonModeConfigBuilder -> {
                            jsonModeConfigBuilder.withEnable(true);
                            jsonModeConfigBuilder.withSchema(jsonStringSchema);
                        })
                        .build()
        );

        assertNotNull(response, "Response should not be null");
        //TODO update to new version
        //assertNotNull(response.getGeneratedText(), "Generated text should not be null");
        //TODO Add assertions to verify that the generated JSON data matches the provided schema.
    }

}