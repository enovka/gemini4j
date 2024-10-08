package com.enovka.gemini4j.resource;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.model.request.TokensRequest;
import com.enovka.gemini4j.model.response.TokensResponse;
import com.enovka.gemini4j.model.type.SupportedModelMethod;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.builder.request.TokensRequestBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.TokensResource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link TokensResource} implementation. It verifies the resource's ability
 * to interact with the Gemini API's token counting endpoint, handling various input scenarios
 * and ensuring correct token counts are returned.  It also tests error handling and ensures
 * that the correct exceptions are thrown for invalid requests.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.2.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TokensResourceTest {

    private static final String GEMINI_API_KEY = System.getenv("GEMINI_API_KEY");
    private static GeminiClient geminiClient;
    private static TokensResource tokensResource;

    /**
     * Initializes the Gemini client and TokensResource before all tests.
     *
     * @since 0.2.0
     */
    @BeforeAll
    void init() {
        geminiClient = GeminiClientBuilder.builder()
                .withApiKey(GEMINI_API_KEY)
                .withModel("models/gemini-1.5-flash-001")
                .withResponseTimeout(60000 * 20)
                .withRateLimiter(5, Duration.ofMinutes(1))
                .build();

        tokensResource = ResourceBuilder.builder(geminiClient).buildCountTokensResource();
    }

    /**
     * Tests counting tokens with valid text input.  Verifies that the returned token count is
     * positive and that no exceptions are thrown.
     *
     * @throws ResourceException If an error occurs during the API call.
     * @since 0.2.0
     */
    @Test
    void countTokens_withValidText_shouldReturnPositiveTokenCount() throws ResourceException {
        String validText = "This is a test sentence.";
        TokensRequest request = TokensRequestBuilder.builder()
                .withUserContent(validText)
                .build();

        TokensResponse response = tokensResource.execute(request);

        assertNotNull(response, "Response should not be null");
        assertTrue(response.getTotalTokens() > 0, "Token count should be positive");
    }

    /**
     * Tests counting tokens with an empty text input. Verifies that a ResourceException is thrown
     * because the API requires a non-empty text input.
     *
     * @since 0.2.0
     */
    @Test
    void countTokens_withEmptyText_shouldThrowResourceException() {
        String emptyText = "";
        assertThrows(IllegalArgumentException.class, () -> TokensRequestBuilder.builder()
                .withUserContent(emptyText)
                .build());
    }

    /**
     * Tests counting tokens with null text input. Verifies that a ResourceException is thrown
     * because the API requires a non-null text input.
     *
     * @since 0.2.0
     */
    @Test
    void countTokens_withNullText_shouldThrowResourceException() {
        assertThrows(IllegalArgumentException.class, () -> TokensRequestBuilder.builder()
                .withUserContent(null)
                .build());
    }


    /**
     * Tests the {@link TokensResource#getModelMethodList()} method.  Verifies that the returned list
     * of supported model methods is not null and contains only the expected method.
     *
     * @since 0.2.0
     */
    @Test
    void getModelMethodList_shouldReturnCorrectMethod() {
        List<SupportedModelMethod> supportedMethods = tokensResource.getModelMethodList();
        assertNotNull(supportedMethods, "Supported methods list should not be null");
        assertEquals(List.of(SupportedModelMethod.COUNT_TOKENS), supportedMethods, "Supported methods list should contain only COUNT_TOKENS");
    }

    /**
     * Tests the {@link TokensResource#getGeminiClient()} method. Verifies that the returned
     * GeminiClient is not null and is the same instance used to create the resource.
     *
     * @since 0.2.0
     */
    @Test
    void getGeminiClient_shouldReturnCorrectInstance() {
        GeminiClient client = tokensResource.getGeminiClient();
        assertNotNull(client, "GeminiClient should not be null");
        assertSame(geminiClient, client, "GeminiClient instances should be the same");
    }

    /**
     * Tests the {@link TokensResource#countTokensBuilder(String)} method.  Verifies that a valid
     * TokensRequestBuilder is returned when a valid text input is provided.
     *
     * @since 0.2.0
     */
    @Test
    void countTokensBuilder_withValidText_shouldReturnValidBuilder() {
        String validText = "This is a test sentence.";
        TokensRequestBuilder builder = tokensResource.countTokensBuilder(validText);
        assertNotNull(builder, "Builder should not be null");
        TokensRequest request = builder.build();
        assertNotNull(request, "Request should not be null");
        assertEquals(validText, request.getContents().get(0).getParts().get(0).getText(), "Text should match");
    }


    /**
     * Tests the {@link TokensResource#countTokensBuilder(String)} method with null text input.
     * Verifies that an IllegalArgumentException is thrown when a null text input is provided.
     *
     * @since 0.2.0
     */
    @Test
    void countTokensBuilder_withNullText_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> tokensResource.countTokensBuilder(null));
    }

    /**
     * Tests the {@link TokensResource#countTokensBuilder(String)} method with empty text input.
     * Verifies that an IllegalArgumentException is thrown when an empty text input is provided.
     *
     * @since 0.2.0
     */
    @Test
    void countTokensBuilder_withEmptyText_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> tokensResource.countTokensBuilder(""));
    }
}