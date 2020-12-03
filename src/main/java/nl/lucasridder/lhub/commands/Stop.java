package nl.lucasridder.lhub.commands;

import nl.lucasridder.lhub.LHub;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stop implements CommandExecutor {

    LHub plugin = LHub.get();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "Kicking all players...");
        if(plugin.getServer().getOnlinePlayers().size() != 0) {
            for(Player players : plugin.getServer().getOnlinePlayers()) {
                if(!players.equals(sender)) {
                    players.kickPlayer(ChatColor.GRAY + "De server wordt momenteel herstart" + "\n" +
                            ChatColor.BLUE + "wacht even met opnieuw joinen" + "\n" +
                            ChatColor.YELLOW + "Zie actuele status via: " + ChatColor.AQUA + "https://lucasridder.nl/discord");
                }
            }
        }
        sender.sendMessage(ChatColor.GREEN + "Stopping server...");
        plugin.getLogger().info("[LHub] stopping server...");
        plugin.getServer().shutdown();
        return true;
    }
}
