package com.enovka.gemini4j.json.spec;

import com.enovka.gemini4j.common.BaseClass;
import com.enovka.gemini4j.json.exception.JsonException;

/**
 * Abstract base class for JSON serialization and deserialization
 * implementations.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public abstract class AbstractJsonService extends BaseClass
        implements JsonService {

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract <T> String serialize(T object) throws JsonException;

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract <T> T deserialize(String json, Class<T> type)
            throws JsonException;
}