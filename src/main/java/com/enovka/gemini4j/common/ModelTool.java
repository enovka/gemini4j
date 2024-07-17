package com.enovka.gemini4j.common;

import com.enovka.gemini4j.client.exception.GeminiApiException;
import com.enovka.gemini4j.domain.ListModel;
import com.enovka.gemini4j.domain.Model;
import com.enovka.gemini4j.domain.type.SupportedGenerationMethod;
import com.enovka.gemini4j.http.exception.HttpException;
import com.enovka.gemini4j.json.exception.JsonException;
import com.enovka.gemini4j.resource.spec.ModelResource;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Singleton class that manages a list of available Gemini models. It loads the
 * models from a JSON file and provides methods for updating the list and
 * searching for models based on their supported generation methods.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class ModelTool extends BaseClass {

    private static final String MODELS_JSON_FILE = "gemini_models.json";
    private static ModelTool instance;
    private List<Model> models;

    private ModelTool() {
        this.models = loadModelsFromResource();
    }

    /**
     * Gets the singleton instance of the ModelTool.
     *
     * @return The singleton instance of the ModelTool.
     */
    public static ModelTool getInstance() {
        if (instance == null) {
            instance = new ModelTool();
        }
        return instance;
    }

    /**
     * Loads the list of models from the JSON file in the resources folder.
     *
     * @return The list of models loaded from the JSON file.
     */
    private List<Model> loadModelsFromResource() {
        logDebug("Loading models from resource file: " + MODELS_JSON_FILE);
        try (InputStream inputStream =
                     ListModel.class.getClassLoader()
                             .getResourceAsStream(MODELS_JSON_FILE)) {
            ObjectMapper objectMapper = new ObjectMapper();
            ListModel loadedModels = objectMapper.readValue(inputStream,
                    new TypeReference<>() {
                    });
            // Convert strings to SupportedGenerationMethod enum
            for (Model model : loadedModels.getModels()) {
                List<String> supportedMethods
                        = model.getSupportedGenerationMethods().stream()
                        .map(SupportedGenerationMethod::fromValue)
                        .filter(Objects::nonNull)
                        .map(SupportedGenerationMethod::getValue)
                        .collect(Collectors.toList());
                model.setSupportedGenerationMethods(supportedMethods);
            }
            return loadedModels.getModels();
        } catch (IOException e) {
            logError("Error loading models from resource file: "
                    + MODELS_JSON_FILE, e);
            return new ArrayList<>();
        }
    }

    /**
     * Gets the list of available Gemini models.
     *
     * @return The list of available Gemini models.
     */
    public List<Model> getModels() {
        return new ArrayList<>(models);
    }

    /**
     * Updates the list of models using the provided {@link ModelResource}.
     *
     * @param modelResource The {@link ModelResource} to use for updating the
     * model list.
     */
    public void update(ModelResource modelResource) {
        logDebug("Updating model list using ModelResource.");
        try {
            this.models = modelResource.listModels().getModels();
        } catch (HttpException | JsonException | GeminiApiException e) {
            logError("Error updating model list: " + e.getMessage(), e);
        }
    }

    /**
     * Updates the list of models using the provided list of models.
     *
     * @param models The list of models to update the model list with.
     */
    public void update(List<Model> models) {
        logDebug("Updating model list with provided list.");
        this.models = new ArrayList<>(models);
    }

    /**
     * Finds all supported generation methods for the specified model.
     *
     * @param modelName The name of the model to search for.
     * @return A list of supported generation methods for the model, or an empty
     * list if the model is not found.
     */
    public List<String> findSupportedGenerationMethods(
            String modelName) {
        logDebug("Finding supported generation methods for model: "
                + modelName);
        Optional<Model> model = models.stream()
                .filter(m -> m.getName().equals(modelName))
                .findFirst();
        if (model.isPresent()) {
            return model.get().getSupportedGenerationMethods();
        } else {
            logWarn("Model not found: " + modelName);
            return new ArrayList<>();
        }
    }

    /**
     * Checks if the specified model supports the given generation method.
     *
     * @param modelName The name of the model to check.
     * @param generationMethod The generation method to check.
     * @return {@code true} if the model supports the generation method,
     * {@code false} otherwise.
     */
    public boolean isGenerationMethodSupported(String modelName,
                                               String generationMethod) {
        logDebug("Checking if generation method is supported for model: "
                + modelName);
        Optional<Model> model = models.stream()
                .filter(m -> m.getName().startsWith("models/" + modelName))
                .findFirst();
        if (model.isPresent()) {
            List<String> supportedMethods = model.get()
                    .getSupportedGenerationMethods();
            for (String method : supportedMethods) {
                if (method.equals(generationMethod)) {
                    return true;
                }
            }
            return false;
        } else {
            logWarn("Model not found: " + modelName);
            return false;
        }
    }
}