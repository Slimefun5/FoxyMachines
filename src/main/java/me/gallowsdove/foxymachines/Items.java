package me.gallowsdove.foxymachines;

import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.utils.itemstack.ColoredFireworkStar;
import org.bukkit.Color;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import me.gallowsdove.foxymachines.utils.CompatUtils;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.gallowsdove.foxymachines.utils.MaterialCompat;

// Item display name + lore are authored per-language in resources/languages/<lang>/items.yml
// (rendered per-viewer by the fork's LoreComposer). Items are constructed name-less here.
public final class Items{

    // Item groups
    public static final NestedItemGroup MAIN_ITEM_GROUP = new NestedItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "foxy_machines"),
            CustomItemStack.create(MaterialCompat.safe(XMaterial.SHEARS), "&4Foxy Machines")
    );

    public static final SubItemGroup MATERIALS_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "materials"),
            MAIN_ITEM_GROUP,
            CustomItemStack.create(MaterialCompat.safe(XMaterial.GOLD_INGOT), "&bMaterials")
    );

    public static final SubItemGroup MACHINES_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "machines"),
            MAIN_ITEM_GROUP,
            CustomItemStack.create(MaterialCompat.safe(XMaterial.BEACON), "&aMachines")
    );

    public static final SubItemGroup TOOLS_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "tools"),
            MAIN_ITEM_GROUP,
            CustomItemStack.create(MaterialCompat.safe(XMaterial.BLAZE_ROD), "&eTools")
    );

    public static final SubItemGroup WEAPONS_AND_ARMORS_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "weapons_and_armors"),
            MAIN_ITEM_GROUP,
            CustomItemStack.create(MaterialCompat.safe(XMaterial.NETHERITE_SWORD), "&aWeapons and Armors")
    );

    public static final SubItemGroup ALTAR_ITEM_GROUP = new SubItemGroup(
        new NamespacedKey(FoxyMachines.getInstance(), "sacrificial_altars"),
        MAIN_ITEM_GROUP,
        CustomItemStack.create(MaterialCompat.safe(XMaterial.POLISHED_BLACKSTONE_BRICKS), "&4Sacrificial Altar")
    );

    public static final SubItemGroup BOSSES_ITEM_GROUP = new SubItemGroup(
        new NamespacedKey(FoxyMachines.getInstance(), "bosses"),
        MAIN_ITEM_GROUP,
        CustomItemStack.create(MaterialCompat.safe(XMaterial.DRAGON_HEAD), "&cBosses")
    );

    public static final SubItemGroup GHOST_BLOCKS_ITEM_GROUP = new SubItemGroup(
            new NamespacedKey(FoxyMachines.getInstance(), "ghost_blocks"),
            MAIN_ITEM_GROUP,
            CustomItemStack.create(MaterialCompat.safe(XMaterial.GLASS), "&5Ghost Blocks")
    );

    // Items
    public static final SlimefunItemStack ELECTRIC_WIND_STAFF = new SlimefunItemStack(
            "ELECTRIC_WIND_STAFF",
            MaterialCompat.safe(XMaterial.BLAZE_ROD)
    );

    static {
        CompatUtils.addEnchantment(ELECTRIC_WIND_STAFF, "DURABILITY", 1);
        ItemMeta meta = ELECTRIC_WIND_STAFF.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ELECTRIC_WIND_STAFF.setItemMeta(meta);
    }

    public static final SlimefunItemStack ELECTRIC_FIRE_STAFF = new SlimefunItemStack(
            "ELECTRIC_FIRE_STAFF",
            MaterialCompat.safe(XMaterial.BLAZE_ROD)
    );
    static {
        CompatUtils.addEnchantment(ELECTRIC_FIRE_STAFF, "DURABILITY", 1);
        ItemMeta meta = ELECTRIC_FIRE_STAFF.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ELECTRIC_FIRE_STAFF.setItemMeta(meta);
    }

    public static final SlimefunItemStack ELECTRIC_FIRE_STAFF_II = new SlimefunItemStack(
            "ELECTRIC_FIRE_STAFF_II",
            MaterialCompat.safe(XMaterial.BLAZE_ROD)
    );
    static {
        CompatUtils.addEnchantment(ELECTRIC_FIRE_STAFF_II, "DURABILITY", 1);
        ItemMeta meta = ELECTRIC_FIRE_STAFF_II.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ELECTRIC_FIRE_STAFF_II.setItemMeta(meta);
    }

    public static final SlimefunItemStack HEALING_BOW = new SlimefunItemStack(
            "HEALING_BOW",
            MaterialCompat.safe(XMaterial.BOW)
    );

    public static final SlimefunItemStack REINFORCED_STRING = new SlimefunItemStack(
            "REINFORCED_STRING",
            MaterialCompat.safe(XMaterial.STRING)
    );

    public static final SlimefunItemStack IMPROVEMENT_FORGE = new SlimefunItemStack(
            "IMPROVEMENT_FORGE",
            MaterialCompat.safe(XMaterial.SMITHING_TABLE)
    );

    public static final SlimefunItemStack IMPROVEMENT_CORE = new SlimefunItemStack(
            "IMPROVEMENT_CORE",
            "faff2eb498e5c6a04484f0c9f785b448479ab213df95ec91176a308a12add70"
    );

    public static final SlimefunItemStack POTION_MIXER = new SlimefunItemStack(
            "POTION_MIXER",
            MaterialCompat.safe(XMaterial.BREWING_STAND)
    );

    public static final SlimefunItemStack ELECTRIC_GOLD_REFINERY = new SlimefunItemStack(
            "ELECTRIC_GOLD_REFINERY",
            MaterialCompat.safe(XMaterial.GOLD_BLOCK)
    );

    public static final SlimefunItemStack CHUNK_LOADER = new SlimefunItemStack(
            "CHUNK_LOADER",
            MaterialCompat.safe(XMaterial.BEACON)
    );

    public static final SlimefunItemStack STABILIZED_BLISTERING_BLOCK = new SlimefunItemStack(
            "STABILIZED_BLISTERING_BLOCK",
            MaterialCompat.safe(XMaterial.SNOW_BLOCK)
    );

    public static final SlimefunItemStack BOOSTED_RAIL = new SlimefunItemStack(
            "BOOSTED_RAIL",
            MaterialCompat.safe(XMaterial.RAIL)
    );

    public static final SlimefunItemStack BOOSTED_ACTIVATOR_RAIL = new SlimefunItemStack(
            "BOOSTED_ACTIVATOR_RAIL",
            MaterialCompat.safe(XMaterial.ACTIVATOR_RAIL)
    );

    public static final SlimefunItemStack BOOSTED_DETECTOR_RAIL = new SlimefunItemStack(
            "BOOSTED_DETECTOR_RAIL",
            MaterialCompat.safe(XMaterial.DETECTOR_RAIL)
    );

    public static final SlimefunItemStack BOOSTED_POWERED_RAIL = new SlimefunItemStack(
            "BOOSTED_POWERED_RAIL",
            MaterialCompat.safe(XMaterial.POWERED_RAIL)
    );

    public static final SlimefunItemStack BERRY_BUSH_TRIMMER = new SlimefunItemStack(
            "BERRY_BUSH_TRIMMER",
            MaterialCompat.safe(XMaterial.SHEARS)
    );

    public static final SlimefunItemStack FORCEFIELD_DOME = new SlimefunItemStack(
            "FORCEFIELD_DOME",
            MaterialCompat.safe(XMaterial.OBSERVER)
    );

    public static final SlimefunItemStack REMOTE_CONTROLLER = new SlimefunItemStack(
            "REMOTE_CONTROLLER",
            MaterialCompat.safe(XMaterial.NAME_TAG)
    );

    public static final SlimefunItemStack FORCEFIELD_ENGINE = new SlimefunItemStack(
            "FORCEFIELD_ENGINE",
            MaterialCompat.safe(XMaterial.STRUCTURE_BLOCK)
    );

    public static final SlimefunItemStack FORCEFIELD_STABILIZER = new SlimefunItemStack(
            "FORCEFIELD_STABILIZER",
            MaterialCompat.safe(XMaterial.STRUCTURE_VOID)
    );

    public static final SlimefunItemStack WIRELESS_TRANSMITTER = new SlimefunItemStack(
            "WIRELESS_TRANSMITTER",
            MaterialCompat.safe(XMaterial.REPEATER)
    );

    public static final SlimefunItemStack DEMONIC_INGOT = new SlimefunItemStack(
            "DEMONIC_INGOT",
            MaterialCompat.safe(XMaterial.GOLD_INGOT)
    );

    public static final SlimefunItemStack DEMONIC_PLATE = new SlimefunItemStack(
            "DEMONIC_PLATE",
            MaterialCompat.safe(XMaterial.LIGHT_WEIGHTED_PRESSURE_PLATE)
    );

    public static final SlimefunItemStack AQUATIC_NETHERITE_INGOT = new SlimefunItemStack(
            "AQUATIC_NETHERITE_INGOT",
            MaterialCompat.safe(XMaterial.NETHERITE_INGOT)
    );

    public static final SlimefunItemStack DAMIENIUM = new SlimefunItemStack(
            "DAMIENIUM",
            MaterialCompat.safe(XMaterial.GOLD_INGOT)
    );

    public static final SlimefunItemStack SWEET_INGOT = new SlimefunItemStack(
            "SWEET_INGOT",
            MaterialCompat.safe(XMaterial.GOLD_INGOT)
    );

    public static final SlimefunItemStack SWEETENED_SWEET_INGOT = new SlimefunItemStack(
            "SWEETENED_SWEET_INGOT",
            MaterialCompat.safe(XMaterial.GOLD_INGOT)
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS",
            MaterialCompat.safe(XMaterial.POLISHED_BLACKSTONE_BRICKS)
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_WALL = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_WALL",
            MaterialCompat.safe(XMaterial.POLISHED_BLACKSTONE_BRICK_WALL)
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_STAIRS = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_STAIRS",
            MaterialCompat.safe(XMaterial.POLISHED_BLACKSTONE_BRICK_STAIRS)
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_SOUL_TORCH = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_SOUL_TORCH",
            MaterialCompat.safe(XMaterial.SOUL_TORCH)
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE",
            MaterialCompat.safe(XMaterial.POLISHED_BLACKSTONE_PRESSURE_PLATE)
    );

    public static final SlimefunItemStack CURSED_RABBIT_PAW = new SlimefunItemStack(
            "CURSED_RABBIT_PAW",
            MaterialCompat.safe(XMaterial.RABBIT_FOOT)
    );

    public static final SlimefunItemStack HUMAN_SKULL = new SlimefunItemStack(
            "HUMAN_SKULL",
            MaterialCompat.safe(XMaterial.SKELETON_SKULL)
    );

    public static final SlimefunItemStack BLOOD_INFUSED_SKULL = new SlimefunItemStack(
            "BLOOD_INFUSED_SKULL",
            "daa4e2294df370b9a50cb924cdda78f740b0fbaf5a687106178505c80a79addc"
    );

    public static final SlimefunItemStack BLOOD = new SlimefunItemStack(
            "BLOOD",
            MaterialCompat.safe(XMaterial.REDSTONE)
    );

    public static final SlimefunItemStack UNHOLY_WITHER_SKELETON_BONE = new SlimefunItemStack(
            "UNHOLY_WITHER_SKELETON_BONE",
            MaterialCompat.safe(XMaterial.BONE)
    );

    public static final SlimefunItemStack PURIFIED_BONE = new SlimefunItemStack(
            "PURIFIED_BONE",
            MaterialCompat.safe(XMaterial.BONE)
    );

    public static final SlimefunItemStack PURE_BONE_DUST = new SlimefunItemStack(
            "PURE_BONE_DUST",
            MaterialCompat.safe(XMaterial.BONE_MEAL)
    );

    public static final SlimefunItemStack BUCKET_OF_BLOOD = new SlimefunItemStack(
            "BUCKET_OF_BLOOD",
            MaterialCompat.safe(XMaterial.LAVA_BUCKET)
    );

    public static final SlimefunItemStack POSEIDONS_FISHING_ROD = new SlimefunItemStack(
            "POSEIDONS_FISHING_ROD",
            MaterialCompat.safe(XMaterial.FISHING_ROD)
    );
    static {
        CompatUtils.addEnchantment(POSEIDONS_FISHING_ROD, "LUCK", 5);
        CompatUtils.addEnchantment(POSEIDONS_FISHING_ROD, "LURE", 3);
    }

    public static final SlimefunItemStack POSEIDONS_BLESSING = new SlimefunItemStack(
            "POSEIDONS_BLESSING",
            MaterialCompat.safe(XMaterial.HEART_OF_THE_SEA)
    );

    public static final SlimefunItemStack CURSED_SWORD = new SlimefunItemStack(
            "CURSED_SWORD",
            MaterialCompat.safe(XMaterial.NETHERITE_SWORD)
    );

    public static final SlimefunItemStack CELESTIAL_SWORD = new SlimefunItemStack(
            "CELESTIAL_SWORD",
            MaterialCompat.safe(XMaterial.NETHERITE_SWORD)
    );

    public static final SlimefunItemStack ELUCIDATOR = new SlimefunItemStack(
            "ELUCIDATOR",
            MaterialCompat.safe(XMaterial.NETHERITE_SWORD)
    );
    static {
        CompatUtils.addEnchantment(ELUCIDATOR, "DAMAGE_ALL", 10);
        CompatUtils.addEnchantment(ELUCIDATOR, "LOOT_BONUS_MOBS", 5);
    }

    public static final SlimefunItemStack MAGIC_LUMP_4 = new SlimefunItemStack(
            "MAGIC_LUMP_4",
            MaterialCompat.safe(XMaterial.GOLD_NUGGET)
    );

    public static final SlimefunItemStack MAGIC_LUMP_5 = new SlimefunItemStack(
            "MAGIC_LUMP_5",
            MaterialCompat.safe(XMaterial.GOLD_NUGGET)
    );

    public static final SlimefunItemStack AQUATIC_HELMET = new SlimefunItemStack(
            "AQUATIC_HELMET",
            MaterialCompat.safe(XMaterial.NETHERITE_HELMET)
    );
    static {
        CompatUtils.addEnchantment(AQUATIC_HELMET, "PROTECTION_ENVIRONMENTAL", 5);
        CompatUtils.addEnchantment(AQUATIC_HELMET, "WATER_WORKER", 1);
        CompatUtils.addEnchantment(AQUATIC_HELMET, "OXYGEN", 5);
        CompatUtils.addEnchantment(AQUATIC_HELMET, "DEPTH_STRIDER", 5);
    }


    public static final SlimefunItemStack RESISTANT_CHESTPLATE = new SlimefunItemStack(
            "RESISTANT_CHESTPLATE",
            MaterialCompat.safe(XMaterial.NETHERITE_CHESTPLATE)
    );
    static {
        CompatUtils.addEnchantment(RESISTANT_CHESTPLATE, "PROTECTION_ENVIRONMENTAL", 5);
    }

    public static final SlimefunItemStack FIERY_LEGGINGS = new SlimefunItemStack(
            "FIERY_LEGGINGS",
            MaterialCompat.safe(XMaterial.NETHERITE_LEGGINGS)
    );
    static {
        CompatUtils.addEnchantment(FIERY_LEGGINGS, "PROTECTION_ENVIRONMENTAL", 5);
        CompatUtils.addEnchantment(FIERY_LEGGINGS, "THORNS", 6);
    }

    public static final SlimefunItemStack LIGHT_BOOTS = new SlimefunItemStack(
            "LIGHT_BOOTS",
            MaterialCompat.safe(XMaterial.NETHERITE_BOOTS)
    );
    static {
        CompatUtils.addEnchantment(LIGHT_BOOTS, "PROTECTION_ENVIRONMENTAL", 5);
        CompatUtils.addEnchantment(LIGHT_BOOTS, "PROTECTION_FALL", 5);
        CompatUtils.addEnchantment(LIGHT_BOOTS, "SOUL_SPEED", 5);
    }

    public static final SlimefunItemStack AQUATIC_HELMET_FRAME = new SlimefunItemStack(
            "HELMET_FRAME",
            MaterialCompat.safe(XMaterial.CHAINMAIL_HELMET)
    );

    public static final SlimefunItemStack RESISTANT_CHESTPLATE_FRAME = new SlimefunItemStack(
            "RESISTANT_CHESTPLATE_FRAME",
            MaterialCompat.safe(XMaterial.CHAINMAIL_CHESTPLATE)
    );

    public static final SlimefunItemStack FIERY_LEGGINGS_FRAME = new SlimefunItemStack(
            "FIERY_LEGGINGS_FRAME",
            MaterialCompat.safe(XMaterial.CHAINMAIL_LEGGINGS)
    );

    public static final SlimefunItemStack LIGHT_BOOTS_FRAME = new SlimefunItemStack(
            "LIGHT_BOOTS_FRAME",
            MaterialCompat.safe(XMaterial.CHAINMAIL_BOOTS)
    );

    public static final SlimefunItemStack CURSED_SHARD = new SlimefunItemStack(
            "CURSED_SHARD",
            MaterialCompat.safe(XMaterial.NETHERITE_SCRAP)
    );

    public static final SlimefunItemStack CELESTIAL_SHARD = new SlimefunItemStack(
            "CELESTIAL_SHARD",
            MaterialCompat.safe(XMaterial.PRISMARINE_SHARD)
    );

    public static final SlimefunItemStack EQUANIMOUS_GEM = new SlimefunItemStack(
            "EQUANIMOUS_GEM",
            MaterialCompat.safe(XMaterial.EMERALD)
    );

    public static final SlimefunItemStack POLAR_FOX_HIDE = new SlimefunItemStack(
            "POLAR_FOX_HIDE",
            MaterialCompat.safe(XMaterial.SNOWBALL)
    );

    public static final SlimefunItemStack MAGMA_ESSENCE = new SlimefunItemStack(
            "MAGMA_ESSENCE",
            MaterialCompat.safe(XMaterial.MAGMA_CREAM)
    );

    public static final SlimefunItemStack TROPICAL_FISH_SCALE = new SlimefunItemStack(
            "TROPICAL_FISH_SCALE",
            MaterialCompat.safe(XMaterial.TROPICAL_FISH_SPAWN_EGG)
    );

    public static final SlimefunItemStack PARROT_FEATHER = new SlimefunItemStack(
            "PARROT_FEATHER",
            MaterialCompat.safe(XMaterial.FEATHER)
    );

    public static final SlimefunItemStack UNBREAKABLE_RUNE = new SlimefunItemStack(
            "UNBREAKABLE_RUNE",
            ColoredFireworkStar.create(Color.fromRGB(0, 188, 0)));

    public static final SlimefunItemStack PIXIE_QUEEN_SPAWN_EGG = new SlimefunItemStack(
            "PIXIE_QUEEN_SPAWN_EGG",
            MaterialCompat.safe(XMaterial.CREEPER_SPAWN_EGG)
    );

    public static final SlimefunItemStack HEADLESS_HORSEMAN_SPAWN_EGG = new SlimefunItemStack(
            "HEADLESS_HORSEMAN_SPAWN_EGG",
            MaterialCompat.safe(XMaterial.SPIDER_SPAWN_EGG)
    );

    public static final SlimefunItemStack PIXIE_QUEEN_HEART = new SlimefunItemStack(
            "PIXIE_QUEEN_HEART",
            MaterialCompat.safe(XMaterial.FERMENTED_SPIDER_EYE)
    );

    public static final SlimefunItemStack PIXIE_DUST = new SlimefunItemStack(
            "PIXIE_DUST",
            MaterialCompat.safe(XMaterial.SUGAR)
    );

    public static final SlimefunItemStack VILE_PUMPKIN = new SlimefunItemStack(
            "VILE_PUMPKIN",
            MaterialCompat.safe(XMaterial.CARVED_PUMPKIN)
    );

    public static final SlimefunItemStack VILE_SEEDS = new SlimefunItemStack(
            "VILE_SEEDS",
            MaterialCompat.safe(XMaterial.MELON_SEEDS)
    );

    public static final SlimefunItemStack ACRI_ARCUM = new SlimefunItemStack(
            "ACRI_ARCUM",
            MaterialCompat.safe(XMaterial.BOW)
    );
    static {
        CompatUtils.addEnchantment(ACRI_ARCUM, "ARROW_DAMAGE", 7);
        CompatUtils.addEnchantment(ACRI_ARCUM, "ARROW_INFINITE", 1);
    }

    public static final SlimefunItemStack GHOST_BLOCK_REMOVER = new SlimefunItemStack(
            "GHOST_BLOCK_REMOVER",
            MaterialCompat.safe(XMaterial.CLOCK)
    );

    public static final SlimefunItemStack POSITION_SELECTOR = new SlimefunItemStack(
            "POSITION_SELECTOR",
            MaterialCompat.safe(XMaterial.STICK)
    );

    public static final SlimefunItemStack FILL_WAND = new SlimefunItemStack(
            "FILL_WAND",
            MaterialCompat.safe(XMaterial.BLAZE_ROD)
    );

    public static final SlimefunItemStack SPONGE_WAND = new SlimefunItemStack(
            "SPONGE_WAND",
            MaterialCompat.safe(XMaterial.BLAZE_ROD)
    );

    public static final SlimefunItemStack NUCLEAR_SALT = new SlimefunItemStack(
            "NUCLEAR_SALT",
            MaterialCompat.safe(XMaterial.LIME_DYE)
    );

    public static final SlimefunItemStack COMPRESSED_SPONGE = new SlimefunItemStack(
            "COMPRESSED_SPONGE",
            MaterialCompat.safe(XMaterial.WET_SPONGE)
    );
}
