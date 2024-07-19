package com.enovka.gemini4j.resource.builder;

import com.enovka.gemini4j.domain.request.GenerateContentRequest;
import com.enovka.gemini4j.domain.response.GeminiResult;
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.resource.spec.GenerationResource;

/**
 * Executes a {@link GenerateContentRequest} using a
 * {@link GenerationResource}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class GeminiRequestExecutor {

    private final GenerationResource generationResource;

    /**
     * Constructor for the GeminiRequestExecutor.
     *
     * @param generationResource The GenerationResource instance to use for
     * executing requests.
     */
    public GeminiRequestExecutor(GenerationResource generationResource) {
        this.generationResource = generationResource;
    }

    /**
     * Executes the generateContent request synchronously and returns the
     * response.
     *
     * @param request The GenerateContentRequest to execute.
     * @return The GeminiResult from the Gemini API.
     * @throws Exception If an error occurs during the API request.
     */
    public GeminiResult execute(GenerateContentRequest request)
            throws Exception, HttpException {
        return generationResource.generateContent(request);
    }
}