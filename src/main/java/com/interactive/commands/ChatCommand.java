package com.interactive.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ChatCommand - /ichat command handler
 */
public class ChatCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public ChatCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            showHelp(player);
            return true;
        }

        String subcommand = args[0].toLowerCase();

        switch (subcommand) {
            case "help":
                showHelp(player);
                break;
            case "info":
                showInfo(player);
                break;
            case "status":
                showStatus(player);
                break;
            default:
                player.sendMessage("§cUnknown subcommand. Use /ichat help");
                break;
        }

        return true;
    }

    private void showHelp(Player player) {
        player.sendMessage("§6=== InteractiveChat Help ===");
        player.sendMessage("§eChat Placeholders:");
        player.sendMessage("§7  [ec] or [enderchest] - §bShow enderchest reference");
        player.sendMessage("§7  [inv] or [inventory] - §bShow inventory reference");
        player.sendMessage("§7  [item] or [i] - §bShow item reference");
        player.sendMessage("§7  [hold] or [hand] - §bShow held item name");
        player.sendMessage("§eCommands:");
        player.sendMessage("§7  /ichat help - Show this message");
        player.sendMessage("§7  /ichat info - Show plugin info");
        player.sendMessage("§7  /ichat status - Show status");
        player.sendMessage("§7  /item - Show held item info");
        player.sendMessage("§7  /inv - Show inventory info");
        player.sendMessage("§7  /ec - Show enderchest info");
    }

    private void showInfo(Player player) {
        player.sendMessage("§6=== InteractiveChat Info ===");
        player.sendMessage("§eVersion: §7" + plugin.getDescription().getVersion());
        player.sendMessage("§eDescription: §7" + plugin.getDescription().getDescription());
        player.sendMessage("§eAuthors: §7" + String.join(", ", plugin.getDescription().getAuthors()));
        player.sendMessage("§e");
        player.sendMessage("§bUsage:");
        player.sendMessage("§7Type messages with [ec], [inv], or [item] to show interactive elements!");
    }

    private void showStatus(Player player) {
        player.sendMessage("§6=== InteractiveChat Status ===");
        player.sendMessage("§ePlugin: §aEnabled");
        player.sendMessage("§eOnline Players: §a" + Bukkit.getOnlinePlayers().size());
        player.sendMessage("§eServer: §a" + Bukkit.getServer().getName());
        player.sendMessage("§eVersion: §a" + Bukkit.getServer().getBukkitVersion());
    }
}
