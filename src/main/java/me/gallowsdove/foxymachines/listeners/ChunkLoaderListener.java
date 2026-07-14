package me.gallowsdove.foxymachines.listeners;

import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun5.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun5.utils.SlimefunUtils;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.gallowsdove.foxymachines.utils.MaterialCompat;

public class ChunkLoaderListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onChunkLoaderPlace(@Nonnull BlockPlaceEvent e) {
        if(e.getBlock().getType() != MaterialCompat.safe(XMaterial.BEACON)) {
            return;
        }

        ItemStack item = e.getItemInHand();
        Player p = e.getPlayer();

        if (!SlimefunUtils.isItemSimilar(item, Items.CHUNK_LOADER.item(), true, false)) {
            return;
        }

        Block b = e.getBlockPlaced();
        if (b.getChunk().isForceLoaded()) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.LIGHT_PURPLE + "This chunk is already loaded!");
            return;
        }

        String key = "foxymachines:chunkloaders";

        int i = PersistentDataAPI.getInt(p, key, 0) + 1;
        Config cfg = new Config(FoxyMachines.getInstance());
        if (!p.hasPermission("foxymachines.bypass-chunk-loader-limit")) {
            int max = cfg.getInt("max-chunk-loaders");
            if (max != 0 && max < i) {
                p.sendMessage(ChatColor.LIGHT_PURPLE + "Maximum amount of chunk loaders already placed: " + max);
                e.setCancelled(true);
                return;
            }
        }
        int currentComplexity = Slimefun.getGPSNetwork().getNetworkComplexity(p.getUniqueId());
        int requiredComplexity = cfg.getInt("gps-complexity-per-loader") * i;
        if (currentComplexity < requiredComplexity) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + "You have " + currentComplexity + "/" + requiredComplexity + " GPS Network Complexity required to place another Chunk Loader.");
            e.setCancelled(true);
            return;
        }

        PersistentDataAPI.setInt(p, key, i);
        b.getChunk().setForceLoaded(true);
        BlockStorage.addBlockInfo(b, "owner", p.getUniqueId().toString());
    }
}
