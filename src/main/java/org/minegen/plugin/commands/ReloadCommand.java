package org.minegen.plugin.commands;

import org.minegen.plugin.PluginMain;
import org.minegen.plugin.handlers.MessagesHandler;
import org.minegen.plugin.helpers.MessagesHelper;
import org.minegen.plugin.helpers.PermissionsHelper;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;

public class ReloadCommand implements SubCommand {

    private final PluginMain plugin;
    private final MessagesHelper messagesHelper;
    private final MessagesHandler messagesHandler;

    public ReloadCommand(PluginMain plugin, MessagesHelper messagesHelper, MessagesHandler messagesHandler) {
        this.plugin = plugin;
        this.messagesHelper = messagesHelper;
        this.messagesHandler = messagesHandler;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!PermissionsHelper.hasReloadPermission(sender)) {
            messagesHelper.sendCommandSenderMessage(sender, messagesHandler.getNoPermissionMessage());
            return true;
        }

        messagesHelper.sendCommandSenderMessage(sender, messagesHandler.getReloadStartMessage());

        try {
            // Delegate the entire reload process to the main class
            plugin.reload();
            messagesHelper.sendCommandSenderMessage(sender, messagesHandler.getReloadSuccessMessage());
        } catch (Exception e) {
            messagesHelper.sendCommandSenderMessage(sender, "&cAn error occurred during reload. Please check the console for details.");
            plugin.getLogger().log(Level.SEVERE, "A critical error occurred during plugin reload.", e);
        }

        return true;
    }
}