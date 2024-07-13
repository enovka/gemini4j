package com.enovka.gemini4j.json;

import com.enovka.gemini4j.common.BaseClass;
import com.enovka.gemini4j.domain.Model;
import com.enovka.gemini4j.json.exception.JsonException;
import com.enovka.gemini4j.json.impl.JacksonJsonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the {@link JacksonJsonService}. It verifies the service's
 * ability to serialize and deserialize JSON data correctly, handling various
 * data types and potential error scenarios.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 0.0.1
 */
public class JacksonJsonServiceTest extends BaseClass {

    private JacksonJsonService jsonService;

    /**
     * Initializes the {@link JacksonJsonService} before each test.
     */
    @BeforeEach
    public void setUp() {
        jsonService = new JacksonJsonService();
    }

    /**
     * Tests the serialization of a simple object to a JSON string.
     *
     * @throws JsonException If an error occurs during serialization.
     */
    @Test
    public void testSerialize() throws JsonException {
        Model model = new Model().setName("models/test-model")
                .setBaseModelId("test-model")
                .setVersion("001")
                .setDisplayName("Test Model")
                .setDescription("A test model for Gemini4J.")
                .setInputTokenLimit(4096)
                .setOutputTokenLimit(4096)
                .setSupportedGenerationMethods(
                        Collections.singletonList("generateText"))
                .setTemperature(0.5)
                .setTopP(0.8)
                .setTopK(40);

        logDebug("Model object before serialization: " + model);
        String expectedJson = "{\"name\":\"models/test-model\",\"baseModelId\":\"test-model\",\"version\":\"001\",\"displayName\":\"Test Model\",\"description\":\"A test model for Gemini4J.\",\"inputTokenLimit\":4096,\"outputTokenLimit\":4096,\"supportedGenerationMethods\":[\"generateText\"],\"temperature\":0.5,\"topP\":0.8,\"topK\":40}";
        String actualJson = jsonService.serialize(model);

        assertEquals(expectedJson, actualJson);
        logDebug("Serialization successful.");
    }

    /**
     * Tests the deserialization of a JSON string to a {@link Model} object.
     *
     * @throws JsonException If an error occurs during deserialization.
     */
    @Test
    public void testDeserialize() throws JsonException {
        String json = "{\"name\":\"models/test-model\",\"baseModelId\":\"test-model\",\"version\":\"001\",\"displayName\":\"Test Model\",\"description\":\"A test model for Gemini4J.\",\"inputTokenLimit\":4096,\"outputTokenLimit\":4096,\"supportedGenerationMethods\":[\"generateText\"],\"temperature\":0.5,\"topP\":0.8,\"topK\":40}";

        Model expectedModel = new Model().setName("models/test-model")
                .setBaseModelId("test-model")
                .setVersion("001")
                .setDisplayName("Test Model")
                .setDescription("A test model for Gemini4J.")
                .setInputTokenLimit(4096)
                .setOutputTokenLimit(4096)
                .setSupportedGenerationMethods(
                        Collections.singletonList("generateText"))
                .setTemperature(0.5)
                .setTopP(0.8)
                .setTopK(40);

        Model actualModel = jsonService.deserialize(json, Model.class);

        assertEquals(expectedModel, actualModel);
        logDebug("Deserialization successful.");
    }

    /**
     * Tests the scenario where an invalid JSON string is provided for
     * deserialization, expecting a {@link JsonException} to be thrown.
     */
    @Test
    public void testDeserializeInvalidJson() {
        String invalidJson = "{\"name\":\"models/test-model\",";

        assertThrows(JsonException.class,
                () -> jsonService.deserialize(invalidJson, Model.class));
        logDebug("DeserializeInvalidJson test successful.");
    }

    /**
     * Tests the scenario where a JSON string with missing fields is provided
     * for deserialization, expecting a {@link JsonException} to be thrown.
     */
    @Test
    public void testDeserializeMissingFields() {
        String jsonWithMissingFields = "{}";

        assertThrows(JsonException.class,
                () -> jsonService.deserialize(jsonWithMissingFields,
                        Model.class));
        logDebug("DeserializeMissingFields test successful.");
    }
}