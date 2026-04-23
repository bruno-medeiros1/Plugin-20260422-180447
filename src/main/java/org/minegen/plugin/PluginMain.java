package org.minegen.plugin;

import org.minegen.plugin.commands.HelpCommand;
import org.minegen.plugin.commands.MainCommand;
import org.minegen.plugin.commands.ReloadCommand;
import org.minegen.plugin.handlers.ConfigHandler;
import org.minegen.plugin.handlers.MessagesHandler;
import org.minegen.plugin.helpers.FileHelper;
import org.minegen.plugin.helpers.FoliaHelper;
import org.minegen.plugin.helpers.MessagesHelper;
import org.minegen.plugin.utils.Constants;
import org.minegen.plugin.utils.ServerVersion;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

public final class PluginMain extends JavaPlugin {
    public static final int CURRENT_YEAR = java.time.Year.now().getValue();
    public final String VERSION = this.getPluginMeta().getVersion();

    // Core handlers - DO NOT MODIFY
    private MessagesHandler messagesHandler;
    private ConfigHandler configHandler;

    // Core helpers - DO NOT MODIFY
    private MessagesHelper messagesHelper;
    private FileHelper fileHelper;
    private FoliaHelper foliaHelper;
    private ServerVersion serverVersion;

    // AI_CAN_DECLARE_HANDLERS - Add custom handler fields below this line

    // AI_END - Do not add handlers after this line

    // AI_CAN_DECLARE_HELPERS - Add custom helper fields below this line

    // AI_END - Do not add helpers after this line

    @Override
    public void onLoad() {
        this.serverVersion = ServerVersion.getCurrent();
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        try {
            setupComponents();
            setupListeners();
            setupCommands();

            startAllPluginTasks();

            sendStartupMessages();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "A critical error occurred during plugin startup. Disabling plugin.", e);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        sendOnDisableMessages();

        shutdownAllPluginTasks();
        HandlerList.unregisterAll(this);
    }

    public void startAllPluginTasks() {
        // AI_CAN_START_TASKS - Add task start calls below this line
        // Example: if (exampleHandler != null) exampleHandler.start();

        // AI_END - Do not add task starts after this line
    }

    public void reload() {
        shutdownAllPluginTasks();

        reloadConfig();
        fileHelper.reloadAll();

        configHandler.reload(getConfig());
        messagesHandler.reload(fileHelper.getMessagesConfig());
        messagesHelper.setPrefix(messagesHandler.getPrefixMessage());

        // AI_CAN_RELOAD_HANDLERS - Add custom handler reload calls below this line
        // Example: if (exampleHandler != null) exampleHandler.reload();

        // AI_END - Do not add reload calls after this line

        startAllPluginTasks();
    }

    private void setupComponents() throws Exception {
        saveDefaultConfig();
        this.serverVersion = ServerVersion.getCurrent();
        this.foliaHelper = new FoliaHelper(this);
        this.fileHelper = new FileHelper(this, getLogger());
        this.messagesHelper = new MessagesHelper(this.serverVersion);

        this.configHandler = new ConfigHandler(this.getConfig(), getLogger());
        this.messagesHandler = new MessagesHandler(this.fileHelper.getMessagesConfig());
        messagesHelper.setPrefix(this.messagesHandler.getPrefixMessage());
        messagesHelper.setDebugMode(this.configHandler.getIsDebugModeEnabled());

        // AI_CAN_INITIALIZE_HANDLERS - Add custom handler initialization below this line
        // Example: this.exampleHandler = new ExampleHandler(this.configHandler, getLogger());

        // AI_END - Do not add initializations after this line
    }

    private void setupListeners() {
        getLogger().info("Registering event listeners...");

        // AI_CAN_DECLARE_LISTENERS - Declare listener instances below this line
        // Example: var exampleListener = new ExampleListener(this.exampleHandler);

        // AI_END - Do not declare listeners after this line

        // AI_CAN_REGISTER_LISTENERS - Register listeners below this line
        // Example: Bukkit.getPluginManager().registerEvents(exampleListener, this);

        // AI_END - Do not register listeners after this line
    }

    private void setupCommands() {
        getLogger().info("Registering commands...");

        var helpCommand = new HelpCommand(this);
        var reloadCommand = new ReloadCommand(this, this.messagesHelper, this.messagesHandler);

        // AI_CAN_DECLARE_COMMANDS - Declare custom command instances below this line
        // Example: var customCommand = new CustomCommand(this.exampleHandler);

        // AI_END - Do not declare commands after this line

        MainCommand mainCommand = new MainCommand(getLogger(), this.messagesHelper);
        mainCommand.registerSubCommand("reload", reloadCommand);
        mainCommand.registerSubCommand("help", helpCommand);

        // AI_CAN_REGISTER_SUBCOMMANDS - Register custom subcommands below this line
        // Example: mainCommand.registerSubCommand("custom", customCommand);

        // AI_END - Do not register subcommands after this line

        Objects.requireNonNull(getCommand(Constants.PLUGIN_ALIAS)).setExecutor(mainCommand);
        Objects.requireNonNull(getCommand(Constants.PLUGIN_ALIAS)).setTabCompleter(mainCommand);
    }

    public void shutdownAllPluginTasks() {
        // AI_CAN_SHUTDOWN_TASKS - Add task shutdown calls below this line
        // Example: if (exampleHandler != null) exampleHandler.shutdown();

        // AI_END - Do not add shutdowns after this line
    }

    private void sendStartupMessages() {
        messagesHelper.sendConsoleMessage("&e┌───────────────────────────────────────────┐");
        messagesHelper.sendConsoleMessage(" ");
        messagesHelper.sendConsoleMessage("  &b&l" + Constants.PLUGIN_NAME + " &fv" + VERSION);
        messagesHelper.sendConsoleMessage("  &7by @MineGen " + CURRENT_YEAR);
        messagesHelper.sendConsoleMessage(" ");

        String platform = foliaHelper.isFolia() ? "&dFolia" : "&fSpigot/Paper/Purpur";
        messagesHelper.sendConsoleMessage("  &bStatus:");
        messagesHelper.sendConsoleMessage("    &7Platform: " + platform);
        messagesHelper.sendConsoleMessage(" ");
        messagesHelper.sendConsoleMessage("  &aPlugin has been enabled successfully!");
        messagesHelper.sendConsoleMessage(" ");
        messagesHelper.sendConsoleMessage("&e└───────────────────────────────────────────┘");

        messagesHelper.sendDebugMessage(" ");
        messagesHelper.sendDebugMessage("&6&lDEBUG MODE IS ENABLED. THIS WILL SPAM YOUR CONSOLE.");
        messagesHelper.sendDebugMessage("&6&lIT IS NOT INTENDED FOR PRODUCTION USE.");
        messagesHelper.sendDebugMessage(" ");
    }

    private void sendOnDisableMessages() {
        messagesHelper.sendConsoleMessage("&e┌───────────────────────────────────────────┐");
        messagesHelper.sendConsoleMessage(" ");
        messagesHelper.sendConsoleMessage("  &b&l" + Constants.PLUGIN_NAME + " &fv" + VERSION);
        messagesHelper.sendConsoleMessage("  &7by @MineGen" + CURRENT_YEAR);
        messagesHelper.sendConsoleMessage(" ");
        messagesHelper.sendConsoleMessage("  &cPlugin has been disabled. All tasks stopped and data saved.");
        messagesHelper.sendConsoleMessage(" ");
        messagesHelper.sendConsoleMessage("&e└───────────────────────────────────────────┘");
    }
}