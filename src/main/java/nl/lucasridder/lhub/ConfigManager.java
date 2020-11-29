package nl.lucasridder.lhub;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigManager {
    private static final LHub plugin = LHub.get();
    // Possible bug: This might be null depending on when java executes this.
    //               Requires testing.
    private static final FileConfiguration config = LHub.get().getConfig();


    public static int getInt(String key) {
        return ConfigManager.getInt(key, 0);
    }

    public static int getInt(String key, int d) {
        // Shouldn't be necessary because an int cannot be null, but added anyway.
        return Util.preventNull(config.getInt(key, d), d);
    }

    @NotNull
    public static String getString(@NotNull String key) {
        return ConfigManager.getString(key, "");
    }

    @NotNull
    public static String getString(@NotNull String key, @NotNull String d) {
        return Util.preventNull(config.getString(key, d), d);
    }

    @Nullable
    public static Object get(@NotNull String key) {
        return config.get(key);
    }

    @NotNull
    public static Object get(@NotNull String key, @NotNull Object d) {
        return Util.preventNull(config.get(key, d), d);
    }


    public static void set(@NotNull String key, @NotNull Object value) {
        config.set(key, value);
    }

    public static void save() {
        plugin.saveConfig();
    }

    public static void setAndSave(@NotNull String key, @NotNull Object value) {
        set(key, value);
        save();
    }
}
