package com.enovka.gemini4j.http.impl;

import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.spec.AbstractHttpClient;
import com.enovka.gemini4j.http.spec.HttpClient;
import com.enovka.gemini4j.http.spec.HttpResponse;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link HttpClient} interface using Apache
 * HttpClient.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class DefaultHttpClient extends AbstractHttpClient {

    private final CloseableHttpClient apacheHttpClient;

    /**
     * Constructs a new DefaultHttpClient with default settings.
     */
    public DefaultHttpClient() {
        this.apacheHttpClient = HttpClientBuilder.create().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponse get(String url, Map<String, String> headers)
            throws HttpException {
        HttpGet request = new HttpGet(url);
        addHeadersToRequest(request, headers);

        try (CloseableHttpResponse response = apacheHttpClient.execute(
                request)) {
            return createHttpResponse(response);
        } catch (Exception e) {
            logError("Error executing GET request: " + e.getMessage(), e);
            throw new HttpException(
                    "Error executing GET request: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponse post(String url, String body,
                             Map<String, String> headers) throws HttpException {
        HttpPost request = new HttpPost(url);
        addHeadersToRequest(request, headers);

        try {
            request.setEntity(new StringEntity(body));
            try (CloseableHttpResponse response = apacheHttpClient.execute(
                    request)) {
                return createHttpResponse(response);
            }
        } catch (Exception e) {
            logError("Error executing POST request: " + e.getMessage(), e);
            throw new HttpException(
                    "Error executing POST request: " + e.getMessage(), e);
        }
    }

    private void addHeadersToRequest(org.apache.http.HttpRequest request,
                                     Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
    }

    private HttpResponse createHttpResponse(CloseableHttpResponse response)
            throws HttpException {
        int statusCode = response.getStatusLine().getStatusCode();
        Map<String, String> responseHeaders = new HashMap<>();
        for (Header header : response.getAllHeaders()) {
            responseHeaders.put(header.getName(), header.getValue());
        }
        String responseBody;
        try {
            responseBody = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new HttpException("HTTP error: " + statusCode
                        + ", response body: " + responseBody);
            }
        } catch (Exception e) {
            logError("Error reading response body: " + e.getMessage(), e);
            throw new HttpException(
                    "Error reading response body: " + e.getMessage(), e);
        }

        return new HttpResponse(statusCode, responseHeaders, responseBody);
    }
}