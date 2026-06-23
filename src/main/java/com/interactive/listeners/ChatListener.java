package com.interactive.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;

/**
 * ChatListener - Handles chat events and replaces placeholders with interactive elements
 */
public class ChatListener implements Listener {

    private JavaPlugin plugin;
    private static final String ENDERCHEST_TAG = "§3§n[ENDERCHEST]§r";
    private static final String INVENTORY_TAG = "§3§n[INVENTORY]§r";
    private static final String ITEM_TAG = "§3§n[ITEM]§r";

    public ChatListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        // Check if message contains interactive placeholders
        if (containsPlaceholder(message)) {
            event.setCancelled(true);
            String formatted = formatMessage(message, player);
            
            // Send to all players on main thread
            Bukkit.getScheduler().runTask(plugin, () -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(formatted);
                }
            });
        }
    }

    /**
     * Check if message contains any interactive placeholders
     */
    private boolean containsPlaceholder(String message) {
        return message.contains("[ec]") || message.contains("[enderchest]") || message.contains("[e]") ||
               message.contains("[inv]") || message.contains("[inventory]") ||
               message.contains("[item]") || message.contains("[i]") ||
               message.contains("[hold]") || message.contains("[hand]");
    }

    /**
     * Format message by replacing placeholders with colored interactive elements
     */
    private String formatMessage(String message, Player player) {
        String formatted = message;

        // Replace enderchest placeholders
        formatted = formatted.replace("[ec]", ENDERCHEST_TAG);
        formatted = formatted.replace("[enderchest]", ENDERCHEST_TAG);
        formatted = formatted.replace("[e]", ENDERCHEST_TAG);

        // Replace inventory placeholders
        formatted = formatted.replace("[inv]", INVENTORY_TAG);
        formatted = formatted.replace("[inventory]", INVENTORY_TAG);

        // Replace item placeholders
        formatted = formatted.replace("[item]", ITEM_TAG);
        formatted = formatted.replace("[i]", ITEM_TAG);

        // Replace held item placeholders
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        String itemName = (heldItem != null && heldItem.getType().name() != null) 
            ? heldItem.getType().name() 
            : "AIR";
        String itemDisplay = "§e§n[" + itemName + "]§r";
        formatted = formatted.replace("[hold]", itemDisplay);
        formatted = formatted.replace("[hand]", itemDisplay);

        // Add player name and timestamp
        long timestamp = System.currentTimeMillis();
        return String.format("§b%s§r §8[%s]§r: %s", 
            player.getName(), 
            formatTime(timestamp), 
            formatted);
    }

    /**
     * Format timestamp for display
     */
    private String formatTime(long timestamp) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        return sdf.format(new java.util.Date(timestamp));
    }
}
