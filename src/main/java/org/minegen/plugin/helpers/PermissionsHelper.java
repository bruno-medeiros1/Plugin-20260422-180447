package org.minegen.plugin.helpers;

import org.minegen.plugin.utils.Constants;
import org.bukkit.command.CommandSender;

/**
 * A utility class for handling all plugin permission checks.
 */
public final class PermissionsHelper {

    private PermissionsHelper() {}

    // Core permission checks - DO NOT MODIFY
    public static boolean hasReloadPermission(CommandSender sender) {
        return sender.hasPermission(Constants.Permissions.ALL) ||
                sender.hasPermission(Constants.Permissions.ALL_COMMANDS) ||
                sender.hasPermission(Constants.Permissions.CMD_RELOAD);
    }

    public static boolean hasHelpPermission(CommandSender sender) {
        return sender.hasPermission(Constants.Permissions.ALL) ||
                sender.hasPermission(Constants.Permissions.ALL_COMMANDS) ||
                sender.hasPermission(Constants.Permissions.CMD_HELP);
    }

    // AI_CAN_ADD_PERMISSION_CHECKS - Add custom permission check methods below this line
    // Example:
    // public static boolean hasTeleportPermission(CommandSender sender) {
    //     return sender.hasPermission(Constants.Permissions.ALL) ||
    //             sender.hasPermission(Constants.Permissions.ALL_COMMANDS) ||
    //             sender.hasPermission(Constants.Permissions.CMD_TELEPORT);
    // }

    // AI_END - Do not add permission checks after this line
}
