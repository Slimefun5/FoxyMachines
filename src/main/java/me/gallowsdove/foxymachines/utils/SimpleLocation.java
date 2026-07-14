package me.gallowsdove.foxymachines.utils;

import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.PdcCompat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import me.gallowsdove.foxymachines.FoxyMachines;
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
        PdcCompat.setInt(container, getXKey(this.prefix), this.x);
        PdcCompat.setInt(container, getYKey(this.prefix), this.y);
        PdcCompat.setInt(container, getZKey(this.prefix), this.z);
        PdcCompat.setString(container, getWorldKey(this.prefix), this.worldUUID);
    }

    @Nullable
    public static SimpleLocation fromPersistentStorage(@Nonnull Object container, @Nonnull String prefix) {
        if (PdcCompat.has(container, getWorldKey(prefix), "STRING")) {
            return new SimpleLocation(PdcCompat.getInt(container, getXKey(prefix)), PdcCompat.getInt(container, getYKey(prefix)),
                    PdcCompat.getInt(container, getZKey(prefix)), PdcCompat.getString(container, getWorldKey(prefix)), prefix);
        } else {
            return null;
        }
    }

    private static NamespacedKey getWorldKey(@Nonnull String prefix) {
        return new NamespacedKey(FoxyMachines.getInstance(), prefix + "_world");
    }

    private static NamespacedKey getXKey(@Nonnull String prefix) {
        return new NamespacedKey(FoxyMachines.getInstance(), prefix + "_x");
    }

    private static NamespacedKey getYKey(@Nonnull String prefix) {
        return new NamespacedKey(FoxyMachines.getInstance(), prefix + "_y");
    }

    private static NamespacedKey getZKey(@Nonnull String prefix) {
        return new NamespacedKey(FoxyMachines.getInstance(), prefix + "_z");
    }

    public String toString() {
        return "X: " + this.x + " Y: " + this.y + " Z: " + this.z;
    }
}
