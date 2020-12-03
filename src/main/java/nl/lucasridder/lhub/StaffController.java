package nl.lucasridder.lhub;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

/**
 * StaffController controls staff options for a specific player.
 * It works tightly with {@link StaffManager}
 *
 * @author Snowp
 */
public class StaffController {

    private static final HashMap<UUID, StaffController> controllers = new HashMap<>();

    private final OfflinePlayer player;
    private boolean online = false;
    private Player onlinePlayer;
    private boolean permission;
    private boolean enabled = false;

    private StaffController(Player player) {
        this.player = player;
        this.permission = StaffManager.get().settings.hasPermission(player);
    }

    /**
     * Called whenever this {@link StaffController} is retrieved.
     *
     * @return Returns itself for method chaining in {@link StaffController#of(Player)}.
     */
    private StaffController get() {
        this.refresh();
        if(enabled) enable();
        else disable();

        return this;
    }

    /**
     * Refresh player modes and views based on staff settings for that player.
     */
    private void refresh() {
        online = player.isOnline();
        if(online) {
            onlinePlayer = player.getPlayer();
            this.permission = StaffManager.get().settings.hasPermission(onlinePlayer);
        }
    }

    /**
     * Enables staff mode for the player represented by this {@link StaffController}
     *
     * @return A string containing an error, if any.
     */
    public String enable() {
        this.refresh();
        if(!online) return "The target player is not online";

        this.permission = StaffManager.get().settings.hasPermission(this.onlinePlayer);
        if(!permission) return "The target player doesn't have permission to use staff mode";
        this.onlinePlayer.setGameMode(StaffManager.get().settings.staffMode);
        enabled = true;
        return "";
    }

    public String disable() {
        this.refresh();
        if(!online) return "The target player is not online";

        onlinePlayer.setGameMode(StaffManager.get().settings.playerMode);
        enabled = false;
        return "";
    }

    public boolean isEnabled() {
        return this.enabled;
    }


    public static boolean has(@NotNull OfflinePlayer player) {
        return controllers.containsKey(player.getUniqueId());
    }

    public static StaffController of(@NotNull Player player) {
        // Shouldn't happen, but put here just to be sure.
        // Will prevent everything from breaking down if someone forgets to do StaffManager.init(..)
        if(StaffManager.get() == null) {
            throw new Error("Could not retrieve StaffController for player '" + player.getName() + "', because StaffManager wasn't initialized.");
        }

        UUID playerUUID = player.getUniqueId();

        if(controllers.containsKey(playerUUID)) return controllers.get(playerUUID).get();

        StaffController newController = new StaffController(player);
        controllers.put(playerUUID, newController);
        return newController.get();
    }

}
