package nl.lucasridder.lhub.commands;

import nl.lucasridder.lhub.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Gamemode implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(Util.requirePlayer(sender)) {
            Player player = (Player) sender;
            //continue

            //te weinig args
            if(args.length == 0 | args.length > 2) {
                player.sendMessage(ChatColor.RED + "/gamemode (creative/survival/spectator/adventure/0/1/2/3) (speler)");
            }

            //own gamemode
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("creative") | args[0].equalsIgnoreCase("1")) {
                    player.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(ChatColor.GREEN + "Gedaan.");
                } else if(args[0].equalsIgnoreCase("survival") | args[0].equalsIgnoreCase("0")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(ChatColor.GREEN + "Gedaan.");
                } else if(args[0].equalsIgnoreCase("spectator") | args[0].equalsIgnoreCase("3")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage(ChatColor.GREEN + "Gedaan.");
                } else if(args[0].equalsIgnoreCase("adventure") | args[0].equalsIgnoreCase("2")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(ChatColor.GREEN + "Gedaan.");
                } else {
                    player.sendMessage(ChatColor.RED + "/gamemode (creative/survival/spectator/adventure/0/1/2/3) (speler)");
                }
                return true;
            }


            //other player gamemode
            if(args.length == 2) {
                Player target = Bukkit.getServer().getPlayer(args[1]);
                if(target == null) {
                    sender.sendMessage(ChatColor.RED + "Doel is niet online");
                    return true;
                } else {
                    if(args[0].equalsIgnoreCase("creative") | args[0].equalsIgnoreCase("1")) {
                        target.setGameMode(GameMode.CREATIVE);
                        target.sendMessage(ChatColor.GREEN + "Gamemode aangepast.");
                        sender.sendMessage(ChatColor.GREEN + "Gedaan.");
                    } else if(args[0].equalsIgnoreCase("survival") | args[0].equalsIgnoreCase("0")) {
                        target.setGameMode(GameMode.SURVIVAL);
                        target.sendMessage(ChatColor.GREEN + "Gamemode aangepast.");
                        sender.sendMessage(ChatColor.GREEN + "Gedaan.");
                    } else if(args[0].equalsIgnoreCase("spectator") | args[0].equalsIgnoreCase("3")) {
                        target.setGameMode(GameMode.SPECTATOR);
                        target.sendMessage(ChatColor.GREEN + "Gamemode aangepast.");
                        sender.sendMessage(ChatColor.GREEN + "Gedaan.");
                    } else if(args[0].equalsIgnoreCase("adventure") | args[0].equalsIgnoreCase("2")) {
                        target.setGameMode(GameMode.ADVENTURE);
                        target.sendMessage(ChatColor.GREEN + "Gamemode aangepast.");
                        sender.sendMessage(ChatColor.GREEN + "Gedaan.");
                    } else {
                        sender.sendMessage(ChatColor.RED + "/gamemode (creative/survival/spectator/adventure)/(0/1/2/3) (speler)");
                    }
                    return true;
                }
            }
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // only /gamemode
        if(args.length == 0) {
            //create new gamemodes list
            List<String> gamemodes = new ArrayList<String>();

            //add gamemodes
            gamemodes.add("creative");
            gamemodes.add("survival");
            gamemodes.add("spectator");
            gamemodes.add("adventure");
            gamemodes.add("0");
            gamemodes.add("1");
            gamemodes.add("2");
            gamemodes.add("3");

            //finish
            return gamemodes;
        } else if(args.length == 1) {
            List<String> players = new ArrayList<String>();
            for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                players.add(player.getName());
            }
            return players;
        } else {
            return null;
        }
    }
}
