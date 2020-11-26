package nl.lucasridder.lhub;

import nl.lucasridder.lhub.commands.*;
import nl.lucasridder.lhub.listeners.*;
import nl.lucasridder.lhub.methods.PlayerCount;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

public class LHub extends JavaPlugin implements PluginMessageListener {

    private static LHub plugin;
    public static LHub get()
    {
        return plugin;
    }
    public LHub() {
        LHub.plugin = this;
    }

    //On enable
    @Override
    public void onEnable() {
        //start plugin


        //config
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        //register event listeners
        registerListeners();
        getLogger().info("[LHub] Registered listeners");
        registerCommands();
        getLogger().info("[LHub] Registered commands");
        registerPluginChannel();
        getLogger().info("[LHub] Registered bungee channel");

        //start counter
        new BukkitRunnable() {
            public void run() {
                if(getServer().getOnlinePlayers().size() != 0) {
                    PlayerCount.getCount("all");
                }
            }
        }.runTaskTimer(this, 100, 100);
        getLogger().info("[LHub] Started player listener");

        //finish
        getLogger().info("[LHub] has been successfully enabled");
    }

    //On disable
    @Override
    public void onDisable() {
        //stop plugin
        saveConfig();

        //stop scoreboard
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            onlinePlayers.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        //finish
        getLogger().info("[LHub] has been successfully disabled");
    }

    //register event listeners
    public void registerListeners() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new EntityDamage(), this);
        pm.registerEvents(new EntityInteract(), this);
        pm.registerEvents(new PlayerArmorStand(), this);
        pm.registerEvents(new PlayerChat(), this);
        pm.registerEvents(new PlayerClickInventory(), this);
        pm.registerEvents(new PlayerDrop(), this);
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerLeave(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new PlayerPreCommand(), this);
        pm.registerEvents(new WeatherChange(), this);
    }

    public void registerPluginChannel() {
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    }

    //register command listeners
    public void registerCommands() {
        getCommand("gamemode").setExecutor(new Gamemode());
        getCommand("gamemode").setTabCompleter(new Gamemode());
        getCommand("kitpvp").setExecutor(new KitPvP());
        getCommand("minigames").setExecutor(new Minigames());
        getCommand("playtime").setExecutor(new Playtime());
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("staff").setExecutor(new Staff());
        getCommand("stop").setExecutor(new Stop());
        getCommand("survival").setExecutor(new Staff());
        getCommand("vanish").setExecutor(new Vanish());

    }

    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {

    }
}
