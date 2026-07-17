package me.gallowsdove.foxymachines.listeners;

import io.github.thebusybiscuit.slimefun5.api.events.ExplosiveToolBreakBlocksEvent;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.implementation.machines.ForcefieldDome;
import me.gallowsdove.foxymachines.utils.SimpleLocation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import javax.annotation.Nonnull;
import java.util.UUID;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.gallowsdove.foxymachines.utils.MaterialCompat;

public class ForcefieldListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onPlayerBreak(@Nonnull BlockBreakEvent e) {
        Block b = e.getBlock();

        if (ForcefieldDome.FORCEFIELD_BLOCKS.remove(b)) {
            Bukkit.getScheduler().runTask(FoxyMachines.getInstance(), () -> b.setType(MaterialCompat.safe(XMaterial.BARRIER)));
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onExplosionBreak(@Nonnull BlockExplodeEvent e) {
        Block b = e.getBlock();

        if (ForcefieldDome.FORCEFIELD_BLOCKS.remove(b)) {
            Bukkit.getScheduler().runTask(FoxyMachines.getInstance(), () -> b.setType(MaterialCompat.safe(XMaterial.BARRIER)));
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onBurnBreak(@Nonnull BlockBurnEvent e) {
        Block b = e.getBlock();

        if (ForcefieldDome.FORCEFIELD_BLOCKS.remove(b)) {
            Bukkit.getScheduler().runTask(FoxyMachines.getInstance(), () -> b.setType(MaterialCompat.safe(XMaterial.BARRIER)));
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onLeavesDecay(@Nonnull LeavesDecayEvent e) {
        Block b = e.getBlock();

        if (ForcefieldDome.FORCEFIELD_BLOCKS.remove(b)) {
            Bukkit.getScheduler().runTask(FoxyMachines.getInstance(), () -> b.setType(MaterialCompat.safe(XMaterial.BARRIER)));
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onFadeBreak(@Nonnull BlockFadeEvent e) {
        Block b = e.getBlock();

        if (ForcefieldDome.FORCEFIELD_BLOCKS.remove(b)) {
            Bukkit.getScheduler().runTask(FoxyMachines.getInstance(), () -> b.setType(MaterialCompat.safe(XMaterial.BARRIER)));
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onBlockDropEvent(@Nonnull EntityChangeBlockEvent e) {
        if (!(e.getEntity() instanceof FallingBlock)) {
            return;
        }

        Block b = e.getBlock();

        if (ForcefieldDome.FORCEFIELD_BLOCKS.remove(b)) {
            Bukkit.getScheduler().runTask(FoxyMachines.getInstance(), () -> b.setType(MaterialCompat.safe(XMaterial.BARRIER)));
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onBlocksBreakByExplosiveToolEvent(@Nonnull ExplosiveToolBreakBlocksEvent e) {
        for (Block b : e.getAdditionalBlocks()) {
            if (ForcefieldDome.FORCEFIELD_BLOCKS.remove(b)) {
                Bukkit.getScheduler().runTask(FoxyMachines.getInstance(), () -> b.setType(MaterialCompat.safe(XMaterial.BARRIER)));
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onPlayerTeleport(@Nonnull PlayerTeleportEvent e) {
        // CHORUS_FRUIT is a 1.9+ TeleportCause constant; compare by name so it's never referenced
        // directly (a direct reference throws NoSuchFieldError on 1.8, where the constant doesn't exist).
        if (e.getCause() == TeleportCause.ENDER_PEARL || e.getCause().name().equals("CHORUS_FRUIT")) {
            Location l = e.getTo();
            for (SimpleLocation loc: ForcefieldDome.domeLocations) {
                if (e.getPlayer().getWorld() == Bukkit.getServer().getWorld(UUID.fromString(loc.getWorldUUID()))) {
                    int xdif = (int) (l.getX() - loc.getX());
                    int ydif = (int) (l.getY() - loc.getY());
                    int zdif = (int) (l.getZ() - loc.getZ());
                    if (Math.floor(Math.sqrt((xdif * xdif) + (ydif * ydif) + (zdif * zdif))) <= 32) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You can't teleport to a dome!");
                        break;
                    }
                }
            }
        }
    }
}
