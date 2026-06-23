package com.interactive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ItemCommand - /item command handler
 */
public class ItemCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public ItemCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null || item.getType().name().equals("AIR")) {
            player.sendMessage("§cYou are not holding any item!");
            return true;
        }

        player.sendMessage("§6=== Item Information ===");
        player.sendMessage("§eType: §7" + item.getType().name());
        player.sendMessage("§eAmount: §7" + item.getAmount());
        player.sendMessage("§eDurability: §7" + item.getDurability() + "/" + item.getType().getMaxDurability());
        
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            player.sendMessage("§eDisplay Name: §7" + item.getItemMeta().getDisplayName());
        }
        
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            player.sendMessage("§eLore:");
            for (String line : item.getItemMeta().getLore()) {
                player.sendMessage("§7  " + line);
            }
        }

        return true;
    }
}
