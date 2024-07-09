package com.enovka.gemini4j.http;

import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.http.factory.HttpClientServiceFactory;
import com.enovka.gemini4j.http.factory.HttpClientServiceFactory.ClientType;
import com.enovka.gemini4j.http.spec.HttpClientService;
import com.enovka.gemini4j.http.spec.HttpResponseWrapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the HTTP Client Abstraction Layer. It tests the
 * functionalities of the {@link HttpClientService} interface and the
 * {@link HttpClientServiceFactory} class using WireMock for simulating external
 * API responses.
 *
 * @author Everson Novka <enovka@gmail.com>
 * @since 1.0.0-beta
 */
public class HttpClientServiceTest {

    private static final String TEST_URL = "/test";
    private static final int TEST_PORT = 8080;
    private static final String TEST_BODY = "test body";
    private static final String ERROR_MESSAGE_INVALID_URL
            = "Invalid URL provided.";
    private static final String ERROR_MESSAGE_INVALID_REQUEST_BODY
            = "Invalid request body provided.";

    private static WireMockServer wireMockServer;
    private HttpClientService httpClientService;

    @BeforeAll
    public static void setUpClass() {
        wireMockServer = new WireMockServer(
                WireMockConfiguration.options().port(TEST_PORT));
        wireMockServer.start();
        WireMock.configureFor("localhost", TEST_PORT);
    }

    @AfterAll
    public static void tearDownClass() {
        wireMockServer.stop();
    }

    @BeforeEach
    public void setUp() {
        httpClientService = HttpClientServiceFactory.create(ClientType.DEFAULT);
    }

    @Test
    public void testGetWithValidUrl() throws HttpException {
        stubFor(get(urlEqualTo(TEST_URL)).willReturn(
                aResponse().withStatus(200).withBody(TEST_BODY)));

        HttpResponseWrapper response = httpClientService.get(
                "http://localhost:" + TEST_PORT + TEST_URL,
                Collections.emptyMap());

        assertEquals(200, response.statusCode());
        assertEquals(TEST_BODY, response.body());
    }

    @Test
    public void testPostWithValidUrl() throws HttpException {
        stubFor(post(urlEqualTo(TEST_URL)).withRequestBody(equalTo(TEST_BODY))
                .willReturn(aResponse().withStatus(200).withBody(TEST_BODY)));

        HttpResponseWrapper response = httpClientService.post(
                "http://localhost:" + TEST_PORT + TEST_URL, TEST_BODY,
                Collections.emptyMap());

        assertEquals(200, response.statusCode());
        assertEquals(TEST_BODY, response.body());
    }

    @Test
    public void testGetWithInvalidUrl() {
        assertThrows(HttpException.class,
                () -> httpClientService.get(null, Collections.emptyMap()),
                ERROR_MESSAGE_INVALID_URL);
    }

    @Test
    public void testPostWithInvalidRequestBody() {
        assertThrows(HttpException.class,
                () -> httpClientService.post(
                        "http://localhost:" + TEST_PORT + TEST_URL, null,
                        Collections.emptyMap()),
                ERROR_MESSAGE_INVALID_REQUEST_BODY);
    }

    @Test
    public void testGetWith4xxError() {
        stubFor(get(urlEqualTo(TEST_URL)).willReturn(
                aResponse().withStatus(400).withBody("Error 400")));

        assertThrows(HttpException.class,
                () -> httpClientService.get(
                        "http://localhost:" + TEST_PORT + TEST_URL,
                        Collections.emptyMap()));
    }

    @Test
    public void testPostWith5xxError() {
        stubFor(post(urlEqualTo(TEST_URL)).willReturn(
                aResponse().withStatus(500).withBody("Error 500")));

        assertThrows(HttpException.class,
                () -> httpClientService.post(
                        "http://localhost:" + TEST_PORT + TEST_URL, TEST_BODY,
                        Collections.emptyMap()));
    }

}