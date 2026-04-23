package org.minegen.plugin.handlers;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

public class ConfigHandler {
    private final Logger logger;

    private FileConfiguration fileConfiguration;

    // Core config variables - DO NOT MODIFY
    private boolean isDebugModeEnabled;

    // AI_CAN_DECLARE_CONFIG_VARIABLES - Add custom config variable fields below this line
    // Example: private int teleportCooldown;
    // Example: private boolean enableHomes;

    // AI_END - Do not add config variables after this line

    public ConfigHandler(FileConfiguration fileConfiguration, Logger logger) {
        this.fileConfiguration = fileConfiguration;
        this.logger = logger;

        setConfigVariables();
    }

    public final void setConfigVariables() {
        // Core config loading - DO NOT MODIFY
        this.isDebugModeEnabled = this.fileConfiguration.getBoolean("general.debug-mode", false);

        // AI_CAN_LOAD_CONFIG_VARIABLES - Add custom config variable loading below this line
        // Example: this.teleportCooldown = this.fileConfiguration.getInt("teleport.cooldown-seconds", 5);
        // Example: this.enableHomes = this.fileConfiguration.getBoolean("homes.enabled", true);

        // AI_END - Do not add config loading after this line
    }

    /**
     * Reloads the configuration values from a new FileConfiguration object.
     * This is called from the main plugin's reload sequence.
     * @param newFileConfiguration The newly reloaded config object.
     */
    public void reload(FileConfiguration newFileConfiguration) {
        this.fileConfiguration = newFileConfiguration;
        setConfigVariables();
        logger.info("Configuration values have been reloaded.");
    }

    // Core getters - DO NOT MODIFY
    public final boolean getIsDebugModeEnabled() {
        return this.isDebugModeEnabled;
    }

    // AI_CAN_ADD_CONFIG_GETTERS - Add custom config getter methods below this line
    // Example: public int getTeleportCooldown() { return this.teleportCooldown; }
    // Example: public boolean isHomesEnabled() { return this.enableHomes; }

    // AI_END - Do not add getters after this line
}
