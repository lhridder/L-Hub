package nl.lucasridder.lhub.methods;

import nl.lucasridder.lhub.LHub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerSender {

    public static void sendTarget(Player player, String server) {
        player.sendMessage(ChatColor.DARK_GRAY + "Je wordt nu doorverbonden naar: " + ChatColor.GOLD + server);
        //BUNGEE
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(LHub.get(), "BungeeCord", b.toByteArray());
        //TODO PlayerBoolean.put(player, server);

        /*
        //remove player from PlayerBoolean
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                PlayerBoolean.remove(player);
            }
        }, 5*20L);

         */
    }
}
