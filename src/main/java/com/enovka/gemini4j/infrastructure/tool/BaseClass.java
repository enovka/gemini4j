package com.enovka.gemini4j.infrastructure.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class that provides logging functionality for all classes in the
 * application. It implements common log methods like {@code logDebug},
 * {@code logInfo}, {@code logWarn}, and {@code logError}.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.0.1-beta
 */
public class BaseClass {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Logs a debug message with the class name.
     *
     * @param message The debug message to log.
     */
    protected void logDebug(String message) {
        logger.debug(message);
    }

    /**
     * Logs an info message with the class name.
     *
     * @param message The info message to log.
     */
    protected void logInfo(String message) {
        logger.info(message);
    }

    /**
     * Logs a warning message with the class name.
     *
     * @param message The warning message to log.
     */
    protected void logWarn(String message) {
        logger.warn(message);
    }

    /**
     * Logs an error message with the class name.
     *
     * @param message The error message to log.
     */
    protected void logError(String message) {
        logger.error(message);
    }

    /**
     * Logs an error message with the class name and an exception.
     *
     * @param message The error message to log.
     * @param e       The exception to log.
     */
    protected void logError(String message, Throwable e) {
        logger.error(message, e);
    }
}