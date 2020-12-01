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
    private final boolean permission;
    private boolean enabled = false;

    private StaffController(Player player) {
        this.player = player;
        this.permission = StaffManager.get().settings.hasPermission(player);
    }

    /**
     * Refresh player modes and views based on staff settings for that player.
     */
    public void refresh() {
        if (enabled) enable();
        else disable();
    }

    public boolean enable() {
        if (!permission) return false;

        Player onlinePlayer = player.getPlayer();
        if (onlinePlayer != null) {
            onlinePlayer.setGameMode(StaffManager.get().settings.staffMode);
        }

        enabled = true;
        return true;
    }

    public void disable() {
        Player onlinePlayer = player.getPlayer();
        if (onlinePlayer != null) {
            onlinePlayer.setGameMode(StaffManager.get().settings.playerMode);
        }

        enabled = false;
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
        if (StaffManager.get() == null) return null;

        UUID playerUUID = player.getUniqueId();

        if (controllers.containsKey(playerUUID)) return controllers.get(playerUUID);

        StaffController newController = new StaffController(player);
        controllers.put(playerUUID, newController);
        return newController;
    }

}
