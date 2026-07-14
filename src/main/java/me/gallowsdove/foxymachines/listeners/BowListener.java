package me.gallowsdove.foxymachines.listeners;

import io.github.thebusybiscuit.slimefun5.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun5.utils.SlimefunUtils;
import me.gallowsdove.foxymachines.Items;
import me.gallowsdove.foxymachines.utils.Utils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public class BowListener implements Listener {

    private static final String KEY = "foxymachines:arci_arcum";

    @EventHandler(ignoreCancelled = true)
    private void onShoot(@Nonnull EntityShootBowEvent e) {
        if (e.getEntity() instanceof HumanEntity && e.getProjectile() instanceof Arrow &&
                SlimefunUtils.isItemSimilar(e.getBow(), Items.ACRI_ARCUM.item(), false, false)) {
            Arrow arrow = (Arrow) e.getProjectile();
            arrow.setVelocity(arrow.getVelocity().multiply(2));
            arrow.setGlowing(true);
            PersistentDataAPI.setBoolean(arrow, KEY, true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onHit(@Nonnull EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow && e.getEntity() instanceof LivingEntity &&
                PersistentDataAPI.getBoolean(e.getDamager(), KEY)) {
            LivingEntity entity = (LivingEntity) e.getEntity();
            e.setDamage(e.getDamage() * 0.9);
            Utils.dealDamageBypassingArmor(entity, (e.getDamage() - e.getFinalDamage()) * 0.045);
            entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 21, 10, false, false));
        }
    }
}
