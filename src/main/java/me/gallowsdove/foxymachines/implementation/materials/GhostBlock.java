package me.gallowsdove.foxymachines.implementation.materials;

import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.PdcCompat;
import io.github.thebusybiscuit.slimefun5.libraries.dough.protection.Interaction;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import me.gallowsdove.foxymachines.utils.CompatUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.gallowsdove.foxymachines.utils.MaterialCompat;

public class GhostBlock extends SlimefunItem {

    public static final NamespacedKey KEY = new NamespacedKey(FoxyMachines.getInstance(), "ghost_block");

    public static final Set<Material> EXCLUDED = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(MaterialCompat.safe(XMaterial.BARRIER), MaterialCompat.safe(XMaterial.SPAWNER), MaterialCompat.safe(XMaterial.COMMAND_BLOCK),
            MaterialCompat.safe(XMaterial.STRUCTURE_BLOCK), MaterialCompat.safe(XMaterial.REPEATING_COMMAND_BLOCK), MaterialCompat.safe(XMaterial.CHAIN_COMMAND_BLOCK), MaterialCompat.safe(XMaterial.JIGSAW))));

    public static final Set<UUID> BLOCK_CACHE = new HashSet<>();

    @Nonnull
    private final Material material;

    public GhostBlock(SlimefunItemStack item) {
        super(Items.GHOST_BLOCKS_ITEM_GROUP, item, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.BILLON_INGOT.item(), SlimefunItems.BILLON_INGOT.item(), SlimefunItems.BILLON_INGOT.item(),
                SlimefunItems.MAGICAL_GLASS.item(), new ItemStack(item.getType()), SlimefunItems.MAGICAL_GLASS.item(),
                SlimefunItems.BILLON_INGOT.item(), SlimefunItems.BILLON_INGOT.item(), SlimefunItems.BILLON_INGOT.item()
        });

        this.material = item.getType();
        setGuideType("decoration");
    }

    @Override
    public void preRegister() {
        addItemHandler(onUse());
    }

    @Nonnull
    private ItemUseHandler onUse() {
        return e -> {
            e.cancel();
            if (!e.getClickedBlock().isPresent()) {
                return;
            }

            Player p = e.getPlayer();
            Block b = e.getClickedBlock().get().getRelative(e.getClickedFace());

            if (!Slimefun.getProtectionManager().hasPermission(p, b, Interaction.PLACE_BLOCK)) {
                p.sendMessage(ChatColor.LIGHT_PURPLE + "You don't have permission to place this here!");
                return;
            }

            if (!b.getWorld().getNearbyEntities(b.getLocation().add(0.5, 0, 0.5), 0.01, 0.01, 0.01).isEmpty()) {
                return;
            }

            // Ghost blocks rely on BlockData (1.13+) + Entity#setPersistent (1.14+). Isolate that in
            // spawnGhostBlock so BlockData is never resolved on legacy servers.
            if (!CompatUtils.ghostBlocksSupported()) {
                p.sendMessage(ChatColor.LIGHT_PURPLE + "Ghost Blocks require Minecraft 1.14 or newer.");
                return;
            }

            spawnGhostBlock(b, e.getInteractEvent().getItem());
        };
    }

    private void spawnGhostBlock(@Nonnull Block b, @Nonnull ItemStack usedItem) {
        FallingBlock block = b.getWorld().spawnFallingBlock(b.getLocation().add(0.5, 0, 0.5), material.createBlockData());
        block.setVelocity(new Vector(0, 0, 0));
        block.setGravity(false);
        block.setDropItem(false);
        block.setPersistent(true);
        block.setInvulnerable(true);
        PdcCompat.setString(block, KEY, "true");

        usedItem.setAmount(usedItem.getAmount() - 1);

        BLOCK_CACHE.add(block.getUniqueId());
    }

    public static boolean isGhostBlock(Entity entity) {
        return PdcCompat.has(entity, KEY, "STRING");
    }
}
