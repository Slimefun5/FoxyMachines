package me.gallowsdove.foxymachines.utils;

import javax.annotation.Nonnull;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

/**
 * Resolves {@link XMaterial} constants to a {@link Material} that exists on the
 * running server. Keeps FoxyMachines loadable on legacy versions (e.g. 1.8) where
 * modern constants like {@code NETHERITE_SWORD} or {@code POLISHED_BLACKSTONE_BRICKS}
 * are absent.
 */
public final class MaterialCompat {

    private MaterialCompat() {}

    @Nonnull
    public static Material safe(@Nonnull XMaterial material) {
        Material resolved = material.parseMaterial();
        if (resolved == null) {
            resolved = substitute(material);
        }
        return resolved != null ? resolved : Material.STONE;
    }

    // Sensible legacy substitutes for materials that don't exist on older servers (e.g. 1.8).
    private static final java.util.Map<XMaterial, XMaterial> LEGACY_SUBSTITUTES = buildLegacySubstitutes();

    private static java.util.Map<XMaterial, XMaterial> buildLegacySubstitutes() {
        java.util.Map<XMaterial, XMaterial> m = new java.util.EnumMap<>(XMaterial.class);
        m.put(XMaterial.NETHERITE_BLOCK, XMaterial.DIAMOND_BLOCK);
        m.put(XMaterial.NETHERITE_INGOT, XMaterial.DIAMOND);
        m.put(XMaterial.NETHERITE_SCRAP, XMaterial.IRON_NUGGET);
        m.put(XMaterial.ANCIENT_DEBRIS, XMaterial.NETHERRACK);
        m.put(XMaterial.BEEHIVE, XMaterial.DISPENSER);
        m.put(XMaterial.BEE_NEST, XMaterial.DISPENSER);
        m.put(XMaterial.HONEY_BLOCK, XMaterial.SLIME_BLOCK);
        m.put(XMaterial.BARREL, XMaterial.CHEST);
        m.put(XMaterial.BLAST_FURNACE, XMaterial.FURNACE);
        m.put(XMaterial.SMOKER, XMaterial.FURNACE);
        m.put(XMaterial.CAMPFIRE, XMaterial.NETHERRACK);
        m.put(XMaterial.SMITHING_TABLE, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.CARTOGRAPHY_TABLE, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.FLETCHING_TABLE, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.LOOM, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.STONECUTTER, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.GRINDSTONE, XMaterial.ANVIL);
        m.put(XMaterial.LANTERN, XMaterial.GLOWSTONE);
        m.put(XMaterial.COMPOSTER, XMaterial.CHEST);
        m.put(XMaterial.MAGMA_BLOCK, XMaterial.NETHERRACK);
        m.put(XMaterial.LODESTONE, XMaterial.IRON_BLOCK);
        m.put(XMaterial.BLACKSTONE, XMaterial.COBBLESTONE);
        m.put(XMaterial.OBSERVER, XMaterial.PISTON);

        // FoxyMachines-specific: Netherite tools/armor (1.16+) -> their Diamond equivalents.
        m.put(XMaterial.NETHERITE_SWORD, XMaterial.DIAMOND_SWORD);
        m.put(XMaterial.NETHERITE_AXE, XMaterial.DIAMOND_AXE);
        m.put(XMaterial.NETHERITE_PICKAXE, XMaterial.DIAMOND_PICKAXE);
        m.put(XMaterial.NETHERITE_SHOVEL, XMaterial.DIAMOND_SHOVEL);
        m.put(XMaterial.NETHERITE_HOE, XMaterial.DIAMOND_HOE);
        m.put(XMaterial.NETHERITE_HELMET, XMaterial.DIAMOND_HELMET);
        m.put(XMaterial.NETHERITE_CHESTPLATE, XMaterial.DIAMOND_CHESTPLATE);
        m.put(XMaterial.NETHERITE_LEGGINGS, XMaterial.DIAMOND_LEGGINGS);
        m.put(XMaterial.NETHERITE_BOOTS, XMaterial.DIAMOND_BOOTS);

        // Nether Update blackstone (1.16+), used by the Sacrificial Altar multiblock.
        m.put(XMaterial.POLISHED_BLACKSTONE_BRICKS, XMaterial.STONE_BRICKS);
        m.put(XMaterial.POLISHED_BLACKSTONE_BRICK_WALL, XMaterial.COBBLESTONE_WALL);
        m.put(XMaterial.POLISHED_BLACKSTONE_BRICK_STAIRS, XMaterial.STONE_BRICK_STAIRS);
        m.put(XMaterial.POLISHED_BLACKSTONE_PRESSURE_PLATE, XMaterial.STONE_PRESSURE_PLATE);

        m.put(XMaterial.SOUL_TORCH, XMaterial.TORCH);
        m.put(XMaterial.HONEYCOMB_BLOCK, XMaterial.HAY_BLOCK);
        m.put(XMaterial.MOJANG_BANNER_PATTERN, XMaterial.PAPER);
        m.put(XMaterial.STRUCTURE_VOID, XMaterial.GLASS);
        m.put(XMaterial.DRAGON_HEAD, XMaterial.SKELETON_SKULL);
        return m;
    }

    private static Material substitute(XMaterial xMaterial) {
        XMaterial sub = LEGACY_SUBSTITUTES.get(xMaterial);
        return sub != null ? sub.parseMaterial() : null;
    }
}
