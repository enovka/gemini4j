
## Gemini4J Library

This document outlines the initial architecture of the Gemini4J library, a Java library designed to interact with the Gemini language model API.  The library is currently in Beta and will evolve alongside the Gemini API, which is also in Beta. This analysis aims to provide a clear understanding of the initial structure, design choices, and future development plans.

**Disclaimer:** This is an initial architecture analysis for the Gemini4J library, which is currently in development and does not have a stable release yet. As the Gemini API evolves, Gemini4J will adapt to provide compatibility and support for new features.

**Project Overview:**

Gemini4J is a powerful and easy-to-use Java library that simplifies interactions with the cutting-edge Gemini language model API. The library leverages SOLID principles, Clean Code practices, and modern Java 8 features to provide a robust and user-friendly experience.

**Design Principles:**

The library follows these key design principles:

* **SOLID:** The library adheres to the SOLID principles:
  * **Single Responsibility Principle (SRP):** Each class is responsible for a single, well-defined task.
  * **Open/Closed Principle (OCP):** New features can be added without modifying existing code.
  * **Liskov Substitution Principle (LSP):** Subclasses can be used interchangeably with their base classes without compromising functionality.
  * **Interface Segregation Principle (ISP):** Interfaces are tailored to specific clients and avoid unnecessary dependencies.
  * **Dependency Inversion Principle (DIP):** High-level modules should not depend on low-level modules. Both should depend on abstractions.

* **Clean Code:** The code is well-organized, readable, and follows conventions for naming, formatting, and commenting.

* **Code Reusability:** The library emphasizes code reusability through design patterns and generics, minimizing duplication and promoting maintainability.

* **Thread Safety:** The Gemini4J library is designed to be thread-safe, ensuring that multiple instances can make requests concurrently without compromising data integrity.

* **Object-Oriented Design:**  The library utilizes advanced object-oriented principles like encapsulation, inheritance, polymorphism, and design patterns.

**Package Structure:**

The proposed package structure reflects the modular organization of the project and facilitates navigation and understanding of the code:

``` markdown
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
│       ├── ListModel.java
│       └── Response.java
├── client
│   ├── GeminiClient.java
│   ├── GeminiClientFactory.java
│   ├── GeminiClientBuilder.java
│   └── exception
│       ├── GeminiApiException.java
│       ├── GeminiInvalidArgumentException.java
│       ├── GeminiFailedPreconditionException.java
│       ├── GeminiPermissionDeniedException.java
│       ├── GeminiNotFoundException.java
│       ├── GeminiResourceExhaustedException.java
│       ├── GeminiInternalException.java
│       └── GeminiUnavailableException.java
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

## Class Descriptions

### **Domain Classes**

**`domain.request`:**  Contains classes representing requests to the Gemini API.

* **`EmbedContentRequest.java`:** Represents a request to embed content and generate an embedding from the model.
* **`EmbedTextRequest.java`:** Represents a request to generate a text embedding from the model.
* **`GenerateAnswerRequest.java`:** Represents a request to generate a grounded answer from the model given an input.
* **`GenerateContentRequest.java`:** Represents a request to generate a completion from the model given an input.
* **`GenerateTextRequest.java`:**  Represents a request to generate text from the model given a prompt.

**`domain.response`:**  Contains classes representing responses from the Gemini API. 

* **`Response.java`:**  The base class for all Gemini API responses. This class can hold common attributes across all responses, like timestamps, usage metadata, etc.
* **`BatchEmbedContentsResponse.java`:**  Represents the response to a batch embedding request.
* **`BatchEmbedTextResponse.java`:** Represents the response to a batch text embedding request.
* **`CountMessageTokensResponse.java`:** Represents the response to a request to count message tokens.
* **`CountTextTokensResponse.java`:**  Represents the response to a request to count text tokens.
* **`CountTokensResponse.java`:** Represents the response to a request to count tokens in the overall input.
* **`EmbedContentResponse.java`:**  Represents the response to a request to embed a single content item.
* **`EmbedTextResponse.java`:**  Represents the response to a request to embed text.
* **`FunctionResponse.java`:** Represents the response from a function call.
* **`GenerateAnswerResponse.java`:** Represents the response to a grounded answer request.
* **`GenerateContentResponse.java`:**  Represents the response to a completion request.
* **`GenerateTextResponse.java`:** Represents the response to a text generation request. 
* **`ListCachedContentsResponse.java`:** Represents the response to a request to list cached contents.
* **`ListPermissionsResponse.java`:**  Represents the response to a request to list permissions.
* **`ListModel.java`:**  Represents the response to a request to list models.

### **Client Classes**

**`client`:**  Contains classes related to the Gemini client.

* **`GeminiClient.java`:** The main class responsible for interacting with the Gemini API. It handles HTTP requests, manages JSON serialization/deserialization, and maps requests and responses to DTO classes.
* **`GeminiClientFactory.java`:**  Provides a way to create instances of the GeminiClient.
* **`GeminiClientBuilder.java`:**  Allows customization of GeminiClient instances during creation.

### **Exception Classes**

**`client.exception`:** Contains classes for handling exceptions raised by the Gemini API.

* **`GeminiApiException.java`:**  The base class for all Gemini API exceptions. Stores the HTTP error code and error message.
* **`GeminiInvalidArgumentException.java`:**  Thrown when the request contains an invalid argument (HTTP 400 - INVALID_ARGUMENT).
* **`GeminiFailedPreconditionException.java`:**  Thrown when a precondition is not met (HTTP 400 - FAILED_PRECONDITION).
* **`GeminiPermissionDeniedException.java`:** Thrown when the API key doesn't have sufficient permissions (HTTP 403 - PERMISSION_DENIED).
* **`GeminiNotFoundException.java`:** Thrown when the requested resource is not found (HTTP 404 - NOT_FOUND).
* **`GeminiResourceExhaustedException.java`:**  Thrown when the rate limit is exceeded (HTTP 429 - RESOURCE_EXHAUSTED).
* **`GeminiInternalException.java`:**  Thrown when an unexpected error occurs on the server side (HTTP 500 - INTERNAL).
* **`GeminiUnavailableException.java`:** Thrown when the service is temporarily overloaded or unavailable (HTTP 503 - UNAVAILABLE).

### **Utility Classes**

**`utils`:**  Contains utility classes.

* **`JsonUtils.java`:** Provides methods for JSON serialization and deserialization.

### **Enum Classes**

**`types`:**  Contains enums for representing various data types defined by the Gemini API. 

* **`AnswerStyleEnum.java`:** Represents the styles for grounded answers.
* **`BlockedReasonEnum.java`:** Represents reasons why content may have been blocked.
* **`BlockReasonEnum.java`:** Represents reasons why input was blocked.
* **`FinishReasonEnum.java`:** Represents reasons why the model stopped generating tokens.
* **`HarmBlockThresholdEnum.java`:** Represents thresholds for blocking content based on harm probability.
* **`HarmCategoryEnum.java`:** Represents categories of harm in content.
* **`HarmProbabilityEnum.java`:** Represents the probability levels for harm in content. 
* **`ModeEnum.java`:** Represents different modes for function calling.
* **`OperatorEnum.java`:** Represents operators that can be applied in filtering conditions.
* **`PermissionStateEnum.java`:** Represents states for permissions.
* **`StateEnum.java`:** Represents states for Chunks.
* **`TaskTypeEnum.java`:** Represents the types of tasks for which embeddings are used. 

**Responsibility:** Handles HTTP requests to the Gemini API, mapping requests and responses to DTO classes, managing JSON serialization/deserialization, and handling exceptions. Provides methods for both synchronous and asynchronous requests.

**Attributes:**

* **apiKey (String):** The API key used for authentication in requests.
* **httpClient (HttpClient):** The HTTP client used for making requests (using Java's native HTTP client).
* **baseUrl (String):** The base URL of the Gemini API.

**Methods:**

* **batchEmbedContents(String model, List<EmbedContentRequest> requests):** Sends a batch request to generate embeddings from multiple content items. Returns a BatchEmbedContentsResponse.
* **countTokens(String model, List<Content> contents, GenerateContentRequest generateContentRequest):** Counts the total number of tokens in the request. Returns a CountTokensResponse.
* **embedContent(String model, EmbedContentRequest request):** Generates an embedding from a single content item. Returns an EmbedContentResponse.
* **generateAnswer(String model, GenerateAnswerRequest request):** Generates a grounded answer based on a content item. Returns a GenerateAnswerResponse.
* **generateContent(String model, GenerateContentRequest request):** Generates a response based on a content item. Returns a GenerateContentResponse.
* **getModel(String model):** Retrieves information about a specific model. Returns a Model.
* **listModels(int pageSize, String pageToken):** Lists the available models on the API. Returns a ListModel.
* **batchEmbedContentsAsync(String model, List<EmbedContentRequest> requests):** Asynchronous version of the batchEmbedContents method. Returns a CompletableFuture<BatchEmbedContentsResponse>.
* **countTokensAsync(String model, List<Content> contents, GenerateContentRequest generateContentRequest):** Asynchronous version of the countTokens method. Returns a CompletableFuture<CountTokensResponse>.
* **embedContentAsync(String model, EmbedContentRequest request):** Asynchronous version of the embedContent method. Returns a CompletableFuture<EmbedContentResponse>.
* **generateAnswerAsync(String model, GenerateAnswerRequest request):** Asynchronous version of the generateAnswer method. Returns a CompletableFuture<GenerateAnswerResponse>.
* **generateContentAsync(String model, GenerateContentRequest request):** Asynchronous version of the generateContent method. Returns a CompletableFuture<GenerateContentResponse>.
* **getModelAsync(String model):** Asynchronous version of the getModel method. Returns a CompletableFuture<Model>.
* **listModelsAsync(int pageSize, String pageToken):** Asynchronous version of the listModels method. Returns a CompletableFuture<ListModel>.

**2. GeminiClientFactory.java**

**Responsibility:** Creates instances of the GeminiClient.

**Methods:**

* **createClient(String apiKey):** Creates a new GeminiClient instance using the provided API key.

**3. GeminiClientBuilder.java**

**Responsibility:** Allows for customizing the creation of GeminiClient instances.

**Methods:**

* **withApiKey(String apiKey):** Sets the API key for authentication.
* **withHttpClient(HttpClient httpClient):** Sets a custom HTTP client (optional).
* **withBaseUrl(String baseUrl):** Sets the base URL of the Gemini API.
* **build():** Constructs the GeminiClient instance with the specified configurations.

**4. GeminiApiException.java**

**Responsibility:** The base class for exceptions raised by the Gemini4J library.

**Attributes:**

* **errorCode (Integer):** The error code from the Gemini API.
* **errorMessage (String):** The error message from the Gemini API.

**Methods:**

* **getErrorCode():** Returns the error code from the exception.
* **getErrorMessage():** Returns the error message from the exception.

**5. GeminiErrorResponse.java**

**Responsibility:** Represents the error data returned by the Gemini API.

**Attributes:**

* **error (GeminiApiException):** The exception object containing the error information.

**Methods:**

* **getError():** Returns the exception object.

**6. JsonUtils.java**

**Responsibility:** Handles JSON serialization and deserialization of objects.

**Methods:**

* **serialize(Object object):** Serializes a Java object into a JSON string.
* **deserialize(String json, Class<?> clazz):** Deserializes a JSON string into a Java object.

**Design Patterns:**

The library utilizes various design patterns to improve organization, modularity, and code reusability, such as:

* **Factory:** The GeminiClientFactory class creates instances of the GeminiClient.
* **Builder:** The GeminiClientBuilder class allows for customization of GeminiClient instance creation.
* **Strategy:** Can be implemented for different authentication modes (e.g., API key, OAuth).
* **Template Method:** Can be utilized for the implementation of synchronous and asynchronous methods, sharing common code.

**Generics:**

Generics are used to make the code more flexible and reusable, allowing the library to work with different types of objects.

**Code Reusability:**

The library is designed to promote code reuse through the use of:

* **Abstract Classes:** The GeminiApiException class is an abstract class that defines the base for specific exceptions.
* **Interfaces:** Interfaces are used to define contracts between classes.
* **Utility Classes:** The JsonUtils class and other utility classes encapsulate common functionality for reuse.

**Thread Safety:**

The library is thread-safe through the use of:

* **Immutable Classes:** DTO classes and other relevant classes are immutable, preventing concurrent modification of internal state.
* **Synchronized Methods:** Methods that access shared resources are synchronized to guarantee mutual exclusion and prevent race conditions.

**API Key Management:**

The API key is managed through the GeminiClientBuilder to ensure it is a mandatory parameter during client creation. This approach ensures security and ease of use for developers.

**HTTP Client:**

The Gemini4J library utilizes Java's native HTTP client (java.net.http.HttpClient) for performing requests. This choice offers flexibility and control over the request process.

**Future Development:**

The Gemini4J library will evolve as the Gemini API matures. Future development plans include:

* **Support for New Gemini API Features:** As the Gemini API introduces new features, the library will adapt to support them.
* **Improved Error Handling:** The library will provide more granular and informative error handling, making it easier to diagnose and resolve issues.
* **Additional Models:** The library will support a wider range of Gemini models.
* **Advanced Features:**  The library may eventually include advanced features like custom model tuning and caching.

Ok, aqui está uma seção em inglês que você pode adicionar ao final do seu arquivo `README.md` para explicar o arquivo `GEMINI-API.md`:

## Gemini API Documentation

This repository includes a document titled `GEMINI-API.md` that provides a consolidated view of the Gemini API REST documentation. This document is a direct compilation of the official Gemini API documentation from Google, found at [https://ai.google.dev/api/rest/v1beta/models](https://ai.google.dev/api/rest/v1beta/models).

No modifications or alterations have been made to the original documentation. The `GEMINI-API.md` file simply combines the various sections of the official documentation into a single, easy-to-navigate file. This makes it convenient for developers to access all the essential API information in one place.

We encourage developers to refer to the `GEMINI-API.md` file for a comprehensive understanding of the Gemini API's structure, methods, and data formats.

This file is provided for informational purposes and may not always reflect the latest updates to the official Gemini API documentation. It's recommended to consult the official Google Cloud documentation for the most up-to-date information: [https://ai.google.dev/api/rest/v1beta/models](https://ai.google.dev/api/rest/v1beta/models).



## Conclusion

This architecture analysis provides a foundation for the development of a modern, robust, and easy-to-use Java library for interacting with the Gemini language model API. By adhering to SOLID principles, Clean Code practices, and modern Java 8 features, the Gemini4J library aims to provide a high-quality user experience and promote maintainability and code reuse.

**Remember:** The library is in Beta, and new features and changes will be released as the Gemini API evolves. 

