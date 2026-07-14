package me.gallowsdove.foxymachines.listeners;

import io.github.thebusybiscuit.slimefun5.utils.compatibility.PdcCompat;
import me.gallowsdove.foxymachines.implementation.materials.GhostBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import me.gallowsdove.foxymachines.FoxyMachines;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GhostBlockListener implements Listener {
    private final Map<UUID, Location> preExplosionLocations = new ConcurrentHashMap<>();
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void onHitByFishingRod(PlayerFishEvent e) {
        if (e.getCaught() instanceof FallingBlock && PdcCompat.has((FallingBlock) e.getCaught(), GhostBlock.KEY, "STRING")) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onEntityExplode(EntityExplodeEvent e) {
        for (Entity ent : e.getLocation().getWorld().getNearbyEntities(e.getLocation(), 6, 6, 6)) {
            if (ent instanceof FallingBlock && GhostBlock.isGhostBlock(ent)) {
                FallingBlock b = (FallingBlock) ent;
                preExplosionLocations.put(b.getUniqueId(), b.getLocation());
            }
        }

        Bukkit.getScheduler().runTaskLater(FoxyMachines.getInstance(), () -> {
            for (Map.Entry<UUID, Location> entry : preExplosionLocations.entrySet()) {
                Entity ent = Bukkit.getEntity(entry.getKey());
                if (ent instanceof FallingBlock && GhostBlock.isGhostBlock(ent)) {
                    FallingBlock fb = (FallingBlock) ent;
                    fb.teleport(entry.getValue());
                    fb.setVelocity(new Vector(0, 0, 0));
                    fb.setGravity(false);
                }
                preExplosionLocations.remove(entry.getKey());
            }
        }, 1L);
    }
}
