package com.interactive.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatListener implements Listener {

    private JavaPlugin plugin;

    public ChatListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        // Check if message contains interactive placeholders
        if (message.contains("[ec]") || message.contains("[enderchest]") || message.contains("[e]") ||
            message.contains("[inv]") || message.contains("[inventory]") ||
            message.contains("[item]") || message.contains("[i]")) {

            event.setCancelled(true);

            // Format and send message
            String formatted = formatMessage(message, player);
            
            Bukkit.getScheduler().runTask(plugin, () -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(formatted);
                }
            });
        }
    }

    private String formatMessage(String message, Player player) {
        String formatted = message;

        // Replace enderchest placeholders
        if (formatted.contains("[ec]") || formatted.contains("[enderchest]") || formatted.contains("[e]")) {
            formatted = formatted.replace("[ec]", "§3§n[ENDERCHEST]§r");
            formatted = formatted.replace("[enderchest]", "§3§n[ENDERCHEST]§r");
            formatted = formatted.replace("[e]", "§3§n[ENDERCHEST]§r");
        }

        // Replace inventory placeholders
        if (formatted.contains("[inv]") || formatted.contains("[inventory]")) {
            formatted = formatted.replace("[inv]", "§3§n[INVENTORY]§r");
            formatted = formatted.replace("[inventory]", "§3§n[INVENTORY]§r");
        }

        // Replace item placeholders
        if (formatted.contains("[item]") || formatted.contains("[i]")) {
            formatted = formatted.replace("[item]", "§3§n[ITEM]§r");
            formatted = formatted.replace("[i]", "§3§n[ITEM]§r");
        }

        // Add player name at the start
        return "§b" + player.getName() + "§r: " + formatted;
    }
}
