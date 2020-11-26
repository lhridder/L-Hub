package nl.lucasridder.lhub.commands;

import nl.lucasridder.lhub.Util;
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
        if (Util.requirePlayer(sender)) {
            Player player = (Player) sender;
            Util.sendTarget(player, server);
        }
        return true;
    }
}
