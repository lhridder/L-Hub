package nl.lucasridder.lhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDrop implements Listener {

    //Drop
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        //cancel if player is not in staff mode
        e.setCancelled(!Staff.containsKey(e.getPlayer()));
    }

}
