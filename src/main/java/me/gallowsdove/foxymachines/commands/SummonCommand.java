package me.gallowsdove.foxymachines.commands;

import io.github.mooy1.infinitylib.commands.SubCommand;
import me.gallowsdove.foxymachines.abstracts.CustomMob;
import me.gallowsdove.foxymachines.utils.CompatUtils;
import me.gallowsdove.foxymachines.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;

public final class SummonCommand extends SubCommand {

    public SummonCommand() {
        super("summon", "Summons a custom mob", "foxymachines.admin");
    }

    @Override
    protected void execute(@Nonnull CommandSender sender, @Nonnull String[] args) {
        if (!(sender instanceof Player)) {
            return;
        }
        Player player = (Player) sender;

        if (args.length != 1) {
            Messages.send(sender, ChatColor.LIGHT_PURPLE, "commands.usage", "Usage: /%usage%",
                    line -> line.replace("%usage%", "foxy summon <MOB_ID>"));
            return;
        }

        if (!CompatUtils.customMobsSupported()) {
            Messages.send(sender, ChatColor.LIGHT_PURPLE, "commands.custom-mobs-unsupported", "Custom mobs are not supported on this Minecraft version.");
            return;
        }

        CustomMob mob = CustomMob.getById(args[0]);

        if (mob != null) {
            mob.spawn(player.getLocation());
        }
    }


    @Override
    protected void complete(@Nonnull CommandSender sender, @Nonnull String[] args, @Nonnull List<String> tabs) {
        if (CompatUtils.customMobsSupported()) {
            tabs.addAll(CustomMob.MOBS.keySet());
        }
    }
}