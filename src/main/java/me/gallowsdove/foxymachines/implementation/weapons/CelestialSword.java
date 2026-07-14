package me.gallowsdove.foxymachines.implementation.weapons;

import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import me.gallowsdove.foxymachines.Items;
import me.gallowsdove.foxymachines.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.gallowsdove.foxymachines.utils.CompatUtils;
import me.gallowsdove.foxymachines.utils.MaterialCompat;

public class CelestialSword extends OnHitWeapon {
    public CelestialSword() {
        super(Items.WEAPONS_AND_ARMORS_ITEM_GROUP, Items.CELESTIAL_SWORD, RecipeType.ANCIENT_ALTAR, new ItemStack[] {
                Items.MAGIC_LUMP_5.item(), Items.POSEIDONS_BLESSING.item(), Items.MAGIC_LUMP_5.item(),
                Items.PURE_BONE_DUST.item(), new ItemStack(MaterialCompat.safe(XMaterial.NETHERITE_SWORD)), Items.PURE_BONE_DUST.item(),
                Items.MAGIC_LUMP_5.item(), Items.POSEIDONS_BLESSING.item(), Items.MAGIC_LUMP_5.item()
        });
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onHit(EntityDamageByEntityEvent event, HumanEntity humanoid, LivingEntity entity) {
        // Armor Penetration V
        Utils.dealDamageBypassingArmor(entity, (event.getDamage() - event.getFinalDamage()) * 0.16);

        // Divine Smite II
        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (random.nextInt(100) < 15) {
            entity.getWorld().strikeLightningEffect(entity.getLocation());
            entity.damage(8);
        }

        CompatUtils.applyPotionEffect(entity, "GLOWING", 80, 0, false, false);
    }
}
