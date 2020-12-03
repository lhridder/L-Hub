package nl.lucasridder.lhub;

import net.md_5.bungee.api.ChatColor;
import nl.lucasridder.lhub.commands.*;
import nl.lucasridder.lhub.listeners.EntityInteract;
import nl.lucasridder.lhub.listeners.PlayerLeave;
import nl.lucasridder.lhub.listeners.PlayerMove;
import nl.lucasridder.lhub.listeners.WeatherChange;
import nl.lucasridder.lhub.methods.PlayerCount;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class LHub extends JavaPlugin implements PluginMessageListener {

    private static LHub plugin;

    public static LHub get() {
        return plugin;
    }

    public LHub() {
        LHub.plugin = this;
    }

    public int playerCount = -1;

    //On enable
    @Override
    public void onEnable() {
        //start plugin


        //config
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        //register event listeners
        StaffManager.init(StaffSettings.SURVIVAL);
        getLogger().info("[LHub] Initialized staff manager");
        registerListeners();
        getLogger().info("[LHub] Registered listeners");
        registerCommands();
        getLogger().info("[LHub] Registered commands");
        registerPluginChannel();
        getLogger().info("[LHub] Registered bungee channel");

        //start counter
        new BukkitRunnable() {
            public void run() {
                if(getServer().getOnlinePlayers().size() > 0) {
                    LHub.get().playerCount = PlayerCount.getGlobalPlayerCount();

                    for(Player player : Bukkit.getOnlinePlayers()) {
                        player.setScoreboard(generateScoreboard(player));
                    }
                }
            }
        }.runTaskTimer(this, 100, 100);
        getLogger().info("[LHub] Started scoreboard updater");

        //finish
        getLogger().info("[LHub] has been successfully enabled");
    }

    //On disable
    @Override
    public void onDisable() {
        //stop plugin
        saveConfig();

        //finish
        getLogger().info("[LHub] has been successfully disabled");
    }

    //register event listeners
    public void registerListeners() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new EntityInteract(), this);
        pm.registerEvents(new PlayerLeave(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new WeatherChange(), this);

        pm.registerEvents(new PlayerManager(), this);
    }

    public void registerPluginChannel() {
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    }

    //register command listeners
    public void registerCommands() {
        getCommand("gamemode").setExecutor(new Gamemode());
        getCommand("gamemode").setTabCompleter(new Gamemode());
        getCommand("kitpvp").setExecutor(new ServerTeleport("kitpvp"));
        getCommand("minigames").setExecutor(new ServerTeleport("minigames"));
        getCommand("hub").setExecutor(new ServerTeleport("lobby"));
        getCommand("playtime").setExecutor(new Playtime());
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("stop").setExecutor(new Stop());
        getCommand("survival").setExecutor(new ServerTeleport("survival"));

    }

    private Scoreboard generateScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard b = manager.getNewScoreboard();

        Objective o = b.registerNewObjective("Gold", "", ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Lobby");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);

        //staff 4 t/m 6
        //check if player has staff permission
        if(player.isOp()) {
            //spacer 7
            o.getScore(ChatColor.RED + "").setScore(7);

            o.getScore(ChatColor.DARK_GREEN + "Staffmode: " + ChatColor.RED + "✘").setScore(6);

            /* TODO: Fix staff system and enable this again.
            //if staff mode enabled
            if(Staff.containsKey(player)) {
                //staff on 6
                o.getScore(ChatColor.DARK_GREEN + "Staffmode: " + ChatColor.GREEN + "✔").setScore(6);
                //check invis
                if(Invis.containsKey(player)) {
                    //invis on 5
                    o.getScore(ChatColor.BLUE + "  Invisability: " + ChatColor.GREEN + "✔").setScore(5);
                } else {
                    //invis off 5
                    o.getScore(ChatColor.BLUE + "  Invisability: " + ChatColor.RED + "✘").setScore(5);
                }
            } else {
                //staff off 6
                o.getScore(ChatColor.DARK_GREEN + "Staffmode: " + ChatColor.RED + "✘").setScore(6);
            }
             */

        } else {
            //spacer 6
            o.getScore(ChatColor.RED + "").setScore(6);

            //Welkom *speler* 5
            o.getScore(ChatColor.YELLOW + "Welkom, " + ChatColor.GRAY + player.getName()).setScore(5);
        }

        //spacer 4
        o.getScore(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "").setScore(4);

        //totaal spelers proxy 3
        o.getScore(ChatColor.YELLOW + "Totaal spelers: " + ChatColor.RED + this.playerCount).setScore(3);

        //totaal spelers hub 2
        int spelers = getServer().getOnlinePlayers().size();
        /* TODO: Implement StaffManager and re-enable
        int invis = Invis.size();
        int hub = spelers - invis;
         */
        o.getScore(ChatColor.BLUE + "  Hub: " + ChatColor.RED + spelers).setScore(2);


        //spacer 1
        o.getScore("").setScore(1);

        //server footer 0
        o.getScore(ChatColor.GREEN + "LucasRidder.nl").setScore(0);

        return b;
    }

    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {

    }
}
