package com.enovka.gemini4j.example;

import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.request.BatchEmbedContentsRequest;
import com.enovka.gemini4j.domain.request.EmbedContentRequest;
import com.enovka.gemini4j.domain.response.BatchEmbedContentsResponse;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;
import com.enovka.gemini4j.domain.type.TaskTypeEnum;
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.spec.EmbedResource;

import java.util.Arrays;
import java.util.List;

/**
 * Example demonstrating the usage of the EmbedResource in the Gemini4j library.
 * This class showcases how to generate embeddings for single and multiple
 * texts.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class EmbedContentExample {

    public static void main(String[] args) {
        // Replace with your actual API key
        String apiKey = "AIzaSyBk4YjBizGHvRY2EPWHvGVyvVIMT2giW4w";

        // Create a GeminiClient
        GeminiClient client = GeminiClientBuilder.builder()
                .withApiKey(apiKey)
                .withModel(
                        "text-embedding-004") // Set the desired embedding model
                .build();

        // Create an EmbedResource
        EmbedResource embedResource = ResourceBuilder.builder(client)
                .buildEmbedResource();

        // Example 1: Generate an embedding for a single text
        String text = "This is an example text for embedding.";
        EmbedContentRequest request1 = embedResource.embedContentBuilder(text)
                .withText(text)
                .withTaskType(TaskTypeEnum.RETRIEVAL_QUERY)
                .withOutputDimensionality(
                        128) // Optional: Set the output dimensionality
                .build();

        // Example 2: Generate embeddings for multiple texts in a batch request
        List<String> texts = Arrays.asList(
                "The quick brown fox jumps over the lazy dog.",
                "Never underestimate the power of a good book.",
                "Life is a journey, not a destination."
        );

        BatchEmbedContentsRequest request2
                = embedResource.batchEmbedContentsBuilder(texts)
                .build();

        // Execute the requests and print the generated embeddings
        try {
            EmbedContentResponse response1 = embedResource.embedContent(
                    request1);
            System.out.println(
                    "Example 1 - Embedding: " + response1.getEmbedding());

            BatchEmbedContentsResponse response2
                    = embedResource.batchEmbedContent(request2);
            response2.getEmbeddings().forEach(embedding ->
                    System.out.println(
                            "Example 2 - Embedding: " + embedding.toString())
            );

        } catch (GeminiApiException | JsonException e) {
            e.printStackTrace();
        }
    }
}