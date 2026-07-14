package me.gallowsdove.foxymachines.implementation.tools;

import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.libraries.commons.lang.StringUtils;
import io.github.thebusybiscuit.slimefun5.libraries.dough.data.persistent.PersistentDataAPI;
import me.gallowsdove.foxymachines.Items;
import me.gallowsdove.foxymachines.implementation.materials.GhostBlock;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class GhostBlockRemover extends SlimefunItem {

    public GhostBlockRemover() {
        super(Items.TOOLS_ITEM_GROUP, Items.GHOST_BLOCK_REMOVER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                Items.DAMIENIUM.item(), Items.DAMIENIUM.item(), Items.DAMIENIUM.item(),
                Items.SWEET_INGOT.item(), SlimefunItems.BASIC_CIRCUIT_BOARD.item(), Items.SWEET_INGOT.item(),
                Items.DAMIENIUM.item(), Items.DAMIENIUM.item(), Items.DAMIENIUM.item()
        });
    }

    @Override
    public void preRegister() {
        addItemHandler(onInteract());
    }

    @Nonnull
    protected EntityInteractHandler onInteract() {
        return (e, itemStack, b) -> {
            if (e.getRightClicked() instanceof FallingBlock && PersistentDataAPI.hasString((FallingBlock) e.getRightClicked(), GhostBlock.KEY)) {
                FallingBlock block = (FallingBlock) e.getRightClicked();
                Material material = block.getBlockData().getMaterial();
                SlimefunItemStack stack = new SlimefunItemStack(
                        "GHOST_BLOCK_" + material.name().toUpperCase(),
                        material,
                        "Ghost Block: &6" + StringUtils.capitalize(material.name().replace("_", " ").toLowerCase()),
                        "",
                        "&7An intangible block.");

                block.getWorld().dropItemNaturally(block.getLocation(), stack.item());
                block.remove();
            }
        };
    }
}
