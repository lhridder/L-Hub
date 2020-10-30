package nl.lucasridder.lhub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerPreCommand implements Listener {

    // Command blocker
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        String message = e.getMessage();
        Player player = e.getPlayer();

        if(!player.isOp()) {
            if(!(message.equals("/help")
            | message.startsWith("/playtime")
            | message.startsWith("/pt")
            | message.equals("/survivall")
            | message.equals("/minigames")
            | message.equals("/kitpvp"))) e.setCancelled(true);
        }
    }
}
