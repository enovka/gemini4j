package com.enovka.gemini4j.http;

import com.enovka.gemini4j.common.BaseClass;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.impl.DefaultHttpClient;
import com.enovka.gemini4j.http.spec.HttpResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the {@link DefaultHttpClient}. It uses WireMock for simulating
 * HTTP responses and verifies the client's ability to handle GET and POST
 * requests, headers, and response parsing.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class DefaultHttpClientTest extends BaseClass {

    private static final String WIREMOCK_HOST = "localhost";
    private static final int WIREMOCK_PORT = 8080;
    private static final String TEST_URL = "/test";
    private static final String TEST_RESPONSE_BODY = "{\"message\": \"Hello, World!\"}";
    private static WireMockServer wireMockServer;
    private DefaultHttpClient httpClient;

    /**
     * Sets up the WireMock server before all tests.
     */
    @BeforeAll
    public static void setUpClass() {
        wireMockServer = new WireMockServer(
                WireMockConfiguration.options()
                        .port(WIREMOCK_PORT)
                        .bindAddress("0.0.0.0")); // Bind to all interfaces
        wireMockServer.start();
    }

    /**
     * Stops the WireMock server after all tests.
     */
    @AfterAll
    public static void tearDownClass() {
        wireMockServer.stop();
    }

    /**
     * Initializes the {@link DefaultHttpClient} before each test.
     */
    @BeforeEach
    public void setUp() {
        httpClient = new DefaultHttpClient();
    }

    /**
     * Tests a successful GET request with headers.
     *
     * @throws HttpException If an error occurs during the HTTP request.
     */
    @Test
    public void testGetRequestWithHeaders() throws HttpException {
        // Stub a GET request with specific headers
        stubFor(get(urlEqualTo(TEST_URL))
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("Custom-Header", equalTo("test-value"))
                .willReturn(aResponse().withStatus(200)
                        .withBody(TEST_RESPONSE_BODY)));

        // Prepare headers for the request
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Custom-Header", "test-value");

        // Execute the GET request using the dynamic WireMock port
        HttpResponse response = httpClient.get(
                "http://" + WIREMOCK_HOST + ":" + wireMockServer.port() + TEST_URL, headers);

        // Assertions
        assertEquals(200, response.getStatusCode());
        assertEquals(TEST_RESPONSE_BODY, response.getBody());
        logDebug("GET request with headers successful.");
    }

    /**
     * Tests a successful POST request with headers and a body.
     *
     * @throws HttpException If an error occurs during the HTTP request.
     */
    @Test
    public void testPostRequestWithHeadersAndBody() throws HttpException {
        // Stub a POST request with specific headers and body
        stubFor(post(urlEqualTo(TEST_URL))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(equalToJson(TEST_RESPONSE_BODY))
                .willReturn(aResponse().withStatus(201).withBody(
                        "{\"id\": \"123\"}")));

        // Prepare headers and body for the request
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        // Execute the POST request using the dynamic WireMock port
        HttpResponse response = httpClient.post(
                "http://" + WIREMOCK_HOST + ":" + wireMockServer.port() + TEST_URL,
                TEST_RESPONSE_BODY,
                headers);

        // Assertions
        assertEquals(201, response.getStatusCode());
        assertEquals("{\"id\": \"123\"}", response.getBody());
        logDebug("POST request with headers and body successful.");
    }

    /**
     * Tests an HTTP request that results in a client error (4xx status code).
     */
    @Test
    public void testHttpClientError() {
        // Stub a GET request that returns a 404 Not Found error
        stubFor(get(urlEqualTo(TEST_URL))
                .willReturn(aResponse().withStatus(404)));

        // Execute the GET request using the dynamic WireMock port and assert that it throws an HttpException
        assertThrows(HttpException.class, () -> httpClient.get(
                "http://" + WIREMOCK_HOST + ":" + wireMockServer.port() + TEST_URL,
                new HashMap<>()));
        logDebug("HttpClientError test successful.");
    }

    /**
     * Tests an HTTP request that results in a server error (5xx status code).
     */
    @Test
    public void testHttpServerError() {
        // Stub a GET request that returns a 500 Internal Server Error
        stubFor(get(urlEqualTo(TEST_URL))
                .willReturn(aResponse().withStatus(500)));

        // Execute the GET request using the dynamic WireMock port and assert that it throws an HttpException
        assertThrows(HttpException.class, () -> httpClient.get(
                "http://" + WIREMOCK_HOST + ":" + wireMockServer.port() + TEST_URL,
                new HashMap<>()));
        logDebug("HttpServerError test successful.");
    }
}