package com.enovka.gemini4j.infrastructure.json.spec;

import com.enovka.gemini4j.infrastructure.json.exception.JsonException;

/**
 * Interface defining the contract for JSON serialization and deserialization.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public interface JsonService {

    /**
     * Serializes the given object to a JSON string.
     *
     * @param object The object to serialize.
     * @param <T>    The type of the object.
     * @return The JSON string representation of the object.
     * @throws JsonException If an error occurs during serialization.
     */
    <T> String serialize(T object) throws JsonException;

    /**
     * Deserializes the given JSON string to an object of the specified type.
     *
     * @param json The JSON string to deserialize.
     * @param type The class of the object to deserialize to.
     * @param <T>  The type of the object.
     * @return The deserialized object.
     * @throws JsonException If an error occurs during deserialization.
     */
    <T> T deserialize(String json, Class<T> type) throws JsonException;
}