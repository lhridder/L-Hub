package nl.lucasridder.lhub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitPvP implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            //zeg het
            sender.sendMessage(ChatColor.RED + "Je bent geen speler");
        } else {
            Player player = (Player) sender;
            SendPlayer(player, "kitpvp");
        }
        return true;
    }
}
