package com.enovka.gemini4j.infrastructure.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for splitting large strings into smaller chunks while preserving whole words.
 *
 * @since 0.1.4
 */
public class LargeStringSplitter {

    private static final int DEFAULT_CHUNK_SIZE_BYTES = 8000;

    /**
     * Splits a large string into a list of smaller strings, ensuring that words are not split.
     *
     * @param largeString The input string to be split.
     * @return A list of strings, each smaller than the maximum chunk size.
     * @throws NullPointerException If the input string is null.
     * @throws IllegalArgumentException If the chunk size is not positive.
     */
    public static List<String> splitLargeString(String largeString, int chunkSize) {
        if (largeString == null) {
            throw new NullPointerException("Input string cannot be null.");
        }
        if (chunkSize <= 0) {
            throw new IllegalArgumentException("Chunk size must be positive.");
        }

        List<String> chunks = new ArrayList<>();
        int startIndex = 0;

        while (startIndex < largeString.length()) {
            int endIndex = findNextWordBoundary(largeString, startIndex, chunkSize);
            chunks.add(largeString.substring(startIndex, endIndex));
            startIndex = endIndex;
        }

        return chunks;
    }

    /**
     * Splits a large string into a list of smaller strings, ensuring that words are not split.
     * Uses the default chunk size of {@link #DEFAULT_CHUNK_SIZE_BYTES}.
     *
     * @param largeString The input string to be split.
     * @return A list of strings, each smaller than the maximum chunk size.
     * @throws NullPointerException If the input string is null.
     */
    public static List<String> splitLargeString(String largeString) {
        return splitLargeString(largeString, DEFAULT_CHUNK_SIZE_BYTES);
    }

    /**
     * Finds the next word boundary (whitespace) within a given chunk size, ensuring that words are not split.
     *
     * @param largeString The input string.
     * @param startIndex The starting index for the current chunk.
     * @param chunkSize  The maximum size of the current chunk.
     * @return The end index for the current chunk, ensuring it doesn't split a word.
     */
    private static int findNextWordBoundary(String largeString, int startIndex, int chunkSize) {
        int endIndex = Math.min(startIndex + chunkSize, largeString.length());

        // If endIndex is at the end of the string, no need to adjust
        if (endIndex == largeString.length()) {
            return endIndex;
        }

        // Search for the next whitespace character backwards from endIndex
        while (endIndex > startIndex && !Character.isWhitespace(largeString.charAt(endIndex - 1))) {
            endIndex--;
        }

        // If no whitespace found within the chunk size, split at the chunk size limit
        if (endIndex == startIndex) {
            endIndex = startIndex + chunkSize;
        }

        return endIndex;
    }
}