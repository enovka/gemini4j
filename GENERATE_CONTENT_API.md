## Method: models.generateContent

Generates a response from the model given an input `GenerateContentRequest`.

Input capabilities differ between models, including tuned models. See
the [model guide](https://ai.google.dev/models/gemini)
and [tuning guide](https://ai.google.dev/docs/model_tuning_guidance) for
details.

### Endpoint

POST `https://generativelanguage.googleapis.com/v1beta/{model=models/*}:generateContent`

### Path parameters

| Parameter | Type   | Description                                                                                                                                |
|-----------|--------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `model`   | string | Required. The name of the `Model` to use for generating the completion. Format: `name=models/{model}`. It takes the form `models/{model}`. |

### Request body

The request body contains data with the following structure:

| Field               | Type                                                                            | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
|---------------------|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `contents[]`        | `[Content](https://ai.google.dev/api/rest/v1beta/cachedContents#Content)`       | Required. The content of the current conversation with the model. For single-turn queries, this is a single instance. For multi-turn queries, this is a repeated field that contains conversation history + latest request.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| `tools[]`           | `[Tool](https://ai.google.dev/api/rest/v1beta/cachedContents#Tool)`             | Optional. A list of `Tools` the model may use to generate the next response. A `Tool` is a piece of code that enables the system to interact with external systems to perform an action, or set of actions, outside of knowledge and scope of the model. The only supported tool is currently `Function`.                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `toolConfig`        | `[ToolConfig](https://ai.google.dev/api/rest/v1beta/cachedContents#ToolConfig)` | Optional. Tool configuration for any `Tool` specified in the request.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| `safetySettings[]`  | `[SafetySetting](https://ai.google.dev/api/rest/v1beta/SafetySetting)`          | Optional. A list of unique `SafetySetting` instances for blocking unsafe content. This will be enforced on the `GenerateContentRequest.contents` and `GenerateContentResponse.candidates`. There should not be more than one setting for each `SafetyCategory` type. The API will block any contents and responses that fail to meet the thresholds set by these settings. This list overrides the default settings for each `SafetyCategory` specified in the safetySettings. If there is no `SafetySetting` for a given `SafetyCategory` provided in the list, the API will use the default safety setting for that category. Harm categories HARM_CATEGORY_HATE_SPEECH, HARM_CATEGORY_SEXUALLY_EXPLICIT, HARM_CATEGORY_DANGEROUS_CONTENT, HARM_CATEGORY_HARASSMENT are supported. |
| `systemInstruction` | `[Content](https://ai.google.dev/api/rest/v1beta/cachedContents#Content)`       | Optional. Developer set system instruction. Currently, text only.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `generationConfig`  | `[GenerationConfig](https://ai.google.dev/api/rest/v1beta/GenerationConfig)`    | Optional. Configuration options for model generation and outputs.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `cachedContent`     | string                                                                          | Optional. The name of the cached content used as context to serve the prediction. Note: only used in explicit caching, where users can have control over caching (e.g. what content to cache) and enjoy guaranteed cost savings. Format: `cachedContents/{cachedContent}`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 

## Response body

If successful, the response body contains an instance
of `GenerateContentResponse`.

## Method: models.streamGenerateContent

Generates a streamed response from the model given an
input `GenerateContentRequest`.

### Endpoint

POST `https://generativelanguage.googleapis.com/v1beta/{model=models/*}:streamGenerateContent`

### Path parameters

| Parameter | Type   | Description                                                                                                                                |
|-----------|--------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `model`   | string | Required. The name of the `Model` to use for generating the completion. Format: `name=models/{model}`. It takes the form `models/{model}`. |

### Request body

The request body contains data with the following structure:

| Field               | Type                                                                            | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
|---------------------|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `contents[]`        | `[Content](https://ai.google.dev/api/rest/v1beta/cachedContents#Content)`       | Required. The content of the current conversation with the model. For single-turn queries, this is a single instance. For multi-turn queries, this is a repeated field that contains conversation history + latest request.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| `tools[]`           | `[Tool](https://ai.google.dev/api/rest/v1beta/cachedContents#Tool)`             | Optional. A list of `Tools` the model may use to generate the next response. A `Tool` is a piece of code that enables the system to interact with external systems to perform an action, or set of actions, outside of knowledge and scope of the model. The only supported tool is currently `Function`.                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `toolConfig`        | `[ToolConfig](https://ai.google.dev/api/rest/v1beta/cachedContents#ToolConfig)` | Optional. Tool configuration for any `Tool` specified in the request.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| `safetySettings[]`  | `[SafetySetting](https://ai.google.dev/api/rest/v1beta/SafetySetting)`          | Optional. A list of unique `SafetySetting` instances for blocking unsafe content. This will be enforced on the `GenerateContentRequest.contents` and `GenerateContentResponse.candidates`. There should not be more than one setting for each `SafetyCategory` type. The API will block any contents and responses that fail to meet the thresholds set by these settings. This list overrides the default settings for each `SafetyCategory` specified in the safetySettings. If there is no `SafetySetting` for a given `SafetyCategory` provided in the list, the API will use the default safety setting for that category. Harm categories HARM_CATEGORY_HATE_SPEECH, HARM_CATEGORY_SEXUALLY_EXPLICIT, HARM_CATEGORY_DANGEROUS_CONTENT, HARM_CATEGORY_HARASSMENT are supported. |
| `systemInstruction` | `[Content](https://ai.google.dev/api/rest/v1beta/cachedContents#Content)`       | Optional. Developer set system instruction. Currently, text only.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `generationConfig`  | `[GenerationConfig](https://ai.google.dev/api/rest/v1beta/GenerationConfig)`    | Optional. Configuration options for model generation and outputs.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `cachedContent`     | string                                                                          | Optional. The name of the cached content used as context to serve the prediction. Note: only used in explicit caching, where users can have control over caching (e.g. what content to cache) and enjoy guaranteed cost savings. Format: `cachedContents/{cachedContent}`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 

## Response body

If successful, the response body contains a stream of `GenerateContentResponse`
instances.

### GenerateContentResponse

Response from the model supporting multiple candidates.

Note on safety ratings and content filtering. They are reported for both prompt
in `GenerateContentResponse.prompt_feedback` and for each candidate
in `finishReason` and in `safetyRatings`. The API contract is that:

* Either all requested candidates are returned or no candidates at all.
* No candidates are returned only if there was something wrong with the prompt (
  see `promptFeedback`).
* Feedback on each candidate is reported on `finishReason` and `safetyRatings`.

#### JSON representation

```json
{
  "candidates": [
    {
      object
      (Candidate)
    }
  ],
  "promptFeedback": {
    object
    (PromptFeedback)
  },
  "usageMetadata": {
    object
    (UsageMetadata)
  }
}
```

#### Fields

* **`candidates[]` object (`Candidate`)**: Candidate responses from the model.
* **`promptFeedback` object (`PromptFeedback`)**: Returns the prompt's feedback
  related to the content filters.
* **`usageMetadata` object (`UsageMetadata`)**: Output only. Metadata on the
  generation requests' token usage.

### PromptFeedback

A set of the feedback metadata the prompt specified
in `GenerateContentRequest.content`.

#### JSON representation

```json
{
  "blockReason": enum
  (BlockReason),
  "safetyRatings": [
    {
      object
      (SafetyRating)
    }
  ]
}
```

#### Fields

* **`blockReason` enum (`BlockReason`)**: Optional. If set, the prompt was
  blocked and no candidates are returned. Rephrase your prompt.
* **`safetyRatings[]` object (`SafetyRating`)**: Ratings for safety of the
  prompt. There is at most one rating per category.

### BlockReason

Specifies what was the reason why the prompt was blocked.

#### Enums

* `BLOCK_REASON_UNSPECIFIED`: Default value. This value is unused.
* `SAFETY`: Prompt was blocked due to safety reasons. You can
  inspect `safetyRatings` to understand which safety category blocked it.
* `OTHER`: Prompt was blocked due to unknown reasons.

### UsageMetadata

Metadata on the generation request's token usage.

#### JSON representation

```json
{
  "promptTokenCount": integer,
  "cachedContentTokenCount": integer,
  "candidatesTokenCount": integer,
  "totalTokenCount": integer
}
```

#### Fields

* **`promptTokenCount` integer**: Number of tokens in the prompt.
  When `cachedContent` is set, this is still the total effective prompt size.
  I.e. this includes the number of tokens in the cached content.
* **`cachedContentTokenCount` integer**: Number of tokens in the cached part of
  the prompt, i.e. in the cached content.
* **`candidatesTokenCount` integer**: Total number of tokens across the
  generated candidates.
* **`totalTokenCount` integer**: Total token count for the generation request (
  prompt + candidates).

### Candidate

A response candidate generated from the model.

#### JSON representation

```json
{
  "content": {
    object
    (Content)
  },
  "finishReason": enum
  (FinishReason),
  "safetyRatings": [
    {
      object
      (SafetyRating)
    }
  ],
  "citationMetadata": {
    object
    (CitationMetadata)
  },
  "tokenCount": integer,
  "groundingAttributions": [
    {
      object
      (GroundingAttribution)
    }
  ],
  "index": integer
}
```

#### Fields

* **`content` object (`Content`)**: Output only. Generated content returned from
  the model.
* **`finishReason` enum (`FinishReason`)**: Optional. Output only. The reason
  why the model stopped generating tokens.
    * If empty, the model has not stopped generating the tokens.
* **`safetyRatings[]` object (`SafetyRating`)**: List of ratings for the safety
  of a response candidate. There is at most one rating per category.
* **`citationMetadata` object (`CitationMetadata`)**: Output only. Citation
  information for model-generated candidate. This field may be populated with
  recitation information for any text included in the content. These are
  passages that are "recited" from copyrighted material in the foundational
  LLM's training data.
* **`tokenCount` integer**: Output only. Token count for this candidate.
* **`groundingAttributions[]` object (`GroundingAttribution`)**: Output only.
  Attribution information for sources that contributed to a grounded answer.
  This field is populated for `GenerateAnswer` calls.
* **`index` integer**: Output only. Index of the candidate in the list of
  candidates.

### FinishReason

Defines the reason why the model stopped generating tokens.

#### Enums

* `FINISH_REASON_UNSPECIFIED`: Default value. This value is unused.
* `STOP`: Natural stop point of the model or provided stop sequence.
* `MAX_TOKENS`: The maximum number of tokens as specified in the request was
  reached.
* `SAFETY`: The candidate content was flagged for safety reasons.
* `RECITATION`: The candidate content was flagged for recitation reasons.
* `LANGUAGE`: The candidate content was flagged for using an unsupported
  language.
* `OTHER`: Unknown reason.

### GroundingAttribution

Attribution for a source that contributed to an answer.

#### JSON representation

```json
{
  "sourceId": {
    object
    (AttributionSourceId)
  },
  "content": {
    object
    (Content)
  }
}
```

#### Fields

* **`sourceId` object (`AttributionSourceId`)**: Output only. Identifier for the
  source contributing to this attribution.
* **`content` object (`Content`)**: Grounding source content that makes up this
  attribution.

### AttributionSourceId

Identifier for the source contributing to this attribution.

#### JSON representation

```json
{
  // Union field source can be only one of the following:
  "groundingPassage": {
    object
    (GroundingPassageId)
  },
  "semanticRetrieverChunk": {
    object
    (SemanticRetrieverChunk)
  }
  // End of list of possible types for union field source.
}
```

#### Fields

* **`source`**: Union field.

  `source` can be only one of the following:

    * **`groundingPassage` object (`GroundingPassageId`)**: Identifier for an
      inline passage.
    * **`semanticRetrieverChunk` object (`SemanticRetrieverChunk`)**: Identifier
      for a Chunk fetched via Semantic Retriever.

### GroundingPassageId

Identifier for a part within a `GroundingPassage`.

#### JSON representation

```json
{
  "passageId": string,
  "partIndex": integer
}
```

#### Fields

* **`passageId` string**: Output only. ID of the passage matching
  the `GenerateAnswerRequest`'s `GroundingPassage.id`.
* **`partIndex` integer**: Output only. Index of the part within
  the `GenerateAnswerRequest`'s `GroundingPassage.content`.

### SemanticRetrieverChunk

Identifier for a Chunk retrieved via Semantic Retriever specified in
the `GenerateAnswerRequest` using `SemanticRetrieverConfig`.

#### JSON representation

```json
{
  "source": string,
  "chunk": string
}
```

#### Fields

* **`source` string**: Output only. Name of the source matching the
  request's `SemanticRetrieverConfig.source`. Example: `corpora/123`
  or `corpora/123/documents/abc`
* **`chunk` string**: Output only. Name of the Chunk containing the attributed
  text. Example: `corpora/123/documents/abc/chunks/xyz`

### CitationMetadata

A collection of source attributions for a piece of content.

#### JSON representation

```json
{
  "citationSources": [
    {
      object
      (CitationSource)
    }
  ]
}
```

#### Fields

* **`citationSources[]` object (`CitationSource`)**: Citations to sources for a
  specific response.

### CitationSource

A citation to a source for a portion of a specific response.

#### JSON representation

```json
{
  "startIndex": integer,
  "endIndex": integer,
  "uri": string,
  "license": string
}
```

#### Fields

* **`startIndex` integer**: Optional. Start of segment of the response that is
  attributed to this source. Index indicates the start of the segment, measured
  in bytes.
* **`endIndex` integer**: Optional. End of the attributed segment, exclusive.
* **`uri` string**: Optional. URI that is attributed as a source for a portion
  of the text.
* **`license` string**: Optional. License for the GitHub project that is
  attributed as a source for segment. License info is required for code
  citations.

### GenerationConfig

Configuration options for model generation and outputs. Not all parameters may
be configurable for every model.

#### JSON representation

```json
{
  "stopSequences": [
    string
  ],
  "responseMimeType": string,
  "responseSchema": {
    object
    (Schema)
  },
  "candidateCount": integer,
  "maxOutputTokens": integer,
  "temperature": number,
  "topP": number,
  "topK": integer
}
```

#### Fields

* **`stopSequences[]` string**: Optional. The set of character sequences (up to
    5) that will stop output generation. If specified, the API will stop at the
       first appearance of a stop sequence. The stop sequence will not be
       included as
       part of the response.
* **`responseMimeType` string**: Optional. Output response mimetype of the
  generated candidate text. Supported mimetype:
    * `text/plain`: (default) Text output.
    * `application/json`: JSON response in the candidates.
* **`responseSchema` object (`Schema`)**: Optional. Output response schema of
  the generated candidate text when response mime type can have schema. Schema
  can be objects, primitives or arrays and is a subset of OpenAPI schema.
    * If set, a compatible `responseMimeType` must also be set. Compatible
      mimetypes: `application/json`: Schema for JSON response.
* **`candidateCount` integer**: Optional. Number of generated responses to
  return. Currently, this value can only be set to 1. If unset, this will
  default to 1.
* **`maxOutputTokens` integer**: Optional. The maximum number of tokens to
  include in a candidate. Note: The default value varies by model, see
  the `Model.output_token_limit` attribute of the Model returned from
  the `getModel` function.
* **`temperature` number**: Optional. Controls the randomness of the output.
  Note: The default value varies by model, see the `Model.temperature` attribute
  of the Model returned from the `getModel` function. Values can range
  from [0.0, 2.0].
* **`topP` number**: Optional. The maximum cumulative probability of tokens to
  consider when sampling. The model uses combined Top-k and nucleus sampling.
  Tokens are sorted based on their assigned probabilities so that only the most
  likely tokens are considered. Top-k sampling directly limits the maximum
  number of tokens to consider, while Nucleus sampling limits number of tokens
  based on the cumulative probability. Note: The default value varies by model,
  see the `Model.top_p` attribute of the Model returned from the `getModel`
  function.
* **`topK` integer**: Optional. The maximum number of tokens to consider when
  sampling. Models use nucleus sampling or combined Top-k and nucleus sampling.
  Top-k sampling considers the set of topK most probable tokens. Models running
  with nucleus sampling don't allow topK setting. Note: The default value varies
  by model, see the `Model.top_k` attribute of the Model returned from
  the `getModel` function. Empty topK field in Model indicates the model doesn't
  apply top-k sampling and doesn't allow setting topK on requests.

### HarmCategory

The category of a rating.

These categories cover various kinds of harms that developers may wish to
adjust.

#### Enums

* `HARM_CATEGORY_UNSPECIFIED`: Category is unspecified.
* `HARM_CATEGORY_DEROGATORY`: Negative or harmful comments targeting identity
  and/or protected attribute.
* `HARM_CATEGORY_TOXICITY`: Content that is rude, disrespectful, or profane.
* `HARM_CATEGORY_VIOLENCE`: Describes scenarios depicting violence against an
  individual or group, or general descriptions of gore.
* `HARM_CATEGORY_SEXUAL`: Contains references to sexual acts or other lewd
  content.
* `HARM_CATEGORY_MEDICAL`: Promotes unchecked medical advice.
* `HARM_CATEGORY_DANGEROUS`: Dangerous content that promotes, facilitates, or
  encourages harmful acts.
* `HARM_CATEGORY_HARASSMENT`: Harassment content.
* `HARM_CATEGORY_HATE_SPEECH`: Hate speech and content.
* `HARM_CATEGORY_SEXUALLY_EXPLICIT`: Sexually explicit content.
* `HARM_CATEGORY_DANGEROUS_CONTENT`: Dangerous content.

### SafetyRating

Safety rating for a piece of content.

The safety rating contains the category of harm and the harm probability level
in that category for a piece of content. Content is classified for safety across
a number of harm categories and the probability of the harm classification is
included here.

#### JSON representation

```json
{
  "category": enum
  (HarmCategory),
  "probability": enum
  (HarmProbability),
  "blocked": boolean
}
```

#### Fields

* **`category` enum (`HarmCategory`)**: Required. The category for this rating.
* **`probability` enum (`HarmProbability`)**: Required. The probability of harm
  for this content.
* **`blocked` boolean**: Was this content blocked because of this rating?

### HarmProbability

The probability that a piece of content is harmful.

The classification system gives the probability of the content being unsafe.
This does not indicate the severity of harm for a piece of content.

#### Enums

* `HARM_PROBABILITY_UNSPECIFIED`: Probability is unspecified.
* `NEGLIGIBLE`: Content has a negligible chance of being unsafe.
* `LOW`: Content has a low chance of being unsafe.
* `MEDIUM`: Content has a medium chance of being unsafe.
* `HIGH`: Content has a high chance of being unsafe.

### SafetySetting

Safety setting, affecting the safety-blocking behavior.

Passing a safety setting for a category changes the allowed probability that
content is blocked.

#### JSON representation

```json
{
  "category": enum
  (HarmCategory),
  "threshold": enum
  (HarmBlockThreshold)
}
```

#### Fields

* **`category` enum (`HarmCategory`)**: Required. The category for this setting.
* **`threshold` enum (`HarmBlockThreshold`)**: Required. Controls the
  probability threshold at which harm is blocked.

### HarmBlockThreshold

Block at and beyond a specified harm probability.

#### Enums

* `HARM_BLOCK_THRESHOLD_UNSPECIFIED`: Threshold is unspecified.
* `BLOCK_LOW_AND_ABOVE`: Content with `NEGLIGIBLE` will be allowed.
* `BLOCK_MEDIUM_AND_ABOVE`: Content with `NEGLIGIBLE` and `LOW` will be allowed.
* `BLOCK_ONLY_HIGH`: Content with `NEGLIGIBLE`, `LOW`, and `MEDIUM` will be
  allowed.
* `BLOCK_NONE`: All content will be allowed.