package me.gallowsdove.foxymachines.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.lang.reflect.Method;

import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun5.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XEnchantment;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XPotion;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XSound;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.particles.XParticle;

/**
 * Version-safety helpers so FoxyMachines loads + enables on legacy servers (1.8&ndash;1.13) without a
 * {@code NoSuchFieldError}/{@code NoClassDefFoundError} from post-1.8 constants and APIs.
 *
 * <p>Enchantments/potions/sounds are resolved <em>by name</em> through the fork's relocated XSeries
 * wrappers, so a modern-only value that maps to nothing on the running server simply yields
 * {@code null}/skips instead of referencing a constant that doesn't exist. Particle spawning and the
 * custom-mob subsystem are gated behind runtime version checks and isolated in dedicated methods so the
 * JVM never resolves the 1.9+ {@code org.bukkit.Particle}/boss-bar classes on older servers.
 */
public final class CompatUtils {

    private CompatUtils() {}

    /** Vex (custom bosses) require 1.11; boss bars require 1.9. The whole custom-mob feature is 1.11+. */
    public static boolean customMobsSupported() {
        return Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_11);
    }

    /** {@code World#spawnParticle} + {@code org.bukkit.Particle} are 1.9+. */
    public static boolean particlesSupported() {
        return Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_9);
    }

    /** {@code Material#createBlockData} / {@code Block#getBlockData} / {@code BlockData} are 1.13+. */
    public static boolean blockDataSupported() {
        return Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_13);
    }

    /** Ghost Blocks need {@code createBlockData} (1.13) + {@code Entity#setPersistent} (1.14), so gate at 1.14. */
    public static boolean ghostBlocksSupported() {
        return Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_14);
    }

    // --- Item durability (org.bukkit.inventory.meta.Damageable is 1.13+; routed reflectively) ----------

    public static int getItemDamage(@Nonnull ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            try {
                Method getDamage = meta.getClass().getMethod("getDamage");
                Object result = getDamage.invoke(meta);
                if (result instanceof Integer) {
                    return (Integer) result;
                }
            } catch (ReflectiveOperationException ignored) {
                // fall through to legacy durability
            }
        }
        return item.getDurability();
    }

    public static void setItemDamage(@Nonnull ItemStack item, int damage) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            try {
                Method setDamage = meta.getClass().getMethod("setDamage", int.class);
                setDamage.invoke(meta, damage);
                item.setItemMeta(meta);
                return;
            } catch (ReflectiveOperationException ignored) {
                // fall through to legacy durability
            }
        }
        item.setDurability((short) damage);
    }

    // --- Enchantments (resolved by name; a modern-only enchant is skipped on legacy servers) ---------

    public static void addEnchantment(@Nonnull SlimefunItemStack item, @Nonnull String name, int level) {
        Optional<XEnchantment> match = XEnchantment.matchXEnchantment(name);
        if (match.isPresent() && match.get().isSupported()) {
            Enchantment enchantment = match.get().getEnchant();
            if (enchantment != null) {
                item.addUnsafeEnchantment(enchantment, level);
            }
        }
    }

    // --- Potion effects (name-resolved type + version-safe constructor: 6-arg icon is 1.13+, ---------
    //     5-arg particles is 1.8+, else the 4-arg ctor) ------------------------------------------------

    @Nullable
    public static PotionEffect potionEffect(@Nonnull String typeName, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {
        Optional<XPotion> match = XPotion.matchXPotion(typeName);
        if (!match.isPresent() || !match.get().isSupported()) {
            return null;
        }
        PotionEffectType type = match.get().getPotionEffectType();
        if (type == null) {
            return null;
        }

        try {
            return PotionEffect.class.getConstructor(PotionEffectType.class, int.class, int.class, boolean.class, boolean.class, boolean.class)
                    .newInstance(type, duration, amplifier, ambient, particles, icon);
        } catch (ReflectiveOperationException e6) {
            try {
                return PotionEffect.class.getConstructor(PotionEffectType.class, int.class, int.class, boolean.class, boolean.class)
                        .newInstance(type, duration, amplifier, ambient, particles);
            } catch (ReflectiveOperationException e5) {
                return new PotionEffect(type, duration, amplifier, ambient);
            }
        }
    }

    /** Builds an array of the non-null effects (a type absent on this version yields a null). */
    @Nonnull
    public static PotionEffect[] potionEffects(PotionEffect... effects) {
        List<PotionEffect> list = new ArrayList<>();
        for (PotionEffect effect : effects) {
            if (effect != null) {
                list.add(effect);
            }
        }
        return list.toArray(new PotionEffect[0]);
    }

    /** Applies a single named effect to an entity, no-op if the type doesn't exist on this version. */
    public static void applyPotionEffect(@Nonnull LivingEntity entity, @Nonnull String typeName, int duration, int amplifier, boolean ambient, boolean particles) {
        PotionEffect effect = potionEffect(typeName, duration, amplifier, ambient, particles, false);
        if (effect != null) {
            entity.addPotionEffect(effect);
        }
    }

    // --- Sounds (XSound resolves + plays version-safely; a legacy/renamed value maps correctly) -------

    public static void playSound(@Nonnull Location location, @Nonnull String name, float volume, float pitch) {
        XSound.matchXSound(name).ifPresent(sound -> {
            if (sound.isSupported()) {
                sound.play(location, volume, pitch);
            }
        });
    }

    // --- Particles (1.9+; isolated so org.bukkit.Particle is never resolved on 1.8) -------------------

    public static void spawnParticle(@Nonnull Location location, @Nonnull String name, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        if (particlesSupported()) {
            doSpawnParticle(location, name, count, offsetX, offsetY, offsetZ, extra);
        }
    }

    public static void spawnParticle(@Nonnull Location location, @Nonnull String name, int count) {
        if (particlesSupported()) {
            doSpawnParticle(location, name, count, 0, 0, 0, 0);
        }
    }

    /**
     * References {@code org.bukkit.Particle} (1.9+) via {@link XParticle#getParticle(String)} and
     * {@code World#spawnParticle}. MUST only be invoked behind {@link #particlesSupported()} so the JVM
     * never resolves {@code org.bukkit.Particle} on 1.8.
     */
    private static void doSpawnParticle(Location location, String name, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        if (location.getWorld() == null) {
            return;
        }
        try {
            org.bukkit.Particle particle = XParticle.getParticle(name);
            if (particle != null) {
                location.getWorld().spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, extra);
            }
        } catch (Throwable ignored) {
            // Particle absent on this version - skip silently rather than crash.
        }
    }
}
