package com.enovka.gemini4j.resource.spec.base;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.AsyncCallback;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.tool.BaseClass;
import com.enovka.gemini4j.model.request.spec.Request;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;
import org.apache.hc.core5.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * <p>A powerful and flexible abstract base class for all Gemini API resource implementations,
 * designed to support both low-level HTTP request handling and a high-level, action-oriented API.
 * This class provides concrete implementations for all HTTP methods (GET, POST, PATCH, DELETE),
 * handling request building, serialization, execution, deserialization, and error management
 * internally. It leverages asynchronous operations for enhanced performance and integrates
 * seamlessly with the {@link AsyncResponse} class for fluent asynchronous response handling.</p>
 *
 * <p>This class serves as the foundation for a higher-level abstraction layer, enabling the
 * creation of a more intuitive and user-friendly API that focuses on specific actions rather
 * than low-level HTTP details. Concrete resource classes can extend this class and utilize its
 * methods to interact with the Gemini API efficiently and effectively.</p>
 *
 * <p>Key features include:</p>
 * <ul>
 *     <li>**Concrete HTTP Method Implementations:** Provides concrete implementations for all
 *     HTTP methods, handling the complexities of request building and execution internally.</li>
 *     <li>**Asynchronous Operations:**  Utilizes {@link CompletableFuture} for non-blocking
 *     request execution, improving responsiveness and resource utilization.</li>
 *     <li>**Generic Type Handling:** Supports generic request and response types, promoting
 *     type safety and reducing boilerplate code.</li>
 *     <li>**Seamless AsyncResponse Integration:**  Fully integrates with the {@link AsyncResponse}
 *     class, providing a fluent and functional approach to handling asynchronous responses,
 *     including success, error, and cancellation scenarios.</li>
 *     <li>**Simplified Error Handling:** Provides centralized exception handling, converting
 *     checked exceptions like {@link HttpException} and {@link JsonException} into
 *     {@link ResourceException}, simplifying error management for clients.</li>
 *     <li>**Centralized Header Management:** Handles the construction of standard headers,
 *     including authentication and content type, ensuring consistency across requests.</li>
 * </ul>
 *
 * @param <I> The type of request object this resource handles.
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class AbstractResource< I extends Request> extends BaseClass implements Resource {

    protected final GeminiClient geminiClient;

    /**
     * Constructs a new AbstractResource with the required GeminiClient.
     *
     * @param geminiClient The Gemini client for API communication.
     * @since 0.2.0
     */
    protected AbstractResource(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    /**
     * Executes a synchronous GET request to the specified endpoint.
     *
     * @param endpoint     The API endpoint path.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return The deserialized response object.
     * @throws ResourceException If any error occurs during the request.
     */
    protected <ResponseType extends AbstractResponse> ResponseType get(String endpoint, Class<ResponseType> responseType) throws ResourceException {
        return executeRequest("GET", endpoint, null, ContentType.APPLICATION_JSON, responseType);
    }

    /**
     * Executes an asynchronous GET request to the specified endpoint.
     *
     * @param endpoint     The API endpoint path.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return An {@link AsyncResponse} object representing the asynchronous operation.
     */
    protected <ResponseType extends AbstractResponse> AsyncResponse<ResponseType> getAsync(String endpoint, Class<ResponseType> responseType) {
        return executeRequestAsync("GET", endpoint, null, ContentType.APPLICATION_JSON, responseType);
    }

    /**
     * Executes a synchronous POST request to the specified endpoint.
     *
     * @param endpoint     The API endpoint path.
     * @param requestObject The request object to be serialized and sent in the request body.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return The deserialized response object.
     * @throws ResourceException If any error occurs during the request.
     */
    protected <ResponseType extends AbstractResponse, RequestType extends Request> ResponseType post(String endpoint, RequestType requestObject, Class<ResponseType> responseType) throws ResourceException {
        return executeRequest("POST", endpoint, requestObject, ContentType.APPLICATION_JSON, responseType);
    }

    /**
     * Executes an asynchronous POST request to the specified endpoint.
     *
     * @param endpoint     The API endpoint path.
     * @param requestObject The request object to be serialized and sent in the request body.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return An {@link AsyncResponse} object representing the asynchronous operation.
     */
    protected <ResponseType extends AbstractResponse> AsyncResponse<ResponseType> postAsync(String endpoint, I requestObject, Class<ResponseType> responseType) {
        return executeRequestAsync("POST", endpoint, requestObject, ContentType.APPLICATION_JSON, responseType);
    }

    /**
     * Executes a synchronous PATCH request to the specified endpoint.
     *
     * @param endpoint     The API endpoint path.
     * @param requestObject The request object to be serialized and sent in the request body.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return The deserialized response object.
     * @throws ResourceException If any error occurs during the request.
     */
    protected <ResponseType extends AbstractResponse> ResponseType patch(String endpoint, I requestObject, Class<ResponseType> responseType) throws ResourceException {
        return executeRequest("PATCH", endpoint, requestObject, ContentType.APPLICATION_JSON, responseType);
    }

    /**
     * Executes an asynchronous PATCH request to the specified endpoint.
     *
     * @param endpoint     The API endpoint path.
     * @param requestObject The request object to be serialized and sent in the request body.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return An {@link AsyncResponse} object representing the asynchronous operation.
     */
    protected <ResponseType extends AbstractResponse, RequestType extends Request> AsyncResponse<ResponseType> patchAsync(String endpoint, RequestType requestObject, Class<ResponseType> responseType) {
        return executeRequestAsync("PATCH", endpoint, requestObject, ContentType.APPLICATION_JSON, responseType);
    }

    /**
     * Executes a synchronous DELETE request to the specified endpoint.
     *
     * @param endpoint     The API endpoint path.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return The deserialized response object.
     * @throws ResourceException If any error occurs during the request.
     */
    protected <ResponseType extends AbstractResponse> ResponseType delete(String endpoint, Class<ResponseType> responseType) throws ResourceException {
        return executeRequest("DELETE", endpoint, null, ContentType.APPLICATION_JSON, responseType);
    }

    /**
     * Executes an asynchronous DELETE request to the specified endpoint.
     *
     * @param endpoint     The API endpoint path.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return An {@link AsyncResponse} object representing the asynchronous operation.
     */
    protected <ResponseType extends AbstractResponse> AsyncResponse<ResponseType> deleteAsync(String endpoint, Class<ResponseType> responseType) {
        return executeRequestAsync("DELETE", endpoint, null, ContentType.APPLICATION_JSON, responseType);
    }

    /**
     * Executes a synchronous API request based on the provided parameters. This method handles
     * request building, serialization, execution, deserialization, and error handling.
     *
     * @param method       The HTTP method.
     * @param endpoint     The API endpoint path.
     * @param requestObject The request object to be serialized and sent in the request body.
     * @param contentType  The content type of the request.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return The deserialized response object.
     * @throws ResourceException If any error occurs during the request.
     */
    protected  <ResponseType extends AbstractResponse, RequestType extends Request> ResponseType executeRequest(String method, String endpoint, RequestType requestObject, ContentType contentType, Class<ResponseType> responseType) throws ResourceException {
        String uri = buildEndpointUrl(endpoint, null);
        Map<String, String> headers = buildHeaders(contentType);

        try {
            HttpResponse httpResponse = executeHttpRequest(method, uri, requestObject, headers, contentType);
            return deserializeResponse(httpResponse, responseType);
        } catch (HttpException | JsonException e) {
            throw new ResourceException("Error during request: " + e.getMessage(), e);
        }
    }

    /**
     * Asynchronously executes an API request based on the provided parameters. This method
     * handles request building, serialization, asynchronous execution, deserialization, and
     * error handling.
     *
     * @param method       The HTTP method.
     * @param endpoint     The API endpoint path.
     * @param requestObject The request object to be serialized and sent in the request body.
     * @param contentType  The content type of the request.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return An {@link AsyncResponse} object representing the asynchronous operation.
     */
    protected <ResponseType extends AbstractResponse, RequestType extends Request> AsyncResponse<ResponseType> executeRequestAsync(String method, String endpoint, RequestType requestObject, ContentType contentType, Class<ResponseType> responseType) {
        String uri = buildEndpointUrl(endpoint, null);
        Map<String, String> headers = buildHeaders(contentType);

        CompletableFuture<ResponseType> responseFuture;
        try {
            responseFuture = executeSpecificRequestAsync(method, uri, requestObject, headers, responseType);
        } catch (JsonException e) {
            return AsyncResponse.fromException(new ResourceException(e));
        }

        return AsyncResponse.fromFuture(responseFuture);
    }

    /**
     * Executes an HTTP request using the specified method, URL, request object, headers, and content type.
     * This method delegates the actual execution to the appropriate method of the underlying
     * {@link com.enovka.gemini4j.infrastructure.http.spec.HttpClient}, handling potential
     * {@link HttpException}.
     *
     * @param method       The HTTP method (e.g., "GET", "POST").
     * @param url         The full URL of the API endpoint.
     * @param requestObject The request object to be serialized and sent in the request body.
     * @param headers      The headers to include in the request.
     * @param contentType  The content type of the request.
     * @return The HTTP response.
     * @throws HttpException If an HTTP error occurs during the request.
     * @throws JsonException If an error occurs during JSON serialization of the request object.
     * @since 0.2.0
     */
    protected <RequestType extends Request> HttpResponse executeHttpRequest(String method, String url, RequestType requestObject, Map<String, String> headers, ContentType contentType) throws HttpException, JsonException {
        String body = requestObject != null ? geminiClient.getJsonService().serialize(requestObject) : null;
        switch (method.toUpperCase()) {
            case "GET":
                return geminiClient.getHttpClient().get(url, headers);
            case "POST":
                return geminiClient.getHttpClient().post(url, body, headers, contentType);
            case "PATCH":
                return geminiClient.getHttpClient().patch(url, body, headers, contentType);
            case "DELETE":
                return geminiClient.getHttpClient().delete(url, headers);
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    /**
     * Asynchronously executes a specific HTTP request based on the provided method, URI, request object,
     * headers, and response type. This method handles request building, serialization, asynchronous execution,
     * deserialization, and error handling.
     *
     * @param method       The HTTP method (e.g., "GET", "POST").
     * @param uri          The full URI of the API endpoint.
     * @param requestObject The request object to be sent in the request body.
     * @param headers      The headers to include in the request.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return A {@link CompletableFuture} representing the asynchronous operation, which will resolve to the deserialized response object.
     * @throws JsonException If an error occurs during JSON serialization.
     * @since 0.2.0
     */
    protected <ResponseType extends AbstractResponse, RequestType extends Request> CompletableFuture<ResponseType> executeSpecificRequestAsync(String method, String uri, RequestType requestObject, Map<String, String> headers, Class<ResponseType> responseType) throws JsonException {
        String body = requestObject != null ? geminiClient.getJsonService().serialize(requestObject) : null;
        switch (method.toUpperCase()) {
            case "GET":
                CompletableFuture<ResponseType> completableFutureGet = new CompletableFuture<>();
                return geminiClient.getHttpClient().getAsync(uri, headers, new AsyncCallback<HttpResponse>() {
                    @Override
                    public void onSuccess(HttpResponse result) {
                        try {
                            completableFutureGet.complete(deserializeResponse(result, responseType));
                        } catch (ResourceException e) {
                            completableFutureGet.completeExceptionally(e);
                        }
                    }

                    @Override
                    public void onError(Throwable exception) {
                        completableFutureGet.completeExceptionally(new ResourceException(exception));
                    }
                }).thenApply(httpResponse -> {
                    try {
                        return deserializeResponse(httpResponse, responseType);
                    } catch (ResourceException e) {
                        throw new CompletionException(e);
                    }
                });
            case "POST":
                CompletableFuture<ResponseType> completableFuturePost = new CompletableFuture<>();
                return geminiClient.getHttpClient().postAsync(uri, body, headers, ContentType.APPLICATION_JSON, new AsyncCallback<HttpResponse>() {
                    @Override
                    public void onSuccess(HttpResponse result) {
                        try {
                            completableFuturePost.complete(deserializeResponse(result, responseType));
                        } catch (ResourceException e) {
                            completableFuturePost.completeExceptionally(e);
                        }
                    }

                    @Override
                    public void onError(Throwable exception) {
                        completableFuturePost.completeExceptionally(new ResourceException(exception));
                    }
                }).thenApply(httpResponse -> {
                    try {
                        return deserializeResponse(httpResponse, responseType);
                    } catch (ResourceException e) {
                        throw new CompletionException(e);
                    }
                });
            case "PATCH":
                CompletableFuture<ResponseType> completableFuturePatch = new CompletableFuture<>();
                return geminiClient.getHttpClient().patchAsync(uri, body, headers, ContentType.APPLICATION_JSON, new AsyncCallback<HttpResponse>() {
                    @Override
                    public void onSuccess(HttpResponse result) {
                        try {
                            completableFuturePatch.complete(deserializeResponse(result, responseType));
                        } catch (ResourceException e) {
                            completableFuturePatch.completeExceptionally(e);
                        }
                    }

                    @Override
                    public void onError(Throwable exception) {
                        completableFuturePatch.completeExceptionally(new ResourceException(exception));
                    }
                }).thenApply(httpResponse -> {
                    try {
                        return deserializeResponse(httpResponse, responseType);
                    } catch (ResourceException e) {
                        throw new CompletionException(e);
                    }
                });
            case "DELETE":
                CompletableFuture<ResponseType> completableFutureDelete = new CompletableFuture<>();
                return geminiClient.getHttpClient().deleteAsync(uri, headers, new AsyncCallback<HttpResponse>() {
                    @Override
                    public void onSuccess(HttpResponse result) {
                        try {
                            completableFutureDelete.complete(deserializeResponse(result, responseType));
                        } catch (ResourceException e) {
                            completableFutureDelete.completeExceptionally(e);
                        }
                    }

                    @Override
                    public void onError(Throwable exception) {
                        completableFutureDelete.completeExceptionally(new ResourceException(exception));
                    }
                }).thenApply(httpResponse -> {
                    try {
                        return deserializeResponse(httpResponse, responseType);
                    } catch (ResourceException e) {
                        throw new CompletionException(e);
                    }
                });
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }
    /**
     * Builds standard headers for API requests, including authentication and content type.
     *
     * @param contentType The content type of the request.
     * @return A map containing the standard headers.
     * @since 0.2.0
     */
    protected Map<String, String> buildHeaders(ContentType contentType) {
        Map<String, String> headers = new HashMap<>(geminiClient.buildAuthHeaders());
        headers.put("Content-Type", contentType.getMimeType());
        return headers;
    }

    /**
     * Deserializes the HTTP response into an object of the specified type.
     *
     * @param httpResponse The HTTP response.
     * @param responseType The class of the expected response object.
     * @param <ResponseType> The type of the response object.
     * @return The deserialized response object.
     * @throws ResourceException If an error occurs during deserialization or if the API returns an error response.
     * @since 0.2.0
     */
    protected <ResponseType extends AbstractResponse> ResponseType deserializeResponse(HttpResponse httpResponse, Class<ResponseType> responseType) throws ResourceException {
        try {
            String responseBody = httpResponse.getBody();
            return geminiClient.getJsonService().deserialize(responseBody, responseType);
        } catch (JsonException e) {
            throw new ResourceException("Error deserializing response: " + e.getMessage(), e);
        }
    }

    /**
     * Builds the URL for the API endpoint, including the base URL, model name, endpoint path,
     * and query parameters.
     *
     * @param endpoint    The API endpoint path.
     * @param queryParams A map of query parameters.
     * @return The complete URL for the API endpoint.
     * @since 0.2.0
     */
    protected String buildEndpointUrl(String endpoint, Map<String, ?> queryParams) {
        StringBuilder urlBuilder = new StringBuilder(geminiClient.getBaseUrl());
        if (endpoint != null && !endpoint.isEmpty()) {
            urlBuilder.append(geminiClient.getModelName()).append(":").append(endpoint);
        }
        addQueryParams(urlBuilder, queryParams);
        addQueryParam(urlBuilder, "key", geminiClient.getApiKey());
        return urlBuilder.toString();
    }

    /**
     * Adds query parameters to the URL builder.
     *
     * @param urlBuilder  The URL builder.
     * @param queryParams A map of query parameters.
     * @since 0.2.0
     */
    protected void addQueryParams(StringBuilder urlBuilder, Map<String, ?> queryParams) {
        if (queryParams != null && !queryParams.isEmpty()) {
            queryParams.forEach((key, value) -> addQueryParam(urlBuilder, key, value));
        }
    }

    /**
     * Adds a single query parameter to the URL builder.
     *
     * @param urlBuilder The URL builder.
     * @param paramName  The parameter name.
     * @param paramValue The parameter value.
     * @since 0.2.0
     */
    protected void addQueryParam(StringBuilder urlBuilder, String paramName, Object paramValue) {
        if (paramValue != null) {
            urlBuilder.append(urlBuilder.indexOf("?") == -1 ? "?" : "&");
            urlBuilder.append(paramName).append("=").append(paramValue);
        }
    }

    /**
     * {@inheritDoc}
     * @since 0.2.0
     */
    @Override
    public GeminiClient getGeminiClient() {
        return geminiClient;
    }
}