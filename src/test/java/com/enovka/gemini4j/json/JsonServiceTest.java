package com.enovka.gemini4j.json;

import com.enovka.gemini4j.domain.Model;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Collections;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the {@link JsonService} implementations. It verifies the
 * service's ability to serialize and deserialize JSON data correctly, handling
 * various data types and potential error scenarios.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class JsonServiceTest extends BaseClass {

    @RegisterExtension
    static WireMockExtension wireMockExtension = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    private final JsonService jsonService = JsonServiceBuilder.builder()
            .build().build();

    /**
     * Tests the serialization of a simple object to a JSON string.
     *
     * @throws JsonException If an error occurs during serialization.
     */
    @Test
    public void testSerialize() throws JsonException {
        System.out.println("Starting testSerialize...");

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

        System.out.println("Model object: " + model);

        String expectedJson
                = "{\"name\":\"models/test-model\",\"baseModelId\":\"test-model\",\"version\":\"001\",\"displayName\":\"Test Model\",\"description\":\"A test model for Gemini4J.\",\"inputTokenLimit\":4096,\"outputTokenLimit\":4096,\"supportedGenerationMethods\":[\"generateText\"],\"temperature\":0.5,\"maxTemperature\":null,\"topP\":0.8,\"topK\":40}";
        String actualJson = jsonService.serialize(model);
        System.out.println("Serialized JSON: " + actualJson);

        assertEquals(expectedJson, actualJson);
        System.out.println("Serialization successful.");
    }

    /**
     * Tests the deserialization of a JSON string to a {@link Model} object.
     *
     * @throws JsonException If an error occurs during deserialization.
     */
    @Test
    public void testDeserialize() throws JsonException {
        System.out.println("Starting testDeserialize...");

        String json
                = "{\"name\":\"models/test-model\",\"baseModelId\":\"test-model\",\"version\":\"001\",\"displayName\":\"Test Model\",\"description\":\"A test model for Gemini4J.\",\"inputTokenLimit\":4096,\"outputTokenLimit\":4096,\"supportedGenerationMethods\":[\"generateText\"],\"temperature\":0.5,\"topP\":0.8,\"topK\":40}";

        System.out.println("JSON string: " + json);

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
        System.out.println("Deserialized model: " + actualModel);

        assertEquals(expectedModel, actualModel);
        System.out.println("Deserialization successful.");
    }

    /**
     * Tests the scenario where an invalid JSON string is provided for
     * deserialization, expecting a {@link JsonException} to be thrown.
     */
    @Test
    public void testDeserializeInvalidJson() {
        System.out.println("Starting testDeserializeInvalidJson...");

        String invalidJson = "{\"name\":\"models/test-model\",";
        System.out.println("Invalid JSON string: " + invalidJson);

        assertThrows(JsonException.class,
                () -> jsonService.deserialize(invalidJson, Model.class));
        System.out.println("DeserializeInvalidJson test successful.");
    }

    /**
     * Tests the scenario where a JSON string with missing fields is provided
     * for deserialization, expecting a {@link JsonException} to be thrown.
     */
    @Test
    public void testDeserializeMissingFields() {
        System.out.println("Starting testDeserializeMissingFields...");

        String jsonWithMissingFields = "{}";
        System.out.println("JSON string with missing fields: "
                + jsonWithMissingFields);

        assertThrows(JsonException.class,
                () -> jsonService.deserialize(jsonWithMissingFields,
                        Model.class));
        System.out.println("DeserializeMissingFields test successful.");
    }
}