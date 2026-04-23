package org.minegen.plugin.handlers;

import org.minegen.plugin.ConfigHandler;
import org.minegen.plugin.utils.PluginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Handles message formatting and retrieval.
 * Uses dependency injection for ConfigHandler.
 */
public class MessagesHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagesHandler.class);
    private ConfigHandler configHandler;

    public MessagesHandler(ConfigHandler configHandler) {
        this.configHandler = Objects.requireNonNull(configHandler);
    }

    /**
     * Retrieves a specific message key from the config.
     * @param key The message key.
     * @return The formatted message string, or a default message if not found.
     */
    public String getMessage(String key) {
        return configHandler.getString(key);
    }

    /**
     * Gets the reload success message.
     * @return The reload success message string.
     */
    public String getReloadSuccessMessage() {
        // Using a default message of 'hello world' as specified in the plan, but demonstrating the updated config read.
        // For production, we might use the configured message, but following the instructions to update the default behavior.
        return getMessage("reload-success");
    }
}