package nl.lucasridder.lhub.commands;

import nl.lucasridder.lhub.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Vanish implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(Util.requirePlayer(sender)) {
            //TODO Vanish
        }
        return true;
    }
}
