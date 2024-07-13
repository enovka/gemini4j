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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link HttpClient} interface using Apache HttpClient.
 * This class provides basic HTTP request functionalities such as GET and POST.
 *
 * @author Everson Novka
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
     * Executes a GET request to the specified URL with the provided headers.
     *
     * @param url The URL to send the GET request to.
     * @param headers The headers to include in the request.
     * @return An {@link HttpResponse} object containing the response status code, headers, and body.
     * @throws HttpException If an error occurs during the request execution.
     */
    @Override
    public HttpResponse get(String url, Map<String, String> headers) throws HttpException {
        HttpGet request = new HttpGet(url);
        addHeadersToRequest(request, headers);

        try (CloseableHttpResponse response = apacheHttpClient.execute(request)) {
            if (response.getStatusLine().getStatusCode() >=400) {
                throw new HttpException("GET request failed with status code: " + response.getStatusLine().getStatusCode());
            }
            return createHttpResponse(response);
        } catch (Exception e) {
            throw new HttpException("Error executing GET request: " + e.getMessage(), e);
        }
    }

    /**
     * Executes a POST request to the specified URL with the provided body and headers.
     *
     * @param url The URL to send the POST request to.
     * @param body The body to include in the request.
     * @param headers The headers to include in the request.
     * @return An {@link HttpResponse} object containing the response status code, headers, and body.
     * @throws HttpException If an error occurs during the request execution.
     */
    @Override
    public HttpResponse post(String url, String body, Map<String, String> headers) throws HttpException {
        HttpPost request = new HttpPost(url);
        addHeadersToRequest(request, headers);

        try {
            request.setEntity(new StringEntity(body));
            try (CloseableHttpResponse response = apacheHttpClient.execute(request)) {
                return createHttpResponse(response);
            }
        } catch (Exception e) {
            throw new HttpException("Error executing POST request: " + e.getMessage(), e);
        }
    }

    /**
     * Adds the provided headers to the given HTTP request.
     *
     * @param request The HTTP request to add headers to.
     * @param headers The headers to add.
     */
    private void addHeadersToRequest(org.apache.http.HttpRequest request, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
    }

    /**
     * Creates an {@link HttpResponse} from a {@link CloseableHttpResponse}.
     *
     * @param response The CloseableHttpResponse to convert.
     * @return An HttpResponse containing the status code, headers, and body of the response.
     * @throws HttpException If an error occurs while reading the response body.
     */
    private HttpResponse createHttpResponse(CloseableHttpResponse response) throws HttpException {
        int statusCode = response.getStatusLine().getStatusCode();
        Map<String, String> responseHeaders = new HashMap<>();
        for (Header header : response.getAllHeaders()) {
            responseHeaders.put(header.getName(), header.getValue());
        }
        String responseBody;
        try {
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new HttpException("Error reading response body: " + e.getMessage(), e);
        }

        return new HttpResponse(statusCode, responseHeaders, responseBody);
    }
}