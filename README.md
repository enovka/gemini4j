## Gemini4J: A Java Library for the Google Gemini API

Gemini4J is a powerful and intuitive Java library designed to simplify the integration of the Google Gemini API into your Java applications. This library provides a user-friendly interface for accessing Gemini's advanced AI capabilities, including text generation, language translation, creative content creation, and insightful question answering.

**Key Features of Google Gemini:**

* **Advanced Text Generation:** Gemini excels in generating natural and creative text across diverse styles and formats. Its adaptability to various writing styles, tones, and complexities makes it an ideal tool for tasks ranging from crafting compelling stories and poems to generating marketing copy and technical documentation.
* **Natural Language Understanding:**  Gemini's deep understanding of human language enables it to interpret complex questions, translate languages accurately, and generate contextually relevant content. This makes it well-suited for applications such as chatbots, question answering systems, and content summarization.
* **Creative and Informative Content:** Gemini empowers you to create a wide array of content, including stories, poems, articles, emails, and more. It can also provide informative summaries and answer questions based on its extensive knowledge base, making it a valuable asset for research, education, and entertainment.
* **Flexibility and Customization:** The Gemini API offers customization options to fine-tune the model's behavior. Parameters like temperature and top-k sampling allow developers to control the level of creativity and randomness in the responses, tailoring the output to specific application needs.

**Gemini4J Simplifies Integration with the Gemini API by Providing:**

* **Abstraction of API Complexity:** Gemini4J handles the underlying complexities of HTTP communication and JSON serialization, allowing you to focus on your application logic without getting bogged down in low-level details.
* **Intuitive and Easy-to-Use Interface:** Gemini4J offers a high-level interface for interacting with the Gemini API resources, making integration quick and straightforward. This simplifies the process of leveraging Gemini's capabilities, reducing development time and effort.
* **Support for Key Gemini Features:** Gemini4J provides comprehensive support for key Gemini features, including:
    * **Text Generation:**  Generate creative and informative text using various Gemini models.
    * **Embedding Generation:** Create embeddings for text and content, enabling semantic search and similarity comparisons.
    * **Model Management:** List available models and retrieve detailed information about specific models.
    * **Cached Content Management:** Create, list, retrieve, update, and delete cached content for optimized performance.
    * **Token Counting:**  Count the number of tokens in text and other content using Gemini's tokenizer.

**Important Note:**

Gemini4J is actively under development, continuously evolving to incorporate new features and improvements as they become available in the Google Gemini API. While I strive to maintain compatibility, significant changes in the Gemini API might necessitate breaking changes in Gemini4J, especially before reaching version 1.0.0. To mitigate potential issues during future upgrades, it is highly recommended to implement an abstraction layer on top of Gemini4J within your application. This abstraction layer will provide a buffer against breaking changes, making future updates smoother.

This approach stems from the library's origins. It was initially developed in conjunction with a separate software project, during a time when the Gemini API documentation was limited. The development process involved a degree of trial and error, experimenting with different JSON structures to determine the essential attributes for API requests.  As the library matures and the Gemini API documentation becomes more comprehensive, I am progressively decoupling Gemini4J from the original project and refining it into a standalone, robust library. This involves ongoing refactoring to ensure a clean and maintainable codebase.

## Current Limitations

While Gemini4J strives to provide comprehensive access to the Google Gemini API, some functionalities are still under development due to ongoing refinements in the API itself and the active development status of the library.

**Specifically, the following features are not yet fully functional:**

* **Function Calling:** The methods related to function calling, such as `withTool`, `withToolConfig`, and `withFunctionDeclaration` in the `GenerateContentRequestBuilder` and `CachedContentRequestBuilder`, are not yet fully operational. This is due to the evolving nature of the function calling interface in the Gemini API.

* **Updating Cached Content:**  The `updateCachedContent` method in the `CachedContentResource` is currently not functional. This is related to limitations in the current version of the Gemini API, which does not fully support updating existing cached content.

I am actively working on implementing these features as the Gemini API matures and provides more stable interfaces for these functionalities. Future releases of Gemini4J will include support for these features as they become available.


## Installation

Add the Gemini4J dependency to your project's `pom.xml` file:

```xml
<dependency>
  <groupId>com.enovka</groupId>
  <artifactId>gemini4j</artifactId>
  <version>0.1.3</version>
</dependency>
```

## Getting Started with Gemini4J

This guide provides a step-by-step introduction to using Gemini4J, covering essential concepts and demonstrating how to leverage its core functionalities.

### 1. Setting Up Your Gemini Client

The `GeminiClient` is your gateway to the Gemini API. You'll need to create an instance of it, providing your API key, which you can obtain from the Google Cloud Console.

```java
import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;

public class Gemini4jExample {

    public static void main(String[] args) {
        // Replace "YOUR_API_KEY" with your actual Gemini API key
        String apiKey = "YOUR_API_KEY";

        // Create a GeminiClient instance
        GeminiClient geminiClient = GeminiClientBuilder.builder()
                .withApiKey(apiKey)
                .build();

        // Now you can use the geminiClient to access various resources
        // ...
    }
}
```

### 2. Generating Text with Gemini

Gemini4J makes it easy to generate text using Gemini's powerful language models. You can use the `GenerationResource` to send generation requests and get creative text outputs.

**Example: Generating a Short Story**

```java
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.GenerationResource;
import com.enovka.gemini4j.domain.response.GeminiResult;

public class TextGenerationExample {

    public static void main(String[] args) throws ResourceException {
        // Assuming you have a GeminiClient instance (geminiClient) from the previous step

        // Create a GenerationResource instance
        GenerationResource generationResource = ResourceBuilder.builder(geminiClient)
                .buildGenerationResource();

        // Define your prompt
        String prompt = "Write a short story about a talking cat.";

        // Generate text using the generateText method
        GeminiResult result = generationResource.generateTextBuilder(prompt)
                .execute();

        // Print the generated text
        System.out.println(result.getGeneratedText());
    }
}
```

**Explanation:**

1. **Create a `GenerationResource`:** This resource handles text generation requests.
2. **Define Your Prompt:**  Provide the text prompt that will guide Gemini's text generation.
3. **Generate Text:** Use the `generateTextBuilder` method, providing your prompt, and then call `execute` to send the request to the Gemini API.
4. **Retrieve the Generated Text:** The `GeminiResult` object contains the generated text, which you can access using `result.getGeneratedText()`.

### 3. Working with Embeddings

Embeddings are numerical representations of text that capture semantic meaning. Gemini4J provides the `EmbedResource` to generate embeddings for text and content, enabling tasks like semantic search and similarity comparisons.

**Example: Generating Embeddings for Sentences**

```java
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.EmbedResource;
import com.enovka.gemini4j.domain.response.EmbedContentResponse;

public class EmbeddingExample {

    public static void main(String[] args) throws ResourceException {
        // Assuming you have a GeminiClient instance (geminiClient)

        // Create an EmbedResource instance
        EmbedResource embedResource = ResourceBuilder.builder(geminiClient)
                .buildEmbedResource();

        // Define the text for which you want to generate an embedding
        String text = "This is a sentence.";

        // Generate the embedding
        EmbedContentResponse response = embedResource.embedContentBuilder(text)
                .build();
        response = embedResource.execute(response.build());

        // Access the embedding values
        List<Double> embeddingValues = response.getEmbedding().getValues();

        // Print the embedding values
        System.out.println(embeddingValues);
    }
}
```

**Explanation:**

1. **Create an `EmbedResource`:** This resource handles embedding generation requests.
2. **Define Your Text:** Provide the text for which you want to generate an embedding.
3. **Generate the Embedding:** Use the `embedContentBuilder` method, providing your text, and then call `execute` to send the request to the Gemini API.
4. **Access the Embedding Values:** The `EmbedContentResponse` object contains the embedding, which you can access using `response.getEmbedding().getValues()`.

### 4. Managing Cached Content

Cached content allows you to store pre-processed content for later use, potentially reducing latency and costs. Gemini4J provides the `CachedContentResource` to manage cached content.

**Example: Creating and Retrieving Cached Content**

```java
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.CachedContentResource;
import com.enovka.gemini4j.domain.CachedContent;
import com.enovka.gemini4j.domain.Content;
import com.enovka.gemini4j.domain.Part;

public class CachedContentExample {

    public static void main(String[] args) throws ResourceException {
        // Assuming you have a GeminiClient instance (geminiClient)

        // Create a CachedContentResource instance
        CachedContentResource cachedContentResource = ResourceBuilder.builder(geminiClient)
                .buildCachedContentResource();

        // Define the model and content to cache
        String model = "models/gemini-1.5-flash-001";
        String contentToCache = "This is the content to be cached.";

        // Create cached content
        CachedContent cachedContent = cachedContentResource.execute(
                cachedContentResource.createCachedContentBuilder(model)
                        .withContent(Content.builder()
                                .withRole("user")
                                .withParts(Collections.singletonList(Part.builder()
                                        .withText(contentToCache)
                                        .build()))
                                .build())
                        .withTtl("300s") // Time-to-live for the cached content
                        .build()
        );

        // Retrieve cached content by name
        String cacheName = cachedContent.getName();
        CachedContent retrievedCachedContent = cachedContentResource.getCachedContent(cacheName);

        // Print the retrieved cached content
        System.out.println(retrievedCachedContent);
    }
}
```

**Explanation:**

1. **Create a `CachedContentResource`:** This resource handles cached content management.
2. **Define Model and Content:** Specify the Gemini model and the content you want to cache.
3. **Create Cached Content:** Use the `createCachedContentBuilder` method, providing the model and content, and set a time-to-live (TTL) for the cached content. Call `execute` to create the cached content.
4. **Retrieve Cached Content:**  Use the `getCachedContent` method, providing the cache name, to retrieve the cached content.

### 5. Counting Tokens

Tokens are the basic units of text that Gemini processes. Gemini4J provides the `CountTokensResource` to count the number of tokens in text and other content.

**Example: Counting Tokens in a String**

```java
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.exception.ResourceException;
import com.enovka.gemini4j.resource.spec.CountTokensResource;

public class TokenCountingExample {

    public static void main(String[] args) throws ResourceException {
        // Assuming you have a GeminiClient instance (geminiClient)

        // Create a CountTokensResource instance
        CountTokensResource countTokensResource = ResourceBuilder.builder(geminiClient)
                .buildCountTokensResource();

        // Define the text for which you want to count tokens
        String text = "This is a sentence.";

        // Count the tokens
        int tokenCount = countTokensResource.execute(
                countTokensResource.countTokensBuilder(text)
                        .build()
        ).getTotalTokens();

        // Print the token count
        System.out.println("Token count: " + tokenCount);
    }
}
```

**Explanation:**

1. **Create a `CountTokensResource`:** This resource handles token counting requests.
2. **Define Your Text:** Provide the text for which you want to count tokens.
3. **Count the Tokens:** Use the `countTokensBuilder` method, providing your text, and then call `execute` to send the request to the Gemini API.
4. **Retrieve the Token Count:** The response object contains the total token count, which you can access using `getTotalTokens()`.

This detailed documentation provides a comprehensive overview of Gemini4J's core functionalities and demonstrates how to use its key features. As the library continues to evolve, the documentation will be updated to reflect new capabilities and provide more in-depth examples.


## Gemini4J Roadmap: Version 1.0.0

This roadmap outlines the key milestones for the initial release of Gemini4J,
focusing on providing a comprehensive and robust set of features for interacting
with the Google Gemini API.

### Phase 1: Core Functionality and Essential Resources

* ~~Complete `GenerationResource`:**
    * ~~Implement all remaining methods of the `GenerationResource` interface,
      providing full coverage of the content generation capabilities offered by
      the Gemini API.~~
    * ~~**Crucial Feature:** Implement **incremental conversation support**,
      enabling developers to build conversational experiences by maintaining
      context across multiple interactions with the API.~~
* ~~Enhance `ModelResource`:**
    * ~~Add the `getModel()` method to the `ModelResource` interface, allowing
      developers to retrieve detailed information about specific Gemini
      models.~~
* ~~Implement `EmbedResource`:**
    * ~~Create a new `EmbedResource` interface and its corresponding
      implementation to expose the embedding functionalities of the Gemini API.
      This will allow developers to generate embeddings for text and content,
      enabling various downstream tasks like semantic search and similarity
      comparisons.~~

### Phase 2: Advanced Features and Usability Enhancements

* **Maven Central Release:**
    * Publish the Gemini4J artifact to Maven Central, making it easily
      accessible to developers through a standard dependency management system.
* **Caching Mechanism:**
    * Implement a caching mechanism to store and reuse API responses, reducing
      latency and improving performance for repeated requests. This feature will
      be configurable, allowing developers to control caching behavior based on
      their specific needs.
* **JSON Schema Validation:**
    * Integrate JSON schema validation for requests and responses, ensuring data
      integrity and providing early detection of potential errors. This will
      enhance the reliability and robustness of the library.

### Phase 3: Extended Capabilities and Real-World Examples

* **Script Execution Resource:**
    * Introduce a new `ScriptExecutionResource` interface and implementation to
      enable the execution of custom scripts within the Gemini environment. This
      will empower developers to extend the capabilities of the API and perform
      more complex tasks.
* **Comprehensive Documentation and Examples:**
    * Expand the documentation to cover all implemented features and provide
      detailed usage examples for real-world scenarios. This will help
      developers quickly understand and effectively utilize the library's
      capabilities.

### Future Development

Beyond version 1.0, the roadmap for Gemini4J will focus on:

* **Implementing additional API resources:** Continuously expanding the
  library's coverage to encompass the full range of functionalities offered by
  the Google Gemini API.
* **Exploring advanced use cases:** Providing examples and guidance for
  leveraging Gemini4J in areas like chatbot development, code generation,
  creative writing assistance, and more.
* **Community engagement:** Fostering a vibrant community around Gemini4J by
  encouraging contributions, addressing feedback, and promoting collaboration.

This roadmap reflects the initial vision for Gemini4J, aiming to provide a
powerful and versatile tool for developers to harness the capabilities of the
Google Gemini API. The development process will be iterative and responsive to
community feedback, ensuring that the library remains relevant and valuable for
a wide range of applications.

## Library Architecture

Gemini4J is designed with a layered architecture to promote modularity, code
reusability, and ease of maintenance. This approach ensures a clear separation
of concerns, making the codebase easier to understand, extend, and maintain. The
library is structured into the following layers:

### HTTP Layer

The HTTP layer abstracts the underlying HTTP client used for communication with
the Google Gemini API. This design choice allows developers to seamlessly
integrate Gemini4J with their existing HTTP client infrastructure, promoting
consistency and minimizing potential dependency conflicts. By default, Gemini4J
utilizes the Apache HttpClient, but it provides the flexibility to use any HTTP
client through a custom adapter.

**Key responsibilities of the HTTP layer:**

* **Handling HTTP requests and responses:** This includes sending GET and POST
  requests to the Gemini API endpoints and processing the corresponding
  responses.
* **Managing connection and response timeouts:** Ensuring efficient and reliable
  communication with the API.
* **Providing a consistent interface for different HTTP clients:** Enabling
  developers to use their preferred HTTP client without modifying the core
  library code.

### JSON Layer

The JSON layer handles the serialization and deserialization of data exchanged
with the Google Gemini API. It provides a standardized way to convert Java
objects into their JSON representation and vice versa, ensuring smooth data flow
between the application and the API. While Gemini4J defaults to the Jackson
library for JSON processing, it allows for easy customization to accommodate
different JSON libraries based on project requirements.

**Key responsibilities of the JSON layer:**

* **Serializing Java objects into JSON strings:** Converting data structures
  used within the application into a format suitable for transmission to the
  Gemini API.
* **Deserializing JSON responses into Java objects:** Transforming data received
  from the API into Java objects for convenient processing within the
  application.
* **Providing a consistent interface for different JSON libraries:** Allowing
  developers to choose their preferred JSON library without affecting the core
  library functionality.

### Resource Layer

The Resource layer provides a high-level, intuitive interface for interacting
with the various resources offered by the Google Gemini API. It abstracts the
complexities of HTTP communication and JSON serialization, allowing developers
to focus on the specific tasks they want to accomplish with the API, such as
generating text, translating languages, or managing models. Each API resource is
represented by a dedicated interface and implementation within this layer.

**Key responsibilities of the Resource layer:**

* **Defining clear contracts for interacting with API resources:** Each
  interface outlines the available methods and parameters for a specific
  resource.
* **Encapsulating the logic for making API calls:** The implementations handle
  the details of constructing requests, sending them to the API, and processing
  the responses.
* **Providing a user-friendly abstraction over the lower-level layers:**
  Developers can work with the API resources without needing to deal with the
  intricacies of HTTP and JSON handling.

### Client

The `GeminiClient` acts as the central entry point for interacting with the
Google Gemini API. It encapsulates common configuration settings, such as the
API key and the chosen HTTP client, providing a unified interface for accessing
all available API resources. The `GeminiClient` is not a layer in itself but
rather a facade that simplifies interaction with the underlying layers.

**Key responsibilities of the Client:**

* **Managing global configuration settings:** This includes storing the API key,
  setting timeouts, and configuring the HTTP client.
* **Providing access to the API resources:** The `GeminiClient` exposes methods
  for obtaining instances of the various resource interfaces, allowing
  developers to work with the desired API functionalities.
* **Simplifying the overall interaction with the API:** Developers can use a
  single `GeminiClient` instance to access all API resources, streamlining the
  development process.

### HTTP Layer: Flexibility and Customization

The HTTP layer in Gemini4J plays a crucial role in enabling seamless
communication with the Google Gemini API. It's designed with flexibility and
customization in mind, allowing developers to integrate their preferred HTTP
client library without being restricted to a specific implementation. This
approach ensures compatibility with existing project infrastructure and
minimizes potential dependency conflicts.

**Why is a custom HTTP client important?**

* **Leveraging existing infrastructure:** Many applications already utilize a
  specific HTTP client library like OkHttp, Retrofit, or Apache HttpClient. By
  allowing custom HTTP clients, Gemini4J avoids introducing a new dependency and
  potential conflicts.
* **Fine-grained control:** Custom HTTP clients offer more control over network
  configurations, such as connection pooling, caching, and interceptors. This
  level of control can be crucial for optimizing performance and handling
  specific network requirements.
* **Testing and Mocking:** Using a custom HTTP client simplifies testing by
  allowing developers to mock network requests and responses, ensuring reliable
  and predictable test outcomes.

**Implementing a custom HTTP client with OkHttp:**

To use a custom HTTP client, you need to create an adapter that implements
the `com.enovka.gemini4j.http.spec.HttpClient` interface. This interface defines
the methods for sending GET and POST requests, as well as setting connection and
response timeouts.

Here's a step-by-step guide on how to implement a custom HTTP client adapter
using OkHttp and integrate it with Gemini4J:

**1. Create the OkHttp Adapter:**

```java
import com.enovka.gemini4j.infrastructure.http.exception.HttpException;
import com.enovka.gemini4j.infrastructure.http.spec.HttpClient;
import com.enovka.gemini4j.infrastructure.http.spec.HttpResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class OkHttpAdapter implements HttpClient {

    private final OkHttpClient okHttpClient;

    public OkHttpAdapter(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public HttpResponse get(String url, Map<String, String> headers)
            throws HttpException {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        headers.forEach(requestBuilder::addHeader);
        Request request = requestBuilder.build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return new HttpResponse(response.code(),
                    response.headers().toMultimap(), response.body().string());
        } catch (IOException e) {
            throw new HttpException("Error executing GET request", e);
        }
    }

    @Override
    public HttpResponse post(String url, String body,
                             Map<String, String> headers) throws HttpException {
        RequestBody requestBody = RequestBody.create(body,
                okhttp3.MediaType.parse("application/json"));

        Request.Builder requestBuilder = new Request.Builder().url(url)
                .post(requestBody);
        headers.forEach(requestBuilder::addHeader);
        Request request = requestBuilder.build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return new HttpResponse(response.code(),
                    response.headers().toMultimap(), response.body().string());
        } catch (IOException e) {
            throw new HttpException("Error executing POST request", e);
        }
    }

    @Override
    public void setConnectionTimeout(int connectionTimeout) {
        // OkHttp uses seconds for timeout configuration
        this.okHttpClient.newBuilder().connectTimeout(connectionTimeout / 1000,
                java.util.concurrent.TimeUnit.SECONDS).build();
    }

    @Override
    public void setResponseTimeout(int responseTimeout) {
        // OkHttp uses seconds for timeout configuration
        this.okHttpClient.newBuilder().readTimeout(responseTimeout / 1000,
                java.util.concurrent.TimeUnit.SECONDS).build();
    }
}
```

**2. Configure the GeminiClientBuilder:**

```java
import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientBuilder;
import com.enovka.gemini4j.infrastructure.http.factory.HttpClientType;
import okhttp3.OkHttpClient;

public class CustomHttpClientExample {

    public static void main(String[] args) {
        // Create an OkHttpClient instance
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        // Create the OkHttpAdapter
        OkHttpAdapter okHttpAdapter = new OkHttpAdapter(okHttpClient);

        // Build the GeminiClient with the custom OkHttpAdapter
        GeminiClient client = GeminiClientBuilder.builder()
                .withApiKey("YOUR_API_KEY")
                .withHttpClient(HttpClientBuilder.builder()
                        .withHttpClientType(HttpClientType.CUSTOM)
                        .withCustomClient(okHttpAdapter)
                        .build().build())
                .build().build();

        // Now you can use the 'client' instance to interact with the Gemini API
        // ...
    }
}
```

By following these steps, you can leverage the flexibility of the HTTP layer in
Gemini4J to integrate your preferred HTTP client, ensuring seamless integration
with your existing application infrastructure.

### JSON Layer: Flexibility and Customization for Data Handling

The JSON layer in Gemini4J is the backbone of data exchange with the Google
Gemini API. It's responsible for seamlessly converting Java objects used within
your application into their JSON representation for transmission to the API, and
vice versa, transforming JSON responses from the API back into Java objects for
easy processing. This layer ensures smooth and efficient communication between
your application and the Gemini API.

**Why is a customizable JSON layer important?**

* **Project Consistency and Dependency Management:** Many applications already
  utilize a specific JSON library, such as Gson, Jackson, or Moshi. By allowing
  custom JSON processors, Gemini4J avoids introducing a new dependency and
  potential conflicts, promoting consistency within your project's technology
  stack.
* **Performance Optimization:** Different JSON libraries exhibit varying
  performance characteristics. Choosing a specific library based on its
  performance profile can significantly impact your application's speed and
  efficiency, especially when dealing with large volumes of data.
* **Feature Flexibility:** Certain JSON libraries offer unique features or
  customization options that might be crucial for your project. For instance,
  you might need specific handling of date formats, custom serializers for
  complex objects, or fine-grained control over the serialization process.

**Implementing a Custom JSON Processor with Gson:**

Gemini4J defaults to the Jackson library for JSON processing. However, you can
easily integrate your preferred JSON library by creating an adapter that
implements the `com.enovka.gemini4j.json.spec.JsonService` interface. This
interface defines the core methods for JSON serialization and deserialization:

* **`serialize(T object)`:** Converts a Java object of type `T` into a JSON
  string representation.
* **`deserialize(String json, Class<T> type)`:** Parses a JSON string and
  transforms it into a Java object of the specified type `T`.

Here's a step-by-step guide on implementing a custom JSON processor adapter
using Gson and integrating it with Gemini4J:

**1. Create the Gson Adapter:**

```java
import com.enovka.gemini4j.infrastructure.json.exception.JsonException;
import com.enovka.gemini4j.infrastructure.json.spec.JsonService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonAdapter implements JsonService {

    private final Gson gson;

    public GsonAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> String serialize(T object) throws JsonException {
        try {
            // Utilize Gson's toJson method to serialize the object
            return gson.toJson(object);
        } catch (Exception e) {
            // Wrap any exceptions in a JsonException for consistent error handling
            throw new JsonException("Error serializing object to JSON", e);
        }
    }

    @Override
    public <T> T deserialize(String json, Class<T> type) throws JsonException {
        try {
            // Utilize Gson's fromJson method to deserialize the JSON string
            return gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            // Wrap any exceptions in a JsonException for consistent error handling
            throw new JsonException("Error deserializing JSON string", e);
        }
    }
}
```

**2. Configure the GeminiClientBuilder:**

```java
import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceBuilder;
import com.enovka.gemini4j.infrastructure.json.builder.JsonServiceType;
import com.google.gson.Gson;

public class CustomJsonProcessorExample {

    public static void main(String[] args) {
        // Create a Gson instance with your desired configurations
        Gson gson = new Gson();

        // Create the GsonAdapter instance
        GsonAdapter gsonAdapter = new GsonAdapter(gson);

        // Build the GeminiClient, injecting the custom GsonAdapter
        GeminiClient client = GeminiClientBuilder.builder()
                .withApiKey("YOUR_API_KEY")
                .withJsonService(JsonServiceBuilder.builder()
                        .withJsonServiceType(JsonServiceType.CUSTOM)
                        .withCustomJsonService(gsonAdapter)
                        .build().build())
                .build().build();

        // Now you can use the 'client' instance to interact with the Gemini API. 
        // All resources will utilize the custom GsonAdapter for JSON processing.
        // ...
    }
}
```

By following these steps, you can seamlessly integrate your preferred JSON
library with Gemini4J, ensuring consistency with your project setup and
potentially benefiting from enhanced performance or specific features offered by
your chosen library. This level of customization makes Gemini4J a flexible and
adaptable tool for interacting with the Google Gemini API.
