package com.enovka.gemini4j.infrastructure.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for splitting large strings into smaller chunks while preserving whole words.
 *
 * @since 0.1.4
 */
public class LargeStringSplitter {

    private static final int MAX_CHUNK_SIZE_BYTES = 8000;

    /**
     * Splits a large string into a list of smaller strings, each with a size less than
     * {@link #MAX_CHUNK_SIZE_BYTES} bytes, ensuring that words are not split.
     *
     * @param largeString The input string to be split.
     * @return A list of strings, each smaller than the maximum chunk size.
     * @throws NullPointerException If the input string is null.
     */
    public static List<String> splitLargeString(String largeString) {
        if (largeString == null) {
            throw new NullPointerException("Input string cannot be null.");
        }

        List<String> chunks = new ArrayList<>();
        int startIndex = 0;

        while (startIndex < largeString.length()) {
            int endIndex = findEndIndex(largeString, startIndex);
            chunks.add(largeString.substring(startIndex, endIndex));
            startIndex = endIndex;
        }

        return chunks;
    }

    /**
     * Finds the end index for the next chunk, ensuring it's within the size limit and doesn't split words.
     *
     * @param largeString The input string.
     * @param startIndex  The starting index for the current chunk.
     * @return The end index for the current chunk.
     */
    private static int findEndIndex(String largeString, int startIndex) {
        int endIndex = Math.min(startIndex + MAX_CHUNK_SIZE_BYTES, largeString.length());

        // Adjust endIndex to avoid splitting words only if it's not already at the end of the string
        if (endIndex < largeString.length()) {
            while (endIndex > startIndex && !Character.isWhitespace(largeString.charAt(endIndex - 1))) {
                endIndex--;
            }

            // If no whitespace found within the chunk size, split at the chunk size limit
            if (endIndex == startIndex) {
                endIndex = startIndex + MAX_CHUNK_SIZE_BYTES;
            }
        }

        return endIndex;
    }
}