package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.CachedContent;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;
import com.enovka.gemini4j.domain.request.CachedContentRequest;
import com.enovka.gemini4j.domain.response.ListCachedContentsResponse;
import com.enovka.gemini4j.infrastructure.tool.ResourceFiles;
import com.enovka.gemini4j.resource.builder.CachedContentRequestBuilder;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.impl.CachedContentResourceImpl;
import com.enovka.gemini4j.resource.spec.CachedContentResource;
import org.junit.jupiter.api.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link CachedContentResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CachedContentResourceImplTest {

    private final String modelName = "models/gemini-1.5-flash-001";
    private CachedContentResource cachedContentResource;
    private CachedContentRequestBuilder cachedContentRequestBuilder;
    private CachedContent createdCachedContent;
    private String cacheName;

    @BeforeEach
    public void setUp() {
        GeminiClient geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .build();

        cachedContentResource = ResourceBuilder.builder(geminiClient)
                .buildCachedContentResource();

        cachedContentRequestBuilder
                = cachedContentResource.createCachedContentBuilder(modelName);
    }

    /**
     * Tests the
     * {@link CachedContentResource#createCachedContent(CachedContent)} method.
     *
     * @throws ResourceException If an error occurs during the cached content
     * creation process.
     */
    @Test
    @Order(0)
    public void testCreateCachedContent() throws ResourceException {
        createdCachedContent = cachedContentRequestBuilder
                .withContent(Content.builder().withRole("user").withParts(
                        Collections.singletonList(Part.builder().withText(
                                ResourceFiles.readHtmlFile(
                                        "sample.txt")).build())).build())
                .withTtl("300s")
                .build();
        createdCachedContent = cachedContentResource.createCachedContent(
                createdCachedContent);
        cacheName = createdCachedContent.getName();
        assertNotNull(createdCachedContent,
                "Cached content should not be null.");
        assertNotNull(createdCachedContent.getName(),
                "Cached content name should not be null.");
        assertEquals(modelName, createdCachedContent.getModel(),
                "Cached content model should match the provided model.");
    }

    /**
     * Tests the
     * {@link CachedContentResource#listCachedContents(Integer, String)}
     * method.
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
    }

    /**
     * Tests the {@link CachedContentResource#getCachedContent(String)} method.
     *
     * @throws ResourceException If an error occurs during the cached content
     * reading process.
     */
    @Test()
    @Order(2)
    public void testGetCachedContent() throws ResourceException {
        CachedContent retrievedCachedContent
                = cachedContentResource.getCachedContent(cacheName);

        assertNotNull(retrievedCachedContent,
                "Retrieved cached content should not be null.");
        assertEquals(createdCachedContent.getName(),
                retrievedCachedContent.getName(),
                "Cached content names should match.");
    }

    /**
     * Tests the
     * {@link CachedContentResource#updateCachedContent(CachedContent, String,
     * String)} method.
     *
     * @throws ResourceException If an error occurs during the cached content
     * updating process.
     */
    @Test
    @Order(3)
    public void testUpdateCachedContent() throws ResourceException {
        CachedContent updatedCachedContent
                = cachedContentResource.updateCachedContent(
                new CachedContentRequest(), null, cacheName);

        assertNotNull(updatedCachedContent,
                "Updated cached content should not be null.");
    }

    /**
     * Tests the {@link CachedContentResource#deleteCachedContent(String)}
     * method.
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
}