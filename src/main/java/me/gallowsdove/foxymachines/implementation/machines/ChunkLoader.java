package me.gallowsdove.foxymachines.implementation.machines;

import io.github.mooy1.infinitylib.common.Scheduler;
import io.github.thebusybiscuit.slimefun5.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun5.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.PdcCompat;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.gallowsdove.foxymachines.utils.MaterialCompat;


public class ChunkLoader extends SlimefunItem {
    public ChunkLoader() {
        super(Items.MACHINES_ITEM_GROUP, Items.CHUNK_LOADER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                Items.REINFORCED_STRING.item(), Items.STABILIZED_BLISTERING_BLOCK.item(), Items.REINFORCED_STRING.item(),
                SlimefunItems.ENRICHED_NETHER_ICE.item(), Items.STABILIZED_BLISTERING_BLOCK.item(), Items.WIRELESS_TRANSMITTER.item(),
                Items.REINFORCED_STRING.item(), Items.STABILIZED_BLISTERING_BLOCK.item(), Items.REINFORCED_STRING.item()
        });
    }

    @Override
    public void preRegister() {
        addItemHandler(onBreak(), onBlockUse());
    }

    @Nonnull
    private BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@Nonnull BlockBreakEvent e, @Nonnull ItemStack item, @Nonnull List<ItemStack> drops) {
                Block b = e.getBlock();
                if (BlockStorage.getLocationInfo(b.getLocation(), "owner") != null) {
                    NamespacedKey key = new NamespacedKey(FoxyMachines.getInstance(), "chunkloaders");
                    Player p = Bukkit.getPlayer(UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")));

                    int i = PdcCompat.getInt(p, key) - 1;
                    PdcCompat.setInt(p, key, i);

                    b.getChunk().setForceLoaded(false);
                    BlockStorage.clearBlockInfo(b);
                }

                Scheduler.run(() -> b.setType(MaterialCompat.safe(XMaterial.GLASS)));
            }
        };
    }

    @Nonnull
    private BlockUseHandler onBlockUse() {
        return PlayerRightClickEvent::cancel;
    }

}
