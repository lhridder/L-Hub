package nl.lucasridder.lhub.commands;

import nl.lucasridder.lhub.methods.ServerSender;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Snowp
 */
public class ServerTeleport implements CommandExecutor {

    public String server;

    public ServerTeleport(String server) {
        this.server = server;
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Je bent geen speler!");
            return false;
        } else {
            Player player = (Player) sender;
            ServerSender.sendTarget(player, server);
        }
        return true;
    }
}
