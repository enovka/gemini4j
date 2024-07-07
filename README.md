
# Gemini4J Library

## Project Overview

This project aims to develop a Java library, Gemini4J, designed for interacting with the Gemini language model API. Gemini4J leverages the SOLID principles, Clean Code practices, and modern Java 8 features to provide a robust and user-friendly experience.

**Disclaimer:** This project is currently in pre-development. The documentation outlines the proposed architecture and planned functionalities, but the library code has not been implemented yet.

## Architecture Analysis

### Package Structure

```markdown
com.enovka.gemini4j
├── domain
│   ├── request
│   │   ├── EmbedContentRequest.java
│   │   ├── EmbedTextRequest.java
│   │   ├── GenerateAnswerRequest.java
│   │   ├── GenerateContentRequest.java
│   │   └── GenerateTextRequest.java
│   └── response
│       ├── BatchEmbedContentsResponse.java
│       ├── BatchEmbedTextResponse.java
│       ├── CountMessageTokensResponse.java
│       ├── CountTextTokensResponse.java
│       ├── CountTokensResponse.java
│       ├── EmbedContentResponse.java
│       ├── EmbedTextResponse.java
│       ├── FunctionResponse.java
│       ├── GenerateAnswerResponse.java
│       ├── GenerateContentResponse.java
│       ├── GenerateTextResponse.java
│       ├── ListCachedContentsResponse.java
│       ├── ListPermissionsResponse.java
│       └── ListModel.java
├── client
│   ├── GeminiClient.java
│   ├── GeminiClientFactory.java
│   ├── GeminiClientBuilder.java
│   └── exception
│       ├── GeminiApiException.java
│       └── GeminiErrorResponse.java
├── utils
│   └── JsonUtils.java
└── types
    ├── AnswerStyleEnum.java
    ├── BlockedReasonEnum.java
    ├── BlockReasonEnum.java
    ├── FinishReasonEnum.java
    ├── HarmBlockThresholdEnum.java
    ├── HarmCategoryEnum.java
    ├── HarmProbabilityEnum.java
    ├── ModeEnum.java
    ├── OperatorEnum.java
    ├── PermissionStateEnum.java
    ├── StateEnum.java
    └── TaskTypeEnum.java
```

### Package Descriptions

* **domain:**  Contains the Data Transfer Object (DTO) classes that represent the Gemini API requests and responses.
* **client:**  
    * **GeminiClient:** The main library class responsible for handling HTTP requests to the Gemini API. It maps requests and responses to DTO classes, manages JSON serialization/deserialization, handles API exceptions, and provides methods for both synchronous and asynchronous requests.
    * **GeminiClientFactory:**  A factory class for creating GeminiClient instances.
    * **GeminiClientBuilder:**  A builder class to customize the creation of GeminiClient instances.
    * **exception:** 
        * **GeminiApiException:** The base class for exceptions raised by the Gemini4J library.
        * **GeminiErrorResponse:** Represents the error data returned by the Gemini API.
* **utils:**
    * **JsonUtils:**  A utility class for handling JSON serialization and deserialization of objects.
* **types:**  Contains enums (Enumerations) to represent various data types defined by the Gemini API.

### Class Descriptions

**1. `GeminiClient.java`**

* **Responsibility:**  Handles HTTP requests to the Gemini API, mapping requests and responses to DTO classes, managing JSON serialization/deserialization, and handling exceptions. Provides methods for both synchronous and asynchronous requests.
* **Attributes:**
    * **apiKey (String):**  The API key used for authentication in requests.
    * **httpClient (HttpClient):** The HTTP client used for making requests (using Java's native HTTP client).
    * **baseUrl (String):** The base URL of the Gemini API.
* **Methods:**

    * **`batchEmbedContents(String model, List<EmbedContentRequest> requests)`:**  Sends a batch request to generate embeddings from multiple content items. Returns a `BatchEmbedContentsResponse`.
    * **`countTokens(String model, List<Content> contents, GenerateContentRequest generateContentRequest)`:** Counts the total number of tokens in the request. Returns a `CountTokensResponse`.
    * **`embedContent(String model, EmbedContentRequest request)`:** Generates an embedding from a single content item. Returns an `EmbedContentResponse`.
    * **`generateAnswer(String model, GenerateAnswerRequest request)`:** Generates a grounded answer based on a content item. Returns a `GenerateAnswerResponse`.
    * **`generateContent(String model, GenerateContentRequest request)`:**  Generates a response based on a content item. Returns a `GenerateContentResponse`.
    * **`getModel(String model)`:**  Retrieves information about a specific model. Returns a `Model`.
    * **`listModels(int pageSize, String pageToken)`:** Lists the available models on the API. Returns a `ListModel`.
    * **`batchEmbedContentsAsync(String model, List<EmbedContentRequest> requests)`:** Asynchronous version of the `batchEmbedContents` method. Returns a `CompletableFuture<BatchEmbedContentsResponse>`.
    * **`countTokensAsync(String model, List<Content> contents, GenerateContentRequest generateContentRequest)`:** Asynchronous version of the `countTokens` method. Returns a `CompletableFuture<CountTokensResponse>`.
    * **`embedContentAsync(String model, EmbedContentRequest request)`:** Asynchronous version of the `embedContent` method. Returns a `CompletableFuture<EmbedContentResponse>`.
    * **`generateAnswerAsync(String model, GenerateAnswerRequest request)`:** Asynchronous version of the `generateAnswer` method. Returns a `CompletableFuture<GenerateAnswerResponse>`.
    * **`generateContentAsync(String model, GenerateContentRequest request)`:** Asynchronous version of the `generateContent` method. Returns a `CompletableFuture<GenerateContentResponse>`.
    * **`getModelAsync(String model)`:** Asynchronous version of the `getModel` method. Returns a `CompletableFuture<Model>`.
    * **`listModelsAsync(int pageSize, String pageToken)`:** Asynchronous version of the `listModels` method. Returns a `CompletableFuture<ListModel>`.

**2. `GeminiClientFactory.java`**

* **Responsibility:** Creates instances of the GeminiClient.
* **Methods:**
    * **`createClient(String apiKey)`:** Creates a new GeminiClient instance using the provided API key.

**3. `GeminiClientBuilder.java`**

* **Responsibility:** Allows for customizing the creation of GeminiClient instances.
* **Methods:**
    * **`withApiKey(String apiKey)`:**  Sets the API key for authentication.
    * **`withHttpClient(HttpClient httpClient)`:** Sets a custom HTTP client (optional).
    * **`withBaseUrl(String baseUrl)`:** Sets the base URL of the Gemini API.
    * **`build()`:** Constructs the GeminiClient instance with the specified configurations.

**4. `GeminiApiException.java`**

* **Responsibility:** The base class for exceptions raised by the Gemini4J library.
* **Attributes:**
    * **errorCode (Integer):** The error code from the Gemini API.
    * **errorMessage (String):** The error message from the Gemini API.
* **Methods:**
    * **`getErrorCode()`:** Returns the error code from the exception.
    * **`getErrorMessage()`:** Returns the error message from the exception.

**5. `GeminiErrorResponse.java`**

* **Responsibility:** Represents the error data returned by the Gemini API.
* **Attributes:**
    * **error (GeminiApiException):** The exception object containing the error information.
* **Methods:**
    * **`getError()`:**  Returns the exception object.

**6. `JsonUtils.java`**

* **Responsibility:**  Handles JSON serialization and deserialization of objects.
* **Methods:**
    * **`serialize(Object object)`:** Serializes a Java object into a JSON string.
    * **`deserialize(String json, Class<?> clazz)`:** Deserializes a JSON string into a Java object.

### Design Principles

The Gemini4J library adheres to SOLID principles and Clean Code practices to ensure code quality, maintainability, and reusability:

* **Single Responsibility Principle (SRP):** Each class is responsible for a single, well-defined task.
* **Open/Closed Principle (OCP):** New features can be added without modifying existing code.
* **Liskov Substitution Principle (LSP):**  Subclasses can be used interchangeably with their base classes without compromising functionality.
* **Interface Segregation Principle (ISP):** Interfaces are tailored to specific clients and avoid unnecessary dependencies.
* **Dependency Inversion Principle (DIP):**  High-level modules should not depend on low-level modules. Both should depend on abstractions.
* **Clean Code Practices:**  The code is well-organized, readable, and follows conventions for naming, formatting, and commenting.

### Modern Java 8 Features

The library utilizes modern Java 8 features to enhance code readability and efficiency:

* **Lambdas and Anonymous Functions:**  Simplify code by allowing concise and expressive function implementations.
* **Optional:**  Provides a safe and explicit way to handle potential null values.
* **Objects:**  Provides utility methods for comparing objects, checking for nulls, and generating hash values.

### Thread Safety

The Gemini4J library is designed to be thread-safe, ensuring that multiple instances can make requests concurrently without compromising data integrity:

* **Immutable State:**  The state of the client is encapsulated, and it is not possible to directly modify it.
* **Immutable Classes:** DTO classes are immutable, preventing concurrent modification of internal state.
* **Synchronized Methods:** Methods that access shared resources are synchronized to guarantee mutual exclusion and prevent race conditions.

### API Key Management

The API key is managed through the `GeminiClientBuilder` to ensure it is a mandatory parameter during the creation of the client. This approach ensures security and ease of use for developers.

### HTTP Client

The Gemini4J library utilizes Java's native HTTP client (`java.net.http.HttpClient`) for performing requests. This choice offers flexibility and control over the request process.

## Additional Considerations

* **Testing:**  The library will have comprehensive unit test coverage to ensure code quality and functionality.
* **Documentation:**  The library will be thoroughly documented using Javadoc, including detailed instructions, examples, and notes on functionality.
* **Performance Optimization:**  The project will incorporate performance optimization techniques, such as caching and code profiling, to ensure an efficient and responsive user experience.

## Getting Started

This project is currently in pre-development. However, you can contribute by:

* **Forking the repository**
* **Opening issues for bug reports, feature requests, or documentation improvements**
* **Submitting pull requests for proposed changes**

## Contributing

Contributions are welcome! To contribute, please follow these guidelines:

1. **Fork the repository**
2. **Create a new branch** for your feature or fix
3. **Make your changes** and test them thoroughly
4. **Submit a pull request** for review

## License

This project is licensed under Mozilla Public License 2.0 (MPL-2.0) - see the [LICENSE](LICENSE) file for details.
