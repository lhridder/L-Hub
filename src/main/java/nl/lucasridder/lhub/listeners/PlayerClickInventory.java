package nl.lucasridder.lhub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerClickInventory implements Listener {

    //Inv click
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        //cancel if player is not in staff mode
        if(!Staff.containsKey(player)) {
            e.setCancelled(true);
            if(player.isOp()) {
                staffError(player);
            }
        }
    }

}
