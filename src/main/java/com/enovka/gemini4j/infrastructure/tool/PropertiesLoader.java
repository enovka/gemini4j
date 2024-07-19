package com.enovka.gemini4j.infrastructure.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for loading properties from a properties file.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.2
 */
public class PropertiesLoader {

    private static final String PROPERTIES_FILE = "gemini4j.properties";

    /**
     * Loads properties from the specified properties file.
     *
     * @param propertiesFile The name of the properties file to load.
     * @return A {@link Properties} object containing the loaded properties.
     */
    public static Properties loadProperties(String propertiesFile) {
        Properties properties = new Properties();
        try (InputStream inputStream =
                     PropertiesLoader.class.getClassLoader()
                             .getResourceAsStream(propertiesFile)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.err.println(
                        "Properties file not found: " + propertiesFile);
            }
        } catch (IOException e) {
            System.err.println(
                    "Error loading properties from file: " + propertiesFile);
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * Loads properties from the default properties file "gemini4j.properties".
     *
     * @return A {@link Properties} object containing the loaded properties.
     */
    public static Properties loadProperties() {
        return loadProperties(PROPERTIES_FILE);
    }
}