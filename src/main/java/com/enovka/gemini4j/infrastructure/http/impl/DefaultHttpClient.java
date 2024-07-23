package com.enovka.gemini4j.infrastructure.http.impl;

import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.AbstractHttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
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
 * {@inheritDoc}
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
     * Constructs a new DefaultHttpClient with custom connection and response
     * timeouts.
     *
     * @param responseTimeout The response timeout in milliseconds.
     * @param connectionTimeout The connection timeout in milliseconds.
     * @since 0.0.2
     */
    public DefaultHttpClient(Integer responseTimeout,
                             Integer connectionTimeout) {
        this.responseTimeout = responseTimeout;
        this.connectionTimeout = connectionTimeout;
        this.apacheHttpClient = HttpClientBuilder.create().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponse executeGetRequest(String url,
                                              Map<String, String> headers)
            throws HttpException {
        HttpGet request;
        try {
            request = new HttpGet(url);
            addHeadersToRequest(request, headers);
        } catch (Exception e) {
            throw new HttpException(
                    "Error creating GET request: " + e.getMessage(), e);
        }

        try (CloseableHttpResponse response = apacheHttpClient.execute(
                request)) {
            if (response.getStatusLine().getStatusCode() >= 400) {
                throw new HttpException("GET request failed with status code: "
                        + response.getStatusLine().getStatusCode(), null,
                        response.getStatusLine().getStatusCode());
            }
            return createHttpResponse(response);
        } catch (Exception e) {
            throw new HttpException(
                    "Error executing GET request: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponse executePostRequest(String url, String body,
                                               Map<String, String> headers)
            throws HttpException {
        HttpPost request;
        try {
            request = new HttpPost(url);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            addHeadersToRequest(request, headers);
        } catch (Exception e) {
            throw new HttpException(
                    "Error creating POST request: " + e.getMessage(), e);
        }

        try {
            request.setEntity(new StringEntity(body));
            try (CloseableHttpResponse response = apacheHttpClient.execute(
                    request)) {
                return createHttpResponse(response);
            }
        } catch (Exception e) {
            throw new HttpException(
                    "Error executing POST request: " + e.getMessage(), e);
        }
    }

    /**
     * Adds the provided headers to the given HTTP request.
     *
     * @param request The HTTP request to add headers to.
     * @param headers The headers to add.
     */
    private void addHeadersToRequest(org.apache.http.HttpRequest request,
                                     Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
    }

    /**
     * Creates an {@link HttpResponse} from a {@link CloseableHttpResponse}.
     *
     * @param response The CloseableHttpResponse to convert.
     * @return An HttpResponse containing the status code, headers, and body of
     * the response.
     * @throws HttpException If an error occurs while reading the response
     * body.
     */
    private HttpResponse createHttpResponse(CloseableHttpResponse response)
            throws HttpException {
        String responseBody;
        Map<String, String> responseHeaders;
        int statusCode;
        try {
            statusCode = response.getStatusLine().getStatusCode();
            responseHeaders = new HashMap<>();
            for (Header header : response.getAllHeaders()) {
                responseHeaders.put(header.getName(), header.getValue());
            }
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new HttpException(
                    "Error reading response body: " + e.getMessage(), e);
        }

        return new HttpResponse(statusCode, responseHeaders, responseBody);
    }
}