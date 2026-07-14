package me.gallowsdove.foxymachines.utils;

import io.github.thebusybiscuit.slimefun5.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode
public class SimpleLocation {

    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;
    @Getter @Setter
    private int z;
    @Getter
    private final String worldUUID;
    @Getter
    private final String prefix;

    @Nonnull
    public Block toBlock() {
        return Bukkit.getServer().getWorld(UUID.fromString(this.worldUUID)).getBlockAt(this.x, this.y, this.z);
    }

    public SimpleLocation(@Nonnull Block b, @Nonnull String prefix) {
        this(b.getLocation(), prefix);
    }

    public SimpleLocation(@Nonnull Location loc, @Nonnull String prefix) {
        this.worldUUID = loc.getWorld().getUID().toString();
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
        this.prefix = prefix;
    }

    public void storePersistently(@Nonnull Object container) {
        PersistentDataAPI.setInt(container, getXKey(this.prefix), this.x);
        PersistentDataAPI.setInt(container, getYKey(this.prefix), this.y);
        PersistentDataAPI.setInt(container, getZKey(this.prefix), this.z);
        PersistentDataAPI.setString(container, getWorldKey(this.prefix), this.worldUUID);
    }

    @Nullable
    public static SimpleLocation fromPersistentStorage(@Nonnull Object container, @Nonnull String prefix) {
        if (PersistentDataAPI.hasString(container, getWorldKey(prefix))) {
            return new SimpleLocation(PersistentDataAPI.getInt(container, getXKey(prefix)), PersistentDataAPI.getInt(container, getYKey(prefix)),
                    PersistentDataAPI.getInt(container, getZKey(prefix)), PersistentDataAPI.getString(container, getWorldKey(prefix)), prefix);
        } else {
            return null;
        }
    }

    private static String getWorldKey(@Nonnull String prefix) {
        return "foxymachines:" + prefix + "_world";
    }

    private static String getXKey(@Nonnull String prefix) {
        return "foxymachines:" + prefix + "_x";
    }

    private static String getYKey(@Nonnull String prefix) {
        return "foxymachines:" + prefix + "_y";
    }

    private static String getZKey(@Nonnull String prefix) {
        return "foxymachines:" + prefix + "_z";
    }

    public String toString() {
        return "X: " + this.x + " Y: " + this.y + " Z: " + this.z;
    }
}
