package nl.lucasridder.lhub;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

/**
 * @author Snowp
 */
public class StaffManager {

    private static StaffManager manager = null;
    public StaffSettings settings;

    private StaffManager(StaffSettings settings) {
        manager = this;
        this.settings = settings;

        LHub plugin = LHub.get();
        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new StaffEvents(), plugin);
        LHub.get().getLogger().info("register staff command");
        plugin.getCommand("staff").setExecutor(new StaffCommand());
    }

    public static StaffManager get() {
        return manager;
    }

    public static void init(StaffSettings settings) {
        LHub.get().getLogger().info("staff init, manager null: " + (StaffManager.manager == null));
        if(StaffManager.manager == null) {
            new StaffManager(settings);
        }
    }


    private static class StaffCommand implements CommandExecutor {
        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
            LHub.get().getLogger().info("/staff exec");
            if(Util.requirePlayer(sender)) {
                LHub.get().getLogger().info("/staff get");
                StaffController staff = StaffController.of((Player) sender);
                if(args.length == 0) {
                    LHub.get().getLogger().info("/staff 0");
                    String res = staff.enable();
                    if(res.equals("")) {
                        LHub.get().getLogger().info("/staff 0 yes");
                        sender.sendMessage("Staff mode enabled!");
                    } else {
                        LHub.get().getLogger().info("/staff 0 no");
                        sender.sendMessage("Could not enable staff mode: " + res);
                    }
                } else if(args.length == 1) {
                    LHub.get().getLogger().info("/staff 1");
                    switch(args[0]) {
                        case "vanish":
                            LHub.get().getLogger().info("/staff vanish");
                            if(staff.isEnabled()) {
                                LHub.get().getLogger().info("/staff vanish yes");
                                sender.sendMessage("Not implemented yet!");
                            } else {
                                LHub.get().getLogger().info("/staff vanish no");
                                sender.sendMessage("Enable staff mode first!");
                            }
                        default:
                            LHub.get().getLogger().info("/staff 1 default");
                            return false;
                    }
                } else {
                    LHub.get().getLogger().info("/staff else false");
                    return false;
                }
            }
            LHub.get().getLogger().info("/staff bottom true");
            return true;
        }
    }

    private static class StaffEvents implements Listener {
        // Cancel all the following if player is not in staff mode
        @EventHandler
        public void onArmorManipulate(PlayerArmorStandManipulateEvent e) {
            e.setCancelled(!StaffController.of(e.getPlayer()).isEnabled());
        }

        @EventHandler
        public void onDrop(PlayerDropItemEvent e) {
            e.setCancelled(!StaffController.of(e.getPlayer()).isEnabled());
        }

        @EventHandler
        public void onBreak(BlockBreakEvent e) {
            e.setCancelled(!StaffController.of(e.getPlayer()).isEnabled());
        }

        @EventHandler
        public void onPlace(BlockPlaceEvent e) {
            e.setCancelled(!StaffController.of(e.getPlayer()).isEnabled());
        }

        // Re-set staff mode properties if a player joins with staff mode enabled
        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent e) {
            StaffController.of(e.getPlayer());
        }

        //Inv click
        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
            Player player = (Player) e.getWhoClicked();
            //cancel if player is not in staff mode
            if(!StaffController.of(player).isEnabled()) {
                e.setCancelled(true);
                /* TODO: This does something, although I'm not sure what. Figure it out some time.
                if(player.isOp()) {
                    staffError(player);
                }*/
            }
        }
    }

}
