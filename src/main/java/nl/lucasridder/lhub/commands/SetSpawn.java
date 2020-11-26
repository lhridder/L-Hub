package nl.lucasridder.lhub.commands;

import nl.lucasridder.lhub.LHub;
import nl.lucasridder.lhub.Util;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    LHub plugin = LHub.get();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(Util.requirePlayer(sender)) {
            Player player = (Player) sender;
            Location loc = player.getLocation();
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();
            float yaw = loc.getYaw();
            float pitch = loc.getPitch();
            plugin.getConfig().set("spawn.x", x);
            plugin.getConfig().set("spawn.y", y);
            plugin.getConfig().set("spawn.z", z);
            plugin.getConfig().set("spawn.yaw", yaw);
            plugin.getConfig().set("spawn.pitch", pitch);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.GREEN + "Spawn set");
        }
        return true;
    }
}
