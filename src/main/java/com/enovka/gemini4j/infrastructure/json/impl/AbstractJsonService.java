package com.enovka.gemini4j.infrastructure.json.impl;

import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;

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
    public abstract <T> String serialize(T object);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract <T> T deserialize(String json, Class<T> type)
    ;
}