package com.interactive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * InventoryCommand - /inv command handler
 */
public class InventoryCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public InventoryCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();

        player.sendMessage("§6=== Inventory Information ===");
        player.sendMessage("§eSize: §7" + inventory.getSize() + " slots");
        player.sendMessage("§eItems:");

        int itemCount = 0;
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && !item.getType().name().equals("AIR")) {
                itemCount++;
                player.sendMessage(String.format("§7  Slot %d: §b%s §7x%d", 
                    i, item.getType().name(), item.getAmount()));
            }
        }

        if (itemCount == 0) {
            player.sendMessage("§7  (empty)");
        }

        player.sendMessage("§eTotal Items: §7" + itemCount);
        player.sendMessage("§eEmpty Slots: §7" + (inventory.getSize() - itemCount));

        return true;
    }
}
