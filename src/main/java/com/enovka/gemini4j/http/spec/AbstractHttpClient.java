package com.enovka.gemini4j.http.spec;

import com.enovka.gemini4j.common.BaseClass;
import com.enovka.gemini4j.http.exception.HttpException;

import java.util.Map;

/**
 * Abstract base class for HTTP client implementations, providing common
 * functionality.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public abstract class AbstractHttpClient extends BaseClass
        implements HttpClient {

    private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
    private static final int DEFAULT_RESPONSE_TIMEOUT = 10000;

    protected int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
    protected int responseTimeout = DEFAULT_RESPONSE_TIMEOUT;

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract HttpResponse get(String url, Map<String, String> headers)
            throws HttpException;

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract HttpResponse post(String url, String body,
                                      Map<String, String> headers)
            throws HttpException;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResponseTimeout(int responseTimeout) {
        this.responseTimeout = responseTimeout;
    }
}