package nl.lucasridder.lhub.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    //Chat
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String name = e.getPlayer().getName();
        String message = e.getMessage();
        if(player.isOp()) {
            e.setFormat(ChatColor.GOLD + name + ChatColor.DARK_GRAY + " » " + ChatColor.RESET + message);
        } else {
            e.setFormat(ChatColor.GRAY + name + ChatColor.DARK_GRAY + " » " + ChatColor.RESET + message);
        }

        //TODO redis
    }

}
