package org.minegen.plugin.utils;

public final class Constants {
    private Constants() {}

    // Core plugin identifiers - DO NOT MODIFY
    public static final String PLUGIN_NAME = "PLUGIN_NAME";
    public static final String PLUGIN_LOWERCASE_NAME = "PLUGIN_LOWERCASE_NAME";
    public static final String PLUGIN_ALIAS = "PLUGIN_ALIAS";

    public static final class Files {
        private Files() {}

        // Include any other files excluding config.yml
        public static final String MESSAGES_FILE = "messages.yml";
    }

    public static final class Permissions {
        private Permissions() {}

        // Wildcard Permissions - DO NOT MODIFY
        public static final String ALL = PLUGIN_LOWERCASE_NAME + ".*";
        public static final String ALL_COMMANDS = PLUGIN_LOWERCASE_NAME + ".command.*";
        public static final String ALL_BYPASS = PLUGIN_LOWERCASE_NAME + ".bypass.*";

        // Core Command Permissions - DO NOT MODIFY
        public static final String CMD_RELOAD = PLUGIN_LOWERCASE_NAME + ".command.reload";
        public static final String CMD_HELP = PLUGIN_LOWERCASE_NAME + ".command.help";

        // AI_CAN_ADD_COMMAND_PERMISSIONS - Add custom command permissions below this line
        // Example: public static final String CMD_CUSTOM = PLUGIN_LOWERCASE_NAME + ".command.custom";

        // AI_END - Do not add command permissions after this line

        // Core Bypass Permissions - DO NOT MODIFY

        // AI_CAN_ADD_BYPASS_PERMISSIONS - Add custom bypass permissions below this line
        // Example: public static final String BYPASS_COOLDOWN = PLUGIN_LOWERCASE_NAME + ".bypass.cooldown";

        // AI_END - Do not add bypass permissions after this line
    }
}