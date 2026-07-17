package me.gallowsdove.foxymachines.listeners;

import io.github.thebusybiscuit.slimefun5.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.HandCompat;
import me.gallowsdove.foxymachines.Items;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.gallowsdove.foxymachines.utils.MaterialCompat;

public class RemoteControllerListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    private void onNametagEvent(PlayerInteractEntityEvent e)  {

        ItemStack item;
        if (e.getHand() == EquipmentSlot.HAND) {
            item = HandCompat.getMainHand(e.getPlayer().getInventory());
        } else {
            item = HandCompat.getOffHand(e.getPlayer().getInventory());
        }

        if (item.getType() == MaterialCompat.safe(XMaterial.NAME_TAG) && SlimefunUtils.isItemSimilar(HandCompat.getMainHand(e.getPlayer().getInventory()), Items.REMOTE_CONTROLLER.item(), false)) {
            e.setCancelled(true);
        }
    }
}
