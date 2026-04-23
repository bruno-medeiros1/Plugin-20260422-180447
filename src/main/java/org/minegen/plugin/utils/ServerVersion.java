package org.minegen.plugin.utils;

import org.bukkit.Bukkit;

public enum ServerVersion {
    V_1_21,
    UNKNOWN;

    private static ServerVersion currentVersion;

    /**
     * Gets the current server version, determined once at startup.
     * @return The current ServerVersion enum constant.
     */
    public static ServerVersion getCurrent() {
        if (currentVersion != null)
            return currentVersion;

        String versionString = Bukkit.getBukkitVersion();

        if (versionString.contains("1.21")) {
            currentVersion = V_1_21;
        }

        return currentVersion;
    }
}