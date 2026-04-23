package org.minegen.plugin.helpers;

import org.minegen.plugin.PluginMain;
import org.minegen.plugin.utils.Constants;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.logging.Logger;

/**
 * A helper class to manage all custom configuration files for the plugin.
 */
public final class FileHelper {
    private final PluginMain plugin;
    private final Logger logger;

    private FileConfiguration messagesConfig;

    public FileHelper(PluginMain plugin, Logger logger) {
        this.plugin = plugin;
        this.logger = logger;
        initialize();
    }

    /**
     * Initializes all custom configuration files.
     */
    public void initialize() {
        logger.info("Loading plugin configuration files...");

        this.messagesConfig = setupCustomFile(Constants.Files.MESSAGES_FILE);

        logger.info("All plugin configuration files loaded successfully.");
    }

    /**
     * Reloads all custom configuration files from the disk.
     */
    public void reloadAll() {
        logger.info("Reloading plugin configuration files...");

        this.messagesConfig = setupCustomFile(Constants.Files.MESSAGES_FILE);

        logger.info("All plugin files have been reloaded.");
    }

    public FileConfiguration getMessagesConfig() {
        return this.messagesConfig;
    }


    /**
     * A generic method to handle the setup of any custom .yml file.
     * It ensures the file exists (creating it from defaults if necessary) and returns its configuration.
     *
     * @param fileName The name of the file (e.g., "regions.yml").
     * @return The loaded FileConfiguration for that file.
     */
    private FileConfiguration setupCustomFile(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            logger.info("File not found: " + fileName + ". Creating from defaults.");
            plugin.saveResource(fileName, false);
        }

        return YamlConfiguration.loadConfiguration(file);
    }
}
