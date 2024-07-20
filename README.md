## Gemini4J: A Java Library for the Google Gemini API

Gemini4J is a powerful and intuitive Java library that empowers developers to
seamlessly integrate the advanced capabilities of the Google Gemini API into
their applications. With Gemini4J, you can unlock the potential of Gemini's
state-of-the-art artificial intelligence to generate text, translate languages,
create diverse creative content, and obtain informative answers to your
questions.

**Key Features of Google Gemini:**

* **Advanced Text Generation:** Gemini is a cutting-edge language model capable
  of generating natural and creative text in various styles and formats. It can
  adapt to different writing styles, tones, and complexities, making it ideal
  for tasks ranging from writing stories and poems to crafting marketing copy
  and technical documentation.
* **Natural Language Understanding:** Gemini possesses a deep understanding of
  human language, enabling it to comprehend complex questions, translate
  languages accurately, and generate contextually relevant content. This makes
  it suitable for applications like chatbots, question answering systems, and
  content summarization.
* **Creative and Informative Content:** Gemini can be used to create a wide
  range of content, including stories, poems, articles, emails, and much more.
  It can also provide informative summaries and answer questions based on its
  vast knowledge base, making it a valuable tool for research, education, and
  entertainment.
* **Flexibility and Customization:** The Gemini API offers customization options
  to fine-tune the model's behavior. Parameters like temperature and top-k
  sampling allow developers to control the level of creativity and randomness in
  the responses, tailoring the output to specific application needs.

**Gemini4J Simplifies Integration with the Gemini API by Providing:**

* **Abstraction of API Complexity:** Gemini4J encapsulates the underlying
  complexities of HTTP communication and JSON serialization, allowing developers
  to focus on their application logic without needing to delve into low-level
  details.
* **Intuitive and Easy-to-Use Interface:** Gemini4J provides a high-level
  interface for interacting with the Gemini API resources, making integration
  quick and straightforward. This simplifies the process of leveraging Gemini's
  capabilities, reducing development time and effort.
* **Support for Key Gemini Features:** Gemini4J offers support for key Gemini
  features, including text generation, embedding generation, listing available
  models, and more. This allows developers to access a wide range of Gemini's
  capabilities through a unified and consistent API.

**Example Usage:**

```java
import com.enovka.gemini4j.client.builder.GeminiClientBuilder;
import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.client.spec.GeminiClient;
import com.enovka.gemini4j.domain.ListModel;
import com.enovka.gemini4j.domain.response.GenerateContentResponse;
import com.enovka.gemini4j.resource.builder.ResourceBuilder;
import com.enovka.gemini4j.resource.spec.GenerationResource;
import com.enovka.gemini4j.resource.spec.ModelResource;

public class SimpleExample {

  public static void main(String[] args) {
    // Replace with your actual API key
    String apiKey = "API-KEY";

    // Create a GeminiClient
    GeminiClient client = GeminiClientBuilder.builder()
            .withApiKey(apiKey)
            .withModel(
                    "gemini-1.5-flash-001") // Set the desired Gemini model
            .build();

    // Create a GenerationResource
    GenerationResource generationResource = ResourceBuilder.builder(client)
            .buildGenerationResource();

    // Example 1: Simple text generation with system instructions
    GenerateContentRequest request1 = GenerateContentRequestBuilder.builder(
                    generationResource.getGeminiClient())
            .withUserInput(
                    "Write a short story about a cat who goes on an adventure.") // User input prompt
            .withSystemInstructions(
                    "Make it a humorous story with a happy ending.") // System-level instructions for the model
            .withGenerationConfig()
            .withTemperature(
                    0.7) // Controls the randomness of the output (higher = more random)
            .withMaxOutputTokens(
                    200) // Limits the maximum number of tokens in the generated response
            .and()
            .build();

    // Example 2: Text generation with safety settings
    GenerateContentRequest request2 = GenerateContentRequestBuilder.builder(
                    generationResource.getGeminiClient())
            .withUserInput("Tell me a joke.") // User input prompt
            .withSafetySetting()
            .withCategory(HarmCategoryEnum.HARM_CATEGORY_DANGEROUS_CONTENT)
            .withThreshold(HarmBlockThresholdEnum.BLOCK_MEDIUM_AND_ABOVE)
            .and()
            .build();

    // Example 3: Text generation with multiple candidates
    GenerateContentRequest request3 = GenerateContentRequestBuilder.builder(
                    generationResource.getGeminiClient())
            .withUserInput(
                    "Give me three ideas for a birthday party.") // User input prompt
            .withGenerationConfig()
            .withCandidateCount(
                    1) // Request 1 different response candidates
            .and()
            .build();

    // Execute the requests and print the generated text
    try {
      GeminiResult result1 = generationResource.generateContent(request1);
      System.out.println("Example 1 - Generated Text: "
              + result1.getGeneratedText());

      GeminiResult result2 = generationResource.generateContent(request2);
      System.out.println("Example 2 - Generated Text: "
              + result2.getGeneratedText());

      GeminiResult result3 = generationResource.generateContent(request3);
      System.out.println("Example 3 - Generated Text: "
              + result3.getGeneratedText());

    } catch (GeminiApiException e) {
      e.printStackTrace();
    }
  }
}
```

## Gemini4J Roadmap: Version 1.0.0

This roadmap outlines the key milestones for the initial release of Gemini4J,
focusing on providing a comprehensive and robust set of features for interacting
with the Google Gemini API.

### Phase 1: Core Functionality and Essential Resources

* **Complete `GenerationResource`:**
    * ~~Implement all remaining methods of the `GenerationResource` interface,
      providing full coverage of the content generation capabilities offered by
      the Gemini API.~~
    * **Crucial Feature:** Implement **incremental conversation support**,
      enabling developers to build conversational experiences by maintaining
      context across multiple interactions with the API.
* **Enhance `ModelResource`:**
    * ~~Add the `getModel()` method to the `ModelResource` interface, allowing
      developers to retrieve detailed information about specific Gemini
      models.~~
* **Implement `EmbedResource`:**
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
