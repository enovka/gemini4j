package com.enovka.gemini4j.http;

import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.spec.AbstractHttpClientService;
import com.enovka.gemini4j.http.spec.HttpResponseWrapper;
import com.enovka.gemini4j.http.validator.HttpRequestValidator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Map;

/**
 * Default implementation of the
 * {@link com.enovka.gemini4j.http.spec.HttpClientService} interface using the
 * Apache HTTP Client library.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 1.0.0-beta
 */
public class DefaultHttpClientService extends AbstractHttpClientService {

    private final HttpClient httpClient;

    /**
     * Constructs a new DefaultHttpClientService with the default Apache
     * HttpClient and validator.
     */
    public DefaultHttpClientService() {
        this(HttpClientBuilder.create().build(), new HttpRequestValidator());
    }

    /**
     * Constructs a new DefaultHttpClientService with the default Apache
     * HttpClient and the specified validator.
     *
     * @param validator The validator to use for request validation.
     */
    public DefaultHttpClientService(HttpRequestValidator validator) {
        this(HttpClientBuilder.create().build(), validator);
    }

    /**
     * Constructs a new DefaultHttpClientService with the specified HttpClient
     * and validator.
     *
     * @param httpClient The HttpClient to use for making requests.
     * @param validator The validator to use for request validation.
     */
    public DefaultHttpClientService(HttpClient httpClient,
                                    HttpRequestValidator validator) {
        super(validator);
        this.httpClient = httpClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponseWrapper doGET(String url, Map<String, String> headers)
            throws HttpException {
        HttpGet request = new HttpGet(url);
        addHeadersToRequest(request, headers);
        try {
            HttpResponse response = httpClient.execute(request);
            return createHttpResponseWrapper(response);
        } catch (IOException e) {
            throw new HttpException(
                    "Error executing GET request: " + e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HttpResponseWrapper doPOST(String url, String body,
                                         Map<String, String> headers)
            throws HttpException {
        HttpPost request = new HttpPost(url);
        addHeadersToRequest(request, headers);
        try {
            request.setEntity(new StringEntity(body));
            HttpResponse response = httpClient.execute(request);
            return createHttpResponseWrapper(response);
        } catch (IOException e) {
            throw new HttpException(
                    "Error executing POST request: " + e.getMessage(), e);
        }
    }

    /**
     * Adds the given headers to the HTTP request.
     *
     * @param request The HTTP request.
     * @param headers The headers to add.
     */
    private void addHeadersToRequest(org.apache.http.HttpRequest request,
                                     Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
    }

    /**
     * Creates an {@link HttpResponseWrapper} from the given
     * {@link HttpResponse}.
     *
     * @param response The HTTP response.
     * @return The {@link HttpResponseWrapper}.
     * @throws HttpException If an error occurs while reading the response.
     */
    private HttpResponseWrapper createHttpResponseWrapper(HttpResponse response)
            throws HttpException {
        int statusCode = response.getStatusLine().getStatusCode();

        // Check the status code and throw HttpException if necessary
        if (statusCode >= 400) {
            throw new HttpException("HTTP error: " + statusCode,
                    new Throwable(), statusCode);
        }

        return new DefaultHttpResponseWrapper(response);
    }
}