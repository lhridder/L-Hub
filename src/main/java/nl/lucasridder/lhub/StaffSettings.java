package nl.lucasridder.lhub;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;

/**
 * This class contains the settings for staff mode for a specific server.
 * For example, on a survival server players would be in survival but a moderator
 * in staff mode would be in creative.
 * More settings can be defined at a later point, but isn't needed at the moment.
 *
 * @author Snowp
 */
public class StaffSettings {

    private final StaffPermissionChecker checker;
    public final GameMode playerMode;
    public final GameMode staffMode;

    public StaffSettings(GameMode playerMode, GameMode staffMode) {
        this(ServerOperator::isOp, playerMode, staffMode);
    }

    public StaffSettings(StaffPermissionChecker checker, GameMode playerMode, GameMode staffMode) {
        this.checker = checker;
        this.playerMode = playerMode;
        this.staffMode = staffMode;
    }

    public boolean hasPermission(Player p) {
        return this.checker.hasPermission(p);
    }


    public static final StaffSettings SURVIVAL = new StaffSettings(
            GameMode.SURVIVAL,
            GameMode.CREATIVE
    );


    /**
     * Interface to define whether a player has permission to use staff mode or not.
     * Override this interface and define your own hasPermission function to determine for each player whether they have permission.
     */
    public interface StaffPermissionChecker {
        /**
         * Checks whether a {@link Player} has permission to use staff mode.
         *
         * @param player The {@link Player} to be checked.
         * @return Whether {@code player} has permission to use staff mode
         */
        boolean hasPermission(Player player);
    }

}
