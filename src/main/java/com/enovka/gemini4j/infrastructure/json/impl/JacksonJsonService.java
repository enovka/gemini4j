package com.enovka.gemini4j.infrastructure.json.impl;

import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.AbstractJsonService;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Default implementation of the {@link JsonService} interface using Jackson.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class JacksonJsonService extends AbstractJsonService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> String serialize(T object) throws JsonException {
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.ALWAYS);
        try {
            if (object == null) {
                throw new JsonException(
                        "Error serializing object: Object is null");
            }
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonException(
                    "Error serializing object: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T deserialize(String json, Class<T> type) throws JsonException {
        try {
            if (json == null || json.length() < 4) {
                throw new JsonException(
                        "Error deserializing JSON: Json string is null or empty");

            }
            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new JsonException(
                    "Error deserializing JSON: " + e.getMessage(), e);
        }
    }
}