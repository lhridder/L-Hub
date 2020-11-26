package nl.lucasridder.lhub;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;
import java.util.logging.Level;

/**
 * @author Snowp
 */
public class PlayerManager implements Listener {

    FileConfiguration config = LHub.get().getConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String name = player.getName();

        //spawn loc
        try {
            int x = config.getInt("spawn.x", 0);
            int y = config.getInt("spawn.y", 0);
            int z = config.getInt("spawn.z", 0);
            float yaw = config.getInt("spawn.yaw", 0);
            float pitch = config.getInt("spawn.pitch", 0);
            Location loc = new Location(player.getWorld(), x, y, z, pitch, yaw);
            player.teleport(loc);
        } catch (Exception e1) {
            LHub.get().getLogger().log(Level.WARNING, "Could not teleport player to spawn!");
            e1.printStackTrace();
        }

        UUID uuid = player.getUniqueId();
        /* TODO: Re-enable when staff has been implemented
        //check which inv is needed
        if(getConfig().getBoolean("player." + uuid + ".staff")) {
            staffOn(player);
        } else {
            //set inv
            setPlayerInventory(player);
        }
         */

        //tablist
        player.setPlayerListName((player.isOp() ? ChatColor.RED : ChatColor.YELLOW) + player.getName());

        /* TODO: Uncomment this, I have no idea what it does but we'll figure it out along the way.
        //check config for player invis info
        if(!Invis.containsKey(player)) {
            //join message
            if (player.isOp()) {
                //Join message
                e.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_GREEN + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name);
            } else if (!player.hasPlayedBefore()) {
                //Join message
                e.setJoinMessage(ChatColor.DARK_GRAY + "Welkom " + ChatColor.RESET + name);
            } else {
                //Join message
                e.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_GREEN + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.YELLOW + name);
            }
            motd(player);
        } else {
            motd(player);
            e.setJoinMessage(null);
        }
         */

        /* TODO: Will be part of staff features, leave it out for now.
        //get invis staff and nametag
        for(Player players : this.getServer().getOnlinePlayers()) {
            if(Invis.containsKey(players)) {
                //check the players if they are staff
                if(!Invis.containsKey(players)) {
                    players.hidePlayer(this, player);
                }
            }

        }
         */

        //register player in config
        // UUID uuid = player.getUniqueId();
        config.set("player." + uuid + ".name", player.getName());
        LHub.get().saveConfig();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String name = e.getPlayer().getName();
        String message = e.getMessage();


        String format = config.getString("chat.format", "$player $separator $message");
        if(format == null) format = "$player $separator $message";
        String separator = config.getString("chat.separator", "»");
        if(separator == null) format = "»";


        ChatColor playerColor = ChatColor.GRAY;
        ChatColor opColor = ChatColor.GOLD;
        ChatColor separatorColor = ChatColor.DARK_GRAY;
        ChatColor messageColor = ChatColor.RESET;

        try {
            playerColor = ChatColor.of(config.getString("chat.color.player", "GRAY"));
        } catch(Exception ex) {}
        try {
            opColor = ChatColor.of(config.getString("chat.color.opPlayer", "GOLD"));
        } catch(Exception ex) {}
        try {
            separatorColor = ChatColor.of(config.getString("chat.color.separator", "DARK_GRAY"));
        } catch(Exception ex) {}
        try {
            messageColor = ChatColor.of(config.getString("chat.color.message", "RESET"));
        } catch(Exception ex) {}


        format = format.replaceFirst("\\$name", (player.isOp() ? opColor : playerColor) + name);
        format = format.replaceFirst("\\$separator", separatorColor + separator);
        format = format.replaceFirst("\\$message", messageColor + message);

        e.setFormat(format);
    }

}
