package nl.lucasridder.lhub.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Playtime implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        //eigen playtime
        if(args.length == 0) {
            //get source
            int ptt = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
            //calculate
            int pts = ptt / 20; //seconds
            int ptm = pts / 60; //minutes
            int pth = ptm / 60; //hours
            int ptd = pth / 24; //dagen

            int rm = ptm - (pth * 60); //res minutes
            int rs = pts - (ptm * 60); //res seconds
            int rh = pth - (ptd * 24); //res hours

            //report
            player.sendMessage(ChatColor.GREEN + "Hub playtime: "
                    + ChatColor.GOLD + ptd + ChatColor.GREEN + " dagen, "
                    + ChatColor.GOLD + rh + ChatColor.GREEN + " uren, "
                    + ChatColor.GOLD + rm + ChatColor.GREEN + " minuten en "
                    + ChatColor.GOLD + rs + ChatColor.GREEN + " seconden");
            return true;
        }
        if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                sender.sendMessage(ChatColor.RED + "Doel is niet online");
                return true;
            } else {
                String name = player.getName();
                //get source
                int ptt = target.getStatistic(Statistic.PLAY_ONE_MINUTE);
                //calculate
                int pts = ptt / 20; //seconds
                int ptm = pts / 60; //minutes
                int pth = ptm / 60; //hours
                int ptd = pth / 24; //dagen

                int rm = ptm - (pth * 60); //res minutes
                int rs = pts - (ptm * 60); //res seconds
                int rh = pth - (ptd * 24); //res hours

                //report
                player.sendMessage(ChatColor.GOLD + name + ":" + ChatColor.GREEN + " hub playtime: "
                        + ChatColor.GOLD + ptd + ChatColor.GREEN + " dagen, "
                        + ChatColor.GOLD + rh + ChatColor.GREEN + " uren, "
                        + ChatColor.GOLD + rm + ChatColor.GREEN + " minuten en "
                        + ChatColor.GOLD + rs + ChatColor.GREEN + " seconden");
                return true;
            }
        }
        return true;
    }

}
