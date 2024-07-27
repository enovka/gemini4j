package com.enovka.gemini4j.resource.impl;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.CachedContent;
import com.enovka.gemini4j.domain.response.AbstractGeminiResponse;
import com.enovka.gemini4j.domain.response.ListCachedContentsResponse;
import com.enovka.gemini4j.infrastructure.exception.GeminiInfrastructureException;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.builder.CachedContentRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.AbstractResource;
import com.enovka.gemini4j.resource.spec.CachedContentResource;

import java.util.Map;

/**
 * Implementation of the {@link CachedContentResource} interface for interacting
 * with the cached content resource of the Gemini API. This class provides
 * methods for creating, listing, getting, updating and deleting cached
 * content.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class CachedContentResourceImpl
        extends AbstractResource<CachedContentResource>
        implements CachedContentResource {

    private static final String CREATE_CACHED_CONTENT_ENDPOINT
            = "/cachedContents";
    private static final String LIST_CACHED_CONTENTS_ENDPOINT
            = "/cachedContents";
    private static final String GET_CACHED_CONTENT_ENDPOINT = "/%s";
    private static final String UPDATE_CACHED_CONTENT_ENDPOINT = "/%s";
    private static final String DELETE_CACHED_CONTENT_ENDPOINT = "/%s";

    /**
     * Constructs a new CachedContentResourceImpl with the required GeminiClient
     * and JsonService.
     *
     * @param geminiClient The {@link GeminiClient} instance to use for API
     * communication.
     * @param jsonService The {@link JsonService} instance to use for JSON
     * serialization/deserialization.
     */
    public CachedContentResourceImpl(GeminiClient geminiClient,
                                     JsonService jsonService) {
        super(geminiClient, jsonService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GeminiClient getGeminiClient() {
        return geminiClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CachedContent createCachedContent(CachedContent request)
            throws ResourceException {
        return executePostRequest(CREATE_CACHED_CONTENT_ENDPOINT, request,
                CachedContent.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListCachedContentsResponse listCachedContents(Integer pageSize,
                                                         String pageToken)
            throws ResourceException {
        String endpoint = LIST_CACHED_CONTENTS_ENDPOINT;
        if (pageSize != null) {
            endpoint += "?pageSize=" + pageSize;
        }
        if (pageToken != null) {
            endpoint += (pageSize != null ? "&" : "?") + "pageToken="
                    + pageToken;
        }
        return executeGetRequest(endpoint, ListCachedContentsResponse.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CachedContent getCachedContent(String name)
            throws ResourceException {
        String endpoint = String.format(GET_CACHED_CONTENT_ENDPOINT,
                name.replace("/cachedContents", ""));
        return executeGetRequest(endpoint, CachedContent.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CachedContent updateCachedContent(CachedContent cachedContent,
                                             String updateMask, String name)
            throws ResourceException {
        String endpoint = String.format(UPDATE_CACHED_CONTENT_ENDPOINT,
                name.replace("/cachedContents", ""));
        if (updateMask != null) {
            endpoint += "?updateMask=" + updateMask;
        }
        return executePatchRequest(endpoint, cachedContent,
                CachedContent.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCachedContent(String name)
            throws ResourceException {
        String endpoint = String.format(DELETE_CACHED_CONTENT_ENDPOINT,
                name.replace("/cachedContents", ""));
        executeDeleteRequest(endpoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CachedContentRequestBuilder createCachedContentBuilder(
            String model) {
        return CachedContentRequestBuilder.builder(geminiClient)
                .withModel(model);
    }

    /**
     * Executes a PATCH request to the specified endpoint with the given request
     * object and returns the deserialized response.
     *
     * @param endpoint The API endpoint to request.
     * @param requestObject The request object to serialize and send in the
     * request body.
     * @param type The class of the response object.
     * @return The deserialized response object.
     * @throws ResourceException If an error occurs during the request.
     * @since 0.1.0
     */
    private <R extends AbstractGeminiResponse, S> R executePatchRequest(
            String endpoint, S requestObject,
            Class<R> type)
            throws ResourceException {
        try {
            logDebug("Executing PATCH request to endpoint: " + endpoint);
            String requestBody = serializeRequest(requestObject);
            logDebug("Request Body: " + requestBody);
            HttpResponse response = patch(endpoint, requestBody,
                    geminiClient.buildAuthHeaders());
            logDebug("Response Body: " + response.getBody());
            return deserializeResponse(response, type);
        } catch (JsonException e) {
            throw new ResourceException("Error deserializing response", e);
        } catch (GeminiInfrastructureException e) {
            throw new ResourceException(
                    "Infrastructure error executing PATCH request", e);
        } catch (GeminiApiException e) {
            throw new ResourceException("API error executing PATCH request", e);
        }
    }

    /**
     * Makes a PATCH request to the specified endpoint and handles potential
     * errors.
     *
     * @param endpoint The API endpoint to request.
     * @param body The request body.
     * @param headers The headers to include in the request.
     * @return The {@link HttpResponse} object.
     * @throws GeminiInfrastructureException If an error occurs during the HTTP
     * request.
     */
    protected HttpResponse patch(String endpoint, String body,
                                 Map<String, String> headers)
            throws GeminiInfrastructureException {
        try {
            return geminiClient.getHttpClient()
                    .patch(buildEndpointUrl(endpoint), body, headers);
        } catch (HttpException e) {
            throw new GeminiInfrastructureException(
                    "Error executing PATCH request: " + e.getMessage(), e);
        }
    }

    /**
     * Executes a DELETE request to the specified endpoint.
     *
     * @param endpoint The API endpoint to request.
     * @throws ResourceException If an error occurs during the request.
     * @since 0.1.0
     */
    protected void executeDeleteRequest(String endpoint)
            throws ResourceException {
        try {
            logDebug("Executing DELETE request to endpoint: " + endpoint);
            HttpResponse response = delete(endpoint,
                    geminiClient.buildAuthHeaders());
            logDebug("Response Body: " + response.getBody());
        } catch (GeminiInfrastructureException e) {
            throw new ResourceException(
                    "Infrastructure error executing DELETE request", e);
        } catch (GeminiApiException e) {
            throw new ResourceException("API error executing DELETE request",
                    e);
        }
    }

    /**
     * Makes a DELETE request to the specified endpoint and handles potential
     * errors.
     *
     * @param endpoint The API endpoint to request.
     * @param headers The headers to include in the request.
     * @return The {@link HttpResponse} object.
     * @throws GeminiInfrastructureException If an error occurs during the HTTP
     * request.
     */
    protected HttpResponse delete(String endpoint, Map<String, String> headers)
            throws GeminiInfrastructureException, GeminiApiException {
        try {
            return geminiClient.getHttpClient()
                    .delete(buildEndpointUrl(endpoint), headers);
        } catch (HttpException e) {
            throw new GeminiInfrastructureException(
                    "Error executing DELETE request: " + e.getMessage(), e);
        }
    }
}