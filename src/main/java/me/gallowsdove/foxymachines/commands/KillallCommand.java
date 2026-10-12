package me.gallowsdove.foxymachines.commands;

import io.github.mooy1.infinitylib.commands.SubCommand;
import me.gallowsdove.foxymachines.abstracts.CustomBoss;
import me.gallowsdove.foxymachines.abstracts.CustomMob;
import me.gallowsdove.foxymachines.utils.CompatUtils;
import org.bukkit.Bukkit;
import me.gallowsdove.foxymachines.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class KillallCommand extends SubCommand {
    public KillallCommand() {
        super("killall", "Kills all Custom Mobs from FoxyMachines in your current World.", "foxymachines.admin");
    }

    @Override
    protected void execute(@Nonnull CommandSender sender, @Nonnull String[] args) {
        if (!(sender instanceof Player)) {
            return;
        }
        Player player = (Player) sender;

        if (args.length != 0) {
            Messages.send(sender, ChatColor.LIGHT_PURPLE, "commands.usage", "Usage: /%usage%",
                    line -> line.replace("%usage%", "foxy killall"));
            return;
        }

        // Touching CustomMob/CustomBoss loads a 1.9+ boss-bar/spellcaster listener; skip on legacy servers.
        if (!CompatUtils.customMobsSupported()) {
            Messages.send(player, ChatColor.LIGHT_PURPLE, "commands.custom-mobs-unsupported", "Custom mobs are not supported on this Minecraft version.");
            return;
        }

        int count = 0;
        for (Set<UUID> uuids : CustomMob.MOB_CACHE.values()) {
            for (UUID uuid : uuids) {
                Entity entity = Bukkit.getEntity(uuid);
                if (entity instanceof LivingEntity && entity.getWorld().equals(player.getWorld())) {
                    entity.remove();
                    count++;
                }
            }
        }

        CustomBoss.removeBossBars();

        int killed = count;
        Messages.send(player, "commands.killed-entities", "Killed %count% Entities",
                line -> line.replace("%count%", String.valueOf(killed)));
    }

    @Override
    protected void complete(@Nonnull CommandSender commandSender, @Nonnull String[] strings, @Nonnull List<String> list) { }
}