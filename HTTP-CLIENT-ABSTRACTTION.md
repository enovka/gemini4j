## HTTP Client Abstraction Layer for Java - Documentation

This document provides a comprehensive guide to the HTTP client abstraction layer, detailing its architecture, usage, and customization options. This abstraction allows for flexibility in choosing the most suitable HTTP client library for your Java project, promoting adaptability and maintainability.

**1. Introduction**

Many Java projects require interaction with external APIs or services through HTTP requests.  However, the choice of the HTTP client library can vary depending on project needs, dependencies, and developer preferences.  To ensure flexibility and avoid tight coupling to a specific client library, a robust abstraction layer for HTTP communication is beneficial.

**2. Architecture Overview**

The HTTP client abstraction layer follows a layered architecture, designed to promote modularity and maintainability.  The key components are:

* **`HttpClientService` Interface:** This interface defines the core methods for making HTTP requests (GET and POST), serving as a contract for all HTTP client implementations.  It acts as a high-level abstraction, hiding the underlying details of the chosen HTTP client library.

* **`AbstractHttpClientService` Class:** This abstract class implements the `HttpClientService` interface, providing common functionalities like URL and header validation. It delegates the actual HTTP request execution to concrete implementations through abstract methods (`doGET` and `doPOST`). This class simplifies the development of specific client implementations by handling essential tasks.

* **Concrete HTTP Client Implementations:** This layer provides concrete implementations of the `HttpClientService` interface, each tailored to a specific HTTP client library (e.g., Apache HttpClient, Spring WebClient, OkHttp, etc.). These implementations handle the library-specific details, including:
   * Sending HTTP requests.
   * Receiving and parsing HTTP responses.
   * Managing error scenarios.

* **`HttpResponseWrapper` Interface:**  This interface defines a generic representation of the HTTP response, decoupling the `HttpClientService` interface from specific HTTP client response classes. It provides a unified interface for accessing response data, regardless of the underlying HTTP client library.

* **Concrete `HttpResponseWrapper` Implementations:** Concrete implementations of the `HttpResponseWrapper` interface are created for each HTTP client library used. These classes encapsulate the logic for extracting the status code, headers, and body from the response object of the specific HTTP client, offering a consistent way to interact with the response data.

* **`HttpClientServiceFactory` Class:** This factory class provides methods to create instances of `HttpClientService` based on the desired client type (e.g., default Apache HttpClient, custom Spring WebClient, etc.). It promotes loose coupling and facilitates the selection of the appropriate HTTP client implementation.

**3. Benefits of the Architecture**

* **Flexibility:**  You can easily adapt to different HTTP client libraries by simply implementing the `HttpClientService` interface. This allows you to choose the most suitable HTTP client for your project's requirements.

* **Maintainability:** The abstraction layer separates the core HTTP logic from the client-specific implementations. This makes it easier to maintain and update your code as new HTTP client libraries are introduced or existing ones evolve.

* **Testability:** The abstraction layer allows for independent testing of the HTTP client implementations without affecting the core HTTP interaction logic. This simplifies unit testing and increases the reliability of your codebase.

* **Extensibility:** The factory pattern (`HttpClientServiceFactory`) makes adding new HTTP client implementations easy without modifying existing code. This promotes a flexible and adaptable system.

**4. Basic Usage**

To use the HTTP client abstraction layer, you need to:

* **Include the library in your project:** You can add the library to your project using Maven or Gradle.

* **Create an instance of the `HttpClientService` interface:** The factory class (`HttpClientServiceFactory`) provides methods for creating different types of `HttpClientService` instances.

* **Make HTTP requests:** Use the `get` and `post` methods of the `HttpClientService` interface to send GET and POST requests, respectively.

**5. Customizing the HTTP Client**

You can customize the HTTP client by using the `HttpClientServiceFactory`. The factory provides methods to create `HttpClientService` instances with different configurations, including:

* **Default HttpClient:** This is the default implementation of the `HttpClientService`, typically using a standard library like Apache HttpClient. You can create a `HttpClientService` instance using the default configuration:

   ```java
   HttpClientService httpClientService = HttpClientServiceFactory.create(HttpClientServiceFactory.ClientType.DEFAULT);
   ```

* **Custom Validator:** You can provide a custom validator to validate HTTP requests:

   ```java
   HttpRequestValidator customValidator = new MyCustomValidator();
   HttpClientService httpClientService = HttpClientServiceFactory.create(HttpClientServiceFactory.ClientType.DEFAULT, customValidator);
   ```

* **Custom Client:** You can create a custom client, such as Spring WebClient or OkHttp, and use it with the abstraction layer. You need to provide a custom implementation of `HttpClientService` and `HttpResponseWrapper` for your custom client:

   ```java
   WebClient webClient = WebClient.builder().baseUrl("https://api.example.com").build();
   SpringWebClientService springWebClientService = new SpringWebClientService(webClient);
   SpringWebClientHttpResponseWrapper springWebClientHttpResponseWrapper = new SpringWebClientHttpResponseWrapper();
   HttpClientService httpClientService = HttpClientServiceFactory.create(HttpClientServiceFactory.ClientType.CUSTOM, springWebClientService, springWebClientHttpResponseWrapper, new HttpRequestValidator());
   ```

**6. Example: Using Spring WebClient**

Here's an example of how to use Spring WebClient with the HTTP client abstraction layer:

1. **Create a `SpringWebClientService` class:** This class implements the `HttpClientService` interface and uses Spring WebClient to make HTTP requests.

   ```java
   package com.enovka.gemini4j.http.springwebclient;

   // ... (implementation of SpringWebClientService and SpringWebClientHttpResponseWrapper) ...
   ```

2. **Create a `HttpClientService` instance:** Use the `HttpClientServiceFactory` to create a `HttpClientService` instance for your custom client.

   ```java
   HttpClientService httpClientService = HttpClientServiceFactory.create(
           HttpClientServiceFactory.ClientType.CUSTOM,
           new SpringWebClientService(WebClient.builder().baseUrl("https://api.example.com").build()),
           new SpringWebClientHttpResponseWrapper(),
           new HttpRequestValidator()
   );
   ```

3. **Make HTTP requests:** Use the `get` and `post` methods of the `HttpClientService` to send requests.

   ```java
   HttpResponseWrapper response = httpClientService.get("https://api.example.com/v1/data", Map.of("User-Agent", "MyApplication"));
   System.out.println("Status Code: " + response.statusCode());
   System.out.println("Headers: " + response.headers());
   System.out.println("Body: " + response.body());
   ```

**7. Creating Custom Clients**

To create a custom client, you need to:

* **Implement the `HttpClientService` interface:**  Create a new class that implements the `HttpClientService` interface using your desired HTTP client library.
* **Implement the `HttpResponseWrapper` interface:** Create a new class that implements the `HttpResponseWrapper` interface to handle responses from your custom client.

**8. Integrating Custom Clients**

Once you've implemented your custom client, you need to integrate it with the HTTP client abstraction layer:

* **Update the `HttpClientServiceFactory`:**  Add a new case to the `HttpClientServiceFactory`'s enum (`ClientType`) to represent your custom client type.
* **Create a `HttpClientService` instance using the factory:** Use the `HttpClientServiceFactory` to create a `HttpClientService` instance for your custom client.

**9. Example of Custom Client Integration**

Let's imagine you have a custom HTTP client called `MyCustomHttpClient`.

1. **Implement `HttpClientService`:**

   ```java
   package com.enovka.gemini4j.http.custom;

   // ... (implementation of MyCustomHttpClientService and MyCustomHttpResponseWrapper) ...
   ```

2. **Update `HttpClientServiceFactory`:**

   ```java
   package com.enovka.gemini4j.http.factory;

   public enum ClientType {
       DEFAULT,
       CUSTOM,
       MY_CUSTOM_CLIENT
   }

   public static HttpClientService create(ClientType clientType, Object httpClient, HttpResponseWrapper httpResponseWrapper, HttpRequestValidator validator) {
       switch (clientType) {
           case DEFAULT:
               return new DefaultHttpClientService((HttpClient) httpClient, validator);
           case CUSTOM:
               return new CustomHttpClientService((HttpClientService) httpClient, httpResponseWrapper, validator);
           case MY_CUSTOM_CLIENT:
               return new CustomHttpClientService((HttpClientService) httpClient, httpResponseWrapper, validator); // Assuming 'httpClient' is a MyCustomHttpClientService instance
           default:
               throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_CLIENT_TYPE);
       }
   }
   ```

3. **Create a `HttpClientService` instance with your custom client:**

   ```java
   // Assuming 'myCustomHttpClient' is an instance of MyCustomHttpClientService
   HttpClientService httpClientService = HttpClientServiceFactory.create(
           HttpClientServiceFactory.ClientType.MY_CUSTOM_CLIENT,
           myCustomHttpClient,
           myCustomHttpResponseWrapper,
           new HttpRequestValidator()
   );
   ```

**10. Conclusion**

The HTTP client abstraction layer allows you to easily adapt to different HTTP client libraries, making your application robust and adaptable. This guide provided a comprehensive overview of using and customizing the layer, including examples of using Spring WebClient and integrating custom clients. With these tools, you can seamlessly integrate this abstraction into your projects, enhancing their flexibility and performance.


