package com.interactive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * EnderchestCommand - /ec command handler
 */
public class EnderchestCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public EnderchestCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;

        player.sendMessage("§6=== Enderchest Information ===");
        player.sendMessage("§eSize: §727 slots");
        player.sendMessage("§eItems:");

        int itemCount = 0;
        for (int i = 0; i < player.getEnderChest().getSize(); i++) {
            ItemStack item = player.getEnderChest().getItem(i);
            if (item != null && !item.getType().name().equals("AIR")) {
                itemCount++;
                player.sendMessage(String.format("§7  Slot %d: §3%s §7x%d", 
                    i, item.getType().name(), item.getAmount()));
            }
        }

        if (itemCount == 0) {
            player.sendMessage("§7  (empty)");
        }

        player.sendMessage("§eTotal Items: §7" + itemCount);
        player.sendMessage("§eEmpty Slots: §7" + (27 - itemCount));

        return true;
    }
}
