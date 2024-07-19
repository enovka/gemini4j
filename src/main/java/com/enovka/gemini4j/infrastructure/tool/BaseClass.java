package com.enovka.gemini4j.infrastructure.tool;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Base class that provides logging functionality for all classes in the
 * application. It implements common log methods like {@code logDebug},
 * {@code logInfo}, {@code logWarn}, and {@code logError}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1-beta
 */
public abstract class BaseClass {

    private static final Logger LOGGER = Logger.getLogger(
            BaseClass.class.getName());

    /**
     * Logs a debug message with the class name.
     *
     * @param message The debug message to log.
     */
    protected void logDebug(String message) {
        LOGGER.log(Level.FINE,
                String.format("[%s] - %s", this.getClass().getSimpleName(),
                        message));
    }

    /**
     * Logs an info message with the class name.
     *
     * @param message The info message to log.
     */
    protected void logInfo(String message) {
        LOGGER.log(Level.INFO,
                String.format("[%s] - %s", this.getClass().getSimpleName(),
                        message));
    }

    /**
     * Logs a warning message with the class name.
     *
     * @param message The warning message to log.
     */
    protected void logWarn(String message) {
        LOGGER.log(Level.WARNING,
                String.format("[%s] - %s", this.getClass().getSimpleName(),
                        message));
    }

    /**
     * Logs an error message with the class name.
     *
     * @param message The error message to log.
     */
    protected void logError(String message) {
        LOGGER.log(Level.SEVERE,
                String.format("[%s] - %s", this.getClass().getSimpleName(),
                        message));
    }

    /**
     * Logs an error message with the class name and an exception.
     *
     * @param message The error message to log.
     * @param e The exception to log.
     */
    protected void logError(String message, Throwable e) {
        LOGGER.log(Level.SEVERE,
                String.format("[%s] - %s", this.getClass().getSimpleName(),
                        message), e);
    }
}