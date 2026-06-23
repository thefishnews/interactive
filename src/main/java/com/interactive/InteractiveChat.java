package com.interactive;

import com.interactive.commands.ChatCommand;
import com.interactive.commands.ItemCommand;
import com.interactive.commands.InventoryCommand;
import com.interactive.commands.EnderchestCommand;
import com.interactive.listeners.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * InteractiveChat - A Minecraft plugin for interactive chat with item/inventory/enderchest viewing
 */
public class InteractiveChat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Log plugin startup
        getLogger().info("================================================");
        getLogger().info("InteractiveChat Plugin v" + getDescription().getVersion() + " is loading...");
        getLogger().info("================================================");

        // Register listeners
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getLogger().info("✓ Chat listener registered");

        // Register commands
        getCommand("ichat").setExecutor(new ChatCommand(this));
        getCommand("item").setExecutor(new ItemCommand(this));
        getCommand("inv").setExecutor(new InventoryCommand(this));
        getCommand("ec").setExecutor(new EnderchestCommand(this));
        getLogger().info("✓ Commands registered: /ichat, /item, /inv, /ec");

        // Load configuration
        saveDefaultConfig();
        getLogger().info("✓ Configuration loaded");

        getLogger().info("================================================");
        getLogger().info("InteractiveChat Plugin successfully enabled!");
        getLogger().info("================================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("InteractiveChat Plugin has been disabled.");
    }
}
