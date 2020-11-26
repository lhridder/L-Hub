package nl.lucasridder.lhub.methods;

import nl.lucasridder.lhub.LHub;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleport {

    LHub plugin = LHub.get();

    public static void teleportSpawn(Player player) {
        //get spawn
        int x = plugin.getConfig().getInt("spawn.x");
        int y = plugin.getConfig().getInt("spawn.y");
        int z = plugin.getConfig().getInt("spawn.z");
        float yaw = plugin.getConfig().getInt("spawn.yaw");
        float pitch = plugin.getConfig().getInt("spawn.pitch");
        String world = plugin.getConfig().getString("spawn.world");

        Location spawn = new Location(world, x, y, z, pitch, yaw);
        //teleport
        player.teleport(spawn);
    }

}
