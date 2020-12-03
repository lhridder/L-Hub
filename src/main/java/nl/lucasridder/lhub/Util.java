package nl.lucasridder.lhub;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Snowp
 */
public class Util {

    public static boolean requirePlayer(CommandSender p) {
        return requirePlayer(p, true);
    }

    public static boolean requirePlayer(CommandSender p, boolean printError) {
        boolean r = (p instanceof Player);
        if(printError && !r) p.sendMessage(ChatColor.RED + "Je bent geen speler!");
        return r;
    }

    public static boolean requirePlayer(Object p) {
        return p instanceof Player;
    }


    public static void sendTarget(Player player, String server) {
        player.sendMessage(ChatColor.DARK_GRAY + "Je wordt nu doorverbonden naar: " + ChatColor.GOLD + server);
        //BUNGEE
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch(IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(LHub.get(), "BungeeCord", b.toByteArray());
    }


    @NotNull
    public static <T> T preventNull(@Nullable T value, @NotNull T d) {
        return (value == null ? d : value);
    }

}
