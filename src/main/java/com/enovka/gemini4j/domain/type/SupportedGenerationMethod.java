package com.enovka.gemini4j.domain.type;

import java.util.Objects;

/**
 * Enum representing the supported generation methods for Gemini models.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public enum SupportedGenerationMethod {

    /**
     * Generates content from the model.
     */
    GENERATE_CONTENT("generateContent"),

    /**
     * Counts the number of tokens in the input content.
     */
    COUNT_TOKENS("countTokens"),

    /**
     * Generates a message from the model.
     */
    GENERATE_MESSAGE("generateMessage"),

    /**
     * Counts the number of tokens in the input message.
     */
    COUNT_MESSAGE_TOKENS("countMessageTokens"),

    /**
     * Generates text from the model.
     */
    GENERATE_TEXT("generateText"),

    /**
     * Counts the number of tokens in the input text.
     */
    COUNT_TEXT_TOKENS("countTextTokens"),

    /**
     * Creates a tuned text model.
     */
    CREATE_TUNED_TEXT_MODEL("createTunedTextModel"),

    /**
     * Embeds text into a vector representation.
     */
    EMBED_TEXT("embedText"),

    /**
     * Creates a tuned model.
     */
    CREATE_TUNED_MODEL("createTunedModel"),

    /**
     * Creates cached content for the model.
     */
    CREATE_CACHED_CONTENT("createCachedContent"),

    /**
     * Embeds content into a vector representation.
     */
    EMBED_CONTENT("embedContent"),

    /**
     * Generates an answer to a question.
     */
    GENERATE_ANSWER("generateAnswer");

    private final String value;

    SupportedGenerationMethod(String value) {
        this.value = value;
    }

    /**
     * Returns the string representation of the generation method.
     *
     * @return The string representation of the generation method.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the SupportedGenerationMethod enum value for the given string.
     *
     * @param value The string representation of the generation method.
     * @return The SupportedGenerationMethod enum value, or null if the string
     * is not a valid generation method.
     */
    public static SupportedGenerationMethod fromValue(String value) {
        for (SupportedGenerationMethod method : SupportedGenerationMethod.values()) {
            Objects.compare(method.getValue(), value, (o1, o2) -> {
                if (o1 == null) {
                    return -1;
                } else if (o2 == null) {
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            });
            if (method.value.equals(value)) {
                return method;
            }
        }
        return null;
    }
}