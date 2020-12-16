package nl.lucasridder.lhub.commands;

import nl.lucasridder.lhub.ConfigManager;
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
            ConfigManager.set("spawn.x", x);
            ConfigManager.set("spawn.y", y);
            ConfigManager.set("spawn.z", z);
            ConfigManager.set("spawn.yaw", Math.round(yaw));
            ConfigManager.set("spawn.pitch", Math.round(pitch));
            ConfigManager.save();
            sender.sendMessage(ChatColor.GREEN + "Spawn set");
        }
        return true;
    }
}
