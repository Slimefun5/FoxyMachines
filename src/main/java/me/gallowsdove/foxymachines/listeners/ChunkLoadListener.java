package me.gallowsdove.foxymachines.listeners;

import io.github.thebusybiscuit.slimefun5.libraries.dough.blocks.ChunkPosition;
import me.gallowsdove.foxymachines.abstracts.CustomMob;
import me.gallowsdove.foxymachines.implementation.materials.GhostBlock;
import me.gallowsdove.foxymachines.utils.CompatUtils;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class ChunkLoadListener implements Listener {
    private static final Set<ChunkPosition> SCANNED_CHUNKS = new HashSet<>();

    @EventHandler
    public void onChunkLoad(@Nonnull ChunkLoadEvent e) {
        Chunk chunk = e.getChunk();
        ChunkPosition chunkPosition = new ChunkPosition(chunk);
        if (SCANNED_CHUNKS.contains(chunkPosition)) {
            return;
        }
        SCANNED_CHUNKS.add(chunkPosition);

        boolean customMobs = CompatUtils.customMobsSupported();
        for (Entity entity : chunk.getEntities()) {
            // CustomMob loads a Spellcaster/EntitySpellCastEvent listener (1.9+) in its static init - only
            // touch it where the custom-mob feature is supported so it never resolves on legacy servers.
            if (customMobs) {
                CustomMob customMob = CustomMob.getByEntity(entity);
                if (customMob != null) {
                    customMob.cacheEntity(entity);
                }
            }

            if (entity instanceof FallingBlock && GhostBlock.isGhostBlock(entity)) {
                GhostBlock.BLOCK_CACHE.add(entity.getUniqueId());
            }
        }
    }
}
