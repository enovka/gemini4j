package com.enovka.gemini4j.resource.spec.base;

import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.request.spec.Request;
import com.enovka.gemini4j.model.response.spec.AbstractResponse;
import com.enovka.gemini4j.resource.exception.ResourceException;

/**
 * <p>A high-level abstract base class for Gemini API resource implementations, providing a
 * simplified and intuitive API for interacting with the Gemini API. This class builds upon
 * the robust foundation of {@link AbstractResource}, abstracting away the low-level details
 * of HTTP requests and responses. It offers convenient methods for common API actions,
 * handling request building, serialization, execution, deserialization, and error management
 * transparently. Asynchronous operations are seamlessly supported through the
 * {@link AsyncResponse} class.</p>
 *
 * <p>Concrete resource classes should extend this class and implement the abstract methods
 * {@link #getEndpointForRequest(Request)} and
 * type mappings for their respective API actions. This class handles the common HTTP
 * communication logic, allowing subclasses to focus on their unique business logic and
 * provide a clean, action-oriented API to clients.</p>
 *
 * <p>Key features include:</p>
 * <ul>
 *     <li>**High-Level Action Methods:** Offers convenient methods like `get`, `post`, `patch`,
 *     and `delete` that correspond to common API actions, hiding the complexities of HTTP
 *     method selection and request building.</li>
 *     <li>**Automatic Endpoint and Response Type Resolution:**  Dynamically determines the
 *     appropriate endpoint and response type based on the provided request object, reducing
 *     boilerplate code in concrete resource implementations.</li>
 *     <li>**Seamless Asynchronous Operations:** Provides asynchronous counterparts for all
 *     action methods, returning {@link AsyncResponse} objects for non-blocking execution and
 *     fluent response handling.</li>
 *     <li>**Simplified Error Handling:**  Leverages the centralized exception handling of
 *     {@link AbstractResource}, ensuring that checked exceptions are consistently wrapped in
 *     {@link ResourceException}.</li>
 * </ul>
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
public abstract class BaseAbstractResource<R extends AbstractResponse, I extends Request> extends AbstractResource<I> {

    /**
     * Constructs a new BaseAbstractResource instance.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API communication. Must not be null.
     * @throws NullPointerException if geminiClient is null.
     */
    public BaseAbstractResource(GeminiClient geminiClient) {
        super(geminiClient);
    }

    /**
     * Executes a synchronous GET request for the specified request object.
     *
     * @param request The request object containing the necessary information for the GET request.
     * @return The deserialized response object.
     * @throws ResourceException If any error occurs during the request.
     */
    public R get(I request, Class<R> responseType) throws ResourceException {
        String endpoint = getEndpointForRequest(request);
        return get(endpoint, responseType);
    }

    /**
     * Executes an asynchronous GET request for the specified request object.
     *
     * @param request The request object containing the necessary information for the GET request.
     * @return An {@link AsyncResponse} object representing the asynchronous operation.
     */
    public AsyncResponse<R> getAsync(I request, Class<R> responseType) {
        String endpoint = getEndpointForRequest(request);
        return getAsync(endpoint, responseType);
    }

    /**
     * Executes a synchronous POST request for the specified request object.
     *
     * @param request The request object to be serialized and sent in the request body.
     * @return The deserialized response object.
     * @throws ResourceException If any error occurs during the request.
     */
    public R post(I request, Class<R> responseType) throws ResourceException {
        String endpoint = getEndpointForRequest(request);
        return post(endpoint, request, responseType);
    }

    /**
     * Executes an asynchronous POST request for the specified request object.
     *
     * @param request The request object to be serialized and sent in the request body.
     * @return An {@link AsyncResponse} object representing the asynchronous operation.
     */
    public AsyncResponse<R> postAsync(I request, Class<R> responseType) {
        String endpoint = getEndpointForRequest(request);
        return postAsync(endpoint, request, responseType);
    }

    /**
     * Executes a synchronous PATCH request for the specified request object.
     *
     * @param request The request object to be serialized and sent in the request body.
     * @return The deserialized response object.
     * @throws ResourceException If any error occurs during the request.
     */
    public R patch(I request, Class<R> responseType) throws ResourceException {
        String endpoint = getEndpointForRequest(request);
        return patch(endpoint, request, responseType);
    }

    /**
     * Executes an asynchronous PATCH request for the specified request object.
     *
     * @param request The request object to be serialized and sent in the request body.
     * @return An {@link AsyncResponse} object representing the asynchronous operation.
     */
    public AsyncResponse<R> patchAsync(I request, Class<R> responseType) {
        String endpoint = getEndpointForRequest(request);
        return patchAsync(endpoint, request, responseType);
    }

    /**
     * Executes a synchronous DELETE request for the specified request object.
     *
     * @param request The request object containing the necessary information for the DELETE request.
     * @return The deserialized response object.
     * @throws ResourceException If any error occurs during the request.
     */
    public R delete(I request, Class<R> responseType) throws ResourceException {
        String endpoint = getEndpointForRequest(request);
        return delete(endpoint, responseType);
    }

    /**
     * Executes an asynchronous DELETE request for the specified request object.
     *
     * @param request The request object containing the necessary information for the DELETE request.
     * @return An {@link AsyncResponse} object representing the asynchronous operation.
     */
    public AsyncResponse<R> deleteAsync(I request, Class<R> responseType) {
        String endpoint = getEndpointForRequest(request);
        return deleteAsync(endpoint, responseType);
    }

    /**
     * Determines the API endpoint path based on the provided request object. This method must be
     * implemented by concrete resource classes to define the mapping between request objects and
     * API endpoints.
     *
     * @param request The request object.
     * @return The API endpoint path.
     */
    protected abstract String getEndpointForRequest(Request request);

}