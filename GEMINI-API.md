# REST Resource: cachedContents

### Resource: CachedContent

Content that has been preprocessed and can be used in subsequent requests to
GenerativeService.

Cached content can be only used with the model it was created for.

#### JSON representation

```json
{
  "contents": [
    {
      "object": "Content"
    }
  ],
  "tools": [
    {
      "object": "Tool"
    }
  ],
  "createTime": "string",
  "updateTime": "string",
  "usageMetadata": {
    "object": "UsageMetadata"
  },
  // Union field expiration can be only one of the following:
  "expireTime": "string",
  "ttl": "string"
  // End of list of possible types for union field expiration.
  "name": "string",
  "displayName": "string",
  "model": "string",
  "systemInstruction": {
    "object": "Content"
  },
  "toolConfig": {
    "object": "ToolConfig"
  }
}
```

#### Fields

* **contents[]**
    * **object (Content)**
        * Optional. Input only. Immutable. The content to cache.

* **tools[]**
    * **object (Tool)**
        * Optional. Input only. Immutable. A list of Tools the model may use to
          generate the next response.

* **createTime**
    * **string (Timestamp format)**
        * Output only. Creation time of the cache entry.
        * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond resolution
          and up to nine fractional digits. Examples: "2014-10-02T15:01:23Z"
          and "2014-10-02T15:01:23.045123456Z".

* **updateTime**
    * **string (Timestamp format)**
        * Output only. When the cache entry was last updated in UTC time.
        * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond resolution
          and up to nine fractional digits. Examples: "2014-10-02T15:01:23Z"
          and "2014-10-02T15:01:23.045123456Z".

* **usageMetadata**
    * **object (UsageMetadata)**
        * Output only. Metadata on the usage of the cached content.

* **Union field expiration.** Specifies when this resource will
  expire. `expiration` can be only one of the following:
    * **expireTime**
        * **string (Timestamp format)**
            * Timestamp in UTC of when this resource is considered expired. This
              is always provided on output, regardless of what was sent on
              input.
            * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond
              resolution and up to nine fractional digits. Examples: "
              2014-10-02T15:01:23Z" and "2014-10-02T15:01:23.045123456Z".

    * **ttl**
        * **string (Duration format)**
            * Input only. New TTL for this resource, input only.
            * A duration in seconds with up to nine fractional digits, ending
              with 's'. Example: "3.5s".

* **name**
    * **string**
        * Optional. Identifier. The resource name referring to the cached
          content. Format: `cachedContents/{id}`

* **displayName**
    * **string**
        * Optional. Immutable. The user-generated meaningful display name of the
          cached content. Maximum 128 Unicode characters.

* **model**
    * **string**
        * Required. Immutable. The name of the Model to use for cached content
          Format: `models/{model}`

* **systemInstruction**
    * **object (Content)**
        * Optional. Input only. Immutable. Developer set system instruction.
          Currently text only.

* **toolConfig**
    * **object (ToolConfig)**
        * Optional. Input only. Immutable. Tool config. This config is shared
          for all tools.

### Content

The base structured datatype containing multi-part content of a message.

A Content includes a `role` field designating the producer of the Content and
a `parts` field containing multi-part data that contains the content of the
message turn.

#### JSON representation

```json
{
  "parts": [
    {
      "object": "Part"
    }
  ],
  "role": "string"
}
```

#### Fields

* **parts[]**
    * **object (Part)**
        * Ordered Parts that constitute a single message. Parts may have
          different MIME types.

* **role**
    * **string**
        * Optional. The producer of the content. Must be either 'user' or '
          model'.
        * Useful to set for multi-turn conversations, otherwise can be left
          blank or unset.

### Part

A datatype containing media that is part of a multi-part Content message.

A Part consists of `data` which has an associated datatype. A Part can only
contain one of the accepted types in Part.data.

A Part must have a fixed IANA MIME type identifying the type and subtype of the
media if the `inlineData` field is filled with raw bytes.

#### JSON representation

```json
{
  // Union field data can be only one of the following:
  "text": "string",
  "inlineData": {
    "object": "Blob"
  },
  "functionCall": {
    "object": "FunctionCall"
  },
  "functionResponse": {
    "object": "FunctionResponse"
  },
  "fileData": {
    "object": "FileData"
  }
  // End of list of possible types for union field data.
}
```

#### Fields

* **Union field data.**
    * **data** can be only one of the following:
        * **text**
            * **string**
                * Inline text.

        * **inlineData**
            * **object (Blob)**
                * Inline media bytes.

        * **functionCall**
            * **object (FunctionCall)**
                * A predicted FunctionCall returned from the model that contains
                  a string representing the FunctionDeclaration.name with the
                  arguments and their values.

        * **functionResponse**
            * **object (FunctionResponse)**
                * The result output of a FunctionCall that contains a string
                  representing the FunctionDeclaration.name and a structured
                  JSON object containing any output from the function is used as
                  context to the model.

        * **fileData**
            * **object (FileData)**
                * URI based data.

### Blob

Raw media bytes.

Text should not be sent as raw bytes, use the 'text' field.

#### JSON representation

```json
{
  "mimeType": "string",
  "data": "string"
}
```

#### Fields

* **mimeType**
    * **string**
        * The IANA standard MIME type of the source data. Examples: -
          image/png - image/jpeg If an unsupported MIME type is provided, an
          error will be returned. For a complete list of supported types, see
          Supported file formats.

* **data**
    * **string (bytes format)**
        * Raw bytes for media formats.
        * A base64-encoded string.

### FunctionCall

A predicted FunctionCall returned from the model that contains a string
representing the FunctionDeclaration.name with the arguments and their values.

#### JSON representation

```json
{
  "name": "string",
  "args": {
    "object": "Struct format"
  }
}
```

#### Fields

* **name**
    * **string**
        * Required. The name of the function to call. Must be a-z, A-Z, 0-9, or
          contain underscores and dashes, with a maximum length of 63.

* **args**
    * **object (Struct format)**
        * Optional. The function parameters and values in JSON object format.

### FunctionResponse

The result output from a FunctionCall that contains a string representing the
FunctionDeclaration.name and a structured JSON object containing any output from
the function is used as context to the model. This should contain the result of
aFunctionCall made based on model prediction.

#### JSON representation

```json
{
  "name": "string",
  "response": {
    "object": "Struct format"
  }
}
```

#### Fields

* **name**
    * **string**
        * Required. The name of the function to call. Must be a-z, A-Z, 0-9, or
          contain underscores and dashes, with a maximum length of 63.

* **response**
    * **object (Struct format)**
        * Required. The function response in JSON object format.

### FileData

URI based data.

#### JSON representation

```json
{
  "mimeType": "string",
  "fileUri": "string"
}
```

#### Fields

* **mimeType**
    * **string**
        * Optional. The IANA standard MIME type of the source data.

* **fileUri**
    * **string**
        * Required. URI.

### Tool

Tool details that the model may use to generate response.

A Tool is a piece of code that enables the system to interact with external
systems to perform an action, or set of actions, outside of knowledge and scope
of the model.

#### JSON representation

```json
{
  "functionDeclarations": [
    {
      "object": "FunctionDeclaration"
    }
  ]
}
```

#### Fields

* **functionDeclarations[]**
    * **object (FunctionDeclaration)**
        * Optional. A list of FunctionDeclarations available to the model that
          can be used for function calling.

      The model or system does not execute the function. Instead the defined
      function may be returned as a [FunctionCall][content.part.function_call]
      with arguments to the clientService side for execution. The model may
      decide to
      call a subset of these functions by
      populating [FunctionCall][content.part.function_call] in the response. The
      next conversation turn may contain
      a [FunctionResponse][content.part.function_response] with
      the [content.role] "function" generation context for the next model turn.

### FunctionDeclaration

Structured representation of a function declaration as defined by the OpenAPI
3.03 specification. Included in this declaration are the function name and
parameters. This FunctionDeclaration is a representation of a block of code that
can be used as a Tool by the model and executed by the clientService.

#### JSON representation

```json
{
  "name": "string",
  "description": "string",
  "parameters": {
    "object": "Schema"
  }
}
```

#### Fields

* **name**
    * **string**
        * Required. The name of the function. Must be a-z, A-Z, 0-9, or contain
          underscores and dashes, with a maximum length of 63.

* **description**
    * **string**
        * Required. A brief description of the function.

* **parameters**
    * **object (Schema)**
        * Optional. Describes the parameters to this function. Reflects the Open
          API 3.03 Parameter Object string Key: the name of the parameter.
          Parameter names are case sensitive. Schema Value: the Schema defining
          the type used for the parameter.

### Schema

The Schema object allows the definition of input and output data types. These
types can be objects, but also primitives and arrays. Represents a select subset
of an OpenAPI 3.0 schema object.

#### JSON representation

```json
{
  "type": "enum (Type)",
  "format": "string",
  "description": "string",
  "nullable": "boolean",
  "enum": [
    "string"
  ],
  "properties": {
    "string": {
      "object": "Schema"
    },
    ...
  },
  "required": [
    "string"
  ],
  "items": {
    "object": "Schema"
  }
}
```

#### Fields

* **type**
    * **enum (Type)**
        * Required. Data type.

* **format**
    * **string**
        * Optional. The format of the data. This is used only for primitive
          datatypes. Supported formats: for NUMBER type: float, double for
          INTEGER type: int32, int64

* **description**
    * **string**
        * Optional. A brief description of the parameter. This could contain
          examples of use. Parameter description may be formatted as Markdown.

* **nullable**
    * **boolean**
        * Optional. Indicates if the value may be null.

* **enum[]**
    * **string**
        * Optional. Possible values of the element of Type.STRING with enum
          format. For example we can define an Enum Direction as : {type:STRING,
          format:enum, enum:["EAST", NORTH", "SOUTH", "WEST"]}

* **properties**
    * **map (key: string, value: object (Schema))**
        * Optional. Properties of Type.OBJECT.
        * An object containing a list of "key": value pairs. Example: { "
          name": "wrench", "mass": "1.3kg", "count": "3" }.

* **required[]**
    * **string**
        * Optional. Required properties of Type.OBJECT.

* **items**
    * **object (Schema)**
        * Optional. Schema of the elements of Type.ARRAY.

### Type

Type contains the list of OpenAPI data types as defined
by https://spec.openapis.org/oas/v3.0.3#data-types

#### Enums

* **TYPE_UNSPECIFIED**
    * Not specified, should not be used.

* **STRING**
    * String type.

* **NUMBER**
    * Number type.

* **INTEGER**
    * Integer type.

* **BOOLEAN**
    * Boolean type.

* **ARRAY**
    * Array type.

* **OBJECT**
    * Object type.

### ToolConfig

The Tool configuration containing parameters for specifying Tool use in the
request.

#### JSON representation

```json
{
  "functionCallingConfig": {
    "object": "FunctionCallingConfig"
  }
}
```

#### Fields

* **functionCallingConfig**
    * **object (FunctionCallingConfig)**
        * Optional. Function calling config.

### FunctionCallingConfig

Configuration for specifying function calling behavior.

#### JSON representation

```json
{
  "mode": "enum (Mode)",
  "allowedFunctionNames": [
    "string"
  ]
}
```

#### Fields

* **mode**
    * **enum (Mode)**
        * Optional. Specifies the mode in which function calling should execute.
          If unspecified, the default value will be set to AUTO.

* **allowedFunctionNames[]**
    * **string**
        * Optional. A set of function names that, when provided, limits the
          functions the model will call.
        * This should only be set when the Mode is ANY. Function names should
          match [FunctionDeclaration.name]. With mode set to ANY, model will
          predict a function call from the set of function names provided.

### Mode

Defines the execution behavior for function calling by defining the execution
mode.

#### Enums

* **MODE_UNSPECIFIED**
    * Unspecified function calling mode. This value should not be used.

* **AUTO**
    * Default model behavior, model decides to predict either a function call or
      a natural language response.

* **ANY**
    * Model is constrained to always predicting a function call only. If "
      allowedFunctionNames" are set, the predicted function call will be limited
      to any one of "allowedFunctionNames", else the predicted function call
      will be any one of the provided "functionDeclarations".

* **NONE**
    * Model will not predict any function call. Model behavior is same as when
      not passing any function declarations.

### UsageMetadata

Metadata on the usage of the cached content.

#### JSON representation

```json
{
  "totalTokenCount": "integer"
}
```

#### Fields

* **totalTokenCount**
    * **integer**
        * Total number of tokens that the cached content consumes.

### Methods

* **create**
    * Creates CachedContent resource.
* **delete**
    * Deletes CachedContent resource.
* **get**
    * Reads CachedContent resource.
* **list**
    * Lists CachedContents.
* **patch**
    * Updates CachedContent resource (only expiration is updatable).

## Method: cachedContents.create

### Creates CachedContent resource.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/cachedContents
```

#### Request body

The request body contains an instance of CachedContent.

#### Response body

If successful, the response body contains a newly created instance of
CachedContent.

## Method: cachedContents.delete

### Deletes CachedContent resource.

#### HTTP request

```
DELETE https://generativelanguage.googleapis.com/v1beta/{name=cachedContents/*}
```

#### Path parameters

| Parameter | Type   | Description                                                                                    |
|-----------|--------|------------------------------------------------------------------------------------------------|
| name      | string | Required. The resource name referring to the content cache entry Format: `cachedContents/{id}` |

#### Request body

The request body must be empty.

#### Response body

If successful, the response body is empty.

## Method: cachedContents.get

### Reads CachedContent resource.

#### HTTP request

```
GET https://generativelanguage.googleapis.com/v1beta/{name=cachedContents/*}
```

#### Path parameters

| Parameter | Type   | Description                                                                                     |
|-----------|--------|-------------------------------------------------------------------------------------------------|
| name      | string | Required. The resource name referring to the content cache entry. Format: `cachedContents/{id}` |

#### Request body

The request body must be empty.

#### Response body

If successful, the response body contains an instance of CachedContent.

## Method: cachedContents.list

### Lists CachedContents.

#### HTTP request

```
GET https://generativelanguage.googleapis.com/v1beta/cachedContents
```

#### Query parameters

| Parameter | Type    | Description                                                                                                                                                                                                                                                   |
|-----------|---------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pageSize  | integer | Optional. The maximum number of cached contents to return. The service may return fewer than this value. If unspecified, some default (under maximum) number of items will be returned. The maximum value is 1000; values above 1000 will be coerced to 1000. |
| pageToken | string  | Optional. A page token, received from a previous `cachedContents.list` call. Provide this to retrieve the subsequent page. When paginating, all other parameters provided to `cachedContents.list` must match the call that provided the page token.          |

#### Request body

The request body must be empty.

#### Response body

Response with CachedContents list.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "cachedContents": [
    {
      "object": "CachedContent"
    }
  ],
  "nextPageToken": "string"
}
```

#### Fields

* **cachedContents[]**
    * **object (CachedContent)**
        * List of cached contents.

* **nextPageToken**
    * **string**
        * A token, which can be sent as `pageToken` to retrieve the next page.
          If this field is omitted, there are no subsequent pages.

## Method: cachedContents.patch

### Updates CachedContent resource (only expiration is updatable).

#### HTTP request

```
PATCH https://generativelanguage.googleapis.com/v1beta/{cachedContent.name=cachedContents/*}
```

#### Path parameters

| Parameter          | Type   | Description                                                                                            |
|--------------------|--------|--------------------------------------------------------------------------------------------------------|
| cachedContent.name | string | Optional. Identifier. The resource name referring to the cached content. Format: `cachedContents/{id}` |

#### Query parameters

| Parameter  | Type                      | Description                                                                                                                         |
|------------|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| updateMask | string (FieldMask format) | The list of fields to update. This is a comma-separated list of fully qualified names of fields. Example: "user.displayName,photo". |

#### Request body

The request body contains an instance of CachedContent.

#### Response body

If successful, the response body contains an instance of CachedContent.

## REST Resource: models

### Resource: Model

Information about a Generative Language Model.

#### JSON representation

```json
{
  "name": "string",
  "baseModelId": "string",
  "version": "string",
  "displayName": "string",
  "description": "string",
  "inputTokenLimit": "integer",
  "outputTokenLimit": "integer",
  "supportedGenerationMethods": [
    "string"
  ],
  "temperature": "number",
  "topP": "number",
  "topK": "integer"
}
```

#### Fields

* **name**
    * **string**
        * Required. The resource name of the Model.
        * Format: `models/{model}` with a `{model}` naming convention of:
        * `"{baseModelId}-{version}"`
        * Examples:
            * `models/chat-bison-001`

* **baseModelId**
    * **string**
        * Required. The name of the base model, pass this to the generation
          request.
        * Examples:
            * `chat-bison`

* **version**
    * **string**
        * Required. The version number of the model.
        * This represents the major version

* **displayName**
    * **string**
        * The human-readable name of the model. E.g. "Chat Bison".
        * The name can be up to 128 characters long and can consist of any UTF-8
          characters.

* **description**
    * **string**
        * A short description of the model.

* **inputTokenLimit**
    * **integer**
        * Maximum number of input tokens allowed for this model.

* **outputTokenLimit**
    * **integer**
        * Maximum number of output tokens available for this model.

* **supportedGenerationMethods[]**
    * **string**
        * The model's supported generation methods.
        * The method names are defined as Pascal case strings, such
          as `generateMessage` which correspond to API methods.

* **temperature**
    * **number**
        * Controls the randomness of the output.
        * Values can range over [0.0,2.0], inclusive. A higher value will
          produce responses that are more varied, while a value closer to 0.0
          will typically result in less surprising responses from the model.
          This value specifies default to be used by the backend while making
          the call to the model.

* **topP**
    * **number**
        * For Nucleus sampling.
        * Nucleus sampling considers the smallest set of tokens whose
          probability sum is at least topP. This value specifies default to be
          used by the backend while making the call to the model.

* **topK**
    * **integer**
        * For Top-k sampling.
        * Top-k sampling considers the set of topK most probable tokens. This
          value specifies default to be used by the backend while making the
          call to the model. If empty, indicates the model doesn't use top-k
          sampling, and topK isn't allowed as a generation parameter.

### Methods

* **batchEmbedContents**
    * Generates multiple embeddings from the model given input text in a
      synchronous call.
* **batchEmbedText**
    * Generates multiple embeddings from the model given input text in a
      synchronous call.
* **countMessageTokens**
    * Runs a model's tokenizer on a string and returns the token count.
* **countTextTokens**
    * Runs a model's tokenizer on a text and returns the token count.
* **countTokens**
    * Runs a model's tokenizer on input content and returns the token count.
* **embedContent**
    * Generates an embedding from the model given an input Content.
* **embedText**
    * Generates an embedding from the model given an input message.
* **generateAnswer**
    * Generates a grounded answer from the model given an input
      GenerateAnswerRequest.
* **generateContent**
    * Generates a response from the model given an input GenerateContentRequest.
* **generateMessage**
    * Generates a response from the model given an input MessagePrompt.
* **generateText**
    * Generates a response from the model given an input message.
* **get**
    * Gets information about a specific Model.
* **list**
    * Lists models available through the API.
* **streamGenerateContent**
    * Generates a streamed response from the model given an input
      GenerateContentRequest.

## Method: models.batchEmbedContents

### Generates multiple embeddings from the model given input text in a synchronous call.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:batchEmbedContents
```

#### Path parameters

| Parameter | Type   | Description                                                                                                                                                                        |
|-----------|--------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| model     | string | Required. The model's resource name. This serves as an ID for the Model to use. This name should match a model name returned by the `models.list` method. Format: `models/{model}` |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "requests": [
    {
      "object": "EmbedContentRequest"
    }
  ]
}
```

#### Fields

* **requests[]**
    * **object (EmbedContentRequest)**
        * Required. Embed requests for the batch. The model in each of these
          requests must match the model specified
          BatchEmbedContentsRequest.model.

#### Response body

The response to a BatchEmbedContentsRequest.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "embeddings": [
    {
      "object": "ContentEmbedding"
    }
  ]
}
```

#### Fields

* **embeddings[]**
    * **object (ContentEmbedding)**
        * Output only. The embeddings for each request, in the same order as
          provided in the batch request.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

### EmbedContentRequest

Request containing the Content for the model to embed.

#### JSON representation

```json
{
  "model": "string",
  "content": {
    "object": "Content"
  },
  "taskType": "enum (TaskType)",
  "title": "string",
  "outputDimensionality": "integer"
}
```

#### Fields

* **model**
    * **string**
        * Required. The model's resource name. This serves as an ID for the
          Model to use.
        * This name should match a model name returned by the models.list
          method.
        * Format: models/{model}

* **content**
    * **object (Content)**
        * Required. The content to embed. Only the `parts.text` fields will be
          counted.

* **taskType**
    * **enum (TaskType)**
        * Optional. Optional task type for which the embeddings will be used.
          Can only be set for models/embedding-001.

* **title**
    * **string**
        * Optional. An optional title for the text. Only applicable when
          TaskType is RETRIEVAL_DOCUMENT.
        * Note: Specifying a title for RETRIEVAL_DOCUMENT provides better
          quality embeddings for retrieval.

* **outputDimensionality**
    * **integer**
        * Optional. Optional reduced dimension for the output embedding. If set,
          excessive values in the output embedding are truncated from the end.
          Supported by newer models since 2024, and the earlier model (
          models/embedding-001) cannot specify this value.

## Method: models.batchEmbedText

### Generates multiple embeddings from the model given input text in a synchronous call.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:batchEmbedText
```

#### Path parameters

| Parameter | Type   | Description                                                                                                 |
|-----------|--------|-------------------------------------------------------------------------------------------------------------|
| model     | string | Required. The name of the Model to use for generating the embedding. Examples: `models/embedding-gecko-001` |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "texts": [
    "string"
  ],
  "requests": [
    {
      "object": "EmbedTextRequest"
    }
  ]
}
```

#### Fields

* **texts[]**
    * **string**
        * Optional. The free-form input texts that the model will turn into an
          embedding. The current limit is 100 texts, over which an error will be
          thrown.

* **requests[]**
    * **object (EmbedTextRequest)**
        * Optional. Embed requests for the batch. Only one of `texts`
          or `requests` can be set.

#### Response body

The response to a EmbedTextRequest.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "embeddings": [
    {
      "object": "Embedding"
    }
  ]
}
```

#### Fields

* **embeddings[]**
    * **object (Embedding)**
        * Output only. The embeddings generated from the input text.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

### EmbedTextRequest

Request to get a text embedding from the model.

#### JSON representation

```json
{
  "model": "string",
  "text": "string"
}
```

#### Fields

* **model**
    * **string**
        * Required. The model name to use with the
          format `model=models/{model}`.

* **text**
    * **string**
        * Optional. The free-form input text that the model will turn into an
          embedding.

## Method: models.countMessageTokens

### Runs a model's tokenizer on a string and returns the token count.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:countMessageTokens
```

#### Path parameters

| Parameter | Type   | Description                                                                                                                                                                      |
|-----------|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| model     | string | Required. The model's resource name. This serves as an ID for the Model to use. This name should match a model name returned by the models.list method. Format: `models/{model}` |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "prompt": {
    "object": "MessagePrompt"
  }
}
```

#### Fields

* **prompt**
    * **object (MessagePrompt)**
        * Required. The prompt, whose token count is to be returned.

#### Response body

A response from models.countMessageTokens.

It returns the model's tokenCount for the prompt.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "tokenCount": "integer"
}
```

#### Fields

* **tokenCount**
    * **integer**
        * The number of tokens that the model tokenizes the prompt into.
        * Always non-negative.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

## Method: models.countTextTokens

### Runs a model's tokenizer on a text and returns the token count.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:countTextTokens
```

#### Path parameters

| Parameter | Type   | Description                                                                                                                                                                      |
|-----------|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| model     | string | Required. The model's resource name. This serves as an ID for the Model to use. This name should match a model name returned by the models.list method. Format: `models/{model}` |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "prompt": {
    "object": "TextPrompt"
  }
}
```

#### Fields

* **prompt**
    * **object (TextPrompt)**
        * Required. The free-form input text given to the model as a prompt.

#### Response body

A response from models.countTextTokens.

It returns the model's tokenCount for the prompt.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "tokenCount": "integer"
}
```

#### Fields

* **tokenCount**
    * **integer**
        * The number of tokens that the model tokenizes the prompt into.
        * Always non-negative.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

## Method: models.countTokens

### Runs a model's tokenizer on input content and returns the token count.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:countTokens
```

#### Path parameters

| Parameter | Type   | Description                                                                                                                                                                      |
|-----------|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| model     | string | Required. The model's resource name. This serves as an ID for the Model to use. This name should match a model name returned by the models.list method. Format: `models/{model}` |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "contents": [
    {
      "object": "Content"
    }
  ],
  "generateContentRequest": {
    "object": "GenerateContentRequest"
  }
}
```

#### Fields

* **contents[]**
    * **object (Content)**
        * Optional. The input given to the model as a prompt. This field is
          ignored when `generateContentRequest` is set.

* **generateContentRequest**
    * **object (GenerateContentRequest)**
        * Optional. The overall input given to the model. `models.countTokens`
          will count prompt, function calling, etc.

#### Response body

A response from models.countTokens.

It returns the model's tokenCount for the prompt.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "totalTokens": "integer"
}
```

#### Fields

* **totalTokens**
    * **integer**
        * The number of tokens that the model tokenizes the prompt into.
        * Always non-negative. When cachedContent is set, this is still the
          total effective prompt size. I.e. this includes the number of tokens
          in the cached content.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

### GenerateContentRequest

Request to generate a completion from the model.

#### JSON representation

```json
{
  "model": "string",
  "contents": [
    {
      "object": "Content"
    }
  ],
  "tools": [
    {
      "object": "Tool"
    }
  ],
  "toolConfig": {
    "object": "ToolConfig"
  },
  "safetySettings": [
    {
      "object": "SafetySetting"
    }
  ],
  "systemInstruction": {
    "object": "Content"
  },
  "generationConfig": {
    "object": "GenerationConfig"
  },
  "cachedContent": "string"
}
```

#### Fields

* **model**
    * **string**
        * Required. The name of the Model to use for generating the completion.
        * Format: `name=models/{model}`.

* **contents[]**
    * **object (Content)**
        * Required. The content of the current conversation with the model.
        * For single-turn queries, this is a single instance. For multi-turn
          queries, this is a repeated field that contains conversation history +
          latest request.

* **tools[]**
    * **object (Tool)**
        * Optional. A list of Tools the model may use to generate the next
          response.
        * A Tool is a piece of code that enables the system to interact with
          external systems to perform an action, or set of actions, outside of
          knowledge and scope of the model. The only supported tool is currently
          Function.

* **toolConfig**
    * **object (ToolConfig)**
        * Optional. Tool configuration for any Tool specified in the request.

* **safetySettings[]**
    * **object (SafetySetting)**
        * Optional. A list of unique SafetySetting instances for blocking unsafe
          content.
        * This will be enforced on the GenerateContentRequest.contents and
          GenerateContentResponse.candidates. There should not be more than one
          setting for each SafetyCategory type. The API will block any contents
          and responses that fail to meet the thresholds set by these settings.
          This list overrides the default settings for each SafetyCategory
          specified in the safetySettings. If there is no SafetySetting for a
          given SafetyCategory provided in the list, the API will use the
          default safety setting for that category. Harm categories
          HARM_CATEGORY_HATE_SPEECH, HARM_CATEGORY_SEXUALLY_EXPLICIT,
          HARM_CATEGORY_DANGEROUS_CONTENT, HARM_CATEGORY_HARASSMENT are
          supported.

* **systemInstruction**
    * **object (Content)**
        * Optional. Developer set system instruction. Currently, text only.

* **generationConfig**
    * **object (GenerationConfig)**
        * Optional. Configuration options for model generation and outputs.

* **cachedContent**
    * **string**
        * Optional. The name of the cached content used as context to serve the
          prediction. Note: only used in explicit caching, where users can have
          control over caching (e.g. what content to cache) and enjoy guaranteed
          cost savings. Format: `cachedContents/{cachedContent}`

## Method: models.embedContent

### Generates an embedding from the model given an input Content.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:embedContent
```

#### Path parameters

| Parameter | Type   | Description                                                                                                                                                                      |
|-----------|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| model     | string | Required. The model's resource name. This serves as an ID for the Model to use. This name should match a model name returned by the models.list method. Format: `models/{model}` |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "content": {
    "object": "Content"
  },
  "taskType": "enum (TaskType)",
  "title": "string",
  "outputDimensionality": "integer"
}
```

#### Fields

* **content**
    * **object (Content)**
        * Required. The content to embed. Only the `parts.text` fields will be
          counted.

* **taskType**
    * **enum (TaskType)**
        * Optional. Optional task type for which the embeddings will be used.
          Can only be set for models/embedding-001.

* **title**
    * **string**
        * Optional. An optional title for the text. Only applicable when
          TaskType is RETRIEVAL_DOCUMENT.
        * Note: Specifying a title for RETRIEVAL_DOCUMENT provides better
          quality embeddings for retrieval.

* **outputDimensionality**
    * **integer**
        * Optional. Optional reduced dimension for the output embedding. If set,
          excessive values in the output embedding are truncated from the end.
          Supported by newer models since 2024, and the earlier model (
          models/embedding-001) cannot specify this value.

#### Response body

The response to an EmbedContentRequest.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "embedding": {
    "object": "ContentEmbedding"
  }
}
```

#### Fields

* **embedding**
    * **object (ContentEmbedding)**
        * Output only. The embedding generated from the input content.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

## Method: models.embedText

### Generates an embedding from the model given an input message.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:embedText
```

#### Path parameters

| Parameter | Type   | Description                                                             |
|-----------|--------|-------------------------------------------------------------------------|
| model     | string | Required. The model name to use with the format `model=models/{model}`. |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "text": "string"
}
```

#### Fields

* **text**
    * **string**
        * Optional. The free-form input text that the model will turn into an
          embedding.

#### Response body

The response to a EmbedTextRequest.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "embedding": {
    "object": "Embedding"
  }
}
```

#### Fields

* **embedding**
    * **object (Embedding)**
        * Output only. The embedding generated from the input text.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

## Method: models.generateAnswer

### Generates a grounded answer from the model given an input GenerateAnswerRequest.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:generateAnswer
```

#### Path parameters

| Parameter | Type   | Description                                                                                                  |
|-----------|--------|--------------------------------------------------------------------------------------------------------------|
| model     | string | Required. The name of the Model to use for generating the grounded response. Format: `model=models/{model}`. |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "contents": [
    {
      "object": "Content"
    }
  ],
  "answerStyle": "enum (AnswerStyle)",
  "safetySettings": [
    {
      "object": "SafetySetting"
    }
  ],
  // Union field grounding_source can be only one of the following:
  "inlinePassages": {
    "object": "GroundingPassages"
  },
  "semanticRetriever": {
    "object": "SemanticRetrieverConfig"
  }
  // End of list of possible types for union field grounding_source.
  "temperature": "number"
}
```

#### Fields

* **contents[]**
    * **object (Content)**
        * Required. The content of the current conversation with the model. For
          single-turn queries, this is a single question to answer. For
          multi-turn queries, this is a repeated field that contains
          conversation history and the last Content in the list containing the
          question.
        * Note: models.generateAnswer currently only supports queries in
          English.

* **answerStyle**
    * **enum (AnswerStyle)**
        * Required. Style in which answers should be returned.

* **safetySettings[]**
    * **object (SafetySetting)**
        * Optional. A list of unique SafetySetting instances for blocking unsafe
          content.
        * This will be enforced on the GenerateAnswerRequest.contents and
          GenerateAnswerResponse.candidate. There should not be more than one
          setting for each SafetyCategory type. The API will block any contents
          and responses that fail to meet the thresholds set by these settings.
          This list overrides the default settings for each SafetyCategory
          specified in the safetySettings. If there is no SafetySetting for a
          given SafetyCategory provided in the list, the API will use the
          default safety setting for that category. Harm categories
          HARM_CATEGORY_HATE_SPEECH, HARM_CATEGORY_SEXUALLY_EXPLICIT,
          HARM_CATEGORY_DANGEROUS_CONTENT, HARM_CATEGORY_HARASSMENT are
          supported.

* **Union field grounding_source.** The sources in which to ground the
  answer. `grounding_source` can be only one of the following:
    * **inlinePassages**
        * **object (GroundingPassages)**
            * Passages provided inline with the request.

    * **semanticRetriever**
        * **object (SemanticRetrieverConfig)**
            * Content retrieved from resources created via the Semantic
              Retriever API.

* **temperature**
    * **number**
        * Optional. Controls the randomness of the output.
        * Values can range from [0.0,1.0], inclusive. A value closer to 1.0 will
          produce responses that are more varied and creative, while a value
          closer to 0.0 will typically result in more straightforward responses
          from the model. A low temperature (~0.2) is usually recommended for
          Attributed-Question-Answering use cases.

#### Response body

Response from the model for a grounded answer.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "answer": {
    "object": "Candidate"
  },
  "answerableProbability": "number",
  "inputFeedback": {
    "object": "InputFeedback"
  }
}
```

#### Fields

* **answer**
    * **object (Candidate)**
        * Candidate answer from the model.
        * Note: The model always attempts to provide a grounded answer, even
          when the answer is unlikely to be answerable from the given passages.
          In that case, a low-quality or ungrounded answer may be provided,
          along with a low answerableProbability.

* **answerableProbability**
    * **number**
        * Output only. The model's estimate of the probability that its answer
          is correct and grounded in the input passages.
        * A low answerableProbability indicates that the answer might not be
          grounded in the sources.
        * When answerableProbability is low, some clients may wish to:
            * Display a message to the effect of "We couldn’t answer that
              question" to the user.
            * Fall back to a general-purpose LLM that answers the question from
              world knowledge. The threshold and nature of such fallbacks will
              depend on individual clients’ use cases. 0.5 is a good starting
              threshold.

* **inputFeedback**
    * **object (InputFeedback)**
        * Output only. Feedback related to the input data used to answer the
          question, as opposed to model-generated response to the question.
        * "Input data" can be one or more of the following:
            * Question specified by the last entry in
              GenerateAnswerRequest.content
            * Conversation history specified by the other entries in
              GenerateAnswerRequest.content
            * Grounding sources (GenerateAnswerRequest.semantic_retriever or
              GenerateAnswerRequest.inline_passages)

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`

For more information, see the Authentication Overview.

### GroundingPassages

A repeated list of passages.

#### JSON representation

```json
{
  "passages": [
    {
      "object": "GroundingPassage"
    }
  ]
}
```

#### Fields

* **passages[]**
    * **object (GroundingPassage)**
        * List of passages.

### GroundingPassage

Passage included inline with a grounding configuration.

#### JSON representation

```json
{
  "id": "string",
  "content": {
    "object": "Content"
  }
}
```

#### Fields

* **id**
    * **string**
        * Identifier for the passage for attributing this passage in grounded
          answers.

* **content**
    * **object (Content)**
        * Content of the passage.

### SemanticRetrieverConfig

Configuration for retrieving grounding content from a Corpus or Document created
using the Semantic Retriever API.

#### JSON representation

```json
{
  "source": "string",
  "query": {
    "object": "Content"
  },
  "metadataFilters": [
    {
      "object": "MetadataFilter"
    }
  ],
  "maxChunksCount": "integer",
  "minimumRelevanceScore": "number"
}
```

#### Fields

* **source**
    * **string**
        * Required. Name of the resource for retrieval, e.g. `corpora/123`
          or `corpora/123/documents/abc`.

* **query**
    * **object (Content)**
        * Required. Query to use for similarity matching Chunks in the given
          resource.

* **metadataFilters[]**
    * **object (MetadataFilter)**
        * Optional. Filters for selecting Documents and/or Chunks from the
          resource.

* **maxChunksCount**
    * **integer**
        * Optional. Maximum number of relevant Chunks to retrieve.

* **minimumRelevanceScore**
    * **number**
        * Optional. Minimum relevance score for retrieved relevant Chunks.

### AnswerStyle

Style for grounded answers.

#### Enums

* **ANSWER_STYLE_UNSPECIFIED**
    * Unspecified answer style.

* **ABSTRACTIVE**
    * Succint but abstract style.

* **EXTRACTIVE**
    * Very brief and extractive style.

* **VERBOSE**
    * Verbose style including extra details. The response may be formatted as a
      sentence, paragraph, multiple paragraphs, or bullet points, etc.

### InputFeedback

Feedback related to the input data used to answer the question, as opposed to
model-generated response to the question.

#### JSON representation

```json
{
  "safetyRatings": [
    {
      "object": "SafetyRating"
    }
  ],
  "blockReason": "enum (BlockReason)"
}
```

#### Fields

* **safetyRatings[]**
    * **object (SafetyRating)**
        * Ratings for safety of the input. There is at most one rating per
          category.

* **blockReason**
    * **enum (BlockReason)**
        * Optional. If set, the input was blocked and no candidates are
          returned. Rephrase your input.

### BlockReason

Specifies what was the reason why input was blocked.

#### Enums

* **BLOCK_REASON_UNSPECIFIED**
    * Default value. This value is unused.

* **SAFETY**
    * Input was blocked due to safety reasons. You can inspect safetyRatings to
      understand which safety category blocked it.

* **OTHER**
    * Input was blocked due to other reasons.

## Method: models.generateContent

### Generates a response from the model given an input GenerateContentRequest.

Input capabilities differ between models, including tuned models. See the model
guide and tuning guide for details.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:generateContent
```

#### Path parameters

| Parameter | Type   | Description                                                                                          |
|-----------|--------|------------------------------------------------------------------------------------------------------|
| model     | string | Required. The name of the Model to use for generating the completion. Format: `name=models/{model}`. |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "contents": [
    {
      "object": "Content"
    }
  ],
  "tools": [
    {
      "object": "Tool"
    }
  ],
  "toolConfig": {
    "object": "ToolConfig"
  },
  "safetySettings": [
    {
      "object": "SafetySetting"
    }
  ],
  "systemInstruction": {
    "object": "Content"
  },
  "generationConfig": {
    "object": "GenerationConfig"
  },
  "cachedContent": "string"
}
```

#### Fields

* **contents[]**
    * **object (Content)**
        * Required. The content of the current conversation with the model.
        * For single-turn queries, this is a single instance. For multi-turn
          queries, this is a repeated field that contains conversation history +
          latest request.

* **tools[]**
    * **object (Tool)**
        * Optional. A list of Tools the model may use to generate the next
          response.
        * A Tool is a piece of code that enables the system to interact with
          external systems to perform an action, or set of actions, outside of
          knowledge and scope of the model. The only supported tool is currently
          Function.

* **toolConfig**
    * **object (ToolConfig)**
        * Optional. Tool configuration for any Tool specified in the request.

* **safetySettings[]**
    * **object (SafetySetting)**
        * Optional. A list of unique SafetySetting instances for blocking unsafe
          content.
        * This will be enforced on the GenerateContentRequest.contents and
          GenerateContentResponse.candidates. There should not be more than one
          setting for each SafetyCategory type. The API will block any contents
          and responses that fail to meet the thresholds set by these settings.
          This list overrides the default settings for each SafetyCategory
          specified in the safetySettings. If there is no SafetySetting for a
          given SafetyCategory provided in the list, the API will use the
          default safety setting for that category. Harm categories
          HARM_CATEGORY_HATE_SPEECH, HARM_CATEGORY_SEXUALLY_EXPLICIT,
          HARM_CATEGORY_DANGEROUS_CONTENT, HARM_CATEGORY_HARASSMENT are
          supported.

* **systemInstruction**
    * **object (Content)**
        * Optional. Developer set system instruction. Currently, text only.

* **generationConfig**
    * **object (GenerationConfig)**
        * Optional. Configuration options for model generation and outputs.

* **cachedContent**
    * **string**
        * Optional. The name of the cached content used as context to serve the
          prediction. Note: only used in explicit caching, where users can have
          control over caching (e.g. what content to cache) and enjoy guaranteed
          cost savings. Format: `cachedContents/{cachedContent}`

#### Response body

If successful, the response body contains an instance of
GenerateContentResponse.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

## Method: models.generateMessage

### Generates a response from the model given an input MessagePrompt.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:generateMessage
```

#### Path parameters

| Parameter | Type   | Description                                                            |
|-----------|--------|------------------------------------------------------------------------|
| model     | string | Required. The name of the model to use. Format: `name=models/{model}`. |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "prompt": {
    "object": "MessagePrompt"
  },
  "temperature": "number",
  "candidateCount": "integer",
  "topP": "number",
  "topK": "integer"
}
```

#### Fields

* **prompt**
    * **object (MessagePrompt)**
        * Required. The structured textual input given to the model as a prompt.
        * Given a prompt, the model will return what it predicts is the next
          message in the discussion.

* **temperature**
    * **number**
        * Optional. Controls the randomness of the output.
        * Values can range over [0.0,1.0], inclusive. A value closer to 1.0 will
          produce responses that are more varied, while a value closer to 0.0
          will typically result in less surprising responses from the model.

* **candidateCount**
    * **integer**
        * Optional. The number of generated response messages to return.
        * This value must be between [1, 8], inclusive. If unset, this will
          default to 1.

* **topP**
    * **number**
        * Optional. The maximum cumulative probability of tokens to consider
          when sampling.
        * The model uses combined Top-k and nucleus sampling.
        * Nucleus sampling considers the smallest set of tokens whose
          probability sum is at least topP.

* **topK**
    * **integer**
        * Optional. The maximum number of tokens to consider when sampling.
        * The model uses combined Top-k and nucleus sampling.
        * Top-k sampling considers the set of topK most probable tokens.

#### Response body

The response from the model.

This includes candidate messages and conversation history in the form of
chronologically-ordered messages.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "candidates": [
    {
      "object": "Message"
    }
  ],
  "messages": [
    {
      "object": "Message"
    }
  ],
  "filters": [
    {
      "object": "ContentFilter"
    }
  ]
}
```

#### Fields

* **candidates[]**
    * **object (Message)**
        * Candidate response messages from the model.

* **messages[]**
    * **object (Message)**
        * The conversation history used by the model.

* **filters[]**
    * **object (ContentFilter)**
        * A set of content filtering metadata for the prompt and response text.
        * This indicates which SafetyCategory(s) blocked a candidate from this
          response, the lowest HarmProbability that triggered a block, and the
          HarmThreshold setting for that category.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

## Method: models.generateText

### Generates a response from the model given an input message.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:generateText
```

#### Path parameters

| Parameter | Type   | Description                                                                                                                                                   |
|-----------|--------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| model     | string | Required. The name of the Model or TunedModel to use for generating the completion. Examples: `models/text-bison-001` `tunedModels/sentence-translator-u3b7m` |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "prompt": {
    "object": "TextPrompt"
  },
  "safetySettings": [
    {
      "object": "SafetySetting"
    }
  ],
  "stopSequences": [
    "string"
  ],
  "temperature": "number",
  "candidateCount": "integer",
  "maxOutputTokens": "integer",
  "topP": "number",
  "topK": "integer"
}
```

#### Fields

* **prompt**
    * **object (TextPrompt)**
        * Required. The free-form input text given to the model as a prompt.
        * Given a prompt, the model will generate a TextCompletion response it
          predicts as the completion of the input text.

* **safetySettings[]**
    * **object (SafetySetting)**
        * Optional. A list of unique SafetySetting instances for blocking unsafe
          content.
        * that will be enforced on the GenerateTextRequest.prompt and
          GenerateTextResponse.candidates. There should not be more than one
          setting for each SafetyCategory type. The API will block any prompts
          and responses that fail to meet the thresholds set by these settings.
          This list overrides the default settings for each SafetyCategory
          specified in the safetySettings. If there is no SafetySetting for a
          given SafetyCategory provided in the list, the API will use the
          default safety setting for that category. Harm categories
          HARM_CATEGORY_DEROGATORY, HARM_CATEGORY_TOXICITY,
          HARM_CATEGORY_VIOLENCE, HARM_CATEGORY_SEXUAL, HARM_CATEGORY_MEDICAL,
          HARM_CATEGORY_DANGEROUS are supported in text service.

* **stopSequences[]**
    * **string**
        * The set of character sequences (up to 5) that will stop output
          generation. If specified, the API will stop at the first appearance of
          a stop sequence. The stop sequence will not be included as part of the
          response.

* **temperature**
    * **number**
        * Optional. Controls the randomness of the output. Note: The default
          value varies by model, see the Model.temperature attribute of the
          Model returned the getModel function.
        * Values can range from [0.0,1.0], inclusive. A value closer to 1.0 will
          produce responses that are more varied and creative, while a value
          closer to 0.0 will typically result in more straightforward responses
          from the model.

* **candidateCount**
    * **integer**
        * Optional. Number of generated responses to return.
        * This value must be between [1, 8], inclusive. If unset, this will
          default to 1.

* **maxOutputTokens**
    * **integer**
        * Optional. The maximum number of tokens to include in a candidate.
        * If unset, this will default to outputTokenLimit specified in the Model
          specification.

* **topP**
    * **number**
        * Optional. The maximum cumulative probability of tokens to consider
          when sampling.
        * The model uses combined Top-k and nucleus sampling.
        * Tokens are sorted based on their assigned probabilities so that only
          the most likely tokens are considered. Top-k sampling directly limits
          the maximum number of tokens to consider, while Nucleus sampling
          limits number of tokens based on the cumulative probability.
        * Note: The default value varies by model, see the Model.top_p attribute
          of the Model returned the getModel function.

* **topK**
    * **integer**
        * Optional. The maximum number of tokens to consider when sampling.
        * The model uses combined Top-k and nucleus sampling.
        * Top-k sampling considers the set of topK most probable tokens.
          Defaults to 40.
        * Note: The default value varies by model, see the Model.top_k attribute
          of the Model returned the getModel function.

#### Response body

If successful, the response body contains an instance of GenerateTextResponse.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

## Method: models.get

### Gets information about a specific Model.

#### HTTP request

```
GET https://generativelanguage.googleapis.com/v1beta/{name=models/*}
```

#### Path parameters

| Parameter | Type   | Description                                                                                                                                |
|-----------|--------|--------------------------------------------------------------------------------------------------------------------------------------------|
| name      | string | Required. The resource name of the model. This name should match a model name returned by the models.list method. Format: `models/{model}` |

#### Request body

The request body must be empty.

#### Response body

If successful, the response body contains an instance of Model.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

## Method: models.list

### Lists models available through the API.

#### HTTP request

```
GET https://generativelanguage.googleapis.com/v1beta/models
```

#### Query parameters

| Parameter | Type    | Description                                                                                                                                                                                                                                                                          |
|-----------|---------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pageSize  | integer | The maximum number of Models to return (per page). The service may return fewer models. If unspecified, at most 50 models will be returned per page. This method returns at most 1000 models per page, even if you pass a larger pageSize.                                           |
| pageToken | string  | A page token, received from a previous models.list call. Provide the pageToken returned by one request as an argument to the next request to retrieve the next page. When paginating, all other parameters provided to models.list must match the call that provided the page token. |

#### Request body

The request body must be empty.

#### Response body

Response from ListModel containing a paginated list of Models.

If successful, the response body contains data with the following structure:

#### JSON representation

```json
{
  "models": [
    {
      "object": "Model"
    }
  ],
  "nextPageToken": "string"
}
```

#### Fields

* **models[]**
    * **object (Model)**
        * The returned Models.

* **nextPageToken**
    * **string**
        * A token, which can be sent as `pageToken` to retrieve the next page.
        * If this field is omitted, there are no more pages.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

## Method: models.streamGenerateContent

### Generates a streamed response from the model given an input GenerateContentRequest.

#### HTTP request

```
POST https://generativelanguage.googleapis.com/v1beta/{model=models/*}:streamGenerateContent
```

#### Path parameters

| Parameter | Type   | Description                                                                                          |
|-----------|--------|------------------------------------------------------------------------------------------------------|
| model     | string | Required. The name of the Model to use for generating the completion. Format: `name=models/{model}`. |

#### Request body

The request body contains data with the following structure:

#### JSON representation

```json
{
  "contents": [
    {
      "object": "Content"
    }
  ],
  "tools": [
    {
      "object": "Tool"
    }
  ],
  "toolConfig": {
    "object": "ToolConfig"
  },
  "safetySettings": [
    {
      "object": "SafetySetting"
    }
  ],
  "systemInstruction": {
    "object": "Content"
  },
  "generationConfig": {
    "object": "GenerationConfig"
  },
  "cachedContent": "string"
}
```

#### Fields

* **contents[]**
    * **object (Content)**
        * Required. The content of the current conversation with the model.
        * For single-turn queries, this is a single instance. For multi-turn
          queries, this is a repeated field that contains conversation history +
          latest request.

* **tools[]**
    * **object (Tool)**
        * Optional. A list of Tools the model may use to generate the next
          response.
        * A Tool is a piece of code that enables the system to interact with
          external systems to perform an action, or set of actions, outside of
          knowledge and scope of the model. The only supported tool is currently
          Function.

* **toolConfig**
    * **object (ToolConfig)**
        * Optional. Tool configuration for any Tool specified in the request.

* **safetySettings[]**
    * **object (SafetySetting)**
        * Optional. A list of unique SafetySetting instances for blocking unsafe
          content.
        * This will be enforced on the GenerateContentRequest.contents and
          GenerateContentResponse.candidates. There should not be more than one
          setting for each SafetyCategory type. The API will block any contents
          and responses that fail to meet the thresholds set by these settings.
          This list overrides the default settings for each SafetyCategory
          specified in the safetySettings. If there is no SafetySetting for a
          given SafetyCategory provided in the list, the API will use the
          default safety setting for that category. Harm categories
          HARM_CATEGORY_HATE_SPEECH, HARM_CATEGORY_SEXUALLY_EXPLICIT,
          HARM_CATEGORY_DANGEROUS_CONTENT, HARM_CATEGORY_HARASSMENT are
          supported.

* **systemInstruction**
    * **object (Content)**
        * Optional. Developer set system instruction. Currently, text only.

* **generationConfig**
    * **object (GenerationConfig)**
        * Optional. Configuration options for model generation and outputs.

* **cachedContent**
    * **string**
        * Optional. The name of the cached content used as context to serve the
          prediction. Note: only used in explicit caching, where users can have
          control over caching (e.g. what content to cache) and enjoy guaranteed
          cost savings. Format: `cachedContents/{cachedContent}`

#### Response body

If successful, the response body contains a stream of GenerateContentResponse
instances.

#### Authorization scopes

Requires one of the following OAuth scopes:

* `https://www.googleapis.com/auth/generative-language`
* `https://www.googleapis.com/auth/generative-language.tuning`
* `https://www.googleapis.com/auth/generative-language.tuning.readonly`
* `https://www.googleapis.com/auth/generative-language.retriever`
* `https://www.googleapis.com/auth/generative-language.retriever.readonly`

For more information, see the Authentication Overview.

# Types

## Candidate

#### A response candidate generated from the model.

#### JSON representation

```json
{
  "content": {
    "object": "Content"
  },
  "finishReason": "enum (FinishReason)",
  "safetyRatings": [
    {
      "object": "SafetyRating"
    }
  ],
  "citationMetadata": {
    "object": "CitationMetadata"
  },
  "tokenCount": "integer",
  "groundingAttributions": [
    {
      "object": "GroundingAttribution"
    }
  ],
  "index": "integer"
}
```

#### Fields

* **content**
    * **object (Content)**
        * Output only. Generated content returned from the model.

* **finishReason**
    * **enum (FinishReason)**
        * Optional. Output only. The reason why the model stopped generating
          tokens.
        * If empty, the model has not stopped generating the tokens.

* **safetyRatings[]**
    * **object (SafetyRating)**
        * List of ratings for the safety of a response candidate.
        * There is at most one rating per category.

* **citationMetadata**
    * **object (CitationMetadata)**
        * Output only. Citation information for model-generated candidate.
        * This field may be populated with recitation information for any text
          included in the content. These are passages that are "recited" from
          copyrighted material in the foundational LLM's training data.

* **tokenCount**
    * **integer**
        * Output only. Token count for this candidate.

* **groundingAttributions[]**
    * **object (GroundingAttribution)**
        * Output only. Attribution information for sources that contributed to a
          grounded answer.
        * This field is populated for GenerateAnswer calls.

* **index**
    * **integer**
        * Output only. Index of the candidate in the list of candidates.

### FinishReason

Defines the reason why the model stopped generating tokens.

#### Enums

* **FINISH_REASON_UNSPECIFIED**
    * Default value. This value is unused.

* **STOP**
    * Natural stop point of the model or provided stop sequence.

* **MAX_TOKENS**
    * The maximum number of tokens as specified in the request was reached.

* **SAFETY**
    * The candidate content was flagged for safety reasons.

* **RECITATION**
    * The candidate content was flagged for recitation reasons.

* **OTHER**
    * Unknown reason.

### GroundingAttribution

Attribution for a source that contributed to an answer.

#### JSON representation

```json
{
  "sourceId": {
    "object": "AttributionSourceId"
  },
  "content": {
    "object": "Content"
  }
}
```

#### Fields

* **sourceId**
    * **object (AttributionSourceId)**
        * Output only. Identifier for the source contributing to this
          attribution.

* **content**
    * **object (Content)**
        * Grounding source content that makes up this attribution.

### AttributionSourceId

Identifier for the source contributing to this attribution.

#### JSON representation

```json
{
  // Union field source can be only one of the following:
  "groundingPassage": {
    "object": "GroundingPassageId"
  },
  "semanticRetrieverChunk": {
    "object": "SemanticRetrieverChunk"
  }
  // End of list of possible types for union field source.
}
```

#### Fields

* **Union field source.**

    * **source** can be only one of the following:

        * **groundingPassage**
            * **object (GroundingPassageId)**
                * Identifier for an inline passage.

        * **semanticRetrieverChunk**
            * **object (SemanticRetrieverChunk)**
                * Identifier for a Chunk fetched via Semantic Retriever.

### GroundingPassageId

Identifier for a part within a GroundingPassage.

#### JSON representation

```json
{
  "passageId": "string",
  "partIndex": "integer"
}
```

#### Fields

* **passageId**
    * **string**
        * Output only. ID of the passage matching the GenerateAnswerRequest's
          GroundingPassage.id.

* **partIndex**
    * **integer**
        * Output only. Index of the part within the GenerateAnswerRequest's
          GroundingPassage.content.

### SemanticRetrieverChunk

Identifier for a Chunk retrieved via Semantic Retriever specified in the
GenerateAnswerRequest using SemanticRetrieverConfig.

#### JSON representation

```json
{
  "source": "string",
  "chunk": "string"
}
```

#### Fields

* **source**
    * **string**
        * Output only. Name of the source matching the request's
          SemanticRetrieverConfig.source. Example: `corpora/123`
          or `corpora/123/documents/abc`

* **chunk**
    * **string**
        * Output only. Name of the Chunk containing the attributed text.
          Example: `corpora/123/documents/abc/chunks/xyz`

## CitationMetadata

### A collection of source attributions for a piece of content.

#### JSON representation

```json
{
  "citationSources": [
    {
      "object": "CitationSource"
    }
  ]
}
```

#### Fields

* **citationSources[]**
    * **object (CitationSource)**
        * Citations to sources for a specific response.

### CitationSource

A citation to a source for a portion of a specific response.

#### JSON representation

```json
{
  "startIndex": "integer",
  "endIndex": "integer",
  "uri": "string",
  "license": "string"
}
```

#### Fields

* **startIndex**
    * **integer**
        * Optional. Start of segment of the response that is attributed to this
          source.
        * Index indicates the start of the segment, measured in bytes.

* **endIndex**
    * **integer**
        * Optional. End of the attributed segment, exclusive.

* **uri**
    * **string**
        * Optional. URI that is attributed as a source for a portion of the
          text.

* **license**
    * **string**
        * Optional. License for the GitHub project that is attributed as a
          source for segment.
        * License info is required for code citations.

## ContentEmbedding

### A list of floats representing an embedding.

#### JSON representation

```json
{
  "values": [
    "number"
  ]
}
```

#### Fields

* **values[]**
    * **number**
        * The embedding values.

## ContentFilter

### Content filtering metadata associated with processing a single request.

ContentFilter contains a reason and an optional supporting string. The reason
may be unspecified.

#### JSON representation

```json
{
  "reason": "enum (BlockedReason)",
  "message": "string"
}
```

#### Fields

* **reason**
    * **enum (BlockedReason)**
        * The reason content was blocked during request processing.

* **message**
    * **string**
        * A string that describes the filtering behavior in more detail.

### BlockedReason

A list of reasons why content may have been blocked.

#### Enums

* **BLOCKED_REASON_UNSPECIFIED**
    * A blocked reason was not specified.

* **SAFETY**
    * Content was blocked by safety settings.

* **OTHER**
    * Content was blocked, but the reason is uncategorized.

## CustomMetadata

### User provided metadata stored as key-value pairs.

#### JSON representation

```json
{
  "key": "string",
  // Union field value can be only one of the following:
  "stringValue": "string",
  "stringListValue": {
    "object": "StringList"
  },
  "numericValue": "number"
  // End of list of possible types for union field value.
}
```

#### Fields

* **key**
    * **string**
        * Required. The key of the metadata to store.

* **Union field value.**

    * **value** can be only one of the following:

        * **stringValue**
            * **string**
                * The string value of the metadata to store.

        * **stringListValue**
            * **object (StringList)**
                * The StringList value of the metadata to store.

        * **numericValue**
            * **number**
                * The numeric value of the metadata to store.

### StringList

User provided string values assigned to a single metadata key.

#### JSON representation

```json
{
  "values": [
    "string"
  ]
}
```

#### Fields

* **values[]**
    * **string**
        * The string values of the metadata to store.

## Embedding

### A list of floats representing the embedding.

#### JSON representation

```json
{
  "value": [
    "number"
  ]
}
```

#### Fields

* **value[]**
    * **number**
        * The embedding values.

## GenerateContentResponse

### Response from the model supporting multiple candidates.

Note on safety ratings and content filtering. They are reported for both prompt
in GenerateContentResponse.prompt_feedback and for each candidate in
finishReason and in safetyRatings. The API contract is that:

- either all requested candidates are returned or no candidates at all
- no candidates are returned only if there was something wrong with the prompt (
  see promptFeedback)
- feedback on each candidate is reported on finishReason and safetyRatings.

#### JSON representation

```json
{
  "candidates": [
    {
      "object": "Candidate"
    }
  ],
  "promptFeedback": {
    "object": "PromptFeedback"
  },
  "usageMetadata": {
    "object": "UsageMetadata"
  }
}
```

#### Fields

* **candidates[]**
    * **object (Candidate)**
        * Candidate responses from the model.

* **promptFeedback**
    * **object (PromptFeedback)**
        * Returns the prompt's feedback related to the content filters.

* **usageMetadata**
    * **object (UsageMetadata)**
        * Output only. Metadata on the generation requests' token usage.

### PromptFeedback

A set of the feedback metadata the prompt specified in
GenerateContentRequest.content.

#### JSON representation

```json
{
  "blockReason": "enum (BlockReason)",
  "safetyRatings": [
    {
      "object": "SafetyRating"
    }
  ]
}
```

#### Fields

* **blockReason**
    * **enum (BlockReason)**
        * Optional. If set, the prompt was blocked and no candidates are
          returned. Rephrase your prompt.

* **safetyRatings[]**
    * **object (SafetyRating)**
        * Ratings for safety of the prompt. There is at most one rating per
          category.

### BlockReason

Specifies what was the reason why prompt was blocked.

#### Enums

* **BLOCK_REASON_UNSPECIFIED**
    * Default value. This value is unused.

* **SAFETY**
    * Prompt was blocked due to safety reasons. You can inspect safetyRatings to
      understand which safety category blocked it.

* **OTHER**
    * Prompt was blocked due to unknown reasons.

### UsageMetadata

Metadata on the generation request's token usage.

#### JSON representation

```json
{
  "promptTokenCount": "integer",
  "cachedContentTokenCount": "integer",
  "candidatesTokenCount": "integer",
  "totalTokenCount": "integer"
}
```

#### Fields

* **promptTokenCount**
    * **integer**
        * Number of tokens in the prompt. When cachedContent is set, this is
          still the total effective prompt size. I.e. this includes the number
          of tokens in the cached content.

* **cachedContentTokenCount**
    * **integer**
        * Number of tokens in the cached part of the prompt, i.e. in the cached
          content.

* **candidatesTokenCount**
    * **integer**
        * Total number of tokens across the generated candidates.

* **totalTokenCount**
    * **integer**
        * Total token count for the generation request (prompt + candidates).

## GenerationConfig

### Configuration options for model generation and outputs. Not all parameters may be configurable for every model.

#### JSON representation

```json
{
  "stopSequences": [
    "string"
  ],
  "responseMimeType": "string",
  "responseSchema": {
    "object": "Schema"
  },
  "candidateCount": "integer",
  "maxOutputTokens": "integer",
  "temperature": "number",
  "topP": "number",
  "topK": "integer"
}
```

#### Fields

* **stopSequences[]**
    * **string**
        * Optional. The set of character sequences (up to 5) that will stop
          output generation. If specified, the API will stop at the first
          appearance of a stop sequence. The stop sequence will not be included
          as part of the response.

* **responseMimeType**
    * **string**
        * Optional. Output response mimetype of the generated candidate text.
          Supported mimetype: `text/plain`: (default) Text
          output. `application/json`: JSON response in the candidates.

* **responseSchema**
    * **object (Schema)**
        * Optional. Output response schema of the generated candidate text when
          response mime type can have schema. Schema can be objects, primitives
          or arrays and is a subset of OpenAPI schema.
        * If set, a compatible responseMimeType must also be set. Compatible
          mimetypes: `application/json`: Schema for JSON response.

* **candidateCount**
    * **integer**
        * Optional. Number of generated responses to return.
        * Currently, this value can only be set to 1. If unset, this will
          default to 1.

* **maxOutputTokens**
    * **integer**
        * Optional. The maximum number of tokens to include in a candidate.
        * Note: The default value varies by model, see the
          Model.output_token_limit attribute of the Model returned from the
          getModel function.

* **temperature**
    * **number**
        * Optional. Controls the randomness of the output.
        * Note: The default value varies by model, see the Model.temperature
          attribute of the Model returned from the getModel function.
        * Values can range from [0.0, 2.0].

* **topP**
    * **number**
        * Optional. The maximum cumulative probability of tokens to consider
          when sampling.
        * The model uses combined Top-k and nucleus sampling.
        * Tokens are sorted based on their assigned probabilities so that only
          the most likely tokens are considered. Top-k sampling directly limits
          the maximum number of tokens to consider, while Nucleus sampling
          limits number of tokens based on the cumulative probability.
        * Note: The default value varies by model, see the Model.top_p attribute
          of the Model returned from the getModel function.

* **topK**
    * **integer**
        * Optional. The maximum number of tokens to consider when sampling.
        * Models use nucleus sampling or combined Top-k and nucleus sampling.
          Top-k sampling considers the set of topK most probable tokens. Models
          running with nucleus sampling don't allow topK setting.
        * Note: The default value varies by model, see the Model.top_k attribute
          of the Model returned from the getModel function. Empty topK field in
          Model indicates the model doesn't apply top-k sampling and doesn't
          allow setting topK on requests.

## HarmCategory

### The category of a rating.

These categories cover various kinds of harms that developers may wish to
adjust.

#### Enums

* **HARM_CATEGORY_UNSPECIFIED**
    * Category is unspecified.

* **HARM_CATEGORY_DEROGATORY**
    * Negative or harmful comments targeting identity and/or protected
      attribute.

* **HARM_CATEGORY_TOXICITY**
    * Content that is rude, disrespectful, or profane.

* **HARM_CATEGORY_VIOLENCE**
    * Describes scenarios depicting violence against an individual or group, or
      general descriptions of gore.

* **HARM_CATEGORY_SEXUAL**
    * Contains references to sexual acts or other lewd content.

* **HARM_CATEGORY_MEDICAL**
    * Promotes unchecked medical advice.

* **HARM_CATEGORY_DANGEROUS**
    * Dangerous content that promotes, facilitates, or encourages harmful acts.

* **HARM_CATEGORY_HARASSMENT**
    * Harasment content.

* **HARM_CATEGORY_HATE_SPEECH**
    * Hate speech and content.

* **HARM_CATEGORY_SEXUALLY_EXPLICIT**
    * Sexually explicit content.

* **HARM_CATEGORY_DANGEROUS_CONTENT**
    * Dangerous content.

## ListPermissionsResponse

### Response from ListPermissions containing a paginated list of permissions.

#### JSON representation

```json
{
  "permissions": [
    {
      "object": "Permission"
    }
  ],
  "nextPageToken": "string"
}
```

#### Fields

* **permissions[]**
    * **object (Permission)**
        * Returned permissions.

* **nextPageToken**
    * **string**
        * A token, which can be sent as `pageToken` to retrieve the next page.
        * If this field is omitted, there are no more pages.

## MetadataFilter

### User provided filter to limit retrieval based on Chunk or Document level metadata values.

Example (genre = drama OR genre = action):
`key = "document.custom_metadata.genre"`
`conditions = [{stringValue = "drama", operation = EQUAL}, {stringValue = "action", operation = EQUAL}]`

#### JSON representation

```json
{
  "key": "string",
  "conditions": [
    {
      "object": "Condition"
    }
  ]
}
```

#### Fields

* **key**
    * **string**
        * Required. The key of the metadata to filter on.

* **conditions[]**
    * **object (Condition)**
        * Required. The Conditions for the given key that will trigger this
          filter. Multiple Conditions are joined by logical ORs.

### Condition

Filter condition applicable to a single key.

#### JSON representation

```json
{
  "operation": "enum (Operator)",
  // Union field value can be only one of the following:
  "stringValue": "string",
  "numericValue": "number"
  // End of list of possible types for union field value.
}
```

#### Fields

* **operation**
    * **enum (Operator)**
        * Required. Operator applied to the given key-value pair to trigger the
          condition.

* **Union field value.** The value type must be consistent with the value type
  defined in the field for the corresponding key. If the value types are not
  consistent, the result will be an empty set. When the CustomMetadata has a
  StringList value type, the filtering condition should use `string_value`
  paired with an INCLUDES/EXCLUDES operation, otherwise the result will also be
  an empty set.
    * **value** can be only one of the following:
        * **stringValue**
            * **string**
                * The string value to filter the metadata on.

        * **numericValue**
            * **number**
                * The numeric value to filter the metadata on.

### Operator

Defines the valid operators that can be applied to a key-value pair.

#### Enums

* **OPERATOR_UNSPECIFIED**
    * The default value. This value is unused.

* **LESS**
    * Supported by numeric.

* **LESS_EQUAL**
    * Supported by numeric.

* **EQUAL**
    * Supported by numeric & string.

* **GREATER_EQUAL**
    * Supported by numeric.

* **GREATER**
    * Supported by numeric.

* **NOT_EQUAL**
    * Supported by numeric & string.

* **INCLUDES**
    * Supported by string only when CustomMetadata value type for the given key
      has a stringListValue.

* **EXCLUDES**
    * Supported by string only when CustomMetadata value type for the given key
      has a stringListValue.

## RelevantChunk

### The information for a chunk relevant to a query.

#### JSON representation

```json
{
  "chunkRelevanceScore": "number",
  "chunk": {
    "object": "Chunk"
  }
}
```

#### Fields

* **chunkRelevanceScore**
    * **number**
        * Chunk relevance to the query.

* **chunk**
    * **object (Chunk)**
        * Chunk associated with the query.

## Chunk

A Chunk is a subpart of a Document that is treated as an independent unit for
the purposes of vector representation and storage. A Corpus can have a maximum
of 1 million Chunks.

#### JSON representation

```json
{
  "name": "string",
  "data": {
    "object": "ChunkData"
  },
  "customMetadata": [
    {
      "object": "CustomMetadata"
    }
  ],
  "createTime": "string",
  "updateTime": "string",
  "state": "enum (State)"
}
```

#### Fields

* **name**
    * **string**
        * Immutable. Identifier. The Chunk resource name. The ID (name excluding
          the "corpora/*/documents/*/chunks/" prefix) can contain up to 40
          characters that are lowercase alphanumeric or dashes (-). The ID
          cannot start or end with a dash. If the name is empty on create, a
          random 12-character unique ID will be generated.
          Example:
          `corpora/{corpus_id}/documents/{document_id}/chunks/123a456b789c`

* **data**
    * **object (ChunkData)**
        * Required. The content for the Chunk, such as the text string. The
          maximum number of tokens per chunk is 2043.

* **customMetadata[]**
    * **object (CustomMetadata)**
        * Optional. User provided custom metadata stored as key-value pairs. The
          maximum number of CustomMetadata per chunk is 20.

* **createTime**
    * **string (Timestamp format)**
        * Output only. The Timestamp of when the Chunk was created.
        * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond resolution
          and up to nine fractional digits. Examples: "2014-10-02T15:01:23Z"
          and "2014-10-02T15:01:23.045123456Z".

* **updateTime**
    * **string (Timestamp format)**
        * Output only. The Timestamp of when the Chunk was last updated.
        * A timestamp in RFC3339 UTC "Zulu" format, with nanosecond resolution
          and up to nine fractional digits. Examples: "2014-10-02T15:01:23Z"
          and "2014-10-02T15:01:23.045123456Z".

* **state**
    * **enum (State)**
        * Output only. Current state of the Chunk.

### ChunkData

Extracted data that represents the Chunk content.

#### JSON representation

```json
{
  // Union field data can be only one of the following:
  "stringValue": "string"
  // End of list of possible types for union field data.
}
```

#### Fields

* **Union field data.**

    * **data** can be only one of the following:

        * **stringValue**
            * **string**
                * The Chunk content as a string. The maximum number of tokens
                  per chunk is 2043.

## State

States for the lifecycle of a Chunk.

#### Enums

* **STATE_UNSPECIFIED**
    * The default value. This value is used if the state is omitted.

* **STATE_PENDING_PROCESSING**
    * Chunk is being processed (embedding and vector storage).

* **STATE_ACTIVE**
    * Chunk is processed and available for querying.

* **STATE_FAILED**
    * Chunk failed processing.

## SafetyRating

### Safety rating for a piece of content.

The safety rating contains the category of harm and the harm probability level
in that category for a piece of content. Content is classified for safety across
a number of harm categories and the probability of the harm classification is
included here.

#### JSON representation

```json
{
  "category": "enum (HarmCategory)",
  "probability": "enum (HarmProbability)",
  "blocked": "boolean"
}
```

#### Fields

* **category**
    * **enum (HarmCategory)**
        * Required. The category for this rating.

* **probability**
    * **enum (HarmProbability)**
        * Required. The probability of harm for this content.

* **blocked**
    * **boolean**
        * Was this content blocked because of this rating?

## HarmProbability

The probability that a piece of content is harmful.

The classification system gives the probability of the content being unsafe.
This does not indicate the severity of harm for a piece of content.

#### Enums

* **HARM_PROBABILITY_UNSPECIFIED**
    * Probability is unspecified.

* **NEGLIGIBLE**
    * Content has a negligible chance of being unsafe.

* **LOW**
    * Content has a low chance of being unsafe.

* **MEDIUM**
    * Content has a medium chance of being unsafe.

* **HIGH**
    * Content has a high chance of being unsafe.

## SafetySetting

### Safety setting, affecting the safety-blocking behavior.

Passing a safety setting for a category changes the allowed probability that
content is blocked.

#### JSON representation

```json
{
  "category": "enum (HarmCategory)",
  "threshold": "enum (HarmBlockThreshold)"
}
```

#### Fields

* **category**
    * **enum (HarmCategory)**
        * Required. The category for this setting.

* **threshold**
    * **enum (HarmBlockThreshold)**
        * Required. Controls the probability threshold at which harm is blocked.

## HarmBlockThreshold

Block at and beyond a specified harm probability.

#### Enums

* **HARM_BLOCK_THRESHOLD_UNSPECIFIED**
    * Threshold is unspecified.

* **BLOCK_LOW_AND_ABOVE**
    * Content with NEGLIGIBLE will be allowed.

* **BLOCK_MEDIUM_AND_ABOVE**
    * Content with NEGLIGIBLE and LOW will be allowed.

* **BLOCK_ONLY_HIGH**
    * Content with NEGLIGIBLE, LOW, and MEDIUM will be allowed.

* **BLOCK_NONE**
    * All content will be allowed.

## TaskType

### Type of task for which the embedding will be used.

#### Enums

* **TASK_TYPE_UNSPECIFIED**
    * Unset value, which will default to one of the other enum values.

* **RETRIEVAL_QUERY**
    * Specifies the given text is a query in a search/retrieval setting.

* **RETRIEVAL_DOCUMENT**
    * Specifies the given text is a document from the corpus being searched.

* **SEMANTIC_SIMILARITY**
    * Specifies the given text will be used for STS.

* **CLASSIFICATION**
    * Specifies that the given text will be classified.

* **CLUSTERING**
    * Specifies that the embeddings will be used for clustering.

* **QUESTION_ANSWERING**
    * Specifies that the given text will be used for question answering.

* **FACT_VERIFICATION**
    * Specifies that the given text will be used for fact verification.
