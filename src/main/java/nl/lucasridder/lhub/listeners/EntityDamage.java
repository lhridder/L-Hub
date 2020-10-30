package nl.lucasridder.lhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    //No Damage
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        //cancel any damage
        e.setCancelled(true);
    }
}
