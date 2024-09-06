package com.enovka.gemini4j.infrastructure.tool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceFiles {

    public static String loadFileFromResources(String fileName) {
        try {
            Path resourcesPath = Paths.get("src", "test", "resources");
            Path filePath = resourcesPath.resolve(fileName);
            return Files.readString(filePath);
        } catch (IOException e) {
            System.err.println(
                    "Error reading ResourceFiles file: " + e.getMessage());
            return "";
        }
    }
}