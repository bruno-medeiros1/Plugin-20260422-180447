package org.minegen.plugin.commands;

import org.minegen.plugin.helpers.MessagesHelper;
import org.minegen.plugin.helpers.PermissionsHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.minegen.plugin.utils.Constants;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MainCommand implements CommandExecutor, TabCompleter {
    private final Logger logger;
    private final MessagesHelper messagesHelper;
    private final Map<String, SubCommand> subCommands = new HashMap<>();

    public MainCommand(Logger logger, MessagesHelper messagesHelper) {
        this.logger = logger;
        this.messagesHelper = messagesHelper;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            SubCommand helpCommand = subCommands.get("help");
            if (helpCommand != null) {
                helpCommand.execute(sender, new String[0]);
            } else {
                messagesHelper.sendCommandSenderMessage(sender, "&cThe help command is not available.");
            }
            return true;
        }

        String subCommandName = args[0].toLowerCase();
        SubCommand subCommand = subCommands.get(subCommandName);

        if (subCommand == null) {
            messagesHelper.sendCommandSenderMessage(sender, "&cUnknown subcommand. Use /" + Constants.PLUGIN_ALIAS + " help for a list of commands.");
            return true;
        }

        try {
            // Pass the remaining arguments to the subcommand.
            String[] subCommandArgs = Arrays.copyOfRange(args, 1, args.length);
            subCommand.execute(sender, subCommandArgs);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred while executing command '" + subCommandName + "' for " + sender.getName(), e);
            messagesHelper.sendCommandSenderMessage(sender, "&cAn unexpected error occurred. Please contact an administrator.");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();

            // Check if the player has permission to show subcommand on tab complete
            if (PermissionsHelper.hasHelpPermission(sender)) completions.add("help");
            if (PermissionsHelper.hasReloadPermission(sender)) completions.add("reload");

            // Return suggestions that start with what the player has already typed
            return completions.stream()
                    .filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());

        }
        else if (args.length > 1)
        {
            // Player is typing arguments for a subcommand
            String subCommandName = args[0].toLowerCase();
            SubCommand subCommand = subCommands.get(subCommandName);

            if (subCommand != null) {
                // Pass only the relevant arguments to the subcommand's completer
                return subCommand.getSubcommandCompletions(sender, args);
            }
        }

        return List.of();
    }

    public void registerSubCommand(String name, SubCommand command) {
        subCommands.put(name, command);
    }
}