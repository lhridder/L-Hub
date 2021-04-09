package nl.lucasridder.lhub.commands;

import nl.lucasridder.lhub.ConfigManager;
import nl.lucasridder.lhub.LHub;
import nl.lucasridder.lhub.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stop implements CommandExecutor {

    LHub plugin = LHub.get();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ConfigManager.getString(Util.color("messages.kickAllPlayers")));
        if(plugin.getServer().getOnlinePlayers().size() != 0) {
            for(Player players : plugin.getServer().getOnlinePlayers()) {
                if(!players.equals(sender)) {
                    players.kickPlayer(ChatColor.GRAY + "De server wordt momenteel herstart" + "\n" +
                            ChatColor.BLUE + "wacht even met opnieuw joinen" + "\n" +
                            ChatColor.YELLOW + "Zie actuele status via: " + ChatColor.AQUA + "https://lucasridder.nl/discord");
                }
            }
        }
        sender.sendMessage(ConfigManager.getString(Util.color("messages.stopServer")));
        plugin.getLogger().info("[LHub] stopping server...");
        plugin.getServer().shutdown();
        return true;
    }
}
