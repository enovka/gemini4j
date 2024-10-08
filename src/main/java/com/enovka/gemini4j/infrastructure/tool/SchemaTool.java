package com.enovka.gemini4j.infrastructure.tool;

import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.model.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SchemaTool extends BaseClass{

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static Schema convertToSchema(String jsonSchema) throws JsonException {
        String cleanedJson = jsonSchema.trim().replaceAll("(?m)^\\s+|\\s+$", "");
        try {
            return objectMapper.readValue(cleanedJson, Schema.class);
        } catch (IOException e) {
            throw new JsonException("Error converting Schema String to Schema Object", e);
        }
    }
}
