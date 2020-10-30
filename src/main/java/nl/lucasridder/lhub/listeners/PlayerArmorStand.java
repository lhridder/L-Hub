package nl.lucasridder.lhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class PlayerArmorStand implements Listener {

    //Armorstand cancel
    @EventHandler
    public void armorStand(PlayerArmorStandManipulateEvent e) {
        //cancel if player is not in staff mode
        e.setCancelled(!Staff.containsKey(e.getPlayer()));
    }
}
