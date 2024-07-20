package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.enovka.gemini4j.resource.impl.GenerationResourceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link GenerationResourceImpl}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.3
 */
public class GenerationResourceImplTest {

    private GenerationResourceImpl generationResource;
    private GeminiClient geminiClient;
    private JsonService jsonService;

    @BeforeEach
    public void setUp() {
        geminiClient = GeminiClientBuilder.builder()
                .withApiKey(System.getenv("GEMINI_API_KEY"))
                .withModel("models/gemini-1.5-flash-001")
                .build();
        jsonService = JsonServiceBuilder.builder().build().build();
        generationResource = new GenerationResourceImpl(geminiClient,
                jsonService);
    }

    /**
     * Tests the
     * {@link GenerationResourceImpl#generateContent(GenerateContentRequest)}
     * method.
     */
    @Test
    public void testGenerateContent() {
        GenerateContentRequest request
                = generationResource.generateContentBuilder("Hello, Gemini!")
                .build();
        try {
            GeminiResult result = generationResource.generateContent(request);
            assertNotNull(result.getGeneratedText(),
                    "Generated text should not be null.");
        } catch (Exception | HttpException e) {
            // Handle exceptions appropriately
            System.err.println("Error generating content: " + e.getMessage());
        }
    }
}