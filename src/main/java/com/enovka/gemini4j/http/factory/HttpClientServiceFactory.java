package com.enovka.gemini4j.http.factory;

import com.enovka.gemini4j.http.CustomHttpClientService;
import com.enovka.gemini4j.http.DefaultHttpClientService;
import com.enovka.gemini4j.http.spec.HttpClientService;
import com.enovka.gemini4j.http.spec.HttpResponseWrapper;
import com.enovka.gemini4j.http.validator.HttpRequestValidator;
import org.apache.http.client.HttpClient;

/**
 * Factory class for creating HttpClientService instances.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 */
public class HttpClientServiceFactory {

    private static final String ERROR_MESSAGE_INVALID_CLIENT_TYPE = "Invalid client type provided.";

    /**
     * Creates a new HttpClientService instance based on the specified client type.
     *
     * @param clientType The type of HTTP client to create.
     * @return A new HttpClientService instance.
     * @throws IllegalArgumentException If an invalid client type is provided.
     */
    public static HttpClientService create(ClientType clientType) {
        switch (clientType) {
            case DEFAULT:
                return new DefaultHttpClientService();
            default:
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CLIENT_TYPE);
        }
    }

    /**
     * Creates a new HttpClientService instance based on the specified client type and custom validator.
     *
     * @param clientType The type of HTTP client to create.
     * @param validator The custom validator to use.
     * @return A new HttpClientService instance.
     * @throws IllegalArgumentException If an invalid client type is provided.
     */
    public static HttpClientService create(ClientType clientType, HttpRequestValidator validator) {
        switch (clientType) {
            case DEFAULT:
                return new DefaultHttpClientService(validator);
            default:
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CLIENT_TYPE);
        }
    }

    /**
     * Creates a new HttpClientService instance based on the specified client type, custom client, and custom validator.
     *
     * @param clientType      The type of HTTP client to create.
     * @param httpClient     The custom HttpClient instance to use.
     * @param httpResponseWrapper The custom HttpResponseWrapper instance to use.
     * @param validator      The custom validator to use.
     * @return A new HttpClientService instance.
     * @throws IllegalArgumentException If an invalid client type is provided.
     */
    public static HttpClientService create(ClientType clientType, Object httpClient, HttpResponseWrapper httpResponseWrapper, HttpRequestValidator validator) {
        switch (clientType) {
            case DEFAULT:
                return new DefaultHttpClientService((HttpClient) httpClient, validator);
            case CUSTOM:
                return new CustomHttpClientService((HttpClientService) httpClient, httpResponseWrapper, validator);
            default:
                throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CLIENT_TYPE);
        }
    }

    /**
     * Enum representing the available HTTP client types.
     */
    public enum ClientType {
        DEFAULT,
        CUSTOM
    }
}