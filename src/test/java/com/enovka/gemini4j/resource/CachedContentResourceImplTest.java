package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.ResourceFiles;
import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.CachedContent;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.response.ListCachedContentsResponse;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.CachedContentResource;
import org.junit.jupiter.api.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for
 * {@link com.enovka.gemini4j.resource.impl.CachedContentResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CachedContentResourceImplTest {

    private static final String MODEL_NAME = "models/gemini-1.5-flash-001";
    private static final String SAMPLE_TEXT_FILE = "sample.txt";
    private static final String TTL = "300s";
    private static final String UPDATED_TTL = "600s";

    private GeminiClient geminiClient;
    private CachedContentResource cachedContentResource;
    private String cacheName;

    @BeforeEach
    public void setUp() {
        geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .build();

        cachedContentResource = ResourceBuilder.builder(geminiClient)
                .buildCachedContentResource();
    }

    /**
     * Tests the creation of cached content.
     *
     * @throws ResourceException If an error occurs during the cached content
     * creation process.
     */
    @Test
    @Order(0)
    public void testCreateCachedContent() throws ResourceException {
        CachedContent cachedContent = createCachedContent(MODEL_NAME,
                SAMPLE_TEXT_FILE, TTL);
        cacheName = cachedContent.getName();

        assertNotNull(cachedContent, "Cached content should not be null.");
        assertNotNull(cachedContent.getName(),
                "Cached content name should not be null.");
        assertEquals(MODEL_NAME, cachedContent.getModel(),
                "Cached content model should match the provided model.");
    }

    /**
     * Tests the listing of cached contents.
     *
     * @throws ResourceException If an error occurs during the cached contents
     * listing process.
     */
    @Test
    @Order(1)
    public void testListCachedContents() throws ResourceException {
        ListCachedContentsResponse response
                = cachedContentResource.listCachedContents(null, null);
        assertNotNull(response,
                "List cached contents response should not be null.");
        assertFalse(response.getCachedContents().isEmpty(),
                "Cached contents list should not be empty.");
    }

    /**
     * Tests the retrieval of cached content by name.
     *
     * @throws ResourceException If an error occurs during the cached content
     * reading process.
     */
    @Test
    @Order(2)
    public void testGetCachedContent() throws ResourceException {
        CachedContent retrievedCachedContent
                = cachedContentResource.getCachedContent(cacheName);

        assertNotNull(retrievedCachedContent,
                "Retrieved cached content should not be null.");
        assertEquals(cacheName, retrievedCachedContent.getName(),
                "Cached content names should match.");
    }

    /*    *//**
     * Tests the update of cached content.
     *
     * @throws ResourceException If an error occurs during the cached content
     * updating process.
     *//*
    @Test
    @Order(3)
    public void testUpdateCachedContent() throws ResourceException {
        CachedContent updatedCachedContent
                = cachedContentResource.updateCachedContent(
                CachedContentRequest.builder()
                        .withTtl(UPDATED_TTL)
                        .build(),
                "ttl", cacheName);

        assertNotNull(updatedCachedContent,
                "Updated cached content should not be null.");
        assertEquals(UPDATED_TTL, updatedCachedContent.getTtl(),
                "TTL should be updated to " + UPDATED_TTL);
    }*/

    /**
     * Tests the deletion of cached content.
     *
     * @throws ResourceException If an error occurs during the cached content
     * deleting process.
     */
    @Test
    @Order(4)
    public void testDeleteCachedContent() throws ResourceException {
        cachedContentResource.deleteCachedContent(cacheName);

        // Attempting to retrieve the deleted cached content should throw an exception
        assertThrows(ResourceException.class,
                () -> cachedContentResource.getCachedContent(cacheName));
    }

    /**
     * Creates a new cached content instance.
     *
     * @param modelName The name of the model to use for the cached content.
     * @param fileName The name of the file containing the text content.
     * @param ttl The time-to-live for the cached content.
     * @return The created {@link CachedContent} instance.
     * @throws ResourceException If an error occurs during the cached content
     * creation process.
     */
    private CachedContent createCachedContent(String modelName, String fileName,
                                              String ttl)
            throws ResourceException {
        return cachedContentResource.execute(
                cachedContentResource.createCachedContentBuilder(modelName)
                        .withContent(Content.builder()
                                .withRole("user")
                                .withParts(
                                        Collections.singletonList(Part.builder()
                                                .withText(
                                                        ResourceFiles.loadFileFromResources(
                                                                fileName))
                                                .build()))
                                .build())
                        .withTtl(ttl)
                        .build());
    }
}