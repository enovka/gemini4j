package com.enovka.gemini4j.http;

import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientBuilder;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.hc.core5.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link HttpClient} implementations. It uses WireMock for
 * simulating HTTP responses and verifies the client's ability to handle GET and
 * POST requests, headers, and response parsing. It also ensures that the
 * correct {@link HttpException} is thrown for various error scenarios.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1
 */
public class HttpClientTest {

    private WireMockServer wireMockServer;
    private HttpClient httpClient;

    @BeforeEach
    public void init() {
        System.out.println("Initializing HttpClientTest...");

        // Configure and start WireMock server
        wireMockServer = new WireMockServer(
                WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());

        System.out.println(
                "WireMock server started on port: " + wireMockServer.port());

        httpClient = HttpClientBuilder.builder().build().getCustomClient();
        System.out.println("HttpClient instance: " + httpClient);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Stopping WireMock server...");
        wireMockServer.stop();
    }

    /**
     * Tests a successful GET request with headers using the default
     * {@link HttpClient} implementation.
     *
     * @throws HttpException If an error occurs during the HTTP request.
     */
    @Test
    public void testGetRequestWithHeaders() throws HttpException {
        System.out.println("Starting testGetRequestWithHeaders...");

        // Stub a GET request with specific headers
        System.out.println(
                "Stubbing GET request on URL: " + wireMockServer.baseUrl()
                        + "/test");
        stubFor(get(urlEqualTo("/test"))
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("Custom-Header", equalTo("test-value"))
                .willReturn(aResponse().withStatus(200)
                        .withBody("{\"message\": \"Hello, World!\"}")));

        // Prepare headers for the request
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Custom-Header", "test-value");
        System.out.println("Request headers: " + headers);

        // Execute the GET request
        System.out.println("Executing GET request...");
        HttpResponse response = httpClient.get(
                wireMockServer.baseUrl() + "/test", headers);
        System.out.println("GET request executed.");

        // Assertions
        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\": \"Hello, World!\"}", response.getBody());

        System.out.println("testGetRequestWithHeaders completed successfully.");
    }

    /**
     * Tests a successful POST request with headers and a body using the updated
     * {@link HttpClient} implementation, which now requires a {@code ContentType}
     * argument for the {@code post} method.  This test verifies that the client
     * correctly sends the POST request with the specified headers and body and
     * receives the expected response.  It uses WireMock to stub the HTTP interaction
     * and asserts on the status code and response body.
     *
     * @throws HttpException If an error occurs during the HTTP request.
     * @since 0.0.1
     */
    @Test
    public void testPostRequestWithHeadersAndBody() throws HttpException {
        System.out.println("Starting testPostRequestWithHeadersAndBody...");

        // Stub a POST request with specific headers and body
        stubFor(post(urlEqualTo("/test"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(equalToJson("{\"message\": \"Hello, World!\"}"))
                .willReturn(aResponse().withStatus(201).withBody("{\"id\": \"123\"}")));

        // Prepare headers and body for the request
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String requestBody = "{\"message\": \"Hello, World!\"}";

        // Execute the POST request, providing the ContentType
        HttpResponse response = httpClient.post(wireMockServer.baseUrl() + "/test", requestBody, headers, ContentType.APPLICATION_JSON);

        // Assertions
        assertEquals(201, response.getStatusCode());
        assertEquals("{\"id\": \"123\"}", response.getBody());

        System.out.println("testPostRequestWithHeadersAndBody completed successfully.");
    }

    /**
     * Tests that the correct {@link HttpException} is thrown when a client
     * error (4xx status code) occurs.
     */
    @Test
    public void testHttpClientError() {
        System.out.println("Starting testHttpClientError...");

        // Stub a GET request that returns a 404 Not Found error
        System.out.println(
                "Stubbing GET request on URL: " + wireMockServer.baseUrl()
                        + "/test");
        stubFor(get(urlEqualTo("/test")).willReturn(
                aResponse().withStatus(404)));

        // Execute the GET request and assert that an HttpException is thrown
        System.out.println("Executing GET request expecting HttpException...");
        HttpException exception = assertThrows(HttpException.class,
                () -> httpClient.get(wireMockServer.baseUrl() + "/test",
                        new HashMap<>()));
        System.out.println("HttpException caught.");

        // Assertions about the exception
        System.out.println(
                "Exception status code: " + exception.getStatusCode());
        System.out.println("Exception message: " + exception.getMessage());
        assertEquals(404, exception.getStatusCode());
        assertNotNull(exception.getMessage());

        System.out.println("testHttpClientError completed successfully.");
    }

    /**
     * Tests that the correct {@link HttpException} is thrown when a server
     * error (5xx status code) occurs.
     */
    @Test
    public void testHttpServerError() {
        System.out.println("Starting testHttpServerError...");
        System.out.println(
                "WireMock isHttpEnabled()? : " + wireMockServer.isRunning());

        // Stub a GET request that returns a 500 Internal Server Error
        System.out.println(
                "Stubbing GET request on URL: " + wireMockServer.baseUrl()
                        + "/test");
        stubFor(get(urlEqualTo("/test")).willReturn(
                aResponse().withStatus(500)));

        // Execute the GET request and assert that an HttpException is thrown
        System.out.println("Executing GET request expecting HttpException...");
        HttpException exception = assertThrows(HttpException.class,
                () -> httpClient.get(wireMockServer.baseUrl() + "/test",
                        new HashMap<>()));
        System.out.println("HttpException caught.");

        // Assertions about the exception
        System.out.println(
                "Exception status code: " + exception.getStatusCode());
        System.out.println("Exception message: " + exception.getMessage());
        assertEquals(500, exception.getStatusCode());
        assertNotNull(exception.getMessage());

        System.out.println("testHttpServerError completed successfully.");
    }
}