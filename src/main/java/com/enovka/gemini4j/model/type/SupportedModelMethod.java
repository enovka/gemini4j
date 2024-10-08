package com.enovka.gemini4j.model.type;

import lombok.Getter;

import java.util.Arrays;

/**
 * Represents the supported generation methods for Gemini models, providing a
 * standardized way to identify the different capabilities of each model. These
 * methods correspond to specific actions or operations that a Gemini model can
 * perform, such as generating text, embedding content, or counting tokens.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
@Getter
public enum SupportedModelMethod {

    /**
     * **Generate Content:** Generates a response from the model based on the
     * provided input and context. This is a core functionality of most Gemini
     * models.
     */
    GENERATE_CONTENT("generateContent"),

    /**
     * **Count Tokens:** Counts the number of tokens in a given text or content,
     * providing insights into the length and complexity of the input.
     */
    COUNT_TOKENS("countTokens"),

    /**
     * **Generate Message:** Generates a message in a conversational context,
     * suitable for chatbot applications and dialogue systems.
     */
    GENERATE_MESSAGE("generateMessage"),

    /**
     * **Count Message Tokens:** Counts the number of tokens in a given message,
     * specifically designed for analyzing conversational text.
     */
    COUNT_MESSAGE_TOKENS("countMessageTokens"),

    /**
     * **Generate Text:** Generates text based on a given prompt or context,
     * typically used for creative writing, summarization, or translation
     * tasks.
     */
    GENERATE_TEXT("generateText"),

    /**
     * **Count Text Tokens:** Counts the number of tokens in a given text,
     * commonly used for text processing and analysis.
     */
    COUNT_TEXT_TOKENS("countTextTokens"),

    /**
     * **Create Tuned Text Model:** Creates a custom-tuned text model based on a
     * specific dataset and task, allowing for more specialized and accurate
     * text generation.
     */
    CREATE_TUNED_TEXT_MODEL("createTunedTextModel"),

    /**
     * **Embed Text:** Embeds text into a vector representation, capturing its
     * semantic meaning and enabling similarity comparisons and other
     * vector-based operations.
     */
    EMBED_TEXT("embedText"),

    /**
     * **Create Tuned Model:** Creates a custom-tuned model for a specific task,
     * going beyond text generation to encompass various model capabilities.
     */
    CREATE_TUNED_MODEL("createTunedModel"),

    /**
     * **Create Cached Content:** Creates and stores cached content for a model,
     * optimizing performance and reducing latency for frequently used inputs or
     * responses.
     */
    CREATE_CACHED_CONTENT("createCachedContent"),

    /**
     * **Embed Content:**  Embeds content, which may include text, images, or
     * other modalities, into a vector representation, facilitating multimodal
     * understanding and processing.
     */
    EMBED_CONTENT("embedContent"),

    /**
     * **Generate Answer:** Generates a grounded answer to a question,
     * leveraging external knowledge sources or provided context to provide more
     * informative and accurate responses.
     */
    GENERATE_ANSWER("generateAnswer");

    /**
     * -- GETTER -- Returns the string representation of the generation method.
     */
    private final String value;

    SupportedModelMethod(String value) {
        this.value = value;
    }

    /**
     * Returns the `SupportedModelMethod` enum value corresponding to the
     * given string representation.
     *
     * @param value The string representation of the generation method.
     * @return The corresponding `SupportedModelMethod` enum value, or
     * `null` if no matching value is found.
     */
    public static SupportedModelMethod fromValue(String value) {
        return Arrays.stream(SupportedModelMethod.values())
                .filter(method -> method.value.equals(value))
                .findFirst()
                .orElse(null);
    }

}