package com.enovka.gemini4j.http;

import com.enovka.gemini4j.http.exception.GeminiHttpException;
import com.enovka.gemini4j.http.spec.HttpResponseWrapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper class for Apache HttpClient response, implementing
 * HttpResponseWrapper interface.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 */
public class DefaultHttpResponseWrapper implements HttpResponseWrapper {

    private static final String ERROR_MESSAGE_RESPONSE_BODY_IS_NULL = "Response body is null";
    private static final String ERROR_MESSAGE_ERROR_READING_RESPONSE_BODY = "Error reading response body";

    private final HttpResponse httpResponse;

    /**
     * Constructs a new DefaultHttpResponseWrapper with the specified HttpResponse.
     *
     * @param httpResponse The HttpResponse to wrap.
     */
    public DefaultHttpResponseWrapper(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int statusCode() {
        return httpResponse.getStatusLine().getStatusCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> headers() {
        Map<String, String> headersMap = new HashMap<>();
        for (Header header : httpResponse.getAllHeaders()) {
            headersMap.put(header.getName(), header.getValue());
        }
        return headersMap;
    }

    /**
     * Returns the response body as a String.
     *
     * @return The response body as a String.
     * @throws GeminiHttpException If an error occurs reading the response body.
     */
    @Override
    public String body() throws GeminiHttpException {
        try {
            String responseBody = EntityUtils.toString(httpResponse.getEntity());
            if (responseBody != null) {
                return responseBody;
            } else {
                throw new GeminiHttpException(ERROR_MESSAGE_RESPONSE_BODY_IS_NULL);
            }
        } catch (IOException e) {
            throw new GeminiHttpException(ERROR_MESSAGE_ERROR_READING_RESPONSE_BODY, e);
        }
    }

    /**
     * Wraps an existing HttpResponseWrapper with this DefaultHttpResponseWrapper.
     * This is used to delegate response handling to the wrapped HttpResponseWrapper.
     *
     * @param response The HttpResponseWrapper to wrap.
     * @return The wrapped HttpResponseWrapper.
     */
    @Override
    public HttpResponseWrapper wrap(HttpResponseWrapper response) {
        // Delegate response handling to the wrapped HttpResponseWrapper
        return response;
    }
}